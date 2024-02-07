package com.plist.service;

import com.plist.entity.AbstractEntity;
import com.plist.repository.CommonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class AbstractService <E extends AbstractEntity, R extends CommonRepository<E>>
        implements CommonService<E> {

    protected final R repository;

    @Autowired
    public AbstractService(R repository) {
        this.repository = repository;
    }

    @Override
    public E save(E entity) {
        return repository.save(entity);
    }

    @Override
    public List<E> getAll() {
        List<E> entities = new ArrayList<>();
        repository.findAll().iterator().forEachRemaining(entities::add);
        return entities;
    }

    @Override
    public E getById(long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public void delete(E entity) {
        repository.delete(entity);
    }
}
