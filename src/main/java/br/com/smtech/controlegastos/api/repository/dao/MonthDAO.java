package br.com.smtech.controlegastos.api.repository.dao;

import org.springframework.data.domain.Page;

import br.com.smtech.controlegastos.api.dto.MonthDTO;
import br.com.smtech.controlegastos.library.dto.FilterBasicDto;

public interface MonthDAO {
    
    Page<MonthDTO> findByFilter(FilterBasicDto filter);
}
