package br.com.smtech.controlegastos.api.service.impl;

import org.springframework.stereotype.Service;

import br.com.smtech.controlegastos.api.model.User;
import br.com.smtech.controlegastos.api.service.UserService;
import br.com.smtech.controlegastos.library.dto.ObjectWithIdDTO;
import br.com.smtech.controlegastos.library.service.CrudPatternServiceImpl;

@Service
public class UserServiceImpl extends CrudPatternServiceImpl<User> implements UserService {

    @Override
    public ObjectWithIdDTO findByToken() {
        // TODO Auto-generated method stub
        return new ObjectWithIdDTO(1l);
    }
}
