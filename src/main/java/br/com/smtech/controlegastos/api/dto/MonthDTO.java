package br.com.smtech.controlegastos.api.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.modelmapper.ModelMapper;

import br.com.smtech.controlegastos.api.enums.MonthEnum;
import br.com.smtech.controlegastos.api.model.Month;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_NULL)
@Data
@NoArgsConstructor
public class MonthDTO implements Serializable {

    public static final String MONTH_DTO_MAPPING = "MonthDTOMapping";

    private Long id;

    private MonthEnum month;

    private String monthDescription;

    private Integer year;

    private Double amountDebt;

    private Double amountCredit;

    private Double expectedValueDebt;

    private Double expectedValueCredit;

    // MonthDTOMapping
    public MonthDTO(Long id, Integer month, Integer year, Double amountDebt,
            Double amountCredit,
            Double expectedValueDebt, Double expectedValueCredit) {
        this.id = id;
        this.month = MonthEnum.getByValue(month);
        this.monthDescription = this.month.getDescription();
        this.year = year;
        this.amountDebt = amountDebt;
        this.amountCredit = amountCredit;
        this.expectedValueDebt = expectedValueDebt;
        this.expectedValueCredit = expectedValueCredit;
    }

    public static MonthDTO create(Month object) {
        MonthDTO dto = new ModelMapper().map(object, MonthDTO.class);
        dto.setMonthDescription(object.getMonth() != null ? object.getMonth().getDescription() : "-");
        return dto;
    }
}
