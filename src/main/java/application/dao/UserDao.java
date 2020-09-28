package application.dao;

import application.exception.UserNotFoundException;
import application.model.User;

import java.util.List;

/**
 * Data access object interface
 *
 * @author Sergey Kadochnikov
 */

public interface UserDao {

    /**
     * This method returns all users without roles
     *
     * @return List of user objects
     */
    List<User> getAll();


    /**
     *
     * This method returns user by its login
     *
     * @param login is a primary key of user entity, unique value
     * @return User by login if exists
     * @throws UserNotFoundException throws exception if user not found
     */
    User getOneByLogin(String login) throws UserNotFoundException;

    /**
     * This method deletes user by irs login
     *
     * @param login is a primary key of user entity, unique value
     */
    void deleteUserByLogin(String login);

    /**
     *
     * This method adds user to data base id not exists
     *
     * @param user User object to be added to the database
     */
    void addUser(User user);

    /**
     *
     * This method checks whether there is a user with this username in the database
     *
     * @param login is a primary key of user entity, unique value
     * @return returns the response true or false
     */
    boolean existsByLogin(String login);


}