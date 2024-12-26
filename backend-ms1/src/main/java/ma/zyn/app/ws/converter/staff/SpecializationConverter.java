package  ma.zyn.app.ws.converter.staff;

import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.config.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.config.util.StringUtil;
import ma.zyn.app.bean.core.staff.Specialization;
import ma.zyn.app.ws.dto.staff.SpecializationDto;

@Component
public class SpecializationConverter {



    public Specialization toItem(SpecializationDto dto) {
        if (dto == null) {
            return null;
        } else {
        Specialization item = new Specialization();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());



        return item;
        }
    }


    public SpecializationDto toDto(Specialization item) {
        if (item == null) {
            return null;
        } else {
            SpecializationDto dto = new SpecializationDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());


        return dto;
        }
    }


	
    public List<Specialization> toItem(List<SpecializationDto> dtos) {
        List<Specialization> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (SpecializationDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<SpecializationDto> toDto(List<Specialization> items) {
        List<SpecializationDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Specialization item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(SpecializationDto dto, Specialization t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Specialization> copy(List<SpecializationDto> dtos) {
        List<Specialization> result = new ArrayList<>();
        if (dtos != null) {
            for (SpecializationDto dto : dtos) {
                Specialization instance = new Specialization();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
