package taskboard.dao;

import taskboard.model.Tablero;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TableroDAO {

    public List<Tablero> listarPorUsuario(int idUsuario) throws SQLException {
        List<Tablero> tableros = new ArrayList<>();
        String sql = "SELECT * FROM tablero WHERE id_usuario = ? ORDER BY fecha_creacion DESC";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tableros.add(mapear(rs));
            }
        }
        return tableros;
    }

    public void insertar(Tablero tablero) throws SQLException {
        String sql = "INSERT INTO tablero (nombre, descripcion, id_usuario) VALUES (?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, tablero.getNombre());
            ps.setString(2, tablero.getDescripcion());
            ps.setInt(3, tablero.getIdUsuario());
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) tablero.setId(keys.getInt(1));
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM tablero WHERE id_tablero = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Tablero mapear(ResultSet rs) throws SQLException {
        return new Tablero(
            rs.getInt("id_tablero"),
            rs.getString("nombre"),
            rs.getString("descripcion"),
            rs.getDate("fecha_creacion").toLocalDate(),
            rs.getInt("id_usuario")
        );
    }
}
