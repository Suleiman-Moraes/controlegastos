package br.com.smtech.controlegastos.api.service;

import java.util.Optional;

import br.com.smtech.controlegastos.api.model.User;
import br.com.smtech.controlegastos.library.dto.ObjectWithIdDTO;
import br.com.smtech.controlegastos.library.interfaces.ICrudPatternService;

public interface UserService extends ICrudPatternService<User>{
    
    ObjectWithIdDTO findByToken();

    Optional<User> findTopByUsername(String username);
}
