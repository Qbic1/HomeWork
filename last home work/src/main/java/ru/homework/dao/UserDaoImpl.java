package ru.homework.dao;

import org.mindrot.jbcrypt.BCrypt;
import ru.homework.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoImpl implements UserDao
{
    private Connection connection;

    //language=SQL
    private final String SQL_FIND = "SELECT * FROM client WHERE name=?";
    private final String SQL_SAVE = "INSERT INTO client (name, hashed_password) VALUES (?,?)";
    private final String SQL_FINDALL = "SELECT * FROM client";

    public UserDaoImpl(DataSource dataSource)
    {
        try
        {
            connection = dataSource.getConnection();
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
            ResultSet resultSet = statement.executeQuery(SQL_FINDALL);

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
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND);
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
    public void save(User user)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            preparedStatement.executeUpdate();
        } catch (SQLException e)
        {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
