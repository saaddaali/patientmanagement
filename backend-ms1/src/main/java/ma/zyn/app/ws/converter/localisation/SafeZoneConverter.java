package  ma.zyn.app.ws.converter.localisation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.config.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.patient.PatientConverter;
import ma.zyn.app.bean.core.patient.Patient;


import ma.zyn.app.config.util.StringUtil;
import ma.zyn.app.bean.core.localisation.SafeZone;
import ma.zyn.app.ws.dto.localisation.SafeZoneDto;

@Component
public class SafeZoneConverter {

    @Autowired
    private PatientConverter patientConverter ;
    private boolean linkedPatient;

    public  SafeZoneConverter() {
        initObject(true);
    }

    public SafeZone toItem(SafeZoneDto dto) {
        if (dto == null) {
            return null;
        } else {
        SafeZone item = new SafeZone();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCentreLongitude()))
                item.setCentreLongitude(dto.getCentreLongitude());
            if(StringUtil.isNotEmpty(dto.getCentreLatitude()))
                item.setCentreLatitude(dto.getCentreLatitude());
            if(StringUtil.isNotEmpty(dto.getRayon()))
                item.setRayon(dto.getRayon());
            if(dto.getLinkedPatient() != null && dto.getLinkedPatient().getId() != null){
                item.setLinkedPatient(new Patient());
                item.getLinkedPatient().setId(dto.getLinkedPatient().getId());
                item.getLinkedPatient().setEmail(dto.getLinkedPatient().getEmail());
            }




        return item;
        }
    }


    public SafeZoneDto toDto(SafeZone item) {
        if (item == null) {
            return null;
        } else {
            SafeZoneDto dto = new SafeZoneDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCentreLongitude()))
                dto.setCentreLongitude(item.getCentreLongitude());
            if(StringUtil.isNotEmpty(item.getCentreLatitude()))
                dto.setCentreLatitude(item.getCentreLatitude());
            if(StringUtil.isNotEmpty(item.getRayon()))
                dto.setRayon(item.getRayon());
            if(this.linkedPatient && item.getLinkedPatient()!=null) {
                dto.setLinkedPatient(patientConverter.toDto(item.getLinkedPatient())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.linkedPatient = value;
    }
	
    public List<SafeZone> toItem(List<SafeZoneDto> dtos) {
        List<SafeZone> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (SafeZoneDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<SafeZoneDto> toDto(List<SafeZone> items) {
        List<SafeZoneDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (SafeZone item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(SafeZoneDto dto, SafeZone t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getLinkedPatient() == null  && dto.getLinkedPatient() != null){
            t.setLinkedPatient(new Patient());
        }else if (t.getLinkedPatient() != null  && dto.getLinkedPatient() != null){
            t.setLinkedPatient(null);
            t.setLinkedPatient(new Patient());
        }
        if (dto.getLinkedPatient() != null)
        patientConverter.copy(dto.getLinkedPatient(), t.getLinkedPatient());
    }

    public List<SafeZone> copy(List<SafeZoneDto> dtos) {
        List<SafeZone> result = new ArrayList<>();
        if (dtos != null) {
            for (SafeZoneDto dto : dtos) {
                SafeZone instance = new SafeZone();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public PatientConverter getPatientConverter(){
        return this.patientConverter;
    }
    public void setPatientConverter(PatientConverter patientConverter ){
        this.patientConverter = patientConverter;
    }
    public boolean  isLinkedPatient(){
        return this.linkedPatient;
    }
    public void  setLinkedPatient(boolean linkedPatient){
        this.linkedPatient = linkedPatient;
    }
}
