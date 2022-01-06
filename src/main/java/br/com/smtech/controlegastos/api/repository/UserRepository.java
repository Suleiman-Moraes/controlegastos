package br.com.smtech.controlegastos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.smtech.controlegastos.api.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
