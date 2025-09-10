--bd--
-- Tabla de eventos
CREATE TABLE evento (
    id SERIAL PRIMARY KEY,
    fecha DATE NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    lugar VARCHAR(255),
    ponente VARCHAR(255),
    organizador VARCHAR(255),
    link VARCHAR(500),
    imagen_url VARCHAR(500),
    descripcion TEXT
);

-- Tabla de asistencias
CREATE TABLE asistencia (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    correo VARCHAR(255) NOT NULL,
    telefono VARCHAR(50),
    evento_id INT NOT NULL,
    CONSTRAINT fk_evento FOREIGN KEY (evento_id) REFERENCES evento(id) ON DELETE CASCADE
);

select * from evento 
