# TaskBoard — Gestor de Tareas tipo Trello

Aplicación de escritorio en Java para gestionar tareas organizadas en tableros y columnas, con persistencia en base de datos MySQL mediante JDBC.

Proyecto Intermodular de 1º DAM — Prometeo by The Power

---

## Qué problema resuelve

Permite a un usuario organizar sus proyectos en tableros con columnas personalizadas (Por hacer, En progreso, Hecho...) y tareas con prioridad, fecha límite y etiquetas. Funciona como un Trello simplificado por consola.

---

## Tecnologías

| Tecnología | Uso |
|---|---|
| Java 17 | Lenguaje principal |
| MySQL 8 | Base de datos |
| JDBC (mysql-connector-j) | Conexión Java ↔ BBDD |
| XML + XSD | Exportación e importación de datos |

---

## Estructura del repositorio

```
/src/taskboard/
  model/        → Clases de entidad (Usuario, Tablero, Columna, Tarea)
  dao/          → Acceso a datos con JDBC
  service/      → Lógica de negocio y validaciones
  ui/           → Menú de consola
  Main.java     → Punto de entrada

/sql/
  create_tables.sql   → Creación de la base de datos y tablas
  insert_data.sql     → Datos de ejemplo
  queries.sql         → Consultas del proyecto

/xml/
  datos.xml           → Exportación de datos en XML
  esquema.xsd         → Esquema de validación XSD

/docs/
  diagramas/          → Diagrama E/R y diagrama de clases
  sistemas/           → Informe técnico de entorno
  empleabilidad/      → Perfil profesional y portfolio
```

---

## Instalación y ejecución

### Requisitos previos

- Java JDK 17 o superior
- MySQL 8.0 o superior
- mysql-connector-j (driver JDBC)

### Pasos

1. Clona el repositorio:
   ```bash
   git clone https://github.com/TU_USUARIO/taskboard.git
   cd taskboard
   ```

2. Crea la base de datos:
   ```bash
   mysql -u root -p < sql/create_tables.sql
   mysql -u root -p taskboard < sql/insert_data.sql
   ```

3. Configura la conexión en `src/taskboard/dao/DatabaseConnection.java`:
   ```java
   private static final String PASSWORD = "tu_password_de_mysql";
   ```

4. Compila y ejecuta:
   ```bash
   javac -cp .:mysql-connector-j.jar -d out src/taskboard/**/*.java src/taskboard/Main.java
   java -cp out:mysql-connector-j.jar taskboard.Main
   ```

---

## Funcionalidades

- Ver tableros del usuario
- Ver tareas de una columna
- Crear nuevas tareas con título, descripción, prioridad y fecha límite
- Marcar tareas como completadas
- Mover tareas entre columnas (equivalente a arrastrar en Trello)
- Buscar tareas por texto
- Eliminar tareas

---

## Módulos del proyecto

| Módulo | Entregable |
|---|---|
| Bases de Datos (0484) | `/sql/` — E/R, modelo relacional, scripts y consultas |
| Entornos de Desarrollo (0487) | Este README + historial de commits |
| Lenguajes de Marcas (0373) | `/xml/` — datos.xml + esquema.xsd validado |
| Programación (0485) | `/src/` — aplicación Java con JDBC |
| MPO Ampliación | Arquitectura por capas model/dao/service/ui |
| Sistemas Informáticos (0483) | `/docs/sistemas/` — informe técnico |
| Empleabilidad (1709) | `/docs/empleabilidad/` — perfil y portfolio |

---

## Aprendizajes clave

- Diseño de bases de datos relacionales con claves foráneas y cardinalidades
- Conexión Java-MySQL mediante JDBC y uso de PreparedStatement
- Arquitectura en capas (model, dao, service, ui) para separar responsabilidades
- Validación de datos XML con esquemas XSD
- Control de versiones con Git y organización profesional de un repositorio

---

## Autor

**[Tu nombre]** — 1º DAM Virtual, Prometeo by The Power  
GitHub: [tu_usuario] | Email: [tu_email]
