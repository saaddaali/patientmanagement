package  ma.zyn.app.ws.dto.localisation;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;


import ma.zyn.app.ws.dto.sensor.CapteurDto;
import ma.zyn.app.ws.dto.patient.PatientDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocalisationDto  extends AuditBaseDto {

    private String dateLocalisation ;
    private BigDecimal longitude  ;
    private BigDecimal latitude  ;

    private PatientDto patient ;
    private CapteurDto capteur ;



    public LocalisationDto(){
        super();
    }




    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateLocalisation(){
        return this.dateLocalisation;
    }
    public void setDateLocalisation(String dateLocalisation){
        this.dateLocalisation = dateLocalisation;
    }


    public BigDecimal getLongitude(){
        return this.longitude;
    }
    public void setLongitude(BigDecimal longitude){
        this.longitude = longitude;
    }


    public BigDecimal getLatitude(){
        return this.latitude;
    }
    public void setLatitude(BigDecimal latitude){
        this.latitude = latitude;
    }


    public PatientDto getPatient(){
        return this.patient;
    }

    public void setPatient(PatientDto patient){
        this.patient = patient;
    }
    public CapteurDto getCapteur(){
        return this.capteur;
    }

    public void setCapteur(CapteurDto capteur){
        this.capteur = capteur;
    }






}
