package br.com.smtech.controlegastos.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.smtech.controlegastos.library.util.ConstantUtil;

@ResponseStatus(HttpStatus.GONE)
public class ObjectUnavailableException extends PatternException{

    public ObjectUnavailableException(){
        this(ConstantUtil.OBJECT_NOT_FOUND);
    }

    public ObjectUnavailableException(String message) {
        this(message, HttpStatus.GONE);
    }

    public ObjectUnavailableException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
