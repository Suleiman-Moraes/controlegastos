package br.com.smtech.controlegastos.api.service.impl;

import org.springframework.stereotype.Service;

import br.com.smtech.controlegastos.api.model.Release;
import br.com.smtech.controlegastos.api.service.ReleaseService;
import br.com.smtech.controlegastos.library.service.CrudPatternServiceImpl;

@Service
public class ReleaseServiceImpl extends CrudPatternServiceImpl<Release> implements ReleaseService{
    
}
