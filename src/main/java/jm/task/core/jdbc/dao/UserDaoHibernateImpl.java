package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    Transaction transaction = null;
    SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl () {
    }

    @Override
    public void createUsersTable () {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS user(id  BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50),lastName VARCHAR(50), age TINYINT)").executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable () {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS user").executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser (String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.save(new User(name, lastName, age));
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById (long id) {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.delete(session.get(User.class, id));
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers () {
        List<User> users = null;
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        users = session.createQuery("FROM User ").list();
        transaction.commit();
        session.close();
        return users;

    }

    @Override
    public void cleanUsersTable () {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.createSQLQuery("TRUNCATE TABLE user").executeUpdate();
        transaction.commit();
        session.close();
    }
}
