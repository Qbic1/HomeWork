package ru.homework.dao;

import ru.homework.products.Product;
import ru.homework.products.ProductImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDaoImpl implements ProductDao
{
    private Connection connection;
    private String name="postgres";
    private String pass="";
    private String url="jdbc:postgresql://localhost:5432/products";


    public ProductDaoImpl()
    {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, name, pass);
        }
        catch (Exception e)
        {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public Product find(String name)
    {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from products WHERE name=?");
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            Product product=null;
            while (resultSet.next()) {
                product = new ProductImpl(resultSet.getString(1), resultSet.getDouble(2));
            }
            return product;
        }
        catch (SQLException e)
        {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    public int save(Product product)
    {
        try{
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO products (name, cost) " +
                    "VALUES (?,?);");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getCost());
            preparedStatement.executeUpdate();
            return 1;
        }
        catch (SQLException e)
        {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return 0;
        }
    }

    public int update(Product product)
    {
        try{
            PreparedStatement preparedStatement=connection.prepareStatement("UPDATE products SET cost=? WHERE name=?");
            preparedStatement.setString(2, product.getName());
            preparedStatement.setDouble(1, product.getCost());
            preparedStatement.executeUpdate();
            return 1;
        }
        catch (SQLException e)
        {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return 0;
        }
    }

    public int delete(String name)
    {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM products WHERE name=?");
            preparedStatement.setString(1,name);
            preparedStatement.executeUpdate();
            return 1;
        }
        catch (SQLException e)
        {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return 0;
        }
    }

    public List<Product> findAll()
    {
        try
        {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM products");

            String name;
            Double cost;
            List<Product> products=new ArrayList<Product>();

            while(resultSet.next())
            {
                name=resultSet.getString(1);
                cost=resultSet.getDouble(2);

                ProductImpl product=new ProductImpl(name, cost);
                products.add(product);
            }
            return products;
        }
        catch (SQLException e) {
            Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
}
