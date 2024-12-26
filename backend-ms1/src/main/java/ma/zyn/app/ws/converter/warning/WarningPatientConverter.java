package  ma.zyn.app.ws.converter.warning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.config.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.patient.PatientConverter;
import ma.zyn.app.bean.core.patient.Patient;
import ma.zyn.app.bean.core.warning.WarningType;


import ma.zyn.app.config.util.StringUtil;
import ma.zyn.app.config.util.DateUtil;
import ma.zyn.app.bean.core.warning.WarningPatient;
import ma.zyn.app.ws.dto.warning.WarningPatientDto;

@Component
public class WarningPatientConverter {

    @Autowired
    private PatientConverter patientConverter ;
    @Autowired
    private WarningTypeConverter warningTypeConverter ;
    private boolean patient;
    private boolean warningType;

    public  WarningPatientConverter() {
        initObject(true);
    }

    public WarningPatient toItem(WarningPatientDto dto) {
        if (dto == null) {
            return null;
        } else {
        WarningPatient item = new WarningPatient();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getMessage()))
                item.setMessage(dto.getMessage());
            if(StringUtil.isNotEmpty(dto.getDateWarning()))
                item.setDateWarning(DateUtil.stringEnToDate(dto.getDateWarning()));
            if(dto.getPatient() != null && dto.getPatient().getId() != null){
                item.setPatient(new Patient());
                item.getPatient().setId(dto.getPatient().getId());
                item.getPatient().setEmail(dto.getPatient().getEmail());
            }

            if(this.warningType && dto.getWarningType()!=null)
                item.setWarningType(warningTypeConverter.toItem(dto.getWarningType())) ;




        return item;
        }
    }


    public WarningPatientDto toDto(WarningPatient item) {
        if (item == null) {
            return null;
        } else {
            WarningPatientDto dto = new WarningPatientDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getMessage()))
                dto.setMessage(item.getMessage());
            if(item.getDateWarning()!=null)
                dto.setDateWarning(DateUtil.dateTimeToString(item.getDateWarning()));
            if(this.patient && item.getPatient()!=null) {
                dto.setPatient(patientConverter.toDto(item.getPatient())) ;

            }
            if(this.warningType && item.getWarningType()!=null) {
                dto.setWarningType(warningTypeConverter.toDto(item.getWarningType())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.patient = value;
        this.warningType = value;
    }
	
    public List<WarningPatient> toItem(List<WarningPatientDto> dtos) {
        List<WarningPatient> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (WarningPatientDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<WarningPatientDto> toDto(List<WarningPatient> items) {
        List<WarningPatientDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (WarningPatient item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(WarningPatientDto dto, WarningPatient t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getPatient() == null  && dto.getPatient() != null){
            t.setPatient(new Patient());
        }else if (t.getPatient() != null  && dto.getPatient() != null){
            t.setPatient(null);
            t.setPatient(new Patient());
        }
        if(t.getWarningType() == null  && dto.getWarningType() != null){
            t.setWarningType(new WarningType());
        }else if (t.getWarningType() != null  && dto.getWarningType() != null){
            t.setWarningType(null);
            t.setWarningType(new WarningType());
        }
        if (dto.getPatient() != null)
        patientConverter.copy(dto.getPatient(), t.getPatient());
        if (dto.getWarningType() != null)
        warningTypeConverter.copy(dto.getWarningType(), t.getWarningType());
    }

    public List<WarningPatient> copy(List<WarningPatientDto> dtos) {
        List<WarningPatient> result = new ArrayList<>();
        if (dtos != null) {
            for (WarningPatientDto dto : dtos) {
                WarningPatient instance = new WarningPatient();
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
    public WarningTypeConverter getWarningTypeConverter(){
        return this.warningTypeConverter;
    }
    public void setWarningTypeConverter(WarningTypeConverter warningTypeConverter ){
        this.warningTypeConverter = warningTypeConverter;
    }
    public boolean  isPatient(){
        return this.patient;
    }
    public void  setPatient(boolean patient){
        this.patient = patient;
    }
    public boolean  isWarningType(){
        return this.warningType;
    }
    public void  setWarningType(boolean warningType){
        this.warningType = warningType;
    }
}
