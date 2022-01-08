package br.com.smtech.controlegastos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.smtech.controlegastos.api.model.Category;
import br.com.smtech.controlegastos.api.repository.dao.CategoryDAO;

public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryDAO{
    
}
