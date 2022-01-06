package br.com.smtech.controlegastos.api.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.modelmapper.ModelMapper;

import br.com.smtech.controlegastos.api.model.Person;
import br.com.smtech.controlegastos.library.dto.ObjectWithIdDTO;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_NULL)
@Data
@NoArgsConstructor
public class PersonDTO {
    
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", locale = "pt-BR", timezone = "America/Sao_Paulo")
	private Date birth;

    private String email;

    private String name;

    private String cpf;

    private String city;

    private String state;

    private String countrie;

    private AddressDTO address;

    private ObjectWithIdDTO occupation;

    @Builder(builderMethodName = "builder")
    public PersonDTO(Long id, Date birth, String email, String name, String cpf, String city, String state,
            String countrie, AddressDTO address, ObjectWithIdDTO occupation) {
        this.id = id;
        this.birth = birth;
        this.email = email;
        this.name = name;
        this.cpf = cpf;
        this.city = city;
        this.state = state;
        this.countrie = countrie;
        this.address = address;
        this.occupation = occupation;
    }

    public static PersonDTO create(Person object) {
        return new ModelMapper().map(object, PersonDTO.class);
    }
}
