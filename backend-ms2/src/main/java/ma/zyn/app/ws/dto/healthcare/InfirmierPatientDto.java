package  ma.zyn.app.ws.dto.healthcare;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.ws.dto.staff.InfirmierDto;
import ma.zyn.app.ws.dto.patient.PatientDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class InfirmierPatientDto  extends AuditBaseDto {

    private String dateDebut ;
    private String dateFin ;

    private PatientDto patient ;
    private InfirmierDto infirmier ;



    public InfirmierPatientDto(){
        super();
    }




    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateDebut(){
        return this.dateDebut;
    }
    public void setDateDebut(String dateDebut){
        this.dateDebut = dateDebut;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateFin(){
        return this.dateFin;
    }
    public void setDateFin(String dateFin){
        this.dateFin = dateFin;
    }


    public PatientDto getPatient(){
        return this.patient;
    }

    public void setPatient(PatientDto patient){
        this.patient = patient;
    }
    public InfirmierDto getInfirmier(){
        return this.infirmier;
    }

    public void setInfirmier(InfirmierDto infirmier){
        this.infirmier = infirmier;
    }






}
