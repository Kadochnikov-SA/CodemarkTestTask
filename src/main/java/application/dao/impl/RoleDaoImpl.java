package application.dao.impl;

import application.dao.RoleDao;
import application.model.Role;
import application.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleDaoImpl implements RoleDao {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleDaoImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
