package application.dao;

import application.model.Role;

/**
 * Data access object interface
 *
 * @author Sergey Kadochnikov
 */

public interface RoleDao {

    /**
     * This method returns the role by its name
     *
     * @param name Role name
     * @return Role by its name
     */
    Role findByName(String name);

}