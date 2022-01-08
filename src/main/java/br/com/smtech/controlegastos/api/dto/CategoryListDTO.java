package br.com.smtech.controlegastos.api.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.smtech.controlegastos.api.enums.ExpenseClassificationEnum;
import br.com.smtech.controlegastos.api.enums.OperationEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_NULL)
@Data
@NoArgsConstructor
public class CategoryListDTO implements Serializable {

    public static final String CATEGORY_LIST_DTO_MAPPING = "CategoryListDTOMapping";

    private Long id;

    private Long monthId;

    private Long itemId;

    private String name;

    private ExpenseClassificationEnum classification;

    private OperationEnum operation;

    private Double amount;

    private Double expectedValue;

    private Date dateExpected;

    // CategoryListDTOMapping
    public CategoryListDTO(Long id, Long monthId, Long itemId, String name, String classification,
            String operation, Double amount, Double expectedValue, Date dateExpected) {
        this.id = id;
        this.monthId = monthId;
        this.itemId = itemId;
        this.name = name;
        this.classification = ExpenseClassificationEnum.valueOf(classification);
        this.operation = OperationEnum.valueOf(operation);
        this.amount = amount;
        this.expectedValue = expectedValue;
        this.dateExpected = dateExpected;
    }
}
