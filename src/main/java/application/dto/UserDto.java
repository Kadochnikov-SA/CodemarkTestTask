package application.dto;
import application.model.User;


/**
 *
 * This public class describes data transfer object for user without roles
 *
 * @author Sergey Kadochnikov
 */

public class UserDto {

    protected String login;
    protected String name;
    protected String password;

    public UserDto(String login, String name, String password) {
        this.login = login;
        this.name = name;
        this.password = password;
    }

    public UserDto(User user) {
        this.login = user.getLogin();
        this.name = user.getName();
        this.password = user.getPassword();
    }


    public UserDto() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
