package taskboard.model;

import java.time.LocalDate;

public class Tablero {
    private int id;
    private String nombre;
    private String descripcion;
    private LocalDate fechaCreacion;
    private int idUsuario;

    public Tablero() {}

    public Tablero(int id, String nombre, String descripcion, LocalDate fechaCreacion, int idUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.idUsuario = idUsuario;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public LocalDate getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDate fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    @Override
    public String toString() {
        return "Tablero{id=" + id + ", nombre='" + nombre + "'}";
    }
}
