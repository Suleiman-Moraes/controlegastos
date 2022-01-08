package br.com.smtech.controlegastos.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.smtech.controlegastos.api.dto.CategoryFilterDTO;
import br.com.smtech.controlegastos.api.dto.CategoryListDTO;
import br.com.smtech.controlegastos.api.model.Category;
import br.com.smtech.controlegastos.api.repository.CategoryRepository;
import br.com.smtech.controlegastos.api.service.CategoryService;
import br.com.smtech.controlegastos.api.service.UserService;
import br.com.smtech.controlegastos.library.service.CrudPatternServiceImpl;

@Service
public class CategoryServiceImpl extends CrudPatternServiceImpl<Category> implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private UserService userService;

    @Override
    public Page<CategoryListDTO> findByFilter(CategoryFilterDTO filter) {
        filter.setUserId(userService.findByToken().getId());
        return repository.findByFilter(filter);
    }
}
