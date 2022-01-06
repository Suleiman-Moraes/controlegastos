package br.com.smtech.controlegastos.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;

import br.com.smtech.controlegastos.api.dto.AddressDTO;
import br.com.smtech.controlegastos.library.model.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Table(name = "address")
public class Address extends Model {

    @Column(length = 100, nullable = false)
    private String street;

    @Column(length = 10, nullable = false)
    private String number;

    @Column(length = 100, nullable = false)
    private String sector;

    @Column(length = 100, nullable = false)
    private String city;

    @Column(length = 100, nullable = false)
    private String state;

    @Column(length = 100, nullable = false)
    private String countrie;

    @Column(length = 8)
    private String zip;

    @Column(length = 10)
    private String quad;

    @Column(length = 10)
    private String batch;

    @Column(length = 100)
    private String complement;

    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
    private Person person;

    @Builder(builderMethodName = "builder")
    public Address(Long id, String street, String number, String sector, String city, String state, String zip,
            String quad, String batch, String complement, String countrie, Person person) {
        this.street = street;
        this.number = number;
        this.sector = sector;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.quad = quad;
        this.batch = batch;
        this.complement = complement;
        this.countrie = countrie;
        this.person = person;
    }

    public Boolean isUsed() {
        return !StringUtils.isAllBlank(street, number, sector, city, state, zip, quad, batch, complement, countrie);
    }

    public static Address create(AddressDTO object) {
        return new ModelMapper().map(object, Address.class);
    }

    @Override
    protected void prePersistAndUpdate() {
        super.prePersistAndUpdate();
        number = StringUtils.isBlank(number) ? "S/N" : number;
        countrie = StringUtils.isBlank(countrie) ? "Brasil" : countrie;
    }
}
