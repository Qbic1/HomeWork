package ru.homework.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.homework.dao.UserDao;
import ru.homework.models.User;

import java.util.List;

@Component
public class IndependedUserDao
{
    @Autowired
    private UserDao userDao;

    public boolean find(String name, String password)
    {
        return userDao.find(name, password);
    }

    public void save(User user)
    {
        userDao.save(user);
    }

    public List<User> findAll()
    {
        return userDao.findAll();
    }
}
