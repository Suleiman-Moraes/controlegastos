package br.com.smtech.controlegastos.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.smtech.controlegastos.api.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

    Optional<Person> findTopByUserId(Long userId);
}
