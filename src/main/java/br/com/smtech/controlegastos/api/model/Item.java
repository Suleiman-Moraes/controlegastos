package br.com.smtech.controlegastos.api.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import br.com.smtech.controlegastos.api.dto.ItemListDTO;
import br.com.smtech.controlegastos.library.model.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SqlResultSetMapping(name = ItemListDTO.ITEM_LIST_DTO_MAPPING, classes = {
        @ConstructorResult(targetClass = ItemListDTO.class, columns = {
                @ColumnResult(name = "id", type = Long.class),
                @ColumnResult(name = "monthId", type = Long.class),
                @ColumnResult(name = "categoryId", type = Long.class),
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
@Table(name = "item", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "month_id", "category_id" })
})
public class Item extends Model {

    @Column(nullable = false)
    private Double amount = 0.0;

    @Column(nullable = false, name = "expected_value")
    private Double expectedValue = 0.0;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_expected", nullable = false)
    private Date dateExpected;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "month_id")
    private Month month;

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    private List<Release> releases;

    public Item(Long id) {
        this.id = id;
    }
}
