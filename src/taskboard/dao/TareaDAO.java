package taskboard.dao;

import taskboard.model.Tarea;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TareaDAO {

    public List<Tarea> listarPorColumna(int idColumna) throws SQLException {
        List<Tarea> tareas = new ArrayList<>();
        String sql = "SELECT * FROM tarea WHERE id_columna = ? ORDER BY prioridad DESC";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idColumna);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                tareas.add(mapear(rs));
            }
        }
        return tareas;
    }

    public List<Tarea> buscarPorTitulo(String texto) throws SQLException {
        List<Tarea> tareas = new ArrayList<>();
        String sql = "SELECT * FROM tarea WHERE titulo LIKE ? OR descripcion LIKE ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            String patron = "%" + texto + "%";
            ps.setString(1, patron);
            ps.setString(2, patron);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                tareas.add(mapear(rs));
            }
        }
        return tareas;
    }

    public void insertar(Tarea tarea) throws SQLException {
        String sql = "INSERT INTO tarea (titulo, descripcion, prioridad, fecha_limite, completada, id_columna, id_usuario) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, tarea.getTitulo());
            ps.setString(2, tarea.getDescripcion());
            ps.setString(3, tarea.getPrioridad().name());
            ps.setDate(4, tarea.getFechaLimite() != null ? Date.valueOf(tarea.getFechaLimite()) : null);
            ps.setBoolean(5, tarea.isCompletada());
            ps.setInt(6, tarea.getIdColumna());
            ps.setInt(7, tarea.getIdUsuario());

            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                tarea.setId(keys.getInt(1));
            }
            System.out.println("Tarea creada con ID: " + tarea.getId());
        }
    }

    public void actualizarEstado(int idTarea, boolean completada) throws SQLException {
        String sql = "UPDATE tarea SET completada = ? WHERE id_tarea = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setBoolean(1, completada);
            ps.setInt(2, idTarea);
            int filas = ps.executeUpdate();
            System.out.println("Tareas actualizadas: " + filas);
        }
    }

    public void moverColumna(int idTarea, int idColumnaDestino) throws SQLException {
        String sql = "UPDATE tarea SET id_columna = ? WHERE id_tarea = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idColumnaDestino);
            ps.setInt(2, idTarea);
            ps.executeUpdate();
        }
    }

    public void eliminar(int idTarea) throws SQLException {
        String sql = "DELETE FROM tarea WHERE id_tarea = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idTarea);
            int filas = ps.executeUpdate();
            System.out.println("Tareas eliminadas: " + filas);
        }
    }

    private Tarea mapear(ResultSet rs) throws SQLException {
        Tarea t = new Tarea();
        t.setId(rs.getInt("id_tarea"));
        t.setTitulo(rs.getString("titulo"));
        t.setDescripcion(rs.getString("descripcion"));
        t.setPrioridad(Tarea.Prioridad.valueOf(rs.getString("prioridad")));
        Date fl = rs.getDate("fecha_limite");
        if (fl != null) t.setFechaLimite(fl.toLocalDate());
        t.setCompletada(rs.getBoolean("completada"));
        t.setIdColumna(rs.getInt("id_columna"));
        t.setIdUsuario(rs.getInt("id_usuario"));
        return t;
    }
}
