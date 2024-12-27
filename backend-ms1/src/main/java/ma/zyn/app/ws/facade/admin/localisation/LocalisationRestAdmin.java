package  ma.zyn.app.ws.facade.admin.localisation;

import io.swagger.v3.oas.annotations.Operation;

import ma.zyn.app.bean.core.localisation.SafeZone;
import ma.zyn.app.bean.core.warning.WarningPatient;
import ma.zyn.app.bean.core.warning.WarningType;
import ma.zyn.app.service.facade.admin.localisation.SafeZoneAdminService;
import ma.zyn.app.service.facade.admin.warning.WarningPatientAdminService;
import ma.zyn.app.service.facade.admin.warning.WarningTypeAdminService;
import ma.zyn.app.ws.converter.patient.PatientConverter;
import org.apache.poi.hpsf.Decimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;

import ma.zyn.app.bean.core.localisation.Localisation;
import ma.zyn.app.dao.criteria.core.localisation.LocalisationCriteria;
import ma.zyn.app.service.facade.admin.localisation.LocalisationAdminService;
import ma.zyn.app.ws.converter.localisation.LocalisationConverter;
import ma.zyn.app.ws.dto.localisation.LocalisationDto;
import ma.zyn.app.config.util.PaginatedList;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/localisation/")
public class LocalisationRestAdmin {


    @Value("${flask.server.url:http://127.0.0.1:5000/check-location}")
    private String analyseurApi;

    private final RestTemplate restTemplate;


    @Operation(summary = "Finds a list of all localisations")
    @GetMapping("")
    public ResponseEntity<List<LocalisationDto>> findAll() throws Exception {
        ResponseEntity<List<LocalisationDto>> res = null;
        List<Localisation> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<LocalisationDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a localisation by id")
    @GetMapping("id/{id}")
    public ResponseEntity<LocalisationDto> findById(@PathVariable Long id) {
        Localisation t = service.findById(id);
        if (t != null) {
            converter.init(true);
            LocalisationDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  localisation")
    @PostMapping("")
    public ResponseEntity<LocalisationDto> save(@RequestBody LocalisationDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Localisation myT = converter.toItem(dto);
            Localisation t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                LocalisationDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  localisation")
    @PutMapping("")
    public ResponseEntity<LocalisationDto> update(@RequestBody LocalisationDto dto) throws Exception {
        ResponseEntity<LocalisationDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Localisation t = service.findById(dto.getId());
            converter.copy(dto,t);
            Localisation updated = service.update(t);
            LocalisationDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
            SafeZone safeZone = safeZoneAdminService.findByLinkedPatientId(dto.getPatient().getId()).get(0);
            ResponseEntity<Boolean> respone = checkLocation(dto.getLatitude(), dto.getLongitude(), safeZone.getCentreLatitude(), safeZone.getCentreLongitude(), safeZone.getRayon());
            System.out.println("Localisation updated: "+respone.getBody());
            if (respone.getBody().booleanValue()) {
                if (Boolean.TRUE.equals(dto.getInZone())){
                    myDto.setInZone(false);
                    WarningPatient warningPatient = warningPatientAdminService.findById(22L);
                    warningPatient.setDateWarning(LocalDateTime.now());
                    warningPatientAdminService.update(warningPatient);
                }
            }

        }
        return res;
    }

    @PostMapping("/check-location")
    public ResponseEntity<Boolean> checkLocation(
            @RequestParam BigDecimal localisationLat,
            @RequestParam BigDecimal localisationLong,
            @RequestParam BigDecimal centerLat,
            @RequestParam BigDecimal centerLong,
            @RequestParam BigDecimal radius) {
        try {
            // Build the request body to send to the Flask service
            Map<String, Object> flaskRequestBody = Map.of(
                    "location", List.of(localisationLat, localisationLong),  // Location coordinates
                    "safeZone", Map.of(
                            "center", List.of(centerLat, centerLong),  // Safe zone center
                            "radius", radius  // Safe zone radius
                    )
            );


            // Set headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Create request entity
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(flaskRequestBody, headers);

            // Send POST request to Flask service
            ResponseEntity<Map> response = restTemplate.postForEntity(analyseurApi, entity, Map.class);

            // Extract warning status from Flask response
            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null && responseBody.containsKey("warning")) {
                boolean warning = (boolean) responseBody.get("warning");
                return ResponseEntity.ok(warning); // Return the boolean value (true or false)
            }

            // Handle unexpected response structure
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false); // Default to false in case of error

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false); // Default to false if an exception occurs
        }
    }





    @Operation(summary = "Delete list of localisation")
    @PostMapping("multiple")
    public ResponseEntity<List<LocalisationDto>> delete(@RequestBody List<LocalisationDto> dtos) throws Exception {
        ResponseEntity<List<LocalisationDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Localisation> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified localisation")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }


    @Operation(summary = "Finds a localisation and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<LocalisationDto> findWithAssociatedLists(@PathVariable Long id) {
        Localisation loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        LocalisationDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds localisations by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<LocalisationDto>> findByCriteria(@RequestBody LocalisationCriteria criteria) throws Exception {
        ResponseEntity<List<LocalisationDto>> res = null;
        List<Localisation> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<LocalisationDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated localisations by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody LocalisationCriteria criteria) throws Exception {
        List<Localisation> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<LocalisationDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets localisation data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody LocalisationCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<LocalisationDto> findDtos(List<Localisation> list){
        converter.initObject(true);
        List<LocalisationDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<LocalisationDto> getDtoResponseEntity(LocalisationDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public LocalisationRestAdmin(RestTemplate restTemplate, LocalisationAdminService service, LocalisationConverter converter, SafeZoneAdminService safeZoneAdminService, WarningPatientAdminService warningPatientAdminService, WarningTypeAdminService warningTypeAdminService){
       this.restTemplate = restTemplate;
       this.service = service;
        this.converter = converter;
       this.safeZoneAdminService = safeZoneAdminService;
       this.warningPatientAdminService = warningPatientAdminService;
       this.warningTypeAdminService = warningTypeAdminService;
   }

    private final LocalisationAdminService service;
    private final LocalisationConverter converter;
    private final SafeZoneAdminService safeZoneAdminService;
    private final WarningPatientAdminService warningPatientAdminService;
    private final WarningTypeAdminService warningTypeAdminService;





}
