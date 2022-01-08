package br.com.smtech.controlegastos.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.smtech.controlegastos.api.dto.ItemFilterDTO;
import br.com.smtech.controlegastos.api.dto.ItemListDTO;
import br.com.smtech.controlegastos.api.model.Item;
import br.com.smtech.controlegastos.api.repository.ItemRepository;
import br.com.smtech.controlegastos.api.service.ItemService;
import br.com.smtech.controlegastos.api.service.UserService;
import br.com.smtech.controlegastos.library.service.CrudPatternServiceImpl;

@Service
public class ItemServiceImpl extends CrudPatternServiceImpl<Item> implements ItemService {

    @Autowired
    private ItemRepository repository;

    @Autowired
    private UserService userService;

    @Override
    public Page<ItemListDTO> findByFilter(ItemFilterDTO filter) {
        filter.setUserId(userService.findByToken().getId());
        return repository.findByFilter(filter);
    }
}
