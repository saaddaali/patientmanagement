package  ma.zyn.app.ws.facade.admin.patient;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.HttpStatus;

import ma.zyn.app.bean.core.patient.Gender;
import ma.zyn.app.dao.criteria.core.patient.GenderCriteria;
import ma.zyn.app.service.facade.admin.patient.GenderAdminService;
import ma.zyn.app.ws.converter.patient.GenderConverter;
import ma.zyn.app.ws.dto.patient.GenderDto;
import ma.zyn.app.config.util.PaginatedList;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/admin/gender/")
public class GenderRestAdmin {




    @Operation(summary = "Finds a list of all genders")
    @GetMapping("")
    public ResponseEntity<List<GenderDto>> findAll() throws Exception {
        ResponseEntity<List<GenderDto>> res = null;
        List<Gender> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<GenderDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all genders")
    @GetMapping("optimized")
    public ResponseEntity<List<GenderDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<GenderDto>> res = null;
        List<Gender> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<GenderDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a gender by id")
    @GetMapping("id/{id}")
    public ResponseEntity<GenderDto> findById(@PathVariable Long id) {
        Gender t = service.findById(id);
        if (t != null) {
            GenderDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a gender by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<GenderDto> findByLibelle(@PathVariable String libelle) {
	    Gender t = service.findByReferenceEntity(new Gender(libelle));
        if (t != null) {
            GenderDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  gender")
    @PostMapping("")
    public ResponseEntity<GenderDto> save(@RequestBody GenderDto dto) throws Exception {
        if(dto!=null){
            Gender myT = converter.toItem(dto);
            Gender t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                GenderDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  gender")
    @PutMapping("")
    public ResponseEntity<GenderDto> update(@RequestBody GenderDto dto) throws Exception {
        ResponseEntity<GenderDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Gender t = service.findById(dto.getId());
            converter.copy(dto,t);
            Gender updated = service.update(t);
            GenderDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of gender")
    @PostMapping("multiple")
    public ResponseEntity<List<GenderDto>> delete(@RequestBody List<GenderDto> dtos) throws Exception {
        ResponseEntity<List<GenderDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Gender> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified gender")
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


    @Operation(summary = "Finds a gender and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<GenderDto> findWithAssociatedLists(@PathVariable Long id) {
        Gender loaded =  service.findWithAssociatedLists(id);
        GenderDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds genders by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<GenderDto>> findByCriteria(@RequestBody GenderCriteria criteria) throws Exception {
        ResponseEntity<List<GenderDto>> res = null;
        List<Gender> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<GenderDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated genders by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody GenderCriteria criteria) throws Exception {
        List<Gender> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<GenderDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets gender data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody GenderCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<GenderDto> findDtos(List<Gender> list){
        List<GenderDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<GenderDto> getDtoResponseEntity(GenderDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public GenderRestAdmin(GenderAdminService service, GenderConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final GenderAdminService service;
    private final GenderConverter converter;





}
