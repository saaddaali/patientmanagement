package  ma.zyn.app.ws.converter.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zyn.app.zynerator.util.ListUtil;

import ma.zyn.app.ws.converter.staff.InfirmierConverter;
import ma.zyn.app.bean.core.staff.Infirmier;
import ma.zyn.app.ws.converter.healthcare.InfirmierPatientConverter;
import ma.zyn.app.bean.core.healthcare.InfirmierPatient;
import ma.zyn.app.ws.converter.patient.GenderConverter;
import ma.zyn.app.bean.core.patient.Gender;
import ma.zyn.app.ws.converter.staff.DoctorConverter;
import ma.zyn.app.bean.core.staff.Doctor;
import ma.zyn.app.ws.converter.warning.WarningPatientConverter;
import ma.zyn.app.bean.core.warning.WarningPatient;
import ma.zyn.app.ws.converter.warning.WarningTypeConverter;
import ma.zyn.app.bean.core.warning.WarningType;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.patient.Patient;
import ma.zyn.app.ws.dto.patient.PatientDto;

@Component
public class PatientConverter {

    @Autowired
    private InfirmierConverter infirmierConverter ;
    @Autowired
    private InfirmierPatientConverter infirmierPatientConverter ;
    @Autowired
    private GenderConverter genderConverter ;
    @Autowired
    private DoctorConverter doctorConverter ;
    @Autowired
    private WarningPatientConverter warningPatientConverter ;
    @Autowired
    private WarningTypeConverter warningTypeConverter ;
    private boolean gender;
    private boolean doctorInCharge;
    private boolean infirmierPatients;
    private boolean warningPatients;

    public  PatientConverter() {
        init(true);
    }

    public Patient toItem(PatientDto dto) {
        if (dto == null) {
            return null;
        } else {
        Patient item = new Patient();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getDateOfBirth()))
                item.setDateOfBirth(DateUtil.stringEnToDate(dto.getDateOfBirth()));
            if(StringUtil.isNotEmpty(dto.getAddress()))
                item.setAddress(dto.getAddress());
            if(StringUtil.isNotEmpty(dto.getEmergencyContact()))
                item.setEmergencyContact(dto.getEmergencyContact());
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
            if(this.gender && dto.getGender()!=null)
                item.setGender(genderConverter.toItem(dto.getGender())) ;

            if(this.doctorInCharge && dto.getDoctorInCharge()!=null)
                item.setDoctorInCharge(doctorConverter.toItem(dto.getDoctorInCharge())) ;


            if(this.infirmierPatients && ListUtil.isNotEmpty(dto.getInfirmierPatients()))
                item.setInfirmierPatients(infirmierPatientConverter.toItem(dto.getInfirmierPatients()));
            if(this.warningPatients && ListUtil.isNotEmpty(dto.getWarningPatients()))
                item.setWarningPatients(warningPatientConverter.toItem(dto.getWarningPatients()));

            //item.setRoles(dto.getRoles());

        return item;
        }
    }


    public PatientDto toDto(Patient item) {
        if (item == null) {
            return null;
        } else {
            PatientDto dto = new PatientDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(item.getDateOfBirth()!=null)
                dto.setDateOfBirth(DateUtil.dateTimeToString(item.getDateOfBirth()));
            if(StringUtil.isNotEmpty(item.getAddress()))
                dto.setAddress(item.getAddress());
            if(StringUtil.isNotEmpty(item.getEmergencyContact()))
                dto.setEmergencyContact(item.getEmergencyContact());
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
            if(this.gender && item.getGender()!=null) {
                dto.setGender(genderConverter.toDto(item.getGender())) ;

            }
            if(this.doctorInCharge && item.getDoctorInCharge()!=null) {
                dto.setDoctorInCharge(doctorConverter.toDto(item.getDoctorInCharge())) ;

            }
        if(this.infirmierPatients && ListUtil.isNotEmpty(item.getInfirmierPatients())){
            infirmierPatientConverter.init(true);
            infirmierPatientConverter.setPatient(false);
            dto.setInfirmierPatients(infirmierPatientConverter.toDto(item.getInfirmierPatients()));
            infirmierPatientConverter.setPatient(true);

        }
        if(this.warningPatients && ListUtil.isNotEmpty(item.getWarningPatients())){
            warningPatientConverter.init(true);
            warningPatientConverter.setPatient(false);
            dto.setWarningPatients(warningPatientConverter.toDto(item.getWarningPatients()));
            warningPatientConverter.setPatient(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.infirmierPatients = value;
        this.warningPatients = value;
    }
    public void initObject(boolean value) {
        this.gender = value;
        this.doctorInCharge = value;
    }
	
    public List<Patient> toItem(List<PatientDto> dtos) {
        List<Patient> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (PatientDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<PatientDto> toDto(List<Patient> items) {
        List<PatientDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Patient item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(PatientDto dto, Patient t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getGender() == null  && dto.getGender() != null){
            t.setGender(new Gender());
        }else if (t.getGender() != null  && dto.getGender() != null){
            t.setGender(null);
            t.setGender(new Gender());
        }
        if(t.getDoctorInCharge() == null  && dto.getDoctorInCharge() != null){
            t.setDoctorInCharge(new Doctor());
        }else if (t.getDoctorInCharge() != null  && dto.getDoctorInCharge() != null){
            t.setDoctorInCharge(null);
            t.setDoctorInCharge(new Doctor());
        }
        if (dto.getGender() != null)
        genderConverter.copy(dto.getGender(), t.getGender());
        if (dto.getDoctorInCharge() != null)
        doctorConverter.copy(dto.getDoctorInCharge(), t.getDoctorInCharge());
        if (dto.getInfirmierPatients() != null)
            t.setInfirmierPatients(infirmierPatientConverter.copy(dto.getInfirmierPatients()));
        if (dto.getWarningPatients() != null)
            t.setWarningPatients(warningPatientConverter.copy(dto.getWarningPatients()));
    }

    public List<Patient> copy(List<PatientDto> dtos) {
        List<Patient> result = new ArrayList<>();
        if (dtos != null) {
            for (PatientDto dto : dtos) {
                Patient instance = new Patient();
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
    public InfirmierPatientConverter getInfirmierPatientConverter(){
        return this.infirmierPatientConverter;
    }
    public void setInfirmierPatientConverter(InfirmierPatientConverter infirmierPatientConverter ){
        this.infirmierPatientConverter = infirmierPatientConverter;
    }
    public GenderConverter getGenderConverter(){
        return this.genderConverter;
    }
    public void setGenderConverter(GenderConverter genderConverter ){
        this.genderConverter = genderConverter;
    }
    public DoctorConverter getDoctorConverter(){
        return this.doctorConverter;
    }
    public void setDoctorConverter(DoctorConverter doctorConverter ){
        this.doctorConverter = doctorConverter;
    }
    public WarningPatientConverter getWarningPatientConverter(){
        return this.warningPatientConverter;
    }
    public void setWarningPatientConverter(WarningPatientConverter warningPatientConverter ){
        this.warningPatientConverter = warningPatientConverter;
    }
    public WarningTypeConverter getWarningTypeConverter(){
        return this.warningTypeConverter;
    }
    public void setWarningTypeConverter(WarningTypeConverter warningTypeConverter ){
        this.warningTypeConverter = warningTypeConverter;
    }
    public boolean  isGender(){
        return this.gender;
    }
    public void  setGender(boolean gender){
        this.gender = gender;
    }
    public boolean  isDoctorInCharge(){
        return this.doctorInCharge;
    }
    public void  setDoctorInCharge(boolean doctorInCharge){
        this.doctorInCharge = doctorInCharge;
    }
    public boolean  isInfirmierPatients(){
        return this.infirmierPatients ;
    }
    public void  setInfirmierPatients(boolean infirmierPatients ){
        this.infirmierPatients  = infirmierPatients ;
    }
    public boolean  isWarningPatients(){
        return this.warningPatients ;
    }
    public void  setWarningPatients(boolean warningPatients ){
        this.warningPatients  = warningPatients ;
    }
}
