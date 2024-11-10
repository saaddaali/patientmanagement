package  ma.zyn.app.ws.converter.healthcare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.staff.InfirmierConverter;
import ma.zyn.app.bean.core.staff.Infirmier;
import ma.zyn.app.ws.converter.patient.PatientConverter;
import ma.zyn.app.bean.core.patient.Patient;

import ma.zyn.app.bean.core.patient.Patient;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.healthcare.InfirmierPatient;
import ma.zyn.app.ws.dto.healthcare.InfirmierPatientDto;

@Component
public class InfirmierPatientConverter {

    @Autowired
    private InfirmierConverter infirmierConverter ;
    @Autowired
    private PatientConverter patientConverter ;
    private boolean patient;
    private boolean infirmier;

    public  InfirmierPatientConverter() {
        initObject(true);
    }

    public InfirmierPatient toItem(InfirmierPatientDto dto) {
        if (dto == null) {
            return null;
        } else {
        InfirmierPatient item = new InfirmierPatient();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getDateDebut()))
                item.setDateDebut(DateUtil.stringEnToDate(dto.getDateDebut()));
            if(StringUtil.isNotEmpty(dto.getDateFin()))
                item.setDateFin(DateUtil.stringEnToDate(dto.getDateFin()));
            if(dto.getPatient() != null && dto.getPatient().getId() != null){
                item.setPatient(new Patient());
                item.getPatient().setId(dto.getPatient().getId());
                item.getPatient().setEmail(dto.getPatient().getEmail());
            }

            if(this.infirmier && dto.getInfirmier()!=null)
                item.setInfirmier(infirmierConverter.toItem(dto.getInfirmier())) ;




        return item;
        }
    }


    public InfirmierPatientDto toDto(InfirmierPatient item) {
        if (item == null) {
            return null;
        } else {
            InfirmierPatientDto dto = new InfirmierPatientDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(item.getDateDebut()!=null)
                dto.setDateDebut(DateUtil.dateTimeToString(item.getDateDebut()));
            if(item.getDateFin()!=null)
                dto.setDateFin(DateUtil.dateTimeToString(item.getDateFin()));
            if(this.patient && item.getPatient()!=null) {
                dto.setPatient(patientConverter.toDto(item.getPatient())) ;

            }
            if(this.infirmier && item.getInfirmier()!=null) {
                dto.setInfirmier(infirmierConverter.toDto(item.getInfirmier())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.patient = value;
        this.infirmier = value;
    }
	
    public List<InfirmierPatient> toItem(List<InfirmierPatientDto> dtos) {
        List<InfirmierPatient> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (InfirmierPatientDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<InfirmierPatientDto> toDto(List<InfirmierPatient> items) {
        List<InfirmierPatientDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (InfirmierPatient item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(InfirmierPatientDto dto, InfirmierPatient t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getPatient() == null  && dto.getPatient() != null){
            t.setPatient(new Patient());
        }else if (t.getPatient() != null  && dto.getPatient() != null){
            t.setPatient(null);
            t.setPatient(new Patient());
        }
        if(t.getInfirmier() == null  && dto.getInfirmier() != null){
            t.setInfirmier(new Infirmier());
        }else if (t.getInfirmier() != null  && dto.getInfirmier() != null){
            t.setInfirmier(null);
            t.setInfirmier(new Infirmier());
        }
        if (dto.getPatient() != null)
        patientConverter.copy(dto.getPatient(), t.getPatient());
        if (dto.getInfirmier() != null)
        infirmierConverter.copy(dto.getInfirmier(), t.getInfirmier());
    }

    public List<InfirmierPatient> copy(List<InfirmierPatientDto> dtos) {
        List<InfirmierPatient> result = new ArrayList<>();
        if (dtos != null) {
            for (InfirmierPatientDto dto : dtos) {
                InfirmierPatient instance = new InfirmierPatient();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public InfirmierConverter getInfirmierConverter(){
        return this.infirmierConverter;
    }
    public void setInfirmierConverter(InfirmierConverter infirmierConverter ){
        this.infirmierConverter = infirmierConverter;
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
    public boolean  isInfirmier(){
        return this.infirmier;
    }
    public void  setInfirmier(boolean infirmier){
        this.infirmier = infirmier;
    }
}
