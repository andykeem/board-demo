package loc.example.dev.boarddemo20210102.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Invalid username")
public class UsernameExistException extends RuntimeException {

    public UsernameExistException(String msg) {
        super(msg);
    }
}
