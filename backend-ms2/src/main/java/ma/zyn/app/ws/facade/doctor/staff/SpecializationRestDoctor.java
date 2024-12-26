package ma.zyn.app.ws.facade.doctor.staff;

import io.swagger.v3.oas.annotations.Operation;
import ma.zyn.app.bean.core.staff.Specialization;
import ma.zyn.app.dao.criteria.core.staff.SpecializationCriteria;
import ma.zyn.app.service.facade.admin.staff.SpecializationAdminService;
import ma.zyn.app.ws.converter.staff.SpecializationConverter;
import ma.zyn.app.ws.dto.staff.SpecializationDto;
import ma.zyn.app.config.util.PaginatedList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor/specialization/")
public class SpecializationRestDoctor {




    @Operation(summary = "Finds a list of all specializations")
    @GetMapping("")
    public ResponseEntity<List<SpecializationDto>> findAll() throws Exception {
        ResponseEntity<List<SpecializationDto>> res = null;
        List<Specialization> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<SpecializationDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all specializations")
    @GetMapping("optimized")
    public ResponseEntity<List<SpecializationDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<SpecializationDto>> res = null;
        List<Specialization> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<SpecializationDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a specialization by id")
    @GetMapping("id/{id}")
    public ResponseEntity<SpecializationDto> findById(@PathVariable Long id) {
        Specialization t = service.findById(id);
        if (t != null) {
            SpecializationDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a specialization by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<SpecializationDto> findByLibelle(@PathVariable String libelle) {
	    Specialization t = service.findByReferenceEntity(new Specialization(libelle));
        if (t != null) {
            SpecializationDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  specialization")
    @PostMapping("")
    public ResponseEntity<SpecializationDto> save(@RequestBody SpecializationDto dto) throws Exception {
        if(dto!=null){
            Specialization myT = converter.toItem(dto);
            Specialization t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                SpecializationDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  specialization")
    @PutMapping("")
    public ResponseEntity<SpecializationDto> update(@RequestBody SpecializationDto dto) throws Exception {
        ResponseEntity<SpecializationDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Specialization t = service.findById(dto.getId());
            converter.copy(dto,t);
            Specialization updated = service.update(t);
            SpecializationDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of specialization")
    @PostMapping("multiple")
    public ResponseEntity<List<SpecializationDto>> delete(@RequestBody List<SpecializationDto> dtos) throws Exception {
        ResponseEntity<List<SpecializationDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Specialization> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified specialization")
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


    @Operation(summary = "Finds a specialization and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<SpecializationDto> findWithAssociatedLists(@PathVariable Long id) {
        Specialization loaded =  service.findWithAssociatedLists(id);
        SpecializationDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds specializations by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<SpecializationDto>> findByCriteria(@RequestBody SpecializationCriteria criteria) throws Exception {
        ResponseEntity<List<SpecializationDto>> res = null;
        List<Specialization> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<SpecializationDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated specializations by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody SpecializationCriteria criteria) throws Exception {
        List<Specialization> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<SpecializationDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets specialization data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody SpecializationCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }

	public List<SpecializationDto> findDtos(List<Specialization> list){
        List<SpecializationDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<SpecializationDto> getDtoResponseEntity(SpecializationDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public SpecializationRestDoctor(SpecializationAdminService service, SpecializationConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final SpecializationAdminService service;
    private final SpecializationConverter converter;





}
