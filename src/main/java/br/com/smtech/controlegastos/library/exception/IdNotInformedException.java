package br.com.smtech.controlegastos.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.smtech.controlegastos.library.util.ConstantUtil;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IdNotInformedException extends PatternException{
    
    public IdNotInformedException(){
        this(ConstantUtil.ID_NOT_FOUND);
    }

    public IdNotInformedException(String message) {
        this(message, HttpStatus.BAD_REQUEST);
    }

    public IdNotInformedException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
