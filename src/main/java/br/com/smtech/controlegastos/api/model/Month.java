package br.com.smtech.controlegastos.api.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.smtech.controlegastos.api.converter.MonthEnumConverter;
import br.com.smtech.controlegastos.api.dto.MonthDTO;
import br.com.smtech.controlegastos.api.enums.MonthEnum;
import br.com.smtech.controlegastos.library.model.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SqlResultSetMapping(name = MonthDTO.MONTH_DTO_MAPPING, classes = {
        @ConstructorResult(targetClass = MonthDTO.class, columns = {
                @ColumnResult(name = "id", type = Long.class),
                @ColumnResult(name = "month", type = Integer.class),
                @ColumnResult(name = "year", type = Integer.class),
                @ColumnResult(name = "amountDebt", type = Double.class),
                @ColumnResult(name = "amountCredit", type = Double.class),
                @ColumnResult(name = "expectedValueDebt", type = Double.class),
                @ColumnResult(name = "expectedValueCredit", type = Double.class) }) })
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "month", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "month", "year", "user_id" })
})
public class Month extends Model {

    @Column(nullable = false, length = 2)
    @Convert(converter = MonthEnumConverter.class)
    private MonthEnum month;

    @Column(nullable = false, length = 4)
    private Integer year;

    @Column(nullable = false, name = "amount_debt")
    private Double amountDebt = 0.0;

    @Column(nullable = false, name = "amount_credit")
    private Double amountCredit = 0.0;

    @Column(nullable = false, name = "expected_value_debt")
    private Double expectedValueDebt = 0.0;

    @Column(nullable = false, name = "expected_value_credit")
    private Double expectedValueCredit = 0.0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "month", fetch = FetchType.LAZY)
    private List<Item> items;

    public Month(Long id) {
        this.id = id;
    }

    public Month(MonthEnum month, Integer year, User user) {
        this.month = month;
        this.year = year;
        this.user = user;
    }

    @Builder(builderMethodName = "builder")
    public Month(Long id, MonthEnum month, Integer year, Double amountDebt, Double amountCredit,
            Double expectedValueDebt, Double expectedValueCredit, User user, List<Item> items) {
        super(id);
        this.month = month;
        this.year = year;
        this.amountDebt = amountDebt;
        this.amountCredit = amountCredit;
        this.expectedValueDebt = expectedValueDebt;
        this.expectedValueCredit = expectedValueCredit;
        this.user = user;
        this.items = items;
    }
}
