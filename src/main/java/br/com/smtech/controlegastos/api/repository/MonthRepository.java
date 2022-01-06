package br.com.smtech.controlegastos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.smtech.controlegastos.api.enums.MonthEnum;
import br.com.smtech.controlegastos.api.model.Month;
import br.com.smtech.controlegastos.api.repository.dao.MonthDAO;

public interface MonthRepository extends JpaRepository<Month, Long>, MonthDAO{

    boolean existsByIdNotAndMonthAndYearAndUserId(Long id, MonthEnum month, Integer year, Long userId);
}
