package habsida.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import habsida.spring.boot_security.demo.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

