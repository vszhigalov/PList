package com.plist.service;

import com.plist.entity.Product;
import com.plist.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends AbstractService<Product, ProductRepository>{

    @Autowired
    public ProductService(ProductRepository repository) {
        super(repository);
    }
}
