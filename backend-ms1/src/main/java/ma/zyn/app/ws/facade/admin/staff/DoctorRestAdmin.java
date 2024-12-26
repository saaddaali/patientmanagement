package  ma.zyn.app.ws.facade.admin.staff;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.HttpStatus;

import ma.zyn.app.bean.core.staff.Doctor;
import ma.zyn.app.dao.criteria.core.staff.DoctorCriteria;
import ma.zyn.app.service.facade.admin.staff.DoctorAdminService;
import ma.zyn.app.ws.converter.staff.DoctorConverter;
import ma.zyn.app.ws.dto.staff.DoctorDto;
import ma.zyn.app.config.util.PaginatedList;


import ma.zyn.app.config.security.bean.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/admin/doctor/")
public class DoctorRestAdmin {




    @Operation(summary = "Finds a list of all doctors")
    @GetMapping("")
    public ResponseEntity<List<DoctorDto>> findAll() throws Exception {
        ResponseEntity<List<DoctorDto>> res = null;
        List<Doctor> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<DoctorDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all doctors")
    @GetMapping("optimized")
    public ResponseEntity<List<DoctorDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<DoctorDto>> res = null;
        List<Doctor> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<DoctorDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a doctor by id")
    @GetMapping("id/{id}")
    public ResponseEntity<DoctorDto> findById(@PathVariable Long id) {
        Doctor t = service.findById(id);
        if (t != null) {
            converter.init(true);
            DoctorDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a doctor by email")
    @GetMapping("email/{email}")
    public ResponseEntity<DoctorDto> findByEmail(@PathVariable String email) {
	    Doctor t = service.findByReferenceEntity(new Doctor(email));
        if (t != null) {
            converter.init(true);
            DoctorDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  doctor")
    @PostMapping("")
    public ResponseEntity<DoctorDto> save(@RequestBody DoctorDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Doctor myT = converter.toItem(dto);
            Doctor t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                DoctorDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  doctor")
    @PutMapping("")
    public ResponseEntity<DoctorDto> update(@RequestBody DoctorDto dto) throws Exception {
        ResponseEntity<DoctorDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Doctor t = service.findById(dto.getId());
            converter.copy(dto,t);
            Doctor updated = service.update(t);
            DoctorDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of doctor")
    @PostMapping("multiple")
    public ResponseEntity<List<DoctorDto>> delete(@RequestBody List<DoctorDto> dtos) throws Exception {
        ResponseEntity<List<DoctorDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Doctor> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified doctor")
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


    @Operation(summary = "Finds a doctor and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<DoctorDto> findWithAssociatedLists(@PathVariable Long id) {
        Doctor loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        DoctorDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds doctors by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<DoctorDto>> findByCriteria(@RequestBody DoctorCriteria criteria) throws Exception {
        ResponseEntity<List<DoctorDto>> res = null;
        List<Doctor> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<DoctorDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated doctors by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody DoctorCriteria criteria) throws Exception {
        List<Doctor> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<DoctorDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets doctor data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody DoctorCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<DoctorDto> findDtos(List<Doctor> list){
        converter.initObject(true);
        List<DoctorDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<DoctorDto> getDtoResponseEntity(DoctorDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }



    @Operation(summary = "Change password to the specified  utilisateur")
    @PutMapping("changePassword")
    public boolean changePassword(@RequestBody User dto) throws Exception {
        return service.changePassword(dto.getUsername(),dto.getPassword());
    }

   public DoctorRestAdmin(DoctorAdminService service, DoctorConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final DoctorAdminService service;
    private final DoctorConverter converter;





}
