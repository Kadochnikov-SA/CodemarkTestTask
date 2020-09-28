package application.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * This public class describes a model for connecting user and role tables
 *
 * @author Sergey Kadochnikov
 */


@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {

    @Id
    @Column(name = "login")
    private String login;

    @Id
    @Column(name = "role_id")
    private long roleId;

    public UserRole() {
    }

    public UserRole(String login, long roleId) {
        this.login = login;
        this.roleId = roleId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

}
