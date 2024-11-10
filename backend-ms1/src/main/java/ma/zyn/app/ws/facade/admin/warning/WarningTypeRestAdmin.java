package  ma.zyn.app.ws.facade.admin.warning;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.warning.WarningType;
import ma.zyn.app.dao.criteria.core.warning.WarningTypeCriteria;
import ma.zyn.app.service.facade.admin.warning.WarningTypeAdminService;
import ma.zyn.app.ws.converter.warning.WarningTypeConverter;
import ma.zyn.app.ws.dto.warning.WarningTypeDto;
import ma.zyn.app.zynerator.controller.AbstractController;
import ma.zyn.app.zynerator.dto.AuditEntityDto;
import ma.zyn.app.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/warningType/")
public class WarningTypeRestAdmin {




    @Operation(summary = "Finds a list of all warningTypes")
    @GetMapping("")
    public ResponseEntity<List<WarningTypeDto>> findAll() throws Exception {
        ResponseEntity<List<WarningTypeDto>> res = null;
        List<WarningType> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<WarningTypeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all warningTypes")
    @GetMapping("optimized")
    public ResponseEntity<List<WarningTypeDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<WarningTypeDto>> res = null;
        List<WarningType> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<WarningTypeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a warningType by id")
    @GetMapping("id/{id}")
    public ResponseEntity<WarningTypeDto> findById(@PathVariable Long id) {
        WarningType t = service.findById(id);
        if (t != null) {
            WarningTypeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a warningType by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<WarningTypeDto> findByLibelle(@PathVariable String libelle) {
	    WarningType t = service.findByReferenceEntity(new WarningType(libelle));
        if (t != null) {
            WarningTypeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  warningType")
    @PostMapping("")
    public ResponseEntity<WarningTypeDto> save(@RequestBody WarningTypeDto dto) throws Exception {
        if(dto!=null){
            WarningType myT = converter.toItem(dto);
            WarningType t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                WarningTypeDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  warningType")
    @PutMapping("")
    public ResponseEntity<WarningTypeDto> update(@RequestBody WarningTypeDto dto) throws Exception {
        ResponseEntity<WarningTypeDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            WarningType t = service.findById(dto.getId());
            converter.copy(dto,t);
            WarningType updated = service.update(t);
            WarningTypeDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of warningType")
    @PostMapping("multiple")
    public ResponseEntity<List<WarningTypeDto>> delete(@RequestBody List<WarningTypeDto> dtos) throws Exception {
        ResponseEntity<List<WarningTypeDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<WarningType> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified warningType")
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


    @Operation(summary = "Finds a warningType and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<WarningTypeDto> findWithAssociatedLists(@PathVariable Long id) {
        WarningType loaded =  service.findWithAssociatedLists(id);
        WarningTypeDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds warningTypes by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<WarningTypeDto>> findByCriteria(@RequestBody WarningTypeCriteria criteria) throws Exception {
        ResponseEntity<List<WarningTypeDto>> res = null;
        List<WarningType> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<WarningTypeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated warningTypes by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody WarningTypeCriteria criteria) throws Exception {
        List<WarningType> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<WarningTypeDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets warningType data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody WarningTypeCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<WarningTypeDto> findDtos(List<WarningType> list){
        List<WarningTypeDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<WarningTypeDto> getDtoResponseEntity(WarningTypeDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public WarningTypeRestAdmin(WarningTypeAdminService service, WarningTypeConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final WarningTypeAdminService service;
    private final WarningTypeConverter converter;





}
