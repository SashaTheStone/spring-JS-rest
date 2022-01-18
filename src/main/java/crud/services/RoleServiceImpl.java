package crud.services;

import crud.models.Role;
import crud.repository.RoleRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role readRoleByID(long id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public void updateRole(long id, Role role) {
        roleRepository.save(role);
    }

    @Override
    public void deleteRole(long id) {
        roleRepository.delete(readRoleByID(id));
    }

    @Override
    public Role readRoleByRole(String role) {
        return roleRepository.findByRole(role).get();
    }
}
