package br.com.smtech.controlegastos.api.service;

import org.springframework.data.domain.Page;

import br.com.smtech.controlegastos.api.dto.ItemFilterDTO;
import br.com.smtech.controlegastos.api.dto.ItemListDTO;
import br.com.smtech.controlegastos.api.model.Item;
import br.com.smtech.controlegastos.library.interfaces.ICrudPatternService;

public interface ItemService extends ICrudPatternService<Item>{
    
    Page<ItemListDTO> findByFilter(ItemFilterDTO filter);
}
