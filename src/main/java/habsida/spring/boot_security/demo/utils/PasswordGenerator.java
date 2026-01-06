package habsida.spring.boot_security.demo.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        System.out.println("admin = " + encoder.encode("admin"));
        System.out.println("user = " + encoder.encode("user"));
    }
}

