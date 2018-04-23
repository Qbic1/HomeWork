package ru.homework.dao;

import org.springframework.stereotype.Component;
import ru.homework.models.Product;

import java.util.List;

public interface ProductDao
{
    Product find(String name);

    void save(Product product);

    void update(Product product);

    void delete(String name);

    List<Product> findAll();
}
