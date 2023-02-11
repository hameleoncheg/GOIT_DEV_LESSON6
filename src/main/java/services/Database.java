package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final Database INSTANCE = new Database();
    private Connection connection;
    String dbUrl = "jdbc:postgresql://localhost:5432/init_db";
    String dbUser = "postgres";
    String dbPass = "postgres";


    private Database() {
        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Database getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        try {
            if (connection.isClosed()) {
                connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
