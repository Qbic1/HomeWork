package ru.homework.servlets;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.homework.components.IndependentProductDao;
import ru.homework.config.AppConfig;
import ru.homework.dao.ProductDao;
import ru.homework.dao.ProductDaoJdbcTemplateImpl;
import ru.homework.models.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet
{
    IndependentProductDao productDao;

    @Override
    public void init() throws ServletException
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        productDao = context.getBean(IndependentProductDao.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        List<Product> products = productDao.findAll();

        request.setAttribute("productsFromServer", products);

        getServletContext().getRequestDispatcher("/jsp/products.jsp").forward(request, response);
    }
}
