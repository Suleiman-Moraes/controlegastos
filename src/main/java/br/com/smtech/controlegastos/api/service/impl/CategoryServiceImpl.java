package br.com.smtech.controlegastos.api.service.impl;

import org.springframework.stereotype.Service;

import br.com.smtech.controlegastos.api.model.Category;
import br.com.smtech.controlegastos.api.service.CategoryService;
import br.com.smtech.controlegastos.library.service.CrudPatternServiceImpl;

@Service
public class CategoryServiceImpl extends CrudPatternServiceImpl<Category> implements CategoryService{
    
}
