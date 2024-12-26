package  ma.zyn.app.dao.criteria.core.localisation;


import ma.zyn.app.dao.criteria.core.patient.PatientCriteria;

import ma.zyn.app.config.criteria.BaseCriteria;

import java.util.List;

public class SafeZoneCriteria extends  BaseCriteria  {

    private String centreLongitude;
    private String centreLongitudeMin;
    private String centreLongitudeMax;
    private String centreLatitude;
    private String centreLatitudeMin;
    private String centreLatitudeMax;
    private String rayon;
    private String rayonMin;
    private String rayonMax;

    private PatientCriteria linkedPatient ;
    private List<PatientCriteria> linkedPatients ;


    public String getCentreLongitude(){
        return this.centreLongitude;
    }
    public void setCentreLongitude(String centreLongitude){
        this.centreLongitude = centreLongitude;
    }   
    public String getCentreLongitudeMin(){
        return this.centreLongitudeMin;
    }
    public void setCentreLongitudeMin(String centreLongitudeMin){
        this.centreLongitudeMin = centreLongitudeMin;
    }
    public String getCentreLongitudeMax(){
        return this.centreLongitudeMax;
    }
    public void setCentreLongitudeMax(String centreLongitudeMax){
        this.centreLongitudeMax = centreLongitudeMax;
    }
      
    public String getCentreLatitude(){
        return this.centreLatitude;
    }
    public void setCentreLatitude(String centreLatitude){
        this.centreLatitude = centreLatitude;
    }   
    public String getCentreLatitudeMin(){
        return this.centreLatitudeMin;
    }
    public void setCentreLatitudeMin(String centreLatitudeMin){
        this.centreLatitudeMin = centreLatitudeMin;
    }
    public String getCentreLatitudeMax(){
        return this.centreLatitudeMax;
    }
    public void setCentreLatitudeMax(String centreLatitudeMax){
        this.centreLatitudeMax = centreLatitudeMax;
    }
      
    public String getRayon(){
        return this.rayon;
    }
    public void setRayon(String rayon){
        this.rayon = rayon;
    }   
    public String getRayonMin(){
        return this.rayonMin;
    }
    public void setRayonMin(String rayonMin){
        this.rayonMin = rayonMin;
    }
    public String getRayonMax(){
        return this.rayonMax;
    }
    public void setRayonMax(String rayonMax){
        this.rayonMax = rayonMax;
    }
      

    public PatientCriteria getLinkedPatient(){
        return this.linkedPatient;
    }

    public void setLinkedPatient(PatientCriteria linkedPatient){
        this.linkedPatient = linkedPatient;
    }
    public List<PatientCriteria> getLinkedPatients(){
        return this.linkedPatients;
    }

    public void setLinkedPatients(List<PatientCriteria> linkedPatients){
        this.linkedPatients = linkedPatients;
    }
}
