import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="users")
public class User {

    @Id
    @SequenceGenerator(name="user_generator", sequenceName = "users_sequence", allocationSize = 1)
    @GeneratedValue(generator = "person_generator")
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
   // private String authToken;
   @Column
    private int age;
    //private Date birthday;

    public User() {}

    public User(String firstName, String lastName, String authToken, int age, Date birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
       // this.authToken = authToken;
        this.age = age;
        //this.birthday = birthday;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /*public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }*/

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /*public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }*/
}
