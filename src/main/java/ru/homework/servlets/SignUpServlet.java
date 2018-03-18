package ru.homework.servlets;

import ru.homework.dao.UserDao;
import ru.homework.dao.UserDaoImpl;
import ru.homework.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet
{
    UserDao userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        List<User> users = userDao.findAll();
        req.setAttribute("usersFromServer", users);
        getServletContext().getRequestDispatcher("/jsp/signUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        User user = new User(name, password);

        userDao.save(user);
        doGet(req, resp);
    }
}
