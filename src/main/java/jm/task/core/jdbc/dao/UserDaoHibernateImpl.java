package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    Session session = Util.getSessionFactory().openSession();

    public UserDaoHibernateImpl() {}

    @Override
    public void createUsersTable() {
        Transaction tr = session.beginTransaction();
        session.createSQLQuery("CREATE  TABLE  IF NOT  EXISTS  users " +
                "(id BIGINT NOT NULL AUTO_INCREMENT, " +
                "Name VARCHAR(50), " +
                "lastName VARCHAR (50), " +
                "age tinyint(5), " +
                "PRIMARY KEY ( id ))").executeUpdate();
        tr.commit();
    }

    @Override
    public void dropUsersTable() {
        Transaction tr = session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS users");
        tr.commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction tr = session.beginTransaction();
        session.save(new User(name, lastName, age));
        tr.commit();
    }

    @Override
    public void removeUserById(long id) {
        Transaction tr = session.beginTransaction();
        session.createSQLQuery("DELETE FROM users WHERE id").executeUpdate();
        tr.commit();
    }

    @Override
    public List<User> getAllUsers() {
        List list = new ArrayList<>();
        Transaction tr = session.beginTransaction();
        list = session.createCriteria(User.class).list();
        tr.commit();
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Transaction tr = session.beginTransaction();
        session.createSQLQuery("truncate table users").executeUpdate();
        tr.commit();
    }
}