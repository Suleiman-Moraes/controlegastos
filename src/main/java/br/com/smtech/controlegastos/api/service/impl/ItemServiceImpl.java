package br.com.smtech.controlegastos.api.service.impl;

import org.springframework.stereotype.Service;

import br.com.smtech.controlegastos.api.model.Item;
import br.com.smtech.controlegastos.api.service.ItemService;
import br.com.smtech.controlegastos.library.service.CrudPatternServiceImpl;

@Service
public class ItemServiceImpl extends CrudPatternServiceImpl<Item> implements ItemService{
    
}
