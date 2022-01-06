package br.com.smtech.controlegastos.api.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.smtech.controlegastos.api.enums.MonthEnum;

/**
 * 
 * @author Suleiman Moraes
 *
 */
@Converter(autoApply = true)
public class MonthEnumConverter implements AttributeConverter<MonthEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(MonthEnum attribute) {
        return attribute.getValue();
    }

    @Override
    public MonthEnum convertToEntityAttribute(Integer dbData) {
        return MonthEnum.getByValue(dbData);
    }
}
