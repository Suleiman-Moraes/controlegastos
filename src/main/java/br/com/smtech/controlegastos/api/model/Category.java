package br.com.smtech.controlegastos.api.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.smtech.controlegastos.api.dto.CategoryListDTO;
import br.com.smtech.controlegastos.api.enums.ExpenseClassificationEnum;
import br.com.smtech.controlegastos.api.enums.OperationEnum;
import br.com.smtech.controlegastos.library.model.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SqlResultSetMapping(name = CategoryListDTO.CATEGORY_LIST_DTO_MAPPING, classes = {
        @ConstructorResult(targetClass = CategoryListDTO.class, columns = {
                @ColumnResult(name = "id", type = Long.class),
                @ColumnResult(name = "monthId", type = Long.class),
                @ColumnResult(name = "itemId", type = Long.class),
                @ColumnResult(name = "name", type = String.class),
                @ColumnResult(name = "classification", type = String.class),
                @ColumnResult(name = "operation", type = String.class),
                @ColumnResult(name = "amount", type = Double.class),
                @ColumnResult(name = "expectedValue", type = Double.class),
                @ColumnResult(name = "dateExpected", type = Date.class) }) })
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "name", "user_id" })
})
public class Category extends Model {

    @Column(nullable = false, length = 300)
    private String name;

    @Column(length = 300)
    private String descrition;

    @Column(name = "day_month_due", length = 7)
    private String dayMonthDue;

    @Column(name = "day_month_scheduling", length = 7)
    private String dayMonthScheduling;

    @Column(nullable = false, name = "classification")
    @Enumerated(EnumType.STRING)
    private ExpenseClassificationEnum classification;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OperationEnum operation;

    @Column(nullable = false)
    private Double value = 0.0;

    @Column(nullable = false)
    private Boolean active = Boolean.TRUE;

    @Column(nullable = false)
    private Boolean recurrent = Boolean.TRUE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Item> items;

    public Category(Long id) {
        this.id = id;
    }
}
