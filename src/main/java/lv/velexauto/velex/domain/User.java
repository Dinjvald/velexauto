package lv.velexauto.velex.domain;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", columnDefinition = "int(11)")
    private long userId;

    @Column(name = "login", columnDefinition = "char(255)")
    private String login;

    @Column(name = "password", columnDefinition = "char(255")
    private String password;

    @Column(name = "name", columnDefinition = "char(255)")
    private String name;

    @Column(name = "surname", columnDefinition = "char(255)")
    private String surname;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "user")
    private List<UserRole> userRoles = new ArrayList<UserRole>();

    public User() {

    }

    public User(String login, String password, String name, String surname) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }
}
