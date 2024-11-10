package  ma.zyn.app.ws.dto.localisation;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;


import ma.zyn.app.ws.dto.patient.PatientDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class SafeZoneDto  extends AuditBaseDto {

    private BigDecimal centreLongitude  ;
    private BigDecimal centreLatitude  ;
    private BigDecimal rayon  ;

    private PatientDto linkedPatient ;



    public SafeZoneDto(){
        super();
    }




    public BigDecimal getCentreLongitude(){
        return this.centreLongitude;
    }
    public void setCentreLongitude(BigDecimal centreLongitude){
        this.centreLongitude = centreLongitude;
    }


    public BigDecimal getCentreLatitude(){
        return this.centreLatitude;
    }
    public void setCentreLatitude(BigDecimal centreLatitude){
        this.centreLatitude = centreLatitude;
    }


    public BigDecimal getRayon(){
        return this.rayon;
    }
    public void setRayon(BigDecimal rayon){
        this.rayon = rayon;
    }


    public PatientDto getLinkedPatient(){
        return this.linkedPatient;
    }

    public void setLinkedPatient(PatientDto linkedPatient){
        this.linkedPatient = linkedPatient;
    }






}
