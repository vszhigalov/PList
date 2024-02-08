package com.plist.repository;

import com.plist.entity.Dish;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface DishRepository extends CommonRepository<Dish>{

    Dish getDishByNameAndChatId(String name, long chatId);
}
