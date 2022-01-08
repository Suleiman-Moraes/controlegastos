package br.com.smtech.controlegastos.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Table(name = "trelease")
public class Release extends Model {

    @Column(nullable = false)
    private Double value = 0.0;

    @Column(nullable = false)
    private Date date;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_scheduling")
    private Date dateScheduling;

    // Only if you have an appointment date
    @Column(name = "confirm_payment", nullable = false)
    private Boolean confirmPayment = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public Release(Long id) {
        this.id = id;
    }
}
