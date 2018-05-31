package com.loveqrc.autologin.service;

import com.loveqrc.autologin.dao.UserDao;
import com.loveqrc.autologin.domain.User;

import java.sql.SQLException;

public class UserService {

    public User login(String username, String password) throws SQLException {
        return new UserDao().getUserByUsernameAndPassword(username,password);
    }
}
