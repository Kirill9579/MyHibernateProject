package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.management.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory connectDb = Util.getSessionFactory();
    private Session session = null;
    private final String CREATE_TABLE = "CREATE TABLE users " +
            "(id int primary key AUTO_INCREMENT," +
            "name varchar(40) not null ," +
            "last_name varchar(40) not null," +
            "age int not null )";

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        session = connectDb.getCurrentSession();
        session.beginTransaction();
        session.createNativeQuery(CREATE_TABLE, User.class).executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void dropUsersTable() {
        session = connectDb.getCurrentSession();
        session.beginTransaction();
        session.createNativeQuery("DROP TABLE IF EXISTS users", User.class).executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session = connectDb.getCurrentSession();
        session.beginTransaction();
        session.persist(new User(name, lastName, age));
        System.out.println("Пользователь с именем - " + name + " добавлен в базу данных ");
        session.getTransaction().commit();
//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
//            session.persist(new User(name, lastName, age));
//            tx.commit();
//        } catch (Exception e) {
//            if (tx != null)  tx.rollback();
//            System.out.println("При добавлении пользователя произошла ошибка:");
//            System.err.println(e);
//        } finally {
//            session.close();
//        }


    }

    @Override
    public void removeUserById(long id) {
        session = connectDb.getCurrentSession();
        session.beginTransaction();
        session.remove(session.get(User.class, id));
        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        session = connectDb.getCurrentSession();
        session.beginTransaction();
        List<User> tempList = session
                .createQuery("select i from User i", User.class)
                .getResultList();
        session.getTransaction().commit();
        return tempList;
    }

    @Override
    public void cleanUsersTable() {
        session = connectDb.getCurrentSession();
        session.beginTransaction();
        session.createNativeQuery("TRUNCATE TABLE users", User.class).executeUpdate();
        session.getTransaction().commit();
    }
}
