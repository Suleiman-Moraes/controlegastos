package br.com.smtech.controlegastos.library.dto;

import org.springframework.data.domain.Sort.Direction;

import br.com.smtech.controlegastos.api.enums.FieldOrderEnum;
import br.com.smtech.controlegastos.library.interfaces.IFilterBasic;
import lombok.Data;

@Data
public class FilterBasicDto implements IFilterBasic {

    private Integer page = 0;
    private Integer size = 10;
    private Long userId;
    private Direction direction = Direction.DESC;
    private FieldOrderEnum properties;
}
