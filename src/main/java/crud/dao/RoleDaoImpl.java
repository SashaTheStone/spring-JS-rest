package crud.dao;

import crud.models.Role;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;


    public List<Role> getAllRoles(){
        return entityManager.createQuery("select r from Role r", Role.class)
                .getResultList();
    }

    @Override
    public Role getRoleByID(long id) {
        TypedQuery<Role> roleQuery = entityManager.createQuery("select r from Role r where r.id =: id", Role.class);
        roleQuery.setParameter("id", id);
        return roleQuery.getSingleResult();
    }

    @Override
    public Role getRoleByName(String role) {
        TypedQuery<Role> roleQuery = entityManager.createQuery("select r from Role r where r.role =: role", Role.class);
        roleQuery.setParameter("role", role);
        return roleQuery.getSingleResult();
    }
}
