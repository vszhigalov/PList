package com.plist.service;

import com.plist.entity.Dish;
import com.plist.entity.Product;
import com.plist.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService extends AbstractService<Product, ProductRepository>{

    @Autowired
    public ProductService(ProductRepository repository) {
        super(repository);
    }

    public List<Product> getCommonProductsForDishes(List<Dish> dishList){
        List<Long> dishIdList = new ArrayList<>();
        dishList.forEach(x -> dishIdList.add(x.getId()));
        return repository.getProductsWeightAmountByDishIds(dishIdList);
    }
}
