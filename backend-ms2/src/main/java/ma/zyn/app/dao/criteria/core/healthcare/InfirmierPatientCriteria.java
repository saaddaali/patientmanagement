package  ma.zyn.app.dao.criteria.core.healthcare;


import ma.zyn.app.dao.criteria.core.staff.InfirmierCriteria;
import ma.zyn.app.dao.criteria.core.patient.PatientCriteria;

import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class InfirmierPatientCriteria extends  BaseCriteria  {

    private LocalDateTime dateDebut;
    private LocalDateTime dateDebutFrom;
    private LocalDateTime dateDebutTo;
    private LocalDateTime dateFin;
    private LocalDateTime dateFinFrom;
    private LocalDateTime dateFinTo;

    private PatientCriteria patient ;
    private List<PatientCriteria> patients ;
    private InfirmierCriteria infirmier ;
    private List<InfirmierCriteria> infirmiers ;


    public LocalDateTime getDateDebut(){
        return this.dateDebut;
    }
    public void setDateDebut(LocalDateTime dateDebut){
        this.dateDebut = dateDebut;
    }
    public LocalDateTime getDateDebutFrom(){
        return this.dateDebutFrom;
    }
    public void setDateDebutFrom(LocalDateTime dateDebutFrom){
        this.dateDebutFrom = dateDebutFrom;
    }
    public LocalDateTime getDateDebutTo(){
        return this.dateDebutTo;
    }
    public void setDateDebutTo(LocalDateTime dateDebutTo){
        this.dateDebutTo = dateDebutTo;
    }
    public LocalDateTime getDateFin(){
        return this.dateFin;
    }
    public void setDateFin(LocalDateTime dateFin){
        this.dateFin = dateFin;
    }
    public LocalDateTime getDateFinFrom(){
        return this.dateFinFrom;
    }
    public void setDateFinFrom(LocalDateTime dateFinFrom){
        this.dateFinFrom = dateFinFrom;
    }
    public LocalDateTime getDateFinTo(){
        return this.dateFinTo;
    }
    public void setDateFinTo(LocalDateTime dateFinTo){
        this.dateFinTo = dateFinTo;
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
    public InfirmierCriteria getInfirmier(){
        return this.infirmier;
    }

    public void setInfirmier(InfirmierCriteria infirmier){
        this.infirmier = infirmier;
    }
    public List<InfirmierCriteria> getInfirmiers(){
        return this.infirmiers;
    }

    public void setInfirmiers(List<InfirmierCriteria> infirmiers){
        this.infirmiers = infirmiers;
    }
}
