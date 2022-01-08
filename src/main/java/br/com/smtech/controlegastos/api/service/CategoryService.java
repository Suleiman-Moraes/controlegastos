package br.com.smtech.controlegastos.api.service;

import org.springframework.data.domain.Page;

import br.com.smtech.controlegastos.api.dto.CategoryFilterDTO;
import br.com.smtech.controlegastos.api.dto.CategoryListDTO;
import br.com.smtech.controlegastos.api.model.Category;
import br.com.smtech.controlegastos.library.interfaces.ICrudPatternService;

public interface CategoryService extends ICrudPatternService<Category>{

    Page<CategoryListDTO> findByFilter(CategoryFilterDTO filter);
}
