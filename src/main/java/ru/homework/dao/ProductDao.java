package ru.homework.dao;

import ru.homework.products.Product;
import ru.homework.products.ProductImpl;

import java.util.List;

public interface ProductDao
{
    Product find(String name);
    int save(Product product);
    int update(Product product);
    int delete(String name);
    List<Product> findAll();
}
