package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public void createUsersTable () {
        String query = "CREATE TABLE IF NOT EXISTS user(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50),lastName VARCHAR(50), age TINYINT)";
        try (Statement statement = new Util().getConnection().createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println("Что то пошло не так с созданием таблицы " + e);
            e.printStackTrace();

        }
    }

    public void dropUsersTable () {
        String query = "DROP TABLE IF EXISTS user";
        try (Statement statement = new Util().getConnection().createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser (String name, String lastName, byte age) {
        try (PreparedStatement st = new Util().getConnection().prepareStatement("INSERT INTO  user (name, lastName, age) VALUES (?, ?, ?)")) {
            st.setString(1, name);
            st.setString(2, lastName);
            st.setByte(3, age);
            st.execute();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById (long id) {
        try (PreparedStatement st = new Util().getConnection().prepareStatement("DELETE FROM user WHERE id = ?")) {
            st.setLong(1, id);
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers () {
        String query = "SELECT * FROM user";
        List<User> list = new ArrayList<>();
        try (Statement statement = new Util().getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                list.add(new User(rs.getLong("id"), rs.getString("name"), rs.getString("lastName"), rs.getByte("age")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable () {
        String query = "TRUNCATE TABLE user";
        Util util = new Util();
        try (Statement statement = util.getConnection().createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
