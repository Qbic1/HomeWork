package ru.homework.dao;

import ru.homework.models.User;

import java.util.List;

public interface UserDao
{
    boolean find(String name, String password);

    void save(User user);

    List<User> findAll();
}
