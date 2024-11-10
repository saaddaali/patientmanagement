package ma.zyn.app.bean.core.staff;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "specialization")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="specialization_seq",sequenceName="specialization_seq",allocationSize=1, initialValue = 1)
public class Specialization  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    private String description;



    public Specialization(){
        super();
    }

    public Specialization(Long id){
        this.id = id;
    }

    public Specialization(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public Specialization(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="specialization_seq")
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
        Specialization specialization = (Specialization) o;
        return id != null && id.equals(specialization.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

