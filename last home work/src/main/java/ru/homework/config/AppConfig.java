package ru.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.homework.dao.ProductDao;
import ru.homework.dao.ProductDaoJdbcTemplateImpl;
import ru.homework.dao.UserDao;
import ru.homework.dao.UserDaoImpl;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "ru.homework")
public class AppConfig
{
    DataSource dataSource = DataSourceConfig.dataSource();

    @Bean
    public ProductDao productDao()
    {
        return new ProductDaoJdbcTemplateImpl(dataSource);
    }

    @Bean
    public UserDao userDao()
    {
        return new UserDaoImpl(dataSource);
    }

}
