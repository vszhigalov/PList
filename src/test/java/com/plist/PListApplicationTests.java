package com.plist;

import com.plist.entity.Dish;
import com.plist.entity.Product;
import com.plist.service.DishService;
import com.plist.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PListApplicationTests {
    @Autowired
    DishService dishService;
    @Autowired
    ProductService productService;

    @Test
    void contextLoads() {
        Dish dish1 =  dishService.getDishByNameAndChatId("Dish 1",402844728);
        Dish dish2 =  dishService.getDishByNameAndChatId("Dish 2",402844728);
        List<Product> products = productService.getCommonProductsForDishes(List.of(dish1, dish2));
        products.forEach(System.out::println);
    }

}
