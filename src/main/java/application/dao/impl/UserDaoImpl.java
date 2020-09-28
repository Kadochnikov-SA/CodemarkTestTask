package application.dao.impl;

import application.dao.UserDao;
import application.exception.UserNotFoundException;
import application.model.User;
import application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {


    private UserRepository userRepository;

    @Override
    public List<User> getAll() {
        Iterator<User> userIterator = userRepository.findAll().iterator();
        List<User> users = new ArrayList<>();
        while (userIterator.hasNext()) {
            users.add(userIterator.next());
        }
        return users;
    }

    @Override
    public User getOneByLogin(String login) throws UserNotFoundException {
        return userRepository.findById(login).orElseThrow(
                () -> new UserNotFoundException("User with login: " + login + " was not found"));
    }

    @Override
    public void deleteUserByLogin(String login) {
        userRepository.deleteById(login);
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean existsByLogin(String login) {
        return userRepository.existsById(login);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}

