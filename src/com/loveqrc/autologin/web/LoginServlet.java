package com.loveqrc.autologin.web;

import com.loveqrc.autologin.Constants;
import com.loveqrc.autologin.domain.User;
import com.loveqrc.autologin.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

@WebServlet(
        urlPatterns = "/login"
)
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = null;
        try {
            user = new UserService().login(username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user == null) {
            req.setAttribute("msg", "用户名与密码不匹配");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        } else {
            req.getSession().setAttribute("user", user);

            if (Constants.IS_AUTO_LOGIN.equals(req.getParameter("autoLogin"))) {
                Cookie cookie = new Cookie("autoLogin", username + "-" + password);
                cookie.setMaxAge(3600);
                cookie.setPath(req.getContextPath() + "/");
                resp.addCookie(cookie);
            }

            if (Constants.IS_SAVE_NAME.equals(req.getParameter("saveName"))) {
                Cookie cookie = new Cookie("saveName", URLEncoder.encode(username, "utf-8"));
                cookie.setMaxAge(3600);
                cookie.setPath(req.getContextPath() + "/");

                resp.addCookie(cookie);
            }

            resp.sendRedirect(req.getContextPath() + "/success.jsp");

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
