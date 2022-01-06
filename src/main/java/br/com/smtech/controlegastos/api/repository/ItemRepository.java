package br.com.smtech.controlegastos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.smtech.controlegastos.api.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{
    
}
