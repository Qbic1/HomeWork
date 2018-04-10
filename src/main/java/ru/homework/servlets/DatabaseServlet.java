package ru.homework.servlets;

import ru.homework.dao.ProductDao;
import ru.homework.dao.ProductDaoJdbcTemplateImpl;
import ru.homework.models.Product;
import ru.homework.models.ProductImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/database")
public class DatabaseServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        getServletContext().getRequestDispatcher("/jsp/database.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        ProductDao productDao = new ProductDaoJdbcTemplateImpl(SignInServlet.dataSource);

        String name = request.getParameter("name");
        Double cost;
        try
        {
            cost = Double.parseDouble(request.getParameter("cost"));
        } catch (NumberFormatException e)
        {
            cost = 0.0;
        }
        Product product;
        switch (request.getParameter("type").charAt(0))
        {
            case 'F':
                product = productDao.find(name);
                if (product != null)
                    response.getWriter().println("<h1>" + product.getName() + " " + product.getCost() + "</h1>");
                else
                    response.getWriter().println("<h1>We can't find product with name " + name + "</h1>");
                break;
            case 'S':
                product = new ProductImpl(name, cost);
                if (productDao.find(name) == null)
                    response.getWriter().println("<h1>Product with name " + product.getName() + " and cost " + product.getCost() + " was saved.</h1>");
                else
                    response.getWriter().println("<h1>Product with name " + product.getName() + " already exists.</h1>");
                break;
            case 'U':
                product = new ProductImpl(name, cost);
                if (productDao.find(name) != null)
                    response.getWriter().println("<h1>Cost for product with name " + product.getName() + " was updated to " + product.getCost() + ".</h1>");
                else
                    response.getWriter().println("<h1>Product with name " + product.getName() + " not exists.</h1>");
                break;
            case 'D':
                if (productDao.find(name) != null)
                    response.getWriter().println("<h1>Product with name " + name + " was deleted.</h1>");
                else
                    response.getWriter().println("<h1>Product with name " + name + " not exists.</h1>");
                break;

        }
    }
}
