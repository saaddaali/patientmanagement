package  ma.zyn.app.ws.facade.admin.localisation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.localisation.Localisation;
import ma.zyn.app.dao.criteria.core.localisation.LocalisationCriteria;
import ma.zyn.app.service.facade.admin.localisation.LocalisationAdminService;
import ma.zyn.app.ws.converter.localisation.LocalisationConverter;
import ma.zyn.app.ws.dto.localisation.LocalisationDto;
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
@RequestMapping("/api/admin/localisation/")
public class LocalisationRestAdmin {




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
        }
        return res;
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




   public LocalisationRestAdmin(LocalisationAdminService service, LocalisationConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final LocalisationAdminService service;
    private final LocalisationConverter converter;





}
