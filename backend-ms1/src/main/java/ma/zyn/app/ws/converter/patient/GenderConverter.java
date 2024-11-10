package  ma.zyn.app.ws.converter.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.patient.Gender;
import ma.zyn.app.ws.dto.patient.GenderDto;

@Component
public class GenderConverter {



    public Gender toItem(GenderDto dto) {
        if (dto == null) {
            return null;
        } else {
        Gender item = new Gender();
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


    public GenderDto toDto(Gender item) {
        if (item == null) {
            return null;
        } else {
            GenderDto dto = new GenderDto();
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


	
    public List<Gender> toItem(List<GenderDto> dtos) {
        List<Gender> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (GenderDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<GenderDto> toDto(List<Gender> items) {
        List<GenderDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Gender item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(GenderDto dto, Gender t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Gender> copy(List<GenderDto> dtos) {
        List<Gender> result = new ArrayList<>();
        if (dtos != null) {
            for (GenderDto dto : dtos) {
                Gender instance = new Gender();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
