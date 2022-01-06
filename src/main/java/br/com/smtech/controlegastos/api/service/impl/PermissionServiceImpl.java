package br.com.smtech.controlegastos.api.service.impl;

import org.springframework.stereotype.Service;

import br.com.smtech.controlegastos.api.model.Permission;
import br.com.smtech.controlegastos.api.service.PermissionService;
import br.com.smtech.controlegastos.library.service.CrudPatternServiceImpl;

@Service
public class PermissionServiceImpl extends CrudPatternServiceImpl<Permission> implements PermissionService{
    
}
