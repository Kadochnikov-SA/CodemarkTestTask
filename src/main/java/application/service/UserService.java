package application.service;

import application.dto.ResponseDto;
import application.dto.UserDto;
import application.dto.UserDtoWithRoles;
import application.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 *
 * This public interface describes service methods
 *
 * @author Sergey Kadocjnikov
 */

public interface UserService {

    List<UserDto> getAll();

    ResponseEntity getOneByLogin(String login) throws UserNotFoundException;

    ResponseEntity deleteUserByLogin(String login);

    ResponseEntity addUser(UserDtoWithRoles userDtoWithRoles);

    ResponseEntity updateUser(UserDtoWithRoles userDtoWithRoles, String login) throws UserNotFoundException;

    boolean existsByLogin(String login);


}
