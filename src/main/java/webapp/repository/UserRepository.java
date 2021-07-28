package webapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import webapp.entity.User;


public interface UserRepository extends JpaRepository<User, Long>{
    User findUserById(long id);
    User findUserByEmail(String email);
}
