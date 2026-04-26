package taskboard.model;

public class Columna {
    private int id;
    private String nombre;
    private int posicion;
    private int idTablero;

    public Columna() {}

    public Columna(int id, String nombre, int posicion, int idTablero) {
        this.id = id;
        this.nombre = nombre;
        this.posicion = posicion;
        this.idTablero = idTablero;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getPosicion() { return posicion; }
    public void setPosicion(int posicion) { this.posicion = posicion; }
    public int getIdTablero() { return idTablero; }
    public void setIdTablero(int idTablero) { this.idTablero = idTablero; }

    @Override
    public String toString() {
        return "[" + posicion + "] " + nombre;
    }
}
