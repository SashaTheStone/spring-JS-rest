package crud.dao;

import crud.models.Role;
import crud.models.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getAllUsers(){
        return entityManager.createQuery("select u from User u", User.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void createUser(User user) {
        entityManager.persist(user);
        Set<Role> set = new HashSet();
        set.add(new Role(1L, "ADMIN"));
        user.setRoles(set);
    }

    @Override
    public User readUserByID(long id) {
        TypedQuery<User> userQuery = entityManager.createQuery("select u from User u where u.id =: id", User.class);
        userQuery.setParameter("id", id);
        return userQuery.getSingleResult();
    }

    @Override
    public User getUserByEmail (String email) {
        TypedQuery<User> userQuery = entityManager.createQuery("select u from User u where u.email =: email", User.class);
        userQuery.setParameter("email", email);
        return userQuery.getSingleResult();
    }

    @Override
    @Transactional
    public void updateUser(long id, User user) {
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        entityManager.remove(entityManager.find(User.class,id));
    }

}
