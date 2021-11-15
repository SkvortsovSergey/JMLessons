package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String testName = "Ivan";
    private static final String testLastName = "Ivanov";
    private static final byte testAge = 5;

    public static void main (String[] args) {
        String query;
        saveUser(testName, testLastName, testAge);
        query = "SELECT * FROM user";
        List<User> list = new ArrayList<>();
        Util util = new Util();
        try (Statement statement = util.getConnection().createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                list.add(new User(rs.getLong("id"), rs.getString("name"), rs.getString("lastName"), rs.getByte("age")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(list);
    }

    public static void saveUser (String name, String lastName, byte age) {
        Util util = new Util();
        try (PreparedStatement st = util.getConnection().prepareStatement("INSERT INTO user (name, lastName, age) VALUES (?, ?, ?)")) {
            st.setString(1, name);
            st.setString(2, lastName);
            st.setByte(3, age);
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}