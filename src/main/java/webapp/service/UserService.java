package webapp.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import webapp.entity.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    void create(User user);

    void update(User user);

    void delete(User user);

    List<User> getAllUsers();

    User getUserById(long id);

    User getUserByEmail(String email);
}
