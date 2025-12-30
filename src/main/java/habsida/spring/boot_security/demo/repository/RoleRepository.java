package habsida.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import habsida.spring.boot_security.demo.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}


