package  ma.zyn.app.dao.criteria.core.localisation;


import ma.zyn.app.dao.criteria.core.sensor.CapteurCriteria;
import ma.zyn.app.dao.criteria.core.patient.PatientCriteria;

import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class LocalisationCriteria extends  BaseCriteria  {

    private LocalDateTime dateLocalisation;
    private LocalDateTime dateLocalisationFrom;
    private LocalDateTime dateLocalisationTo;
    private String longitude;
    private String longitudeMin;
    private String longitudeMax;
    private String latitude;
    private String latitudeMin;
    private String latitudeMax;

    private PatientCriteria patient ;
    private List<PatientCriteria> patients ;
    private CapteurCriteria capteur ;
    private List<CapteurCriteria> capteurs ;


    public LocalDateTime getDateLocalisation(){
        return this.dateLocalisation;
    }
    public void setDateLocalisation(LocalDateTime dateLocalisation){
        this.dateLocalisation = dateLocalisation;
    }
    public LocalDateTime getDateLocalisationFrom(){
        return this.dateLocalisationFrom;
    }
    public void setDateLocalisationFrom(LocalDateTime dateLocalisationFrom){
        this.dateLocalisationFrom = dateLocalisationFrom;
    }
    public LocalDateTime getDateLocalisationTo(){
        return this.dateLocalisationTo;
    }
    public void setDateLocalisationTo(LocalDateTime dateLocalisationTo){
        this.dateLocalisationTo = dateLocalisationTo;
    }
    public String getLongitude(){
        return this.longitude;
    }
    public void setLongitude(String longitude){
        this.longitude = longitude;
    }   
    public String getLongitudeMin(){
        return this.longitudeMin;
    }
    public void setLongitudeMin(String longitudeMin){
        this.longitudeMin = longitudeMin;
    }
    public String getLongitudeMax(){
        return this.longitudeMax;
    }
    public void setLongitudeMax(String longitudeMax){
        this.longitudeMax = longitudeMax;
    }
      
    public String getLatitude(){
        return this.latitude;
    }
    public void setLatitude(String latitude){
        this.latitude = latitude;
    }   
    public String getLatitudeMin(){
        return this.latitudeMin;
    }
    public void setLatitudeMin(String latitudeMin){
        this.latitudeMin = latitudeMin;
    }
    public String getLatitudeMax(){
        return this.latitudeMax;
    }
    public void setLatitudeMax(String latitudeMax){
        this.latitudeMax = latitudeMax;
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
    public CapteurCriteria getCapteur(){
        return this.capteur;
    }

    public void setCapteur(CapteurCriteria capteur){
        this.capteur = capteur;
    }
    public List<CapteurCriteria> getCapteurs(){
        return this.capteurs;
    }

    public void setCapteurs(List<CapteurCriteria> capteurs){
        this.capteurs = capteurs;
    }
}
