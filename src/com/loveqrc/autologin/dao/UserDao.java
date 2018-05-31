package com.loveqrc.autologin.dao;

import com.loveqrc.autologin.domain.User;
import com.loveqrc.autologin.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.taglibs.standard.tag.common.sql.DataSourceUtil;

import javax.sql.DataSource;
import java.sql.SQLException;

public class UserDao {
    public User getUserByUsernameAndPassword(String username, String password) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from user where username =? and password = ? limit 1";
        return queryRunner.query(sql, new BeanHandler<>(User.class), username, password);
    }
}
