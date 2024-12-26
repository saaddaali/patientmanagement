package  ma.zyn.app.ws.converter.sensor;

import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.config.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.config.util.StringUtil;
import ma.zyn.app.bean.core.sensor.CapteurType;
import ma.zyn.app.ws.dto.sensor.CapteurTypeDto;

@Component
public class CapteurTypeConverter {



    public CapteurType toItem(CapteurTypeDto dto) {
        if (dto == null) {
            return null;
        } else {
        CapteurType item = new CapteurType();
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


    public CapteurTypeDto toDto(CapteurType item) {
        if (item == null) {
            return null;
        } else {
            CapteurTypeDto dto = new CapteurTypeDto();
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


	
    public List<CapteurType> toItem(List<CapteurTypeDto> dtos) {
        List<CapteurType> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CapteurTypeDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CapteurTypeDto> toDto(List<CapteurType> items) {
        List<CapteurTypeDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (CapteurType item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CapteurTypeDto dto, CapteurType t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<CapteurType> copy(List<CapteurTypeDto> dtos) {
        List<CapteurType> result = new ArrayList<>();
        if (dtos != null) {
            for (CapteurTypeDto dto : dtos) {
                CapteurType instance = new CapteurType();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
