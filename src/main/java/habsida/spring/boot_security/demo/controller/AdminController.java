package habsida.spring.boot_security.demo.controller;

import habsida.spring.boot_security.demo.exception.UsernameAlreadyExistsException;
import habsida.spring.boot_security.demo.service.RoleService;
import habsida.spring.boot_security.demo.validation.PatternCheck;
import habsida.spring.boot_security.demo.validation.RequiredCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import habsida.spring.boot_security.demo.model.User;
import habsida.spring.boot_security.demo.service.UserService;

import org.springframework.validation.annotation.Validated;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    private Validator validator;

    public AdminController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping
    public String admin(Model model) {
        model.addAttribute("users", userService.findAll());
        return "/admin/users";
    }

    @GetMapping("/users")
    public String viewUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/users";
    }

    @GetMapping("/users/new")
    public String create(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.findAll());
        return "admin/user-form";
    }

    @PostMapping("/users/save")
    public String saveUser(
            @Validated({RequiredCheck.class, PatternCheck.class}) @ModelAttribute("user") User user,
            BindingResult result,
            @RequestParam(value = "roleIds", required = false) List<Long> roleIds,
            Model model) {

        if (user.getId() == null && (user.getPassword() == null || user.getPassword().isBlank())) {
            result.rejectValue("password", "error.user", "Password is required");
        }

        if (roleIds != null) {
            user.setRoles(new HashSet<>(roleService.findByIds(roleIds)));
        }

        if (result.hasErrors()) {
            model.addAttribute("roles", roleService.findAll());
            return "admin/user-form";
        }

        try {
            userService.save(user, roleIds);
        } catch (UsernameAlreadyExistsException e) {
            result.rejectValue("username", "error.user", e.getMessage());
            model.addAttribute("roles", roleService.findAll());
            return "admin/user-form";
        }
        return "redirect:/admin/users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        User user = userService.findById(id);

        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.findAll());

        return "admin/user-form";
    }


    @GetMapping("/users/delete/{id}")
    public String delete(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }
}
