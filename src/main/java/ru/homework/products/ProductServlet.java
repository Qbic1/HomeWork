package ru.homework.products;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ProductServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        /*PrintWriter printWriter = response.getWriter();
        ArrayList<Product> products = new ArrayList<Product>();
        products.add(new ProductImpl("Notebook", 25675.22));
        products.add(new ProductImpl("IPhone", 89999.99));

        printWriter.write("<h1>List of Goods</h1>");
        printWriter.write("<table frame=\"border\">");
        printWriter.write("<tr><td><b>Name</b></td><td><b>Cost</b></td></tr>");
        for (Product product:products)
        {
            printWriter.write("<tr><td>"+product.getName()+"</td><td>"+product.getCost()+"</td></tr>");
        }
        printWriter.write("</table>");*/
        getServletContext().getRequestDispatcher("/products.jsp").forward(request,response);
    }
}
