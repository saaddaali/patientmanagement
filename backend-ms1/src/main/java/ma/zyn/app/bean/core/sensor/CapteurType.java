package ma.zyn.app.bean.core.sensor;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "capteur_type")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="capteur_type_seq",sequenceName="capteur_type_seq",allocationSize=1, initialValue = 1)
public class CapteurType  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    private String description;



    public CapteurType(){
        super();
    }

    public CapteurType(Long id){
        this.id = id;
    }

    public CapteurType(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public CapteurType(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="capteur_type_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
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
      @Column(columnDefinition="TEXT")
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CapteurType capteurType = (CapteurType) o;
        return id != null && id.equals(capteurType.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

