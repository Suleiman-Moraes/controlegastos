package br.com.smtech.controlegastos.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.smtech.controlegastos.library.util.ConstantUtil;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends PatternException{

    public ObjectNotFoundException(){
        this(ConstantUtil.OBJECT_NOT_FOUND);
    }

    public ObjectNotFoundException(String message) {
        this(message, HttpStatus.NOT_FOUND);
    }

    public ObjectNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
