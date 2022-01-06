package br.com.smtech.controlegastos.api.service.impl;

import org.springframework.stereotype.Service;

import br.com.smtech.controlegastos.api.model.Expected;
import br.com.smtech.controlegastos.api.service.ExpectedService;
import br.com.smtech.controlegastos.library.service.CrudPatternServiceImpl;

@Service
public class ExpectedServiceImpl extends CrudPatternServiceImpl<Expected> implements ExpectedService{
    
}
