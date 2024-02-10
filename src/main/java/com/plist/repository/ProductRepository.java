package com.plist.repository;

import com.plist.entity.Dish;
import com.plist.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends CommonRepository<Product>{

    @Query("select new com.plist.entity.Product(CAST(SUM(p.weight) AS DOUBLE), p.name) " +
            "from Product p " +
            "where p.dishId in :dishIdList " +
            "group by p.name")
    List<Product> getProductsWeightAmountByDishIds(@Param("dishIdList") List<Long> dishIdList);
}
