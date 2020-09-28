package application.controller;


import application.dto.UserDto;
import application.dto.UserDtoWithRoles;
import application.exception.UserNotFoundException;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *This public class describes controllers
 *
 * @author Sergey Kadochnikov
 */

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("all")
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("get/{login}")
    public ResponseEntity getOne(@PathVariable String login) throws UserNotFoundException {
        return userService.getOneByLogin(login);
    }

    @PostMapping(value = "add")
    public ResponseEntity addUser(@RequestBody UserDtoWithRoles userDtoWithRoles) {
        return userService.addUser(userDtoWithRoles);
    }

    @DeleteMapping("delete/{login}")
    public ResponseEntity deleteOne(@PathVariable String login) {
        return userService.deleteUserByLogin(login);
    }

    @PutMapping("edit/{login}")
    public ResponseEntity editUser(@RequestBody UserDtoWithRoles userDtoWithRoles,
                                   @PathVariable String login) throws UserNotFoundException {
        return userService.updateUser(userDtoWithRoles, login);
    }


}
