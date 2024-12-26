package  ma.zyn.app.ws.facade.infirmier.localisation;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.HttpStatus;

import ma.zyn.app.bean.core.localisation.SafeZone;
import ma.zyn.app.dao.criteria.core.localisation.SafeZoneCriteria;
import ma.zyn.app.service.facade.infirmier.localisation.SafeZoneInfirmierService;
import ma.zyn.app.ws.converter.localisation.SafeZoneConverter;
import ma.zyn.app.ws.dto.localisation.SafeZoneDto;
import ma.zyn.app.config.util.PaginatedList;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/infirmier/safeZone/")
public class SafeZoneRestInfirmier {




    @Operation(summary = "Finds a list of all safeZones")
    @GetMapping("")
    public ResponseEntity<List<SafeZoneDto>> findAll() throws Exception {
        ResponseEntity<List<SafeZoneDto>> res = null;
        List<SafeZone> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<SafeZoneDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a safeZone by id")
    @GetMapping("id/{id}")
    public ResponseEntity<SafeZoneDto> findById(@PathVariable Long id) {
        SafeZone t = service.findById(id);
        if (t != null) {
            converter.init(true);
            SafeZoneDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  safeZone")
    @PostMapping("")
    public ResponseEntity<SafeZoneDto> save(@RequestBody SafeZoneDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            SafeZone myT = converter.toItem(dto);
            SafeZone t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                SafeZoneDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  safeZone")
    @PutMapping("")
    public ResponseEntity<SafeZoneDto> update(@RequestBody SafeZoneDto dto) throws Exception {
        ResponseEntity<SafeZoneDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            SafeZone t = service.findById(dto.getId());
            converter.copy(dto,t);
            SafeZone updated = service.update(t);
            SafeZoneDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of safeZone")
    @PostMapping("multiple")
    public ResponseEntity<List<SafeZoneDto>> delete(@RequestBody List<SafeZoneDto> dtos) throws Exception {
        ResponseEntity<List<SafeZoneDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<SafeZone> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified safeZone")
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

    @Operation(summary = "find by linkedPatient id")
    @GetMapping("linkedPatient/id/{id}")
    public List<SafeZoneDto> findByLinkedPatientId(@PathVariable Long id){
        return findDtos(service.findByLinkedPatientId(id));
    }
    @Operation(summary = "delete by linkedPatient id")
    @DeleteMapping("linkedPatient/id/{id}")
    public int deleteByLinkedPatientId(@PathVariable Long id){
        return service.deleteByLinkedPatientId(id);
    }

    @Operation(summary = "Finds a safeZone and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<SafeZoneDto> findWithAssociatedLists(@PathVariable Long id) {
        SafeZone loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        SafeZoneDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds safeZones by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<SafeZoneDto>> findByCriteria(@RequestBody SafeZoneCriteria criteria) throws Exception {
        ResponseEntity<List<SafeZoneDto>> res = null;
        List<SafeZone> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<SafeZoneDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated safeZones by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody SafeZoneCriteria criteria) throws Exception {
        List<SafeZone> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<SafeZoneDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets safeZone data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody SafeZoneCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<SafeZoneDto> findDtos(List<SafeZone> list){
        converter.initObject(true);
        List<SafeZoneDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<SafeZoneDto> getDtoResponseEntity(SafeZoneDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public SafeZoneRestInfirmier(SafeZoneInfirmierService service, SafeZoneConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final SafeZoneInfirmierService service;
    private final SafeZoneConverter converter;





}
