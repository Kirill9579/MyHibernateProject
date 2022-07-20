package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Util user;
    private PreparedStatement pStatement;
    private final String createTable = "CREATE TABLE users " +
            "(ID int primary key AUTO_INCREMENT," +
            "NAME varchar(40) not null ," +
            "LAST_NAME varchar(40) not null," +
            "AGE int not null )";
    private final String dropTable = "DROP TABLE IF EXISTS users";
    public UserDaoJDBCImpl() {
        user = new Util();
    }

    public void createUsersTable() throws SQLException {

        try {
            pStatement = user.getConn().prepareStatement(dropTable);
            pStatement.executeUpdate();
            pStatement.executeUpdate(createTable);
        } catch (SQLException e) {
            System.err.println("Creat users table exception " + e);
        } finally {
            pStatement.close();
        }
    }

    public void dropUsersTable() throws SQLException {
        try {
            pStatement = user.getConn().prepareStatement(dropTable);
        } catch (SQLException e) {
            System.err.println("Drop users table exception " + e);
        } finally {
            pStatement.close();
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        try {
            pStatement = user.getConn().prepareStatement("INSERT users (NAME, LAST_NAME, AGE) VALUES (?, ?, ?)");
            pStatement.setString(1, name);
            pStatement.setString(2, lastName);
            pStatement.setInt(3, age);
            pStatement.executeUpdate();
            pStatement.close();
            System.out.println("User с именем -" + name + " добавлен в базу данных ");
        } catch (SQLException e) {
            System.err.println("Save users table exception " + e);
        } finally {
            pStatement.close();
        }
    }

    public void removeUserById(long id) throws SQLException {
        try {
            pStatement = user.getConn().prepareStatement("DELETE FROM users WHERE (?)");
            pStatement.setInt(1, (int) id);
            pStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Remove users table exception " + e);
        } finally {
            pStatement.close();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        try {
            pStatement = user.getConn().prepareStatement("SELECT * FROM users");
            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                userList.add(new User(rs.getString(2), rs.getString(3), rs.getByte(4)));
            }
        } catch (SQLException e) {
            System.err.println("Get all users exception " + e);
        } finally {
            pStatement.close();
        }
        return userList;
    }

    public void cleanUsersTable() throws SQLException {
        try {
            pStatement = user.getConn().prepareStatement("TRUNCATE TABLE users");
            pStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Clean users exception " + e);
        } finally {
            pStatement.close();
        }
    }
}
