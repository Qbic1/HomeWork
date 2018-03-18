package ru.homework.servlets;

import ru.homework.dao.ProductDao;
import ru.homework.dao.ProductDaoImpl;
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        ProductDao productDao = new ProductDaoImpl();
        List<Product> products = productDao.findAll();

        request.setAttribute("productsFromServer", products);

        getServletContext().getRequestDispatcher("/jsp/products.jsp").forward(request, response);
    }
}
