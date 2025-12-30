package habsida.spring.boot_security.demo.service;

import habsida.spring.boot_security.demo.model.User;
import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    void save(User user);
    void delete(Long id);
}

