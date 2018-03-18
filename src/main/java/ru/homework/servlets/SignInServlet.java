package ru.homework.servlets;

import ru.homework.dao.UserDao;
import ru.homework.dao.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        getServletContext().getRequestDispatcher("/jsp/signIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        UserDao userDao = new UserDaoImpl();
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
