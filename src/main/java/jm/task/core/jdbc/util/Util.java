package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    private String host = "jdbc:mysql://localhost:3306/users_db";
    private String login = "root";
    private String password = "1111";
    private Connection conn;

    public Util() {
        try {
            conn = DriverManager.getConnection(host, login, password);
            System.out.println("Connection OK!" );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Connection getConn() {
        return conn;
    }
    // реализуйте настройку соеденения с БД
}
