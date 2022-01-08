package br.com.smtech.controlegastos.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import br.com.smtech.controlegastos.api.model.User;
import br.com.smtech.controlegastos.api.service.UserService;
import br.com.smtech.controlegastos.library.exception.UserInactiveException;
import br.com.smtech.controlegastos.library.exception.UsernameOrPasswordNotFoundException;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameOrPasswordNotFoundException {
        Optional<? extends User> userOp = service.findTopByUsername(username);
        User user = userOp.orElseThrow(() -> new UsernameOrPasswordNotFoundException());
        if(!user.isEnabled()){
            throw new UserInactiveException();
        }
        return new UserSystem(user, user.getAuthorities());
    }
}
