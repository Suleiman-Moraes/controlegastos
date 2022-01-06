package br.com.smtech.controlegastos.api.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.smtech.controlegastos.api.enums.ExpenseClassificationEnum;
import br.com.smtech.controlegastos.api.enums.OperationEnum;
import br.com.smtech.controlegastos.library.model.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Expected> expecteds;

    public Category(Long id) {
        this.id = id;
    }
}
