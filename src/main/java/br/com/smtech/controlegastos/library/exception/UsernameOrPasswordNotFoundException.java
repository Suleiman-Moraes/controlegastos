package br.com.smtech.controlegastos.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.smtech.controlegastos.library.util.ConstantUtil;
import lombok.Getter;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UsernameOrPasswordNotFoundException extends PatternException {

    @Getter
    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public UsernameOrPasswordNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
        this.httpStatus = httpStatus;
    }

    public UsernameOrPasswordNotFoundException() {
        super(ConstantUtil.USERNAME_PASSWORD_INCORRECT, HttpStatus.BAD_REQUEST);
    }
}
