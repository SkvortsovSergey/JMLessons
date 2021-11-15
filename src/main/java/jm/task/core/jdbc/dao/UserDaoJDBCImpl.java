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

    private String query;

    public UserDaoJDBCImpl () {

    }

    public void createUsersTable () {
        query = "CREATE TABLE IF NOT EXISTS user(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50),lastName VARCHAR(50), age TINYINT)";
        Util util = new Util();
        try (Statement statement = util.getConnection().createStatement()) {
            statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable () {
        query = "DROP TABLE IF EXISTS user";
        Util util = new Util();
        try (Statement statement = util.getConnection().createStatement()) {
            statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser (String name, String lastName, byte age) {
        Util util = new Util();
        try (PreparedStatement st = util.getConnection().prepareStatement("INSERT INTO user (name, lastName, age) VALUES (?, ?, ?)")) {
            st.setString(1,name);
            st.setString(2, lastName);
            st.setByte(3,age);
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById (long id) {
        Util util = new Util();
        try (PreparedStatement st = util.getConnection().prepareStatement("DELETE FROM user WHERE id = ?") ){
            st.setLong(1,id);
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers () {
        query = "SELECT * FROM user";
        List<User> list = new ArrayList<>();
        Util util = new Util();
        try (Statement statement = util.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                list.add(new User(rs.getLong("id"), rs.getString("name"), rs.getString("lastName"),rs.getByte("age")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable () {
        query = "TRUNCATE TABLE user";
        Util util = new Util();
        try (Statement statement = util.getConnection().createStatement()) {
            statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
