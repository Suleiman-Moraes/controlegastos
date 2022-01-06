package br.com.smtech.controlegastos.api.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_NULL)
@Data
@NoArgsConstructor
public class UserListDTO implements Serializable {

    public static final String USER_LIST_DTO_MAPPING = "UserListDTOMapping";

    private Long id;

    private String name;

    private String username;

    private String cpf;

    private Boolean enabled;

    //UserListDTOMapping
    public UserListDTO(Long id, String name, String username, String cpf, Boolean enabled) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.cpf = cpf;
        this.enabled = enabled;
    }

    public String getEnabledDesc(){
        return enabled != null && enabled ? "Ativo" : "Inativo";
    }
}
