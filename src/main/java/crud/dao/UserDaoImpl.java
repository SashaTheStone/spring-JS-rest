package crud.dao;

import crud.models.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

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
    }

    @Override
    public User readUserByID(long id) {
        TypedQuery<User> userQuery = entityManager.createQuery("select u from User u where u.id =: id", User.class);
        userQuery.setParameter("id", id);
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
