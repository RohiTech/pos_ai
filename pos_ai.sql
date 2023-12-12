
drop database if exists pos_ai;

create database pos_ai;

CREATE TABLE clientes (
    id_cliente SERIAL PRIMARY KEY,
    nombre VARCHAR(255),
    direccion VARCHAR(255)
    -- otros campos según necesidades
);

CREATE TABLE productos (
    id_producto SERIAL PRIMARY KEY,
    nombre VARCHAR(255),
    precio DECIMAL(10, 2),
    cantidad_en_stock INT
    -- otros campos según necesidades
);

CREATE TABLE empleados (
    id_empleado SERIAL PRIMARY KEY,
    nombre VARCHAR(255),
    cargo VARCHAR(100)
    -- otros campos según necesidades
);

CREATE TABLE ventas (
    id_venta SERIAL PRIMARY KEY,
    id_cliente INT REFERENCES clientes(id_cliente),
    id_empleado INT REFERENCES empleados(id_empleado),
    fecha TIMESTAMP,
    total DECIMAL(10, 2),
    metodo_de_pago VARCHAR(50)
    -- otros campos según necesidades
);

CREATE TABLE detalle_ventas (
    id_detalle_venta SERIAL PRIMARY KEY,
    id_venta INT REFERENCES ventas(id_venta),
    id_producto INT REFERENCES productos(id_producto),
    cantidad INT,
    precio_unitario DECIMAL(10, 2),
    subtotal DECIMAL(10, 2)
    -- otros campos según necesidades
);

CREATE TABLE usuarios (
    id_usuario SERIAL PRIMARY KEY,
    nombre_usuario VARCHAR(50) UNIQUE,
    contraseña VARCHAR(255),
    id_empleado INT REFERENCES empleados(id_empleado)
    -- otros campos según necesidades
);

