package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserServiceImpl implements UserService {
    private Util user;
    private Statement stat;
    private final String createTable = "CREATE TABLE users " +
            "(ID int primary key AUTO_INCREMENT," +
            "NAME varchar(40) not null ," +
            "LAST_NAME varchar(40) not null," +
            "AGE int not null )";

    public UserServiceImpl()  {
        user = new Util();
        try {
            stat = user.getConn().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createUsersTable() throws SQLException {
        stat.executeUpdate(createTable);
    }

    public void dropUsersTable() throws SQLException {

        stat.executeUpdate("DROP TABLE IF EXISTS users");
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        PreparedStatement ps = user.getConn().prepareStatement("INSERT users (NAME, LAST_NAME, AGE) VALUES (?, ?, ?)");
        ps.setString(1, name);
        ps.setString(2, lastName);
        ps.setInt(3, age);
        ps.executeUpdate();
        ps.close();
        System.out.println("User с именем -" + name + "добавлен в базу данных ");
    }

    public void removeUserById(long id) throws SQLException {
        PreparedStatement ps = user.getConn().prepareStatement("DELETE FROM users WHERE (?)");
        ps.setInt(1, (int) id);
        ps.executeUpdate();
        ps.close();
    }

    public List<User> getAllUsers() throws SQLException {
        ResultSet rs = stat.executeQuery("SELECT * FROM users");
        List<User> userList = new ArrayList<>();
        while (rs.next()) {
            userList.add(new User(rs.getString(2), rs.getString(3), rs.getByte(4)));
        }
        return userList;
    }


    public void cleanUsersTable() throws SQLException {
        stat.executeUpdate("TRUNCATE TABLE users");
    }
}
