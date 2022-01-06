package br.com.smtech.controlegastos.library.interfaces;

import org.springframework.data.domain.Sort.Direction;

import br.com.smtech.controlegastos.api.enums.FieldOrderEnum;

public interface IFilterBasic {
    
    Integer getSize();
    Integer getPage();
    Direction getDirection();
    FieldOrderEnum getProperties();
}
