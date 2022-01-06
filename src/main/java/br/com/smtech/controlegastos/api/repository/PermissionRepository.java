package br.com.smtech.controlegastos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.smtech.controlegastos.api.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long>{
    
}
