package  ma.zyn.app.dao.criteria.core.sensor;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class CapteurCriteria extends  BaseCriteria  {

    private String code;
    private String codeLike;
    private String libelle;
    private String libelleLike;
    private String description;
    private String descriptionLike;

    private CapteurTypeCriteria capteurType ;
    private List<CapteurTypeCriteria> capteurTypes ;


    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getCodeLike(){
        return this.codeLike;
    }
    public void setCodeLike(String codeLike){
        this.codeLike = codeLike;
    }

    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }
    public String getLibelleLike(){
        return this.libelleLike;
    }
    public void setLibelleLike(String libelleLike){
        this.libelleLike = libelleLike;
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescriptionLike(){
        return this.descriptionLike;
    }
    public void setDescriptionLike(String descriptionLike){
        this.descriptionLike = descriptionLike;
    }


    public CapteurTypeCriteria getCapteurType(){
        return this.capteurType;
    }

    public void setCapteurType(CapteurTypeCriteria capteurType){
        this.capteurType = capteurType;
    }
    public List<CapteurTypeCriteria> getCapteurTypes(){
        return this.capteurTypes;
    }

    public void setCapteurTypes(List<CapteurTypeCriteria> capteurTypes){
        this.capteurTypes = capteurTypes;
    }
}
