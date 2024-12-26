package  ma.zyn.app.ws.facade.admin.sensor;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.HttpStatus;

import ma.zyn.app.bean.core.sensor.CapteurType;
import ma.zyn.app.dao.criteria.core.sensor.CapteurTypeCriteria;
import ma.zyn.app.service.facade.admin.sensor.CapteurTypeAdminService;
import ma.zyn.app.ws.converter.sensor.CapteurTypeConverter;
import ma.zyn.app.ws.dto.sensor.CapteurTypeDto;
import ma.zyn.app.config.util.PaginatedList;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/admin/capteurType/")
public class CapteurTypeRestAdmin {




    @Operation(summary = "Finds a list of all capteurTypes")
    @GetMapping("")
    public ResponseEntity<List<CapteurTypeDto>> findAll() throws Exception {
        ResponseEntity<List<CapteurTypeDto>> res = null;
        List<CapteurType> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CapteurTypeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all capteurTypes")
    @GetMapping("optimized")
    public ResponseEntity<List<CapteurTypeDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<CapteurTypeDto>> res = null;
        List<CapteurType> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CapteurTypeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a capteurType by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CapteurTypeDto> findById(@PathVariable Long id) {
        CapteurType t = service.findById(id);
        if (t != null) {
            CapteurTypeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a capteurType by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<CapteurTypeDto> findByLibelle(@PathVariable String libelle) {
	    CapteurType t = service.findByReferenceEntity(new CapteurType(libelle));
        if (t != null) {
            CapteurTypeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  capteurType")
    @PostMapping("")
    public ResponseEntity<CapteurTypeDto> save(@RequestBody CapteurTypeDto dto) throws Exception {
        if(dto!=null){
            CapteurType myT = converter.toItem(dto);
            CapteurType t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                CapteurTypeDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  capteurType")
    @PutMapping("")
    public ResponseEntity<CapteurTypeDto> update(@RequestBody CapteurTypeDto dto) throws Exception {
        ResponseEntity<CapteurTypeDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            CapteurType t = service.findById(dto.getId());
            converter.copy(dto,t);
            CapteurType updated = service.update(t);
            CapteurTypeDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of capteurType")
    @PostMapping("multiple")
    public ResponseEntity<List<CapteurTypeDto>> delete(@RequestBody List<CapteurTypeDto> dtos) throws Exception {
        ResponseEntity<List<CapteurTypeDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<CapteurType> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified capteurType")
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


    @Operation(summary = "Finds a capteurType and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<CapteurTypeDto> findWithAssociatedLists(@PathVariable Long id) {
        CapteurType loaded =  service.findWithAssociatedLists(id);
        CapteurTypeDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds capteurTypes by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<CapteurTypeDto>> findByCriteria(@RequestBody CapteurTypeCriteria criteria) throws Exception {
        ResponseEntity<List<CapteurTypeDto>> res = null;
        List<CapteurType> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CapteurTypeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated capteurTypes by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody CapteurTypeCriteria criteria) throws Exception {
        List<CapteurType> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<CapteurTypeDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets capteurType data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody CapteurTypeCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<CapteurTypeDto> findDtos(List<CapteurType> list){
        List<CapteurTypeDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<CapteurTypeDto> getDtoResponseEntity(CapteurTypeDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public CapteurTypeRestAdmin(CapteurTypeAdminService service, CapteurTypeConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final CapteurTypeAdminService service;
    private final CapteurTypeConverter converter;





}
