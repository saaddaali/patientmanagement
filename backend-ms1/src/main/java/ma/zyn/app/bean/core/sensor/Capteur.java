package ma.zyn.app.bean.core.sensor;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.config.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "capteur")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="capteur_seq",sequenceName="capteur_seq",allocationSize=1, initialValue = 1)
public class Capteur  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    private String description;

    private CapteurType capteurType ;


    public Capteur(){
        super();
    }

    public Capteur(Long id){
        this.id = id;
    }

    public Capteur(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public Capteur(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="capteur_seq")
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "capteur_type")
    public CapteurType getCapteurType(){
        return this.capteurType;
    }
    public void setCapteurType(CapteurType capteurType){
        this.capteurType = capteurType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Capteur capteur = (Capteur) o;
        return id != null && id.equals(capteur.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

