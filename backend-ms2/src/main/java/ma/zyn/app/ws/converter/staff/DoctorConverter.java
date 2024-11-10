package  ma.zyn.app.ws.converter.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.staff.SpecializationConverter;
import ma.zyn.app.bean.core.staff.Specialization;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.staff.Doctor;
import ma.zyn.app.ws.dto.staff.DoctorDto;

@Component
public class DoctorConverter {

    @Autowired
    private SpecializationConverter specializationConverter ;
    private boolean specialization;

    public  DoctorConverter() {
        initObject(true);
    }

    public Doctor toItem(DoctorDto dto) {
        if (dto == null) {
            return null;
        } else {
        Doctor item = new Doctor();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            item.setPasswordChanged(dto.getPasswordChanged());
            item.setAccountNonLocked(dto.getAccountNonLocked());
            if(StringUtil.isNotEmpty(dto.getPassword()))
                item.setPassword(dto.getPassword());
            if(StringUtil.isNotEmpty(dto.getEmail()))
                item.setEmail(dto.getEmail());
            item.setEnabled(dto.getEnabled());
            item.setCredentialsNonExpired(dto.getCredentialsNonExpired());
            item.setAccountNonExpired(dto.getAccountNonExpired());
            if(StringUtil.isNotEmpty(dto.getUsername()))
                item.setUsername(dto.getUsername());
            if(this.specialization && dto.getSpecialization()!=null)
                item.setSpecialization(specializationConverter.toItem(dto.getSpecialization())) ;



            //item.setRoles(dto.getRoles());

        return item;
        }
    }


    public DoctorDto toDto(Doctor item) {
        if (item == null) {
            return null;
        } else {
            DoctorDto dto = new DoctorDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getPasswordChanged()))
                dto.setPasswordChanged(item.getPasswordChanged());
            if(StringUtil.isNotEmpty(item.getAccountNonLocked()))
                dto.setAccountNonLocked(item.getAccountNonLocked());
            if(StringUtil.isNotEmpty(item.getEmail()))
                dto.setEmail(item.getEmail());
            if(StringUtil.isNotEmpty(item.getEnabled()))
                dto.setEnabled(item.getEnabled());
            if(StringUtil.isNotEmpty(item.getCredentialsNonExpired()))
                dto.setCredentialsNonExpired(item.getCredentialsNonExpired());
            if(StringUtil.isNotEmpty(item.getAccountNonExpired()))
                dto.setAccountNonExpired(item.getAccountNonExpired());
            if(StringUtil.isNotEmpty(item.getUsername()))
                dto.setUsername(item.getUsername());
            if(this.specialization && item.getSpecialization()!=null) {
                dto.setSpecialization(specializationConverter.toDto(item.getSpecialization())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.specialization = value;
    }
	
    public List<Doctor> toItem(List<DoctorDto> dtos) {
        List<Doctor> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (DoctorDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<DoctorDto> toDto(List<Doctor> items) {
        List<DoctorDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Doctor item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(DoctorDto dto, Doctor t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getSpecialization() == null  && dto.getSpecialization() != null){
            t.setSpecialization(new Specialization());
        }else if (t.getSpecialization() != null  && dto.getSpecialization() != null){
            t.setSpecialization(null);
            t.setSpecialization(new Specialization());
        }
        if (dto.getSpecialization() != null)
        specializationConverter.copy(dto.getSpecialization(), t.getSpecialization());
    }

    public List<Doctor> copy(List<DoctorDto> dtos) {
        List<Doctor> result = new ArrayList<>();
        if (dtos != null) {
            for (DoctorDto dto : dtos) {
                Doctor instance = new Doctor();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public SpecializationConverter getSpecializationConverter(){
        return this.specializationConverter;
    }
    public void setSpecializationConverter(SpecializationConverter specializationConverter ){
        this.specializationConverter = specializationConverter;
    }
    public boolean  isSpecialization(){
        return this.specialization;
    }
    public void  setSpecialization(boolean specialization){
        this.specialization = specialization;
    }
}
