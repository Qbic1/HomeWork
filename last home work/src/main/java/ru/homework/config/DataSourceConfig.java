package ru.homework.config;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.homework.dao.UserDaoImpl;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DataSourceConfig
{
    public static DataSource dataSource()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        Properties properties = new Properties();

        try
        {
            properties.load(new FileInputStream("db.properties"));
            String dbUrl = properties.getProperty("db.url");
            String dbUsername = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");
            String driverClassName = properties.getProperty("db.driverClassName");

            dataSource.setUsername(dbUsername);
            dataSource.setPassword(dbPassword);
            dataSource.setUrl(dbUrl);
            dataSource.setDriverClassName(driverClassName);

            return dataSource;
        } catch (IOException e)
        {
            throw new IllegalArgumentException(e);
        }
    }
}
