package  ma.zyn.app.ws.converter.sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.sensor.CapteurTypeConverter;
import ma.zyn.app.bean.core.sensor.CapteurType;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.sensor.Capteur;
import ma.zyn.app.ws.dto.sensor.CapteurDto;

@Component
public class CapteurConverter {

    @Autowired
    private CapteurTypeConverter capteurTypeConverter ;
    private boolean capteurType;

    public  CapteurConverter() {
        initObject(true);
    }

    public Capteur toItem(CapteurDto dto) {
        if (dto == null) {
            return null;
        } else {
        Capteur item = new Capteur();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(this.capteurType && dto.getCapteurType()!=null)
                item.setCapteurType(capteurTypeConverter.toItem(dto.getCapteurType())) ;




        return item;
        }
    }


    public CapteurDto toDto(Capteur item) {
        if (item == null) {
            return null;
        } else {
            CapteurDto dto = new CapteurDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(this.capteurType && item.getCapteurType()!=null) {
                dto.setCapteurType(capteurTypeConverter.toDto(item.getCapteurType())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.capteurType = value;
    }
	
    public List<Capteur> toItem(List<CapteurDto> dtos) {
        List<Capteur> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CapteurDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CapteurDto> toDto(List<Capteur> items) {
        List<CapteurDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Capteur item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CapteurDto dto, Capteur t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getCapteurType() == null  && dto.getCapteurType() != null){
            t.setCapteurType(new CapteurType());
        }else if (t.getCapteurType() != null  && dto.getCapteurType() != null){
            t.setCapteurType(null);
            t.setCapteurType(new CapteurType());
        }
        if (dto.getCapteurType() != null)
        capteurTypeConverter.copy(dto.getCapteurType(), t.getCapteurType());
    }

    public List<Capteur> copy(List<CapteurDto> dtos) {
        List<Capteur> result = new ArrayList<>();
        if (dtos != null) {
            for (CapteurDto dto : dtos) {
                Capteur instance = new Capteur();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public CapteurTypeConverter getCapteurTypeConverter(){
        return this.capteurTypeConverter;
    }
    public void setCapteurTypeConverter(CapteurTypeConverter capteurTypeConverter ){
        this.capteurTypeConverter = capteurTypeConverter;
    }
    public boolean  isCapteurType(){
        return this.capteurType;
    }
    public void  setCapteurType(boolean capteurType){
        this.capteurType = capteurType;
    }
}
