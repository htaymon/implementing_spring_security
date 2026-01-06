package habsida.spring.boot_security.demo.model;

import habsida.spring.boot_security.demo.validation.PatternCheck;
import habsida.spring.boot_security.demo.validation.RequiredCheck;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username is required",groups = RequiredCheck.class)
    @Pattern(
            regexp = "[A-Za-z0-9 ]+",
            message = "Username may contain letters and digits only",
            groups = PatternCheck.class
    )
    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @NotBlank(message = "First name is required",groups = RequiredCheck.class)
    @Pattern(
            regexp = "[A-Za-z ]+",
            message = "First name must contain letters only",
            groups = PatternCheck.class
    )
    private String firstName;

    @NotBlank(message = "Last name is required",groups = RequiredCheck.class)
    @Pattern(
            regexp = "[A-Za-z ]+",
            message = "Last name must contain letters only",
            groups = PatternCheck.class
    )
    private String lastName;


    @NotNull(message = "Age is required",groups = RequiredCheck.class)
    @Min(value = 1, message = "Age must be between 1 and 120",groups = PatternCheck.class)
    @Max(value = 120, message = "Age must be between 1 and 120",groups = PatternCheck.class)
    private Integer age;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public Integer getAge() { return age; }
    public Set<Role> getRoles() { return roles; }

    public void setId(Long id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setAge(Integer age) { this.age = age; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }

    @Override
    public Collection<Role> getAuthorities() {
        return roles;
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}

