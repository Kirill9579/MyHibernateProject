package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jm.task.core.jdbc.model.User;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;


public class Util {
    private static String host = "jdbc:mysql://localhost:3306/users_db";
    private static String login = "root";
    private static String password = "1111";
    private static Connection conn;
    private static SessionFactory sessionFactory;


    public Util() {
    }
    public static Connection getConn() {
        try {
            conn = DriverManager.getConnection(host, login, password);
            System.out.println("Connection OK!" );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration()
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
            System.out.println(!sessionFactory.isClosed() ? "Connection OK" : "Connection ERROR");
        }

        return sessionFactory;
    }

}
