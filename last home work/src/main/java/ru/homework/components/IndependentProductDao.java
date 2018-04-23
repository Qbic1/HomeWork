package ru.homework.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.homework.dao.ProductDao;
import ru.homework.models.Product;

import java.util.List;

@Component
public class IndependentProductDao
{
    @Autowired
    private ProductDao productDao;

    public Product find(String name)
    {
        return productDao.find(name);
    }

    public void save(Product product)
    {
        productDao.save(product);
    }

    public void update(Product product)
    {
        productDao.update(product);
    }

    public void delete(String name)
    {
        productDao.delete(name);
    }

    public List<Product> findAll()
    {
        return productDao.findAll();
    }
}
