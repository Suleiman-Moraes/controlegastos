package br.com.smtech.controlegastos.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.smtech.controlegastos.library.exception.IdNotInformedException;
import br.com.smtech.controlegastos.library.exception.ObjectNotFoundException;
import br.com.smtech.controlegastos.library.interfaces.ICrudPatternService;
import br.com.smtech.controlegastos.library.model.Model;

/**
 * CrudPadraoServiceImpl
 */
public abstract class CrudPatternServiceImpl<T> implements ICrudPatternService<T> {

    @Autowired
    protected JpaRepository<T, Long> repository;

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<T> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public T save(T objeto) {
        return repository.save(objeto);
    }

    @Override
    public T create(T objeto) {
        if (objeto instanceof Model) {
            ((Model) objeto).setId(null);
        }
        return save(objeto);
    }

    @Override
    public T update(T objeto, Long id) throws Exception {
        if (!validId(id)) {
            throw new ObjectNotFoundException();
        }
        objeto.getClass().getMethod("setId", Long.class).invoke(objeto, id);
        return save(objeto);
    }

    @Override
    public void delete(Long id) {
        if (!validId(id)) {
            throw new ObjectNotFoundException();
        }
        repository.deleteById(id);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        if (pageable == null) {
            pageable = PageRequest.of(0, 10, Sort.by(Direction.DESC, "id"));
        }
        return repository.findAll(pageable);
    }

    @Override
    public Boolean validId(Long id) {
        if (id == null || id <= 0) {
            throw new IdNotInformedException();
        }
        return repository.existsById(id);
    }
}