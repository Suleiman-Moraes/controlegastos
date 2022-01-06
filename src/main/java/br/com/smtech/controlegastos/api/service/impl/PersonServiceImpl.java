package br.com.smtech.controlegastos.api.service.impl;

import org.springframework.stereotype.Service;

import br.com.smtech.controlegastos.api.model.Person;
import br.com.smtech.controlegastos.api.service.PersonService;
import br.com.smtech.controlegastos.library.service.CrudPatternServiceImpl;

@Service
public class PersonServiceImpl extends CrudPatternServiceImpl<Person> implements PersonService{
    
}
