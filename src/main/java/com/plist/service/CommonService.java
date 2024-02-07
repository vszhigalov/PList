package com.plist.service;

import com.plist.entity.AbstractEntity;

import java.util.List;

public interface CommonService <E extends AbstractEntity>{

    E save (E entity);

    List<E> getAll();

    E getById(long id);

    void delete(E entity);

}
