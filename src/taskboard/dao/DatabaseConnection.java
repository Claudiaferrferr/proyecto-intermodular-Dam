package taskboard.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL      = "jdbc:mysql://localhost:3308/taskboard?useSSL=false&serverTimezone=UTC";
    private static final String USER     = "root";
    private static final String PASSWORD = "Hola1234.";

    private static Connection connection = null;

    private DatabaseConnection() {}

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexion establecida con la base de datos.");
            } catch (ClassNotFoundException e) {
                throw new SQLException("Driver JDBC no encontrado: " + e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexion cerrada.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
    }
}
