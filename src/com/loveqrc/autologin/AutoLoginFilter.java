package com.loveqrc.autologin;


import com.loveqrc.autologin.domain.User;
import com.loveqrc.autologin.service.UserService;
import com.loveqrc.autologin.utils.CookieUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AutoLoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            String requestURI = request.getRequestURI();
            if (!requestURI.contains("login")) {
                Cookie autoLogin = CookieUtils.getCookieByName("autoLogin", request.getCookies());
                if (autoLogin != null) {
                    String username = autoLogin.getValue().split("-")[0];
                    String password = autoLogin.getValue().split("-")[1];

                    try {
                        user = new UserService().login(username, password);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                }
            }
        }

        filterChain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}
