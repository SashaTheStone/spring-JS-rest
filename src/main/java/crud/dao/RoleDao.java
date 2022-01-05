package crud.dao;

import crud.models.Role;

import java.util.List;

public interface RoleDao {

    public List<Role> getAllRoles();

    public Role getRoleByID(long id);

    public Role getRoleByName(String role);

}
