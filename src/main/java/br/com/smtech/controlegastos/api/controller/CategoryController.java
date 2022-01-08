package br.com.smtech.controlegastos.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.smtech.controlegastos.api.dto.CategoryFilterDTO;
import br.com.smtech.controlegastos.api.dto.CategoryListDTO;
import br.com.smtech.controlegastos.api.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping("/filter")
    public ResponseEntity<Page<CategoryListDTO>> findByFilter(HttpServletRequest request,
            CategoryFilterDTO filter) {
        return ResponseEntity.ok(service.findByFilter(filter));
    }
}
