package br.com.smtech.controlegastos.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;

import br.com.smtech.controlegastos.api.model.Address;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AddressDTO
 */
@JsonInclude(Include.NON_NULL)
@Data
@NoArgsConstructor
public class AddressDTO {

    private Long id;

    private String street;

    private String number;

    private String sector;

    private String city;

    private String state;

    private String zip;

    private String quad;

    private String batch;

    private String complement;

    private String countrie;

    @Builder(builderMethodName = "builder")
    public AddressDTO(Long id, String street, String number, String sector, String city, String state, String zip, String quad,
            String batch, String complement, String countrie) {
        this.id = id;
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
    }

    public static AddressDTO create(Address object) {
        return new ModelMapper().map(object, AddressDTO.class);
    }

    public Boolean isUsed() {
        return !StringUtils.isAllBlank(street, number, sector, city, state, zip, quad, batch, complement, countrie);
    }
}