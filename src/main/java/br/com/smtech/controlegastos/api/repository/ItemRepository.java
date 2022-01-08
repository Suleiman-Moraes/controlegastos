package br.com.smtech.controlegastos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.smtech.controlegastos.api.model.Item;
import br.com.smtech.controlegastos.api.repository.dao.ItemDAO;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemDAO {

}
