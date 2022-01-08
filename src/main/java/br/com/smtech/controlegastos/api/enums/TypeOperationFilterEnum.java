package br.com.smtech.controlegastos.api.enums;

import lombok.Getter;

@Getter
public enum TypeOperationFilterEnum {

    EQUALS("="),
    NOT_EQUALS("!=");

    private String value;

    private TypeOperationFilterEnum(String value) {
        this.value = value;
    }
}
