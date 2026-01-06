package habsida.spring.boot_security.demo.exception;

public class UsernameAlreadyExistsException extends RuntimeException {

    public UsernameAlreadyExistsException() {
        super("This username is already in use");
    }
}
