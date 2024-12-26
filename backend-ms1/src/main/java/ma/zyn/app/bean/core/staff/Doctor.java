package ma.zyn.app.bean.core.staff;








import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import java.util.Objects;
import ma.zyn.app.config.security.bean.User;

@Entity
@Table(name = "doctor")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="doctor_seq",sequenceName="doctor_seq",allocationSize=1, initialValue = 1)
public class Doctor  extends User    {


    public Doctor(String username) {
        super(username);
    }










    private Specialization specialization ;


    public Doctor(){
        super();
    }

    public Doctor(Long id){
        this.id = id;
    }

    public Doctor(Long id,String email){
        this.id = id;
        this.email = email ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="doctor_seq")
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
        Doctor doctor = (Doctor) o;
        return id != null && id.equals(doctor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

