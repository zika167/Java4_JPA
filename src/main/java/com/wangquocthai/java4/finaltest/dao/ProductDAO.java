package com.wangquocthai.java4.finaltest.dao;

import com.wangquocthai.java4.finaltest.entity.Product;
import java.util.List;

public interface ProductDAO {
    List<Product> findAll();
    Product findById(Integer id);
    void create(Product product);
    void update(Product product);
    void deleteById(Integer id);
}
