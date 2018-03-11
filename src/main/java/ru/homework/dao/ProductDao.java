package ru.homework.dao;

import ru.homework.products.Product;
import ru.homework.products.ProductImpl;

import java.util.List;

public interface ProductDao
{
    Product find(String name);
    int save(Product product);
    void update(Product product);
    void delete(String name);
    List<Product> findAll();
}
