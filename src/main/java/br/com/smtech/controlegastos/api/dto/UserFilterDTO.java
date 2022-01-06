package br.com.smtech.controlegastos.api.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.smtech.controlegastos.library.dto.FilterBasicDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
public class UserFilterDTO extends FilterBasicDto implements Serializable {

    private Long id;

    private String name;

    @JsonIgnore
    private boolean listRoot = false;
}
