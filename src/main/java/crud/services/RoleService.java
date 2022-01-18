package crud.services;

import crud.models.Role;

import java.util.List;


public interface RoleService {

    public List<Role> getAllRoles();

    public void saveRole(Role role);

    public Role readRoleByID(long id);

    public void updateRole(long id, Role role);

    public void deleteRole(long id);

    public Role readRoleByRole(String role);
}
