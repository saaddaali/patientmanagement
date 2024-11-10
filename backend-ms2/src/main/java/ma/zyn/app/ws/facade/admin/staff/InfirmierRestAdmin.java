package  ma.zyn.app.ws.facade.admin.staff;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.staff.Infirmier;
import ma.zyn.app.dao.criteria.core.staff.InfirmierCriteria;
import ma.zyn.app.service.facade.admin.staff.InfirmierAdminService;
import ma.zyn.app.ws.converter.staff.InfirmierConverter;
import ma.zyn.app.ws.dto.staff.InfirmierDto;
import ma.zyn.app.zynerator.controller.AbstractController;
import ma.zyn.app.zynerator.dto.AuditEntityDto;
import ma.zyn.app.zynerator.util.PaginatedList;


import ma.zyn.app.zynerator.security.bean.User;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/infirmier/")
public class InfirmierRestAdmin {




    @Operation(summary = "Finds a list of all infirmiers")
    @GetMapping("")
    public ResponseEntity<List<InfirmierDto>> findAll() throws Exception {
        ResponseEntity<List<InfirmierDto>> res = null;
        List<Infirmier> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<InfirmierDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all infirmiers")
    @GetMapping("optimized")
    public ResponseEntity<List<InfirmierDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<InfirmierDto>> res = null;
        List<Infirmier> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<InfirmierDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a infirmier by id")
    @GetMapping("id/{id}")
    public ResponseEntity<InfirmierDto> findById(@PathVariable Long id) {
        Infirmier t = service.findById(id);
        if (t != null) {
            converter.init(true);
            InfirmierDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a infirmier by email")
    @GetMapping("email/{email}")
    public ResponseEntity<InfirmierDto> findByEmail(@PathVariable String email) {
	    Infirmier t = service.findByReferenceEntity(new Infirmier(email));
        if (t != null) {
            converter.init(true);
            InfirmierDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  infirmier")
    @PostMapping("")
    public ResponseEntity<InfirmierDto> save(@RequestBody InfirmierDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Infirmier myT = converter.toItem(dto);
            Infirmier t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                InfirmierDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  infirmier")
    @PutMapping("")
    public ResponseEntity<InfirmierDto> update(@RequestBody InfirmierDto dto) throws Exception {
        ResponseEntity<InfirmierDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Infirmier t = service.findById(dto.getId());
            converter.copy(dto,t);
            Infirmier updated = service.update(t);
            InfirmierDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of infirmier")
    @PostMapping("multiple")
    public ResponseEntity<List<InfirmierDto>> delete(@RequestBody List<InfirmierDto> dtos) throws Exception {
        ResponseEntity<List<InfirmierDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Infirmier> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified infirmier")
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


    @Operation(summary = "Finds a infirmier and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<InfirmierDto> findWithAssociatedLists(@PathVariable Long id) {
        Infirmier loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        InfirmierDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds infirmiers by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<InfirmierDto>> findByCriteria(@RequestBody InfirmierCriteria criteria) throws Exception {
        ResponseEntity<List<InfirmierDto>> res = null;
        List<Infirmier> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<InfirmierDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated infirmiers by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody InfirmierCriteria criteria) throws Exception {
        List<Infirmier> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<InfirmierDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets infirmier data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody InfirmierCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<InfirmierDto> findDtos(List<Infirmier> list){
        converter.initObject(true);
        List<InfirmierDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<InfirmierDto> getDtoResponseEntity(InfirmierDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }



    @Operation(summary = "Change password to the specified  utilisateur")
    @PutMapping("changePassword")
    public boolean changePassword(@RequestBody User dto) throws Exception {
        return service.changePassword(dto.getUsername(),dto.getPassword());
    }

   public InfirmierRestAdmin(InfirmierAdminService service, InfirmierConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final InfirmierAdminService service;
    private final InfirmierConverter converter;





}
