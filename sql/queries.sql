-- TASKBOARD - Consultas del proyecto
USE taskboard;

-- Listar todas las tareas de un tablero con su columna y usuario asignado
SELECT t.id_tarea, t.titulo, t.prioridad, t.fecha_limite, t.completada,
       c.nombre AS columna, u.nombre AS asignado
FROM tarea t
JOIN columna c ON t.id_columna = c.id_columna
JOIN usuario u ON t.id_usuario = u.id_usuario
WHERE c.id_tablero = 1
ORDER BY c.posicion, t.prioridad DESC;

-- Tareas pendientes con prioridad ALTA ordenadas por fecha límite
SELECT t.titulo, t.prioridad, t.fecha_limite, c.nombre AS columna, u.nombre AS responsable
FROM tarea t
JOIN columna c ON t.id_columna = c.id_columna
JOIN usuario u ON t.id_usuario = u.id_usuario
WHERE t.completada = FALSE AND t.prioridad = 'ALTA'
ORDER BY t.fecha_limite ASC;

-- Número de tareas por columna en cada tablero
SELECT tb.nombre AS tablero, c.nombre AS columna,
       COUNT(t.id_tarea) AS total_tareas,
       SUM(t.completada) AS completadas
FROM tablero tb
JOIN columna c ON tb.id_tablero = c.id_tablero
LEFT JOIN tarea t ON c.id_columna = t.id_columna
GROUP BY tb.id_tablero, c.id_columna
ORDER BY tb.nombre, c.posicion;

-- Buscar tareas que contengan una palabra clave
SELECT t.titulo, t.descripcion, c.nombre AS columna, tb.nombre AS tablero
FROM tarea t
JOIN columna c ON t.id_columna = c.id_columna
JOIN tablero tb ON c.id_tablero = tb.id_tablero
WHERE t.titulo LIKE '%API%' OR t.descripcion LIKE '%API%';

-- Tareas con sus etiquetas 
SELECT t.titulo, GROUP_CONCAT(e.nombre ORDER BY e.nombre SEPARATOR ', ') AS etiquetas
FROM tarea t
LEFT JOIN tarea_etiqueta te ON t.id_tarea = te.id_tarea
LEFT JOIN etiqueta e ON te.id_etiqueta = e.id_etiqueta
GROUP BY t.id_tarea, t.titulo
ORDER BY t.titulo;

-- Resumen por usuario
SELECT u.nombre, u.email,
       COUNT(t.id_tarea) AS total_tareas,
       SUM(t.completada) AS completadas,
       COUNT(t.id_tarea) - SUM(t.completada) AS pendientes
FROM usuario u
LEFT JOIN tarea t ON u.id_usuario = t.id_usuario
GROUP BY u.id_usuario
ORDER BY total_tareas DESC;

-- Tableros de un usuario con recuento de tareas pendientes
SELECT tb.nombre AS tablero, tb.fecha_creacion,
       COUNT(t.id_tarea) AS tareas_pendientes
FROM tablero tb
JOIN columna c ON tb.id_tablero = c.id_tablero
LEFT JOIN tarea t ON c.id_columna = t.id_columna AND t.completada = FALSE
WHERE tb.id_usuario = 1
GROUP BY tb.id_tablero;
