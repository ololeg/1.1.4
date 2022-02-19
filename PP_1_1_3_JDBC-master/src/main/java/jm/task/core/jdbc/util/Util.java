package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private Connection connection = null;

    public static  Connection getConnection(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/newbd", "root", "root");
        }

        catch(SQLException e) {
            System.out.println("Connection Failed");
        }

        return connection;
    }

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        Configuration configuration = new Configuration();
        Properties settings = new Properties();
        settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        settings.put(Environment.URL, "jdbc:mysql://localhost:3306/newbd?useSSL=false");
        settings.put(Environment.USER, "root");
        settings.put(Environment.PASS, "root");
        settings.put(Environment. DIALECT, "org.hibernate.dialect.MySQLDialect");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        settings.put(Environment.HBM2DDL_AUTO, "update");

        configuration.setProperties(settings);
        configuration.addAnnotatedClass(User.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
    }
}
