package crud.services;

import crud.dao.RoleDao;
import crud.dao.UserDao;
import crud.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserDao userDao;
    private final RoleDao roleDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }

    @Override
    public void createUser(User user) {
        userDao.createUser(user);
    }

    @Override
    public User readUserByID(long id) {
        return userDao.readUserByID(id);
    }

    @Override
    public void updateUser(long id, User user) {
        userDao.updateUser(id, user);
    }

    @Override
    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }

    public User getUserByEmail (String email){
        return userDao.getUserByEmail(email);
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.getUserByEmail(s);
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), user.isEnabled(), user.isAccountNonExpired(),user.isCredentialsNonExpired(),
                user.isAccountNonLocked(), user.getAuthorities().stream().map(
                        roles -> new SimpleGrantedAuthority(roles.getAuthority())).collect(Collectors.toSet()));
    }
}
