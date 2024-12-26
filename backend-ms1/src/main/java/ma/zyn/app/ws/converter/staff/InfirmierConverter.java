package  ma.zyn.app.ws.converter.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.config.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.bean.core.staff.Specialization;



import ma.zyn.app.config.util.StringUtil;
import ma.zyn.app.bean.core.staff.Infirmier;
import ma.zyn.app.ws.dto.staff.InfirmierDto;

@Component
public class InfirmierConverter {

    @Autowired
    private SpecializationConverter specializationConverter ;
    private boolean specialization;

    public  InfirmierConverter() {
        initObject(true);
    }

    public Infirmier toItem(InfirmierDto dto) {
        if (dto == null) {
            return null;
        } else {
        Infirmier item = new Infirmier();
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


    public InfirmierDto toDto(Infirmier item) {
        if (item == null) {
            return null;
        } else {
            InfirmierDto dto = new InfirmierDto();
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
	
    public List<Infirmier> toItem(List<InfirmierDto> dtos) {
        List<Infirmier> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (InfirmierDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<InfirmierDto> toDto(List<Infirmier> items) {
        List<InfirmierDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Infirmier item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(InfirmierDto dto, Infirmier t) {
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

    public List<Infirmier> copy(List<InfirmierDto> dtos) {
        List<Infirmier> result = new ArrayList<>();
        if (dtos != null) {
            for (InfirmierDto dto : dtos) {
                Infirmier instance = new Infirmier();
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
