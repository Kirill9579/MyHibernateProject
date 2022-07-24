package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        User nikolai = new User("Nikolai", "Sidorov", (byte) 28);
        User oleg = new User("Oleg", "Petrov", (byte) 35);
        User alex = new User("Alex", "Kuzmich", (byte) 64);
        User petr = new User("Petr", "Volkov", (byte) 19);
        UserService users = new UserServiceImpl();
        try {
            users.dropUsersTable();
            users.createUsersTable();
            users.saveUser(nikolai.getName(), nikolai.getLastName(), nikolai.getAge());
            users.saveUser(oleg.getName(), oleg.getLastName(), oleg.getAge());
            users.saveUser(alex.getName(), alex.getLastName(), alex.getAge());
            users.saveUser(petr.getName(), petr.getLastName(), petr.getAge());
            System.out.println(users.getAllUsers());
            users.dropUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


