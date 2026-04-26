package taskboard.ui;

import taskboard.dao.ColumnaDAO;
import taskboard.dao.DatabaseConnection;
import taskboard.dao.TableroDAO;
import taskboard.model.Columna;
import taskboard.model.Tablero;
import taskboard.model.Tarea;
import taskboard.service.TareaService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private final Scanner scanner = new Scanner(System.in);
    private final TareaService tareaService = new TareaService();
    private final TableroDAO tableroDAO = new TableroDAO();
    private final ColumnaDAO columnaDAO = new ColumnaDAO();

    private static final int ID_USUARIO_DEMO = 1;

    public void iniciar() {
        System.out.println("========================================");
        System.out.println("      TASKBOARD - Gestor de Tareas      ");
        System.out.println("========================================");

        boolean salir = false;
        while (!salir) {
            mostrarMenuPrincipal();
            int opcion = leerEntero("Selecciona una opcion: ");

            switch (opcion) {
                case 1 -> verTableros();
                case 2 -> verTareasDeColumna();
                case 3 -> crearTarea();
                case 4 -> completarTarea();
                case 5 -> moverTarea();
                case 6 -> buscarTareas();
                case 7 -> eliminarTarea();
                case 0 -> salir = true;
                default -> System.out.println("Opcion no valida.");
            }
        }

        DatabaseConnection.closeConnection();
        System.out.println("Hasta pronto!");
    }

    private void mostrarMenuPrincipal() {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1. Ver mis tableros");
        System.out.println("2. Ver tareas de una columna");
        System.out.println("3. Crear nueva tarea");
        System.out.println("4. Marcar tarea como completada");
        System.out.println("5. Mover tarea a otra columna");
        System.out.println("6. Buscar tareas");
        System.out.println("7. Eliminar tarea");
        System.out.println("0. Salir");
    }

    private void verTableros() {
        try {
            List<Tablero> tableros = tableroDAO.listarPorUsuario(ID_USUARIO_DEMO);
            if (tableros.isEmpty()) {
                System.out.println("No tienes tableros aun.");
                return;
            }
            System.out.println("\n--- TUS TABLEROS ---");
            tableros.forEach(t -> System.out.println("  [" + t.getId() + "] " + t.getNombre()
                    + (t.getDescripcion() != null ? " - " + t.getDescripcion() : "")));
        } catch (SQLException e) {
            System.err.println("Error al cargar tableros: " + e.getMessage());
        }
    }

    private void verTareasDeColumna() {
        int idColumna = leerEntero("ID de la columna: ");
        try {
            List<Tarea> tareas = tareaService.listarTareasDeColumna(idColumna);
            if (tareas.isEmpty()) {
                System.out.println("Esta columna no tiene tareas.");
                return;
            }
            System.out.println("\n--- TAREAS ---");
            tareas.forEach(t -> System.out.println("  [" + t.getId() + "] " + t));
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void crearTarea() {
        System.out.println("\n--- NUEVA TAREA ---");
        System.out.print("Titulo: ");
        String titulo = scanner.nextLine();
        System.out.print("Descripcion (opcional): ");
        String desc = scanner.nextLine();
        System.out.print("Prioridad (BAJA/MEDIA/ALTA): ");
        String prioridad = scanner.nextLine().toUpperCase();
        System.out.print("Fecha limite (yyyy-MM-dd, opcional): ");
        String fecha = scanner.nextLine();
        int idColumna = leerEntero("ID de columna destino: ");

        try {
            tareaService.crearTarea(titulo, desc, prioridad, fecha.isBlank() ? null : fecha,
                    idColumna, ID_USUARIO_DEMO);
            System.out.println("Tarea creada correctamente.");
        } catch (SQLException | IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void completarTarea() {
        int idTarea = leerEntero("ID de la tarea a completar: ");
        try {
            tareaService.completar(idTarea);
            System.out.println("Tarea marcada como completada.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void moverTarea() {
        int idTarea = leerEntero("ID de la tarea: ");
        int idColumna = leerEntero("ID de la columna destino: ");
        try {
            tareaService.mover(idTarea, idColumna);
            System.out.println("Tarea movida correctamente.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void buscarTareas() {
        System.out.print("Texto a buscar: ");
        String texto = scanner.nextLine();
        try {
            List<Tarea> resultados = tareaService.buscar(texto);
            System.out.println("\nResultados (" + resultados.size() + "):");
            resultados.forEach(t -> System.out.println("  [" + t.getId() + "] " + t));
        } catch (SQLException | IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void eliminarTarea() {
        int idTarea = leerEntero("ID de la tarea a eliminar: ");
        System.out.print("Confirma eliminacion (s/n): ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("s")) {
            try {
                tareaService.eliminar(idTarea);
                System.out.println("Tarea eliminada.");
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Operacion cancelada.");
        }
    }

    private int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = Integer.parseInt(scanner.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduce un numero valido.");
            }
        }
    }
}
