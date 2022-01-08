package br.com.smtech.controlegastos.api.dto;

import br.com.smtech.controlegastos.library.dto.FilterBasicDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemFilterDTO extends FilterBasicDto {

    private Long monthId;
}
