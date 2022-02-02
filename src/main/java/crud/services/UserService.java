package crud.services;

import crud.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface UserService extends UserDetailsService{

    public List<User> getAllUsers();

    public void saveUser(User user);

    public User getUserByID(long id);

    public void updateUser(long id, User user);

    public void deleteUser(long id);

    public User getUserByEmail (String email);

}
