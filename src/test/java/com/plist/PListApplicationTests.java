package com.plist;

import com.plist.entity.Dish;
import com.plist.entity.Product;
import com.plist.service.DishService;
import com.plist.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PListApplicationTests {
    @Autowired
    DishService dishService;
    @Autowired
    ProductService productService;

    @Test
    void contextLoads() {
        Product product = new Product("U", 1.0, 0, 1);
        productService.save(product);
    }

}
