package ru.homework.servlets;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.homework.components.IndependedUserDao;
import ru.homework.config.AppConfig;
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
    private IndependedUserDao userDao;

    @Override
    public void init() throws ServletException
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        userDao = context.getBean(IndependedUserDao.class);
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
