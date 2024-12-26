package ma.zyn.app.bean.core.warning;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.config.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "warning_type")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="warning_type_seq",sequenceName="warning_type_seq",allocationSize=1, initialValue = 1)
public class WarningType  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    private String description;



    public WarningType(){
        super();
    }

    public WarningType(Long id){
        this.id = id;
    }

    public WarningType(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public WarningType(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="warning_type_seq")
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
        WarningType warningType = (WarningType) o;
        return id != null && id.equals(warningType.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

