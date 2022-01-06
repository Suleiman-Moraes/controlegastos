package br.com.smtech.controlegastos.api.service;

import org.springframework.data.domain.Page;

import br.com.smtech.controlegastos.api.dto.MonthDTO;
import br.com.smtech.controlegastos.api.dto.MonthNewDTO;
import br.com.smtech.controlegastos.api.model.Month;
import br.com.smtech.controlegastos.library.dto.FilterBasicDto;
import br.com.smtech.controlegastos.library.interfaces.ICrudPatternService;

public interface MonthService extends ICrudPatternService<Month>{
    
    Page<MonthDTO> findByFilter(FilterBasicDto filter);

    Long newObject(MonthNewDTO dto);
}
