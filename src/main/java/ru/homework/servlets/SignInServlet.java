package ru.homework.servlets;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.homework.dao.UserDao;
import ru.homework.dao.UserDaoHbmImpl;
import ru.homework.dao.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.Properties;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet
{
    private UserDao userDao;

    public static DriverManagerDataSource dataSource = new DriverManagerDataSource();

    @Override
    public void init() throws ServletException
    {
        Properties properties = new Properties();

        try
        {
            properties.load(new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/db.properties")));
            String dbUrl = properties.getProperty("db.url");
            String dbUsername = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");
            String driverClassName = properties.getProperty("db.driverClassName");

            dataSource.setUsername(dbUsername);
            dataSource.setPassword(dbPassword);
            dataSource.setUrl(dbUrl);
            dataSource.setDriverClassName(driverClassName);

            userDao = new UserDaoImpl(dataSource);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        getServletContext().getRequestDispatcher("/jsp/signIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        if (userDao.find(name, password))
        {
            HttpSession session = req.getSession();
            session.setAttribute("user", name);

            Cookie nameCookie = new Cookie("name", name);
            resp.addCookie(nameCookie);
            resp.sendRedirect(req.getContextPath() + "/");
        } else
        {
            resp.sendRedirect(req.getContextPath() + "/signIn");
        }
    }
}
