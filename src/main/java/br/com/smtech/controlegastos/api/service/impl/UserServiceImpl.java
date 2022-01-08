package br.com.smtech.controlegastos.api.service.impl;

import java.util.Optional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.smtech.controlegastos.api.model.User;
import br.com.smtech.controlegastos.api.repository.UserRepository;
import br.com.smtech.controlegastos.api.service.PermissionService;
import br.com.smtech.controlegastos.api.service.PersonService;
import br.com.smtech.controlegastos.api.service.UserService;
import br.com.smtech.controlegastos.library.dto.ObjectWithIdDTO;
import br.com.smtech.controlegastos.library.exception.ValidException;
import br.com.smtech.controlegastos.library.service.CrudPatternServiceImpl;
import br.com.smtech.controlegastos.library.util.ConstantUtil;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UserServiceImpl extends CrudPatternServiceImpl<User> implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PersonService personService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public ObjectWithIdDTO findByToken() {
        try {
            final SecurityContext context = SecurityContextHolder.getContext();
            if (context != null) {
                final Authentication authentication = context.getAuthentication();
                if (authentication != null) {
                    final Long id = repository.findIdByUsername(authentication.getName());
                    return new ObjectWithIdDTO(id);
                }
            }
        } catch (Exception e) {
            log.warn("findByToken " + ExceptionUtils.getRootCauseMessage(e));
        }
        return null;
    }

    @Transactional
    @Override
    public User create(User objeto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        objeto.setId(null);
        objeto.setPassword(passwordEncoder.encode(objeto.getPassword()));
        objeto.setPerson(personService.create(objeto.getPerson()));
        return save(objeto);
    }

    @Override
    public User save(User objeto) {
        objeto.getPerson().setUser(null);
        if (repository.existsByUsernameAndIdNot(objeto.getUsername(), objeto.getId() == null ? 0l : objeto.getId())) {
            throw new ValidException(ConstantUtil.USERNAME_UNIQUE);
        }
        // if (!objeto.getUsername().equals("root")) {
        //     final List<Permission> permissions = objeto.getPerson().getOccupation() != null
        //             ? permissionService.findByOccupationsId(objeto.getPerson().getOccupation().getId())
        //             : null;
        //     objeto.setAuthorities(permissions);
        // }
        objeto = repository.save(objeto);
        return objeto;
    }

    @Override
    public Optional<User> findTopByUsername(String username) {
        return repository.findTopByUsername(username);
    }
}
