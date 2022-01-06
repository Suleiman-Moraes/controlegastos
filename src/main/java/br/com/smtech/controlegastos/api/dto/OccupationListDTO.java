package br.com.smtech.controlegastos.api.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_NULL)
@Data
@NoArgsConstructor
public class OccupationListDTO implements Serializable {

    public static final String OCCUPATION_LIST_DTO_MAPPING = "OccupationListDTOMapping";

    private Long id;

    private String name;

    private String description;

    private Boolean enabled;

    //OccupationListDTOMapping
    public OccupationListDTO(Long id, String name, String description, Boolean enabled) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.enabled = enabled;
    }

    public String getEnabledDesc() {
        return enabled != null && enabled ? "Ativo" : "Inativo";
    }
}
