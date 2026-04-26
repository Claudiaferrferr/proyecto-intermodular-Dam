package taskboard.service;

import taskboard.dao.TareaDAO;
import taskboard.model.Tarea;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class TareaService {

    private final TareaDAO tareaDAO = new TareaDAO();

    public void crearTarea(String titulo, String descripcion, String prioridad,
                           String fechaLimite, int idColumna, int idUsuario) throws SQLException {
        validarTitulo(titulo);

        Tarea tarea = new Tarea();
        tarea.setTitulo(titulo.trim());
        tarea.setDescripcion(descripcion);
        tarea.setPrioridad(Tarea.Prioridad.valueOf(prioridad.toUpperCase()));
        if (fechaLimite != null && !fechaLimite.isBlank()) {
            tarea.setFechaLimite(LocalDate.parse(fechaLimite));
        }
        tarea.setIdColumna(idColumna);
        tarea.setIdUsuario(idUsuario);

        tareaDAO.insertar(tarea);
    }

    public List<Tarea> listarTareasDeColumna(int idColumna) throws SQLException {
        return tareaDAO.listarPorColumna(idColumna);
    }

    public List<Tarea> buscar(String texto) throws SQLException {
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException("El texto de busqueda no puede estar vacio.");
        }
        return tareaDAO.buscarPorTitulo(texto);
    }

    public void completar(int idTarea) throws SQLException {
        tareaDAO.actualizarEstado(idTarea, true);
    }

    public void reabrir(int idTarea) throws SQLException {
        tareaDAO.actualizarEstado(idTarea, false);
    }

    public void mover(int idTarea, int idColumnaDestino) throws SQLException {
        tareaDAO.moverColumna(idTarea, idColumnaDestino);
    }

    public void eliminar(int idTarea) throws SQLException {
        tareaDAO.eliminar(idTarea);
    }

    private void validarTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("El titulo no puede estar vacio.");
        }
        if (titulo.length() > 200) {
            throw new IllegalArgumentException("El titulo no puede superar 200 caracteres.");
        }
    }
}
