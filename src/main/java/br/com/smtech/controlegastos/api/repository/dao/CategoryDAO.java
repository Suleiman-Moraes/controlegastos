package br.com.smtech.controlegastos.api.repository.dao;

import org.springframework.data.domain.Page;

import br.com.smtech.controlegastos.api.dto.CategoryFilterDTO;
import br.com.smtech.controlegastos.api.dto.CategoryListDTO;

public interface CategoryDAO {
    
    Page<CategoryListDTO> findByFilter(CategoryFilterDTO filter);
}
