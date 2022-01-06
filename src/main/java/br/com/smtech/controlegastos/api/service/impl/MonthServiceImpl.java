package br.com.smtech.controlegastos.api.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.smtech.controlegastos.api.dto.MonthDTO;
import br.com.smtech.controlegastos.api.dto.MonthNewDTO;
import br.com.smtech.controlegastos.api.enums.MonthEnum;
import br.com.smtech.controlegastos.api.model.Month;
import br.com.smtech.controlegastos.api.model.User;
import br.com.smtech.controlegastos.api.repository.MonthRepository;
import br.com.smtech.controlegastos.api.service.MonthService;
import br.com.smtech.controlegastos.api.service.UserService;
import br.com.smtech.controlegastos.library.dto.FilterBasicDto;
import br.com.smtech.controlegastos.library.exception.ValidException;
import br.com.smtech.controlegastos.library.service.CrudPatternServiceImpl;

@Service
public class MonthServiceImpl extends CrudPatternServiceImpl<Month> implements MonthService {

    @Autowired
    private MonthRepository repository;

    @Autowired
    private UserService userService;

    @Override
    public Page<MonthDTO> findByFilter(FilterBasicDto filter) {
        return repository.findByFilter(filter);
    }

    @Override
    public Long newObject(MonthNewDTO dto) {
        final Calendar calendar = Calendar.getInstance();
        Month month = new Month(
                dto.getMonth() == null ? MonthEnum.getByValue(calendar.get(Calendar.MONTH)) : dto.getMonth(),
                dto.getYear() == null ? calendar.get(Calendar.YEAR) : dto.getYear(),
                new User(userService.findByToken().getId()));
        checkDuplicates(0l, month.getMonth(), month.getYear(), month.getUser().getId());
        month = save(month);
        return month.getId();
    }

    private void checkDuplicates(Long id, MonthEnum month, Integer year, Long userId) {
        if (repository.existsByIdNotAndMonthAndYearAndUserId(id, month, year, userId)) {
            throw new ValidException("Planilha já cadastrada.");
        }
    }
}
