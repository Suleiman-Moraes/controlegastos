package br.com.smtech.controlegastos.api.service.impl;

import org.springframework.stereotype.Service;

import br.com.smtech.controlegastos.api.model.Address;
import br.com.smtech.controlegastos.api.service.AddressService;
import br.com.smtech.controlegastos.library.service.CrudPatternServiceImpl;

@Service
public class AddressServiceImpl extends CrudPatternServiceImpl<Address> implements AddressService{
    
}
