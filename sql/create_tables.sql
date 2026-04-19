-- TASKBOARD - Gestor de tareas tipo Trello

CREATE DATABASE IF NOT EXISTS taskboard CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE taskboard;

-- Tabla de usuarios
CREATE TABLE usuario (
    id_usuario   INT AUTO_INCREMENT PRIMARY KEY,
    nombre       VARCHAR(100) NOT NULL,
    email        VARCHAR(150) NOT NULL UNIQUE,
    password     VARCHAR(255) NOT NULL,
    fecha_alta   DATE NOT NULL DEFAULT (CURRENT_DATE),
    activo       BOOLEAN NOT NULL DEFAULT TRUE
);

-- Tabla de tableros (equivalente a un "board" de Trello)
CREATE TABLE tablero (
    id_tablero   INT AUTO_INCREMENT PRIMARY KEY,
    nombre       VARCHAR(150) NOT NULL,
    descripcion  TEXT,
    fecha_creacion DATE NOT NULL DEFAULT (CURRENT_DATE),
    id_usuario   INT NOT NULL,
    CONSTRAINT fk_tablero_usuario FOREIGN KEY (id_usuario)
        REFERENCES usuario(id_usuario) ON DELETE CASCADE
);

-- Tabla de columnas (equivalente a las listas: "Por hacer", "En progreso", "Hecho")
CREATE TABLE columna (
    id_columna   INT AUTO_INCREMENT PRIMARY KEY,
    nombre       VARCHAR(100) NOT NULL,
    posicion     INT NOT NULL DEFAULT 0,
    id_tablero   INT NOT NULL,
    CONSTRAINT fk_columna_tablero FOREIGN KEY (id_tablero)
        REFERENCES tablero(id_tablero) ON DELETE CASCADE
);

-- Tabla de tareas (las tarjetas)
CREATE TABLE tarea (
    id_tarea     INT AUTO_INCREMENT PRIMARY KEY,
    titulo       VARCHAR(200) NOT NULL,
    descripcion  TEXT,
    prioridad    ENUM('BAJA','MEDIA','ALTA') NOT NULL DEFAULT 'MEDIA',
    fecha_limite DATE,
    completada   BOOLEAN NOT NULL DEFAULT FALSE,
    fecha_creacion DATE NOT NULL DEFAULT (CURRENT_DATE),
    id_columna   INT NOT NULL,
    id_usuario   INT NOT NULL,
    CONSTRAINT fk_tarea_columna FOREIGN KEY (id_columna)
        REFERENCES columna(id_columna) ON DELETE CASCADE,
    CONSTRAINT fk_tarea_usuario FOREIGN KEY (id_usuario)
        REFERENCES usuario(id_usuario)
);

-- Tabla de etiquetas
CREATE TABLE etiqueta (
    id_etiqueta  INT AUTO_INCREMENT PRIMARY KEY,
    nombre       VARCHAR(50) NOT NULL,
    color        VARCHAR(7) NOT NULL DEFAULT '#5DCAA5'
);

-- Tabla relación tarea-etiqueta (N:M)
CREATE TABLE tarea_etiqueta (
    id_tarea     INT NOT NULL,
    id_etiqueta  INT NOT NULL,
    PRIMARY KEY (id_tarea, id_etiqueta),
    CONSTRAINT fk_te_tarea    FOREIGN KEY (id_tarea)    REFERENCES tarea(id_tarea) ON DELETE CASCADE,
    CONSTRAINT fk_te_etiqueta FOREIGN KEY (id_etiqueta) REFERENCES etiqueta(id_etiqueta) ON DELETE CASCADE
);
