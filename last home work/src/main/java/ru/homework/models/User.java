package ru.homework.models;

public class User
{
    Integer id;
    String name;
    String password;

    public User()
    {

    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {

        return id;
    }

    public User(Integer id, String name, String password)

    {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public User(String name)
    {
        this.name = name;
    }

    public User(String name, String password)
    {
        this.name = name;
        this.password = password;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
