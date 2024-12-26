package ma.zyn.app.ws.facade.doctor.sensor;

import io.swagger.v3.oas.annotations.Operation;
import ma.zyn.app.bean.core.sensor.Capteur;
import ma.zyn.app.dao.criteria.core.sensor.CapteurCriteria;
import ma.zyn.app.service.facade.admin.sensor.CapteurAdminService;
import ma.zyn.app.ws.converter.sensor.CapteurConverter;
import ma.zyn.app.ws.dto.sensor.CapteurDto;
import ma.zyn.app.config.util.PaginatedList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor/capteur/")
public class CapteurRestDoctor {




    @Operation(summary = "Finds a list of all capteurs")
    @GetMapping("")
    public ResponseEntity<List<CapteurDto>> findAll() throws Exception {
        ResponseEntity<List<CapteurDto>> res = null;
        List<Capteur> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<CapteurDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all capteurs")
    @GetMapping("optimized")
    public ResponseEntity<List<CapteurDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<CapteurDto>> res = null;
        List<Capteur> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<CapteurDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a capteur by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CapteurDto> findById(@PathVariable Long id) {
        Capteur t = service.findById(id);
        if (t != null) {
            converter.init(true);
            CapteurDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a capteur by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<CapteurDto> findByLibelle(@PathVariable String libelle) {
	    Capteur t = service.findByReferenceEntity(new Capteur(libelle));
        if (t != null) {
            converter.init(true);
            CapteurDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  capteur")
    @PostMapping("")
    public ResponseEntity<CapteurDto> save(@RequestBody CapteurDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Capteur myT = converter.toItem(dto);
            Capteur t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                CapteurDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  capteur")
    @PutMapping("")
    public ResponseEntity<CapteurDto> update(@RequestBody CapteurDto dto) throws Exception {
        ResponseEntity<CapteurDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Capteur t = service.findById(dto.getId());
            converter.copy(dto,t);
            Capteur updated = service.update(t);
            CapteurDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of capteur")
    @PostMapping("multiple")
    public ResponseEntity<List<CapteurDto>> delete(@RequestBody List<CapteurDto> dtos) throws Exception {
        ResponseEntity<List<CapteurDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Capteur> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified capteur")
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

    @Operation(summary = "find by capteurType id")
    @GetMapping("capteurType/id/{id}")
    public List<CapteurDto> findByCapteurTypeId(@PathVariable Long id){
        return findDtos(service.findByCapteurTypeId(id));
    }
    @Operation(summary = "delete by capteurType id")
    @DeleteMapping("capteurType/id/{id}")
    public int deleteByCapteurTypeId(@PathVariable Long id){
        return service.deleteByCapteurTypeId(id);
    }

    @Operation(summary = "Finds a capteur and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<CapteurDto> findWithAssociatedLists(@PathVariable Long id) {
        Capteur loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        CapteurDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds capteurs by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<CapteurDto>> findByCriteria(@RequestBody CapteurCriteria criteria) throws Exception {
        ResponseEntity<List<CapteurDto>> res = null;
        List<Capteur> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<CapteurDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated capteurs by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody CapteurCriteria criteria) throws Exception {
        List<Capteur> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<CapteurDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets capteur data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody CapteurCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<CapteurDto> findDtos(List<Capteur> list){
        converter.initObject(true);
        List<CapteurDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<CapteurDto> getDtoResponseEntity(CapteurDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public CapteurRestDoctor(CapteurAdminService service, CapteurConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final CapteurAdminService service;
    private final CapteurConverter converter;





}
