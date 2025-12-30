package habsida.spring.boot_security.demo.service;

import habsida.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {
    Role findByName(String name);
    List<Role> findAll();
}


