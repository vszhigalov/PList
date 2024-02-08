package com.plist.repository;

import com.plist.entity.Product;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ProductRepository extends CommonRepository<Product>{
}
