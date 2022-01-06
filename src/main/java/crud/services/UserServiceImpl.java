package crud.services;

import crud.models.User;
import crud.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User readUserByID(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void updateUser(long id, User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.delete(readUserByID(id));
    }

    public User getUserByEmail (String email){
        return userRepository.findByEmail(email).get();
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(s).get();
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), user.isEnabled(), user.isAccountNonExpired(),user.isCredentialsNonExpired(),
                user.isAccountNonLocked(), user.getAuthorities().stream().map(
                        roles -> new SimpleGrantedAuthority(roles.getAuthority())).collect(Collectors.toSet()));
    }
}
