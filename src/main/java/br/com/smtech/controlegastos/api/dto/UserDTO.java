package br.com.smtech.controlegastos.api.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.modelmapper.ModelMapper;

import br.com.smtech.controlegastos.api.model.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_NULL)
@Data
@NoArgsConstructor
public class UserDTO implements Serializable {
    
    private Long id;

    private String username;

    private String password;

    private String passwordNew;

    private PersonDTO person;

    @Builder(builderMethodName = "builder")
    public UserDTO(Long id, String username, String password, PersonDTO person) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.person = person;
    }

    public static UserDTO create(User object) {
        return new ModelMapper().map(object, UserDTO.class);
    }

    public UserDTO resetPassword() {
        this.password = null;
        return this;
    }
}
