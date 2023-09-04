-- Creacion de la tabla Persona
CREATE TABLE Persona
(
    id             BIGINT PRIMARY KEY,
    nombre         VARCHAR(255) NOT NULL,
    genero         VARCHAR(20),
    edad           INT,
    identificacion VARCHAR(20),
    direccion      VARCHAR(255),
    telefono       VARCHAR(20),
    estado         VARCHAR(10)
);

-- Creacion de la tabla Cliente que hereda de Persona
CREATE TABLE Cliente
(
    id         BIGINT PRIMARY KEY,
    contrasena VARCHAR(255),
    estado     BOOLEAN,
    FOREIGN KEY (id) REFERENCES Persona (id)
);

-- Creacion de la tabla Cuenta
CREATE TABLE Cuenta
(
    id           BIGINT PRIMARY KEY,
    numeroCuenta VARCHAR(50) NOT NULL,
    tipoCuenta   VARCHAR(50),
    saldoInicial INT,
    estado       BOOLEAN,
    cliente_id   BIGINT,
    FOREIGN KEY (cliente_id) REFERENCES Cliente (id)
);

-- Creacion de la tabla Movimientos
CREATE TABLE Movimientos
(
    id             BIGINT PRIMARY KEY,
    fecha          TIMESTAMP,
    tipoMovimiento VARCHAR(50),
    valor          INT,
    saldo          INT,
    cuenta_id      BIGINT,
    FOREIGN KEY (cuenta_id) REFERENCES Cuenta (id)
);

--Insertar persona
INSERT INTO Personas (nombre, genero, edad, identificacion, direccion, telefono)
VALUES ('Lizbet Vasquez', 'femenino', 37, '23883314', 'av garcilazo', '987654321');

INSERT INTO Personas (nombre, genero, edad, identificacion, direccion, telefono)
VALUES ('Jose Lema', 'masculino', 37, '098254785', 'Otavalo sn y principal', '987654321');

INSERT INTO Personas (nombre, genero, edad, identificacion, direccion, telefono)
VALUES ('Marianela Montalvo', 'femenino', 37, '097548965', 'Amazonas y NNUU', '987654321');

INSERT INTO Personas (nombre, genero, edad, identificacion, direccion, telefono)
VALUES ('Juan Osorio', 'masculino', 37, '098874587', '13 junio y Equinoccial', '987654321');

INSERT INTO Clientes (contrasena, estado, persona_id)
VALUES ('qwerty', true, (SELECT id FROM Personas WHERE identificacion = '23883314'));

INSERT INTO Clientes (contrasena, estado, persona_id)
VALUES ('1234', true, (SELECT id FROM Personas WHERE identificacion = '098254785'));

INSERT INTO Clientes (contrasena, estado, persona_id)
VALUES ('5678', true, (SELECT id FROM Personas WHERE identificacion = '097548965'));

INSERT INTO Clientes (contrasena, estado, persona_id)
VALUES ('1245', true, (SELECT id FROM Personas WHERE identificacion = '098874587'));

