package br.com.smtech.controlegastos.library.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public abstract class PatternException extends RuntimeException{
    
    @Getter
    protected HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    public PatternException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public PatternException(String message, Throwable cause, HttpStatus httpStatus) {
        super(message, cause);
        this.httpStatus = httpStatus;
    }
}