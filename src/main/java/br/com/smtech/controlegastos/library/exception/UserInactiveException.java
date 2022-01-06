package br.com.smtech.controlegastos.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.smtech.controlegastos.library.util.ConstantUtil;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserInactiveException extends PatternException{

    public UserInactiveException(){
        this(ConstantUtil.USER_INACTIVE);
    }

    public UserInactiveException(String message) {
        this(message, HttpStatus.FORBIDDEN);
    }

    public UserInactiveException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
