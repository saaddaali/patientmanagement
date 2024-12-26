package  ma.zyn.app.ws.dto.sensor;

import ma.zyn.app.config.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class CapteurDto  extends AuditBaseDto {

    private String code  ;
    private String libelle  ;
    private String description  ;

    private CapteurTypeDto capteurType ;



    public CapteurDto(){
        super();
    }




    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }


    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }


    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }


    public CapteurTypeDto getCapteurType(){
        return this.capteurType;
    }

    public void setCapteurType(CapteurTypeDto capteurType){
        this.capteurType = capteurType;
    }






}
