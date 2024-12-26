package  ma.zyn.app.ws.facade.admin.warning;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.HttpStatus;

import ma.zyn.app.bean.core.warning.WarningPatient;
import ma.zyn.app.dao.criteria.core.warning.WarningPatientCriteria;
import ma.zyn.app.service.facade.admin.warning.WarningPatientAdminService;
import ma.zyn.app.ws.converter.warning.WarningPatientConverter;
import ma.zyn.app.ws.dto.warning.WarningPatientDto;
import ma.zyn.app.config.util.PaginatedList;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/admin/warningPatient/")
public class WarningPatientRestAdmin {




    @Operation(summary = "Finds a list of all warningPatients")
    @GetMapping("")
    public ResponseEntity<List<WarningPatientDto>> findAll() throws Exception {
        ResponseEntity<List<WarningPatientDto>> res = null;
        List<WarningPatient> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<WarningPatientDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a warningPatient by id")
    @GetMapping("id/{id}")
    public ResponseEntity<WarningPatientDto> findById(@PathVariable Long id) {
        WarningPatient t = service.findById(id);
        if (t != null) {
            converter.init(true);
            WarningPatientDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  warningPatient")
    @PostMapping("")
    public ResponseEntity<WarningPatientDto> save(@RequestBody WarningPatientDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            WarningPatient myT = converter.toItem(dto);
            WarningPatient t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                WarningPatientDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  warningPatient")
    @PutMapping("")
    public ResponseEntity<WarningPatientDto> update(@RequestBody WarningPatientDto dto) throws Exception {
        ResponseEntity<WarningPatientDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            WarningPatient t = service.findById(dto.getId());
            converter.copy(dto,t);
            WarningPatient updated = service.update(t);
            WarningPatientDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of warningPatient")
    @PostMapping("multiple")
    public ResponseEntity<List<WarningPatientDto>> delete(@RequestBody List<WarningPatientDto> dtos) throws Exception {
        ResponseEntity<List<WarningPatientDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<WarningPatient> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified warningPatient")
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

    @Operation(summary = "find by warningType id")
    @GetMapping("warningType/id/{id}")
    public List<WarningPatientDto> findByWarningTypeId(@PathVariable Long id){
        return findDtos(service.findByWarningTypeId(id));
    }
    @Operation(summary = "delete by warningType id")
    @DeleteMapping("warningType/id/{id}")
    public int deleteByWarningTypeId(@PathVariable Long id){
        return service.deleteByWarningTypeId(id);
    }

    @Operation(summary = "Finds a warningPatient and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<WarningPatientDto> findWithAssociatedLists(@PathVariable Long id) {
        WarningPatient loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        WarningPatientDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds warningPatients by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<WarningPatientDto>> findByCriteria(@RequestBody WarningPatientCriteria criteria) throws Exception {
        ResponseEntity<List<WarningPatientDto>> res = null;
        List<WarningPatient> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<WarningPatientDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated warningPatients by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody WarningPatientCriteria criteria) throws Exception {
        List<WarningPatient> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<WarningPatientDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets warningPatient data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody WarningPatientCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<WarningPatientDto> findDtos(List<WarningPatient> list){
        converter.initObject(true);
        List<WarningPatientDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<WarningPatientDto> getDtoResponseEntity(WarningPatientDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public WarningPatientRestAdmin(WarningPatientAdminService service, WarningPatientConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final WarningPatientAdminService service;
    private final WarningPatientConverter converter;





}
