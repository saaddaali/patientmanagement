package  ma.zyn.app.dao.criteria.core.warning;


import ma.zyn.app.dao.criteria.core.patient.PatientCriteria;

import ma.zyn.app.config.criteria.BaseCriteria;

import java.util.List;
import java.time.LocalDateTime;

public class WarningPatientCriteria extends  BaseCriteria  {

    private String message;
    private String messageLike;
    private LocalDateTime dateWarning;
    private LocalDateTime dateWarningFrom;
    private LocalDateTime dateWarningTo;

    private PatientCriteria patient ;
    private List<PatientCriteria> patients ;
    private WarningTypeCriteria warningType ;
    private List<WarningTypeCriteria> warningTypes ;


    public String getMessage(){
        return this.message;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public String getMessageLike(){
        return this.messageLike;
    }
    public void setMessageLike(String messageLike){
        this.messageLike = messageLike;
    }

    public LocalDateTime getDateWarning(){
        return this.dateWarning;
    }
    public void setDateWarning(LocalDateTime dateWarning){
        this.dateWarning = dateWarning;
    }
    public LocalDateTime getDateWarningFrom(){
        return this.dateWarningFrom;
    }
    public void setDateWarningFrom(LocalDateTime dateWarningFrom){
        this.dateWarningFrom = dateWarningFrom;
    }
    public LocalDateTime getDateWarningTo(){
        return this.dateWarningTo;
    }
    public void setDateWarningTo(LocalDateTime dateWarningTo){
        this.dateWarningTo = dateWarningTo;
    }

    public PatientCriteria getPatient(){
        return this.patient;
    }

    public void setPatient(PatientCriteria patient){
        this.patient = patient;
    }
    public List<PatientCriteria> getPatients(){
        return this.patients;
    }

    public void setPatients(List<PatientCriteria> patients){
        this.patients = patients;
    }
    public WarningTypeCriteria getWarningType(){
        return this.warningType;
    }

    public void setWarningType(WarningTypeCriteria warningType){
        this.warningType = warningType;
    }
    public List<WarningTypeCriteria> getWarningTypes(){
        return this.warningTypes;
    }

    public void setWarningTypes(List<WarningTypeCriteria> warningTypes){
        this.warningTypes = warningTypes;
    }
}
