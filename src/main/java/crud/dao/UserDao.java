package crud.dao;

import crud.models.User;

import java.util.List;

public interface UserDao {

    public List<User> getAllUsers();

    public void createUser(User user);

    public User readUserByID(long id);

    public void updateUser(long id, User user);

    public void deleteUser(long id);

}
