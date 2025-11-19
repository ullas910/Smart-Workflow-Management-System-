package Smart.Work.Force.Management.System.demo.auth.repository;

import Smart.Work.Force.Management.System.demo.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>  {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
