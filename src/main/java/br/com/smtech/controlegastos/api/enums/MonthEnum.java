package br.com.smtech.controlegastos.api.enums;

import lombok.Getter;

@Getter
public enum MonthEnum {
    JANUARY(0, "Janeiro"),
    FEBRUARY(1, "Fevereiro"),
    MARCH(2, "Março"),
    APRIL(3, "Abril"),
    MAY(4, "Maio"),
    JUNE(5, "Junho"),
    JULY(6, "Julho"),
    AUGUST(7, "Agosto"),
    SEPTEMBER(8, "Setembro"),
    OCTOBER(9, "Outubro"),
    NOVEMBER(10, "Novembro"),
    DECEMBER(11, "Dezembro");

    private Integer value;
    private String description;

    private MonthEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public static MonthEnum getByValue(Integer dbData) {
        dbData = dbData == null ? 0 : dbData;
        for (MonthEnum monthEnum : MonthEnum.values()) {
            if(monthEnum.getValue().equals(dbData)){
                return monthEnum;
            }
        }
        return JANUARY;
    }
}
