package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {}


    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        session.createNativeQuery("CREATE  TABLE  IF NOT  EXISTS  user " +
                "(id BIGINT NOT NULL AUTO_INCREMENT, " +
                "Name VARCHAR(50), " +
                "lastName VARCHAR (50), " +
                "age tinyint(5), " +
                "PRIMARY KEY ( id ))");

        System.out.println("Table create");
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        session.createNativeQuery("DROP TABLE IF EXISTS user");
//        session.beginTransaction().commit();
        session.close();
        System.out.println("Table drop");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User usr = new User();
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        usr.setName(name);
        usr.setLastName(lastName);
        usr.setAge(age);
        session.persist(usr);
//        session.save(new User(name, lastName, age));
        session.flush();
        System.out.println("Table save User");
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(session.get(User.class, id));
        System.out.println("Table remove User");
    }

    @Override
    public List<User> getAllUsers() {
        List list;
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        list = session.createNativeQuery("FROM User").list();
        System.out.println("Table keep");
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        session.createNativeQuery("truncate from user");
        System.out.println("Table clear");
    }
}
