package br.com.smtech.controlegastos.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.smtech.controlegastos.api.dto.ItemFilterDTO;
import br.com.smtech.controlegastos.api.dto.ItemListDTO;
import br.com.smtech.controlegastos.api.service.ItemService;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private ItemService service;

    @GetMapping("/filter")
    public ResponseEntity<Page<ItemListDTO>> findByFilter(HttpServletRequest request,
            ItemFilterDTO filter) {
        return ResponseEntity.ok(service.findByFilter(filter));
    }
}
