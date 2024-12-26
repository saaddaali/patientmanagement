package  ma.zyn.app.ws.facade.doctor.healthcare;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.HttpStatus;

import ma.zyn.app.bean.core.healthcare.InfirmierPatient;
import ma.zyn.app.dao.criteria.core.healthcare.InfirmierPatientCriteria;
import ma.zyn.app.service.facade.doctor.healthcare.InfirmierPatientDoctorService;
import ma.zyn.app.ws.converter.healthcare.InfirmierPatientConverter;
import ma.zyn.app.ws.dto.healthcare.InfirmierPatientDto;
import ma.zyn.app.config.util.PaginatedList;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/doctor/infirmierPatient/")
public class InfirmierPatientRestDoctor {




    @Operation(summary = "Finds a list of all infirmierPatients")
    @GetMapping("")
    public ResponseEntity<List<InfirmierPatientDto>> findAll() throws Exception {
        ResponseEntity<List<InfirmierPatientDto>> res = null;
        List<InfirmierPatient> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<InfirmierPatientDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a infirmierPatient by id")
    @GetMapping("id/{id}")
    public ResponseEntity<InfirmierPatientDto> findById(@PathVariable Long id) {
        InfirmierPatient t = service.findById(id);
        if (t != null) {
            converter.init(true);
            InfirmierPatientDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  infirmierPatient")
    @PostMapping("")
    public ResponseEntity<InfirmierPatientDto> save(@RequestBody InfirmierPatientDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            InfirmierPatient myT = converter.toItem(dto);
            InfirmierPatient t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                InfirmierPatientDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  infirmierPatient")
    @PutMapping("")
    public ResponseEntity<InfirmierPatientDto> update(@RequestBody InfirmierPatientDto dto) throws Exception {
        ResponseEntity<InfirmierPatientDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            InfirmierPatient t = service.findById(dto.getId());
            converter.copy(dto,t);
            InfirmierPatient updated = service.update(t);
            InfirmierPatientDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of infirmierPatient")
    @PostMapping("multiple")
    public ResponseEntity<List<InfirmierPatientDto>> delete(@RequestBody List<InfirmierPatientDto> dtos) throws Exception {
        ResponseEntity<List<InfirmierPatientDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<InfirmierPatient> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified infirmierPatient")
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

    @Operation(summary = "find by patient id")
    @GetMapping("patient/id/{id}")
    public List<InfirmierPatientDto> findByPatientId(@PathVariable Long id){
        return findDtos(service.findByPatientId(id));
    }
    @Operation(summary = "delete by patient id")
    @DeleteMapping("patient/id/{id}")
    public int deleteByPatientId(@PathVariable Long id){
        return service.deleteByPatientId(id);
    }

    @Operation(summary = "Finds a infirmierPatient and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<InfirmierPatientDto> findWithAssociatedLists(@PathVariable Long id) {
        InfirmierPatient loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        InfirmierPatientDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds infirmierPatients by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<InfirmierPatientDto>> findByCriteria(@RequestBody InfirmierPatientCriteria criteria) throws Exception {
        ResponseEntity<List<InfirmierPatientDto>> res = null;
        List<InfirmierPatient> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<InfirmierPatientDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated infirmierPatients by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody InfirmierPatientCriteria criteria) throws Exception {
        List<InfirmierPatient> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<InfirmierPatientDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets infirmierPatient data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody InfirmierPatientCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<InfirmierPatientDto> findDtos(List<InfirmierPatient> list){
        converter.initObject(true);
        List<InfirmierPatientDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<InfirmierPatientDto> getDtoResponseEntity(InfirmierPatientDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public InfirmierPatientRestDoctor(InfirmierPatientDoctorService service, InfirmierPatientConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final InfirmierPatientDoctorService service;
    private final InfirmierPatientConverter converter;





}
