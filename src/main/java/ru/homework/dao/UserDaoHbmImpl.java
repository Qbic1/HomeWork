package ru.homework.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.mindrot.jbcrypt.BCrypt;
import ru.homework.models.User;

import java.util.List;

public class UserDaoHbmImpl implements UserDao
{
    Configuration configuration = new Configuration();
    Session session;

    public UserDaoHbmImpl()
    {
        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/products");
        configuration.setProperty("hibernate.connection.username", "postgres");
        configuration.setProperty("hibernate.connection.password", "");
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        configuration.addResource("User.hbm.xml");
        configuration.setProperty("hibernate.show_sql", "true");
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        session = sessionFactory.openSession();
    }

    @Override
    public boolean find(String name, String password)
    {
        User user = session.createQuery("from User user where user.name = name and user.password = password", User.class).getSingleResult();
        return user != null ? true : false;
    }

    @Override
    public void save(User user)
    {
        session.beginTransaction();
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        session.save(user);
        session.getTransaction().commit();
    }

    @Override
    public List<User> findAll()
    {
        return session.createQuery("from User user", User.class).getResultList();
    }
}
