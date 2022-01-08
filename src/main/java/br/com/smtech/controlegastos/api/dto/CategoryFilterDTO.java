package br.com.smtech.controlegastos.api.dto;

import br.com.smtech.controlegastos.api.enums.TypeOperationFilterEnum;
import br.com.smtech.controlegastos.library.dto.FilterBasicDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryFilterDTO extends FilterBasicDto {

    private Long id;

    private Long monthId;

    private TypeOperationFilterEnum typeMonthId = TypeOperationFilterEnum.EQUALS;

    private String name;

    private Boolean active;
}
