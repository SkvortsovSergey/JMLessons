package jm.task.core.jdbc.util;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private final String NAME = "root";
    private final String PASSW = "1234";
    private final String HOST = "jdbc:mysql://localhost:3306/mybdtest?autoReconnect=true&useSSL=false";
    private Connection connection;

    public Util () {
        try {
            connection = DriverManager.getConnection(HOST, NAME, PASSW);
        } catch (SQLException ex) {
            System.err.println("Ошибка соединения : " + ex);
        }
    }

    public Connection getConnection () {
        return connection;
    }

}
