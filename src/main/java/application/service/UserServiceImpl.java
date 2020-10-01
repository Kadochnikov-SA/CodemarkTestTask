package application.service;

import application.dao.RoleDao;
import application.dao.UserDao;
import application.dto.*;
import application.exception.UserNotFoundException;
import application.model.Role;
import application.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;


import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * This public class describes methods for processing user
 * requests and returns response entities based on the result of data processing
 *
 * @author Sergey Kadochnikov
 */

@Component
public class UserServiceImpl implements UserService {

    private PlatformTransactionManager transactionManager;
    private final UserDao userDao;
    private final RoleDao roleDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public List<UserDto> getAll() {
        return userDao.getAll()
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity getOneByLogin(String login) throws UserNotFoundException {
        User user = userDao.getOneByLogin(login);
        UserDtoWithRoles userDtoWithRoles = new UserDtoWithRoles(user);
        userDtoWithRoles.setRoleDtoSet(
                user.getRoles()
                        .stream()
                        .map(Role::getName)
                        .collect(Collectors.toSet())
        );
        return ResponseEntity.ok().body(userDtoWithRoles);
    }


    @Override
    public ResponseEntity deleteUserByLogin(String login) {
        if (existsByLogin(login)) {
            userDao.deleteUserByLogin(login);
            return ResponseEntity.ok().body(new ResponseDto(true));
        } else{
            ErrorResponseDto responseDto = new ErrorResponseDto(false);
            responseDto.setErrors(new ArrayList<>());
            responseDto.getErrors().add("User with login: " + login + " was not found");
            return ResponseEntity.badRequest().body(responseDto);
        }
    }


    @Override
    public ResponseEntity<ResponseDto> addUser(UserDtoWithRoles userDtoWithRoles) {
        if (existsByLogin(userDtoWithRoles.getLogin())) {
            ErrorResponseDto responseDto = new ErrorResponseDto(false);
            responseDto.setErrors(new ArrayList<>());
            responseDto.getErrors().add("User with login " + userDtoWithRoles.getLogin() + " already exist.");
            return ResponseEntity.badRequest().body(responseDto);
        } else {
            ResponseDto responseDto = ValidatorUserForm.validate(userDtoWithRoles);
            if (responseDto.getClass() == ErrorResponseDto.class) {
                return ResponseEntity.badRequest().body(responseDto);
            } else {
                userDao.addUser(createUser(userDtoWithRoles));
                return ResponseEntity.ok().body(responseDto);
            }
        }
    }

    @Override
    public ResponseEntity<ResponseDto> updateUser(UserDtoWithRoles userDtoWithRoles, String login) throws UserNotFoundException {
        User user = userDao.getOneByLogin(login);
        if (existsByLogin(userDtoWithRoles.getLogin()) && !userDtoWithRoles.getLogin().equals(login)) {
            ErrorResponseDto errorResponseDto = new ErrorResponseDto(false);
            errorResponseDto.setErrors(new ArrayList<>());
            errorResponseDto.getErrors().add("This username " + userDtoWithRoles.getLogin() + " is already used by another user.");
            return ResponseEntity.badRequest().body(errorResponseDto);
        } else {
            userDtoWithRoles.setLogin(login);
            ResponseDto responseDto = ValidatorUserForm.validate(userDtoWithRoles);
            if (responseDto.getClass() == ErrorResponseDto.class) {
                return ResponseEntity.badRequest().body(responseDto);
            } else {
                user.setRoles(editRoles(userDtoWithRoles.getRoleDtoSet()));
                user.setLogin(login);
                user.setPassword(userDtoWithRoles.getPassword());
                user.setName(userDtoWithRoles.getName());
                userDao.addUser(user);
                return ResponseEntity.ok().body(responseDto);
            }
        }
    }

    @Override
    public boolean existsByLogin(String login) {
        return userDao.existsByLogin(login);
    }
    private User createUser(UserDtoWithRoles userDtoWithRoles) {
        Set<Role> roles = editRoles(userDtoWithRoles.getRoleDtoSet());
        return new User(userDtoWithRoles.getName(), userDtoWithRoles.getLogin(), userDtoWithRoles.getPassword(),roles);
    }

    private Set<Role> editRoles(Set<String> roleDtoSet) {
        Set<Role> roles = new HashSet<>();
        for (String roleName : roleDtoSet) {
            if (roleDao.findByName(roleName) == null) {
                Role role = new Role(roleName);
                roles.add(role);
            } else {
                roles.add(roleDao.findByName(roleName));
            }
        }
        return roles;
    }
}
