package br.com.smtech.controlegastos.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.smtech.controlegastos.api.enums.MonthEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_NULL)
@Data
@NoArgsConstructor
public class MonthNewDTO {
    
    private MonthEnum month;

    private Integer year;
}
