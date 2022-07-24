package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao user = new UserDaoHibernateImpl();
    public UserServiceImpl()  {

    }

    public void createUsersTable() throws SQLException {
        user.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        user.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        user.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) throws SQLException {
        user.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        return user.getAllUsers();
    }


    public void cleanUsersTable() throws SQLException {
        user.cleanUsersTable();
    }
}
