package  ma.zyn.app.ws.dto.warning;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.ws.dto.patient.PatientDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class WarningPatientDto  extends AuditBaseDto {

    private String message  ;
    private String dateWarning ;

    private PatientDto patient ;
    private WarningTypeDto warningType ;



    public WarningPatientDto(){
        super();
    }




    public String getMessage(){
        return this.message;
    }
    public void setMessage(String message){
        this.message = message;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateWarning(){
        return this.dateWarning;
    }
    public void setDateWarning(String dateWarning){
        this.dateWarning = dateWarning;
    }


    public PatientDto getPatient(){
        return this.patient;
    }

    public void setPatient(PatientDto patient){
        this.patient = patient;
    }
    public WarningTypeDto getWarningType(){
        return this.warningType;
    }

    public void setWarningType(WarningTypeDto warningType){
        this.warningType = warningType;
    }






}
