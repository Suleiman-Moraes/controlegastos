package br.com.smtech.controlegastos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.smtech.controlegastos.api.model.Release;

public interface ReleaseRepository extends JpaRepository<Release, Long>{
    
}
