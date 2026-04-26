package taskboard.dao;

import taskboard.model.Columna;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ColumnaDAO {

    public List<Columna> listarPorTablero(int idTablero) throws SQLException {
        List<Columna> columnas = new ArrayList<>();
        String sql = "SELECT * FROM columna WHERE id_tablero = ? ORDER BY posicion";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idTablero);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                columnas.add(new Columna(
                    rs.getInt("id_columna"),
                    rs.getString("nombre"),
                    rs.getInt("posicion"),
                    rs.getInt("id_tablero")
                ));
            }
        }
        return columnas;
    }

    public void insertar(Columna columna) throws SQLException {
        String sql = "INSERT INTO columna (nombre, posicion, id_tablero) VALUES (?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, columna.getNombre());
            ps.setInt(2, columna.getPosicion());
            ps.setInt(3, columna.getIdTablero());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) columna.setId(keys.getInt(1));
        }
    }
}
