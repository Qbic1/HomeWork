package ru.homework.products;

public class ProductImpl implements Product
{
    private String name;
    private Double cost;
    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setCost(Double cost)
    {
        this.cost = cost;
    }

    public Double getCost()
    {
        return this.cost;
    }

    public ProductImpl(String name, Double cost)
    {
        this.name = name;
        this.cost = cost;
    }
}
