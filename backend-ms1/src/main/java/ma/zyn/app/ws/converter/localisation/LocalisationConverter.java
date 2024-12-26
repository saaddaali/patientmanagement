package  ma.zyn.app.ws.converter.localisation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.config.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.sensor.CapteurConverter;
import ma.zyn.app.bean.core.sensor.Capteur;
import ma.zyn.app.ws.converter.patient.PatientConverter;
import ma.zyn.app.bean.core.patient.Patient;


import ma.zyn.app.config.util.StringUtil;
import ma.zyn.app.config.util.DateUtil;
import ma.zyn.app.bean.core.localisation.Localisation;
import ma.zyn.app.ws.dto.localisation.LocalisationDto;

@Component
public class LocalisationConverter {

    @Autowired
    private CapteurConverter capteurConverter ;
    @Autowired
    private PatientConverter patientConverter ;
    private boolean patient;
    private boolean capteur;

    public  LocalisationConverter() {
        initObject(true);
    }

    public Localisation toItem(LocalisationDto dto) {
        if (dto == null) {
            return null;
        } else {
        Localisation item = new Localisation();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getDateLocalisation()))
                item.setDateLocalisation(DateUtil.stringEnToDate(dto.getDateLocalisation()));
            if(StringUtil.isNotEmpty(dto.getLongitude()))
                item.setLongitude(dto.getLongitude());
            if(StringUtil.isNotEmpty(dto.getLatitude()))
                item.setLatitude(dto.getLatitude());
            if(dto.getPatient() != null && dto.getPatient().getId() != null){
                item.setPatient(new Patient());
                item.getPatient().setId(dto.getPatient().getId());
                item.getPatient().setEmail(dto.getPatient().getEmail());
            }

            if(this.capteur && dto.getCapteur()!=null)
                item.setCapteur(capteurConverter.toItem(dto.getCapteur())) ;




        return item;
        }
    }


    public LocalisationDto toDto(Localisation item) {
        if (item == null) {
            return null;
        } else {
            LocalisationDto dto = new LocalisationDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(item.getDateLocalisation()!=null)
                dto.setDateLocalisation(DateUtil.dateTimeToString(item.getDateLocalisation()));
            if(StringUtil.isNotEmpty(item.getLongitude()))
                dto.setLongitude(item.getLongitude());
            if(StringUtil.isNotEmpty(item.getLatitude()))
                dto.setLatitude(item.getLatitude());
            if(this.patient && item.getPatient()!=null) {
                dto.setPatient(patientConverter.toDto(item.getPatient())) ;

            }
            if(this.capteur && item.getCapteur()!=null) {
                dto.setCapteur(capteurConverter.toDto(item.getCapteur())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.patient = value;
        this.capteur = value;
    }
	
    public List<Localisation> toItem(List<LocalisationDto> dtos) {
        List<Localisation> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (LocalisationDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<LocalisationDto> toDto(List<Localisation> items) {
        List<LocalisationDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Localisation item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(LocalisationDto dto, Localisation t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getPatient() == null  && dto.getPatient() != null){
            t.setPatient(new Patient());
        }else if (t.getPatient() != null  && dto.getPatient() != null){
            t.setPatient(null);
            t.setPatient(new Patient());
        }
        if(t.getCapteur() == null  && dto.getCapteur() != null){
            t.setCapteur(new Capteur());
        }else if (t.getCapteur() != null  && dto.getCapteur() != null){
            t.setCapteur(null);
            t.setCapteur(new Capteur());
        }
        if (dto.getPatient() != null)
        patientConverter.copy(dto.getPatient(), t.getPatient());
        if (dto.getCapteur() != null)
        capteurConverter.copy(dto.getCapteur(), t.getCapteur());
    }

    public List<Localisation> copy(List<LocalisationDto> dtos) {
        List<Localisation> result = new ArrayList<>();
        if (dtos != null) {
            for (LocalisationDto dto : dtos) {
                Localisation instance = new Localisation();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public CapteurConverter getCapteurConverter(){
        return this.capteurConverter;
    }
    public void setCapteurConverter(CapteurConverter capteurConverter ){
        this.capteurConverter = capteurConverter;
    }
    public PatientConverter getPatientConverter(){
        return this.patientConverter;
    }
    public void setPatientConverter(PatientConverter patientConverter ){
        this.patientConverter = patientConverter;
    }
    public boolean  isPatient(){
        return this.patient;
    }
    public void  setPatient(boolean patient){
        this.patient = patient;
    }
    public boolean  isCapteur(){
        return this.capteur;
    }
    public void  setCapteur(boolean capteur){
        this.capteur = capteur;
    }
}
