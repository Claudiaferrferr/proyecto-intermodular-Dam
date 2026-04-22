-- TASKBOARD - Datos de ejemplo
USE taskboard;

-- Usuarios
INSERT INTO usuario (nombre, email, password, fecha_alta) VALUES
('Ana García',  'ana@taskboard.com',   'hastaluegomaricarmen', '2025-03-01'),
('Luis Martínez', 'luis@taskboard.com', 'ponisyranas', '2025-03-02'),
('Sara López',    'sara@taskboard.com',  'hola1234', '2025-03-05');

-- Tableros
INSERT INTO tablero (nombre, descripcion, fecha_creacion, id_usuario) VALUES
('Proyecto Web',    'Desarrollo del sitio web corporativo', '2025-03-10', 1),
('App Móvil',       'Aplicación Android para clientes',     '2025-03-12', 1),
('Marketing Q2',    'Campaña de marketing segundo trimestre','2025-03-15', 2);

-- Columnas del tablero 1
INSERT INTO columna (nombre, posicion, id_tablero) VALUES
('Por hacer',    0, 1),
('En progreso',  1, 1),
('Revisión',     2, 1),
('Hecho',        3, 1);

-- Columnas del tablero 2
INSERT INTO columna (nombre, posicion, id_tablero) VALUES
('Backlog',      0, 2),
('Sprint',       1, 2),
('Hecho',        2, 2);

-- Tareas tablero 1
INSERT INTO tarea (titulo, descripcion, prioridad, fecha_limite, completada, id_columna, id_usuario) VALUES
('Diseñar wireframes',    'Mockups de todas las pantallas',         'ALTA',  '2025-04-01', FALSE, 1, 1),
('Configurar servidor',    'Instalar Apache y MySQL en producción',  'ALTA',  '2025-03-28', FALSE, 2, 2),
('Crear API REST',       'Endpoints de usuarios y productos',      'MEDIA', '2025-04-10', FALSE, 2, 1),
('Revisar diseño',       'Revisión con el cliente',                'MEDIA', '2025-04-05', FALSE, 3, 3),
('Deploy inicial',       'Subir versión 0.1 al servidor',          'BAJA',  '2025-04-15', TRUE,  4, 2),
('Redactar documentación', 'Manual técnico del proyecto',            'BAJA',  '2025-04-20', FALSE, 1, 3);

-- Tareas tablero 2
INSERT INTO tarea (titulo, descripcion, prioridad, fecha_limite, completada, id_columna, id_usuario) VALUES
('Login con Google',   'OAuth2 para autenticación',      'ALTA',  '2025-04-08', FALSE, 5, 1),
('Pantalla de perfil',  'Vista y edición de perfil',    'MEDIA', '2025-04-12', FALSE, 6, 2),
('Notificaciones push', 'Firebase Cloud Messaging',    'ALTA',  '2025-04-20', FALSE, 5, 1);
-- Etiquetas
INSERT INTO etiqueta (nombre, color) VALUES
('Frontend', '#378ADD'),
('Backend',  '#1D9E75'),
('Urgente',  '#D85A30'),
('Testing',  '#7F77DD'),
('Diseño',  '#D4537E');

-- Relaciones tarea-etiqueta
INSERT INTO tarea_etiqueta VALUES
(1, 5),(1, 1),
(2, 2),(2, 3),
(3, 2),(3, 1),
(4, 4),(4, 5),
(5, 2),
(7, 2),(7, 3),
(8, 1),(8, 5),
(9, 2),(9, 3);
