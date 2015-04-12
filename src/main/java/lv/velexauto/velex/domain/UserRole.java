package lv.velexauto.velex.domain;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_role_id", columnDefinition = "int(11)")
    private long userRoleId;

    @Column(name = "role", columnDefinition = "char(45)")
    private String role;

    @Transient
    private int userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public UserRole() {
    }

    public UserRole(String role, User user) {
        this.role = role;
        this.user = user;
    }

    public long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }
}
