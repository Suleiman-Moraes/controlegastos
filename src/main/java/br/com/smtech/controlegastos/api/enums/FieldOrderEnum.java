package br.com.smtech.controlegastos.api.enums;

import org.springframework.data.domain.Sort.Direction;

import lombok.Getter;

@Getter
public enum FieldOrderEnum {
    MONTH_ID("month.id"),
    MONTH_MONTH("month.month"),
    MONTH_YEAR("month.year"),
    MONTH_AMOUNT_DEBT("month.amount_debt"),
    MONTH_AMOUNT_CREDIT("month.amount_credit"),
    MONTH_EXPECTED_VALUE_DEBT("month.expected_value_debt"),
    MONTH_EXPECTED_VALUE_CREDIT("month.expected_value_credit"),
    MONTH_YEAR_MONTH("month.year, month.month");
    
    private String value;

    private FieldOrderEnum(String value){
        this.value = value;
    }

    public String getOrderBy(Direction direction) {
        direction = direction == null ? Direction.ASC : direction;
        return String.format(" ORDER BY %s %s", value, direction.toString());
    }
}
