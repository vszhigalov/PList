package com.plist.service;

import com.plist.entity.Dish;
import com.plist.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishService extends AbstractService<Dish, DishRepository>{

    @Autowired
    public DishService(DishRepository repository) {
        super(repository);
    }
}
