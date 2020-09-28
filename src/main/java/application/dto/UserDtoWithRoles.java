package application.dto;

import application.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;


import java.util.Set;

/**
 *
 * This public class describes data transfer object for user with roles
 *
 * @author Sergey Kadochnikov
 */

public class UserDtoWithRoles extends UserDto {
    public UserDtoWithRoles() {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<String> roleDtoSet;

    public UserDtoWithRoles(User user) {
        super(user);
    }

    public Set<String> getRoleDtoSet() {
        return roleDtoSet;
    }

    public void setRoleDtoSet(Set<String> roleDtoSet) {
        this.roleDtoSet = roleDtoSet;
    }

}
