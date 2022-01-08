package br.com.smtech.controlegastos.api.repository.dao;

import org.springframework.data.domain.Page;

import br.com.smtech.controlegastos.api.dto.ItemFilterDTO;
import br.com.smtech.controlegastos.api.dto.ItemListDTO;

public interface ItemDAO {
    
    Page<ItemListDTO> findByFilter(ItemFilterDTO filter);
}
