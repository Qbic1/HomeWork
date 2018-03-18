package ru.homework.dao;

import org.mindrot.jbcrypt.BCrypt;
import ru.homework.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoImpl implements UserDao
{
    private Connection connection;
    private String name = "postgres";
    private String pass = "";
    private String url = "jdbc:postgresql://localhost:5432/products";

    public UserDaoImpl()
    {
        try
        {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, name, pass);
        } catch (Exception e)
        {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public List<User> findAll()
    {
        try
        {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM client");

            String name;
            String password;
            List<User> users = new ArrayList<User>();

            while (resultSet.next())
            {
                name = resultSet.getString("name");
                password = resultSet.getString("hashed_password");

                User user = new User(name, password);
                users.add(user);
            }
            return users;
        } catch (SQLException e)
        {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    @Override
    public boolean find(String name, String password)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM client WHERE name=?");
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                if (BCrypt.checkpw(password, resultSet.getString("hashed_password")))
                    return true;
            }
            return false;
        } catch (SQLException e)
        {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    @Override
    public int save(User user)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO client (name, hashed_password) " +
                    "VALUES (?,?);");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            preparedStatement.executeUpdate();
            return 1;
        } catch (SQLException e)
        {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return 0;
        }
    }
}
