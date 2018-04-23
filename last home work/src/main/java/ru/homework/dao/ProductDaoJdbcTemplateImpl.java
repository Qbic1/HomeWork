package ru.homework.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.homework.models.Product;
import ru.homework.models.ProductImpl;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbcTemplateImpl implements ProductDao
{
    private JdbcTemplate template;

    //language=SQL
    private final String SQL_FIND = "SELECT * FROM product WHERE name=?";
    private final String SQL_UPDATE = "UPDATE product SET cost=? WHERE name=?";
    private final String SQL_DELETE = "DELETE FROM product WHERE name=?";
    private final String SQL_SAVE = "INSERT INTO product (name, cost) VALUES (?,?)";
    private final String SQL_FINDALL = "SELECT * FROM product";

    private List<Product> products = new ArrayList<>();

    public ProductDaoJdbcTemplateImpl(DataSource dataSource)
    {
        this.template = new JdbcTemplate(dataSource);
    }

    private RowMapper<Product> productRowMapper
            = (ResultSet resultSet, int i) -> {
        String name = resultSet.getString("name");
        Double cost = resultSet.getDouble("cost");
        Product product = new ProductImpl(name, cost);

        if (!products.contains(product))
        {
            products.add(product);
        }

        return products.get(products.indexOf(product));
    };

    public Product find(String name)
    {
        try
        {
            return template.queryForObject(SQL_FIND, productRowMapper, name);
        } catch (Exception e)
        {
            throw new IllegalStateException();
        }
    }

    public void save(Product product)
    {
        template.update(SQL_SAVE, product);
    }

    public void update(Product product)
    {
        template.update(SQL_UPDATE, product);
    }

    public void delete(String name)
    {
        template.update(SQL_DELETE, name);
    }

    public List<Product> findAll()
    {
        return template.query(SQL_FINDALL, productRowMapper);
    }
}
