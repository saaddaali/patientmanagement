package  ma.zyn.app.ws.converter.warning;

import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.config.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.config.util.StringUtil;
import ma.zyn.app.bean.core.warning.WarningType;
import ma.zyn.app.ws.dto.warning.WarningTypeDto;

@Component
public class WarningTypeConverter {



    public WarningType toItem(WarningTypeDto dto) {
        if (dto == null) {
            return null;
        } else {
        WarningType item = new WarningType();
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


    public WarningTypeDto toDto(WarningType item) {
        if (item == null) {
            return null;
        } else {
            WarningTypeDto dto = new WarningTypeDto();
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


	
    public List<WarningType> toItem(List<WarningTypeDto> dtos) {
        List<WarningType> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (WarningTypeDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<WarningTypeDto> toDto(List<WarningType> items) {
        List<WarningTypeDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (WarningType item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(WarningTypeDto dto, WarningType t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<WarningType> copy(List<WarningTypeDto> dtos) {
        List<WarningType> result = new ArrayList<>();
        if (dtos != null) {
            for (WarningTypeDto dto : dtos) {
                WarningType instance = new WarningType();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
