package ma.zyn.app.bean.core.staff;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import ma.zyn.app.zynerator.security.bean.User;

@Entity
@Table(name = "infirmier")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="infirmier_seq",sequenceName="infirmier_seq",allocationSize=1, initialValue = 1)
public class Infirmier  extends User    {


    public Infirmier(String username) {
        super(username);
    }










    private Specialization specialization ;


    public Infirmier(){
        super();
    }

    public Infirmier(Long id){
        this.id = id;
    }

    public Infirmier(Long id,String email){
        this.id = id;
        this.email = email ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="infirmier_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialization")
    public Specialization getSpecialization(){
        return this.specialization;
    }
    public void setSpecialization(Specialization specialization){
        this.specialization = specialization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Infirmier infirmier = (Infirmier) o;
        return id != null && id.equals(infirmier.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

