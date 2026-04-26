package taskboard.model;

import java.time.LocalDate;

public class Tarea {

    public enum Prioridad { BAJA, MEDIA, ALTA }

    private int id;
    private String titulo;
    private String descripcion;
    private Prioridad prioridad;
    private LocalDate fechaLimite;
    private boolean completada;
    private LocalDate fechaCreacion;
    private int idColumna;
    private int idUsuario;

    public Tarea() {}

    public Tarea(int id, String titulo, String descripcion, Prioridad prioridad,
                 LocalDate fechaLimite, boolean completada, int idColumna, int idUsuario) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.fechaLimite = fechaLimite;
        this.completada = completada;
        this.fechaCreacion = LocalDate.now();
        this.idColumna = idColumna;
        this.idUsuario = idUsuario;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Prioridad getPrioridad() { return prioridad; }
    public void setPrioridad(Prioridad prioridad) { this.prioridad = prioridad; }
    public LocalDate getFechaLimite() { return fechaLimite; }
    public void setFechaLimite(LocalDate fechaLimite) { this.fechaLimite = fechaLimite; }
    public boolean isCompletada() { return completada; }
    public void setCompletada(boolean completada) { this.completada = completada; }
    public LocalDate getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDate fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public int getIdColumna() { return idColumna; }
    public void setIdColumna(int idColumna) { this.idColumna = idColumna; }
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    @Override
    public String toString() {
        String estado = completada ? "[HECHO]" : "[" + prioridad + "]";
        return estado + " " + titulo + (fechaLimite != null ? " (hasta " + fechaLimite + ")" : "");
    }
}
