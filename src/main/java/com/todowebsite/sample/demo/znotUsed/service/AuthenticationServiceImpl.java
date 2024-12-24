package com.todowebsite.sample.demo.znotUsed.service;

import com.todowebsite.sample.demo.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationServiceImpl implements AuthenticationService{

    @Autowired
    private DataSource dataSource;


    @Override
    public boolean authenticate(Users users) {
//        try(Connection conn = dataSource.getConnection();
//            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {
//
//            preparedStatement.setString(1, username);
//            preparedStatement.setString(2, password);
//
//            try(ResultSet rs = preparedStatement.executeQuery()) {
//
//                return rs.next();
//
//            }
//
//        }catch (SQLException e){
//            throw new RuntimeException(e);
//        }



        boolean isLoggedin = false;



        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("FROM users WHERE username = ?"
                    + " and password = ?");


            preparedStatement.setString(1, users.getUsername());
            preparedStatement.setString(2, users.getPassword());
            ResultSet rs = preparedStatement.executeQuery();

            isLoggedin = rs.next();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return isLoggedin;

    }
}
