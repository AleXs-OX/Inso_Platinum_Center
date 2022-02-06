CREATE DATABASE platinumcenter;
USE platinumcenter;

CREATE TABLE USUARIOS(
	idUsuario INT(6) NOT NULL PRIMARY KEY,
	nombreCompleto VARCHAR(100) NOT NULL,
	nombreUsuario VARCHAR(20) NOT NULL UNIQUE,
	contrasena VARCHAR(20) NOT NULL,
	fechaNacimiento DATE NOT NULL,
    CIF VARCHAR(9) NOT NULL,
    telefono VARCHAR(9) NOT NULL,
    email VARCHAR(50) NOT NULL,
    IBAN VARCHAR(24) NOT NULL,
    direccion VARCHAR(60) NOT NULL,
    fechaAlta DATE NOT NULL,
    idTarifa INT(2) DEFAULT 0,
    idSalario INT(2) DEFAULT 0,
	tipoUsuario INT(1) NOT NULL,
    FOREIGN KEY(idTarifa) REFERENCES TARIFAS(idTarifa),	
    FOREIGN KEY(idSalario) REFERENCES SALARIOS (idSalario)
);

CREATE TABLE TARIFAS(
	idTarifa INT(2) NOT NULL PRIMARY KEY,
    nombreTarifa VARCHAR(50) NOT NULL UNIQUE,
	importe	FLOAT(3,2) NOT NULL,
	descripcion VARCHAR(500) NOT NULL
);

CREATE TABLE SALARIOS(
	idSalario INT(2) NOT NULL PRIMARY KEY,
    nombreSalario VARCHAR(50) NOT NULL UNIQUE,
    cantidad FLOAT(3,2) NOT NULL,
    descripcion VARCHAR(500) NOT NULL
);

CREATE TABLE ALIMENTOS(
	idAlimento INT(5) NOT NULL PRIMARY KEY,
	nombreAlimento VARCHAR(50) NOT NULL UNIQUE,
	descripcion VARCHAR(500) NOT NULL,
	calorias INT(3) NOT NULL, 
	image VARCHAR(300) NOT NULL
);

CREATE TABLE DIETAS(
	idDieta INT(4) NOT NULL PRIMARY KEY,
    idCreador INT(6) NOT NULL,
	nombreDieta VARCHAR(50) NOT NULL UNIQUE,
	descripcion VARCHAR(500),
	calorias INT(4) NOT NULL DEFAULT 0,
    FOREIGN KEY (idCreador) REFERENCES USUARIOS (idUsuario) ON DELETE CASCADE
);

CREATE TABLE ALIMENTOSDIETAS(
	idDieta INT(4) NOT NULL,
    idAlimento INT(5) NOT NULL,
    FOREIGN KEY(idDieta) REFERENCES DIETAS(idDieta) ON DELETE CASCADE,
    FOREIGN KEY (idAlimento) REFERENCES ALIMENTOS(idAlimento),
    CONSTRAINT PK_DIETASALIMENTOS PRIMARY KEY (idDieta, idAlimento)
);

CREATE TABLE SALAS(
	idSala INT(2) NOT NULL PRIMARY KEY,
	nombreSala VARCHAR(50) NOT NULL UNIQUE,
	aforo INT(3) NOT NULL,
    apertura TIME NOT NULL,
    cierre TIME NOT NULL
);

CREATE TABLE EJERCICIOS(
	idEjercicio INT(3) NOT NULL PRIMARY KEY,
    nombreEjercicio VARCHAR(50) NOT NULL UNIQUE,
    descripcion VARCHAR(500) NOT NULL,
	calorias INT(3) NOT NULL, 
    image VARCHAR(300) NOT NULL
);

CREATE TABLE RUTINAS(
	idRutina INT(4) NOT NULL PRIMARY KEY,
    idCreador INT(6) NOT NULL,
    nombreRutina VARCHAR(50) NOT NULL UNIQUE,
    descripcion VARCHAR(500) NOT NULL,
    calorias INT(4) NOT NULL DEFAULT 0,
    FOREIGN KEY (idCreador) REFERENCES USUARIOS (idUsuario) ON DELETE CASCADE
);

CREATE TABLE EJERCICIOSRUTINAS(
	idRutina INT(4) NOT NULL,
    idEjercicio INT(3) NOT NULL,
    FOREIGN KEY (idRutina) REFERENCES RUTINAS (idRutina) ON DELETE CASCADE,
    FOREIGN KEY (idEjercicio) REFERENCES EJERCICIOS (idEjercicio),
    CONSTRAINT PK_EJERCICIOSRUTINAS PRIMARY KEY (idRutina, idEjercicio)
);

CREATE TABLE ACTIVDADES(
	nombreActividad VARCHAR(50) NOT NULL,
    fecha TIMESTAMP NOT NULL,
    idEmpleado INT(6) NOT NULL,
    idSala INT(2) NOT NULL,
    idRutina INT(4) NOT NULL,
	descripcion VARCHAR(500) NOT NULL,
	duracion TIME NOT NULL,
	FOREIGN KEY (idEmpleado) REFERENCES USUARIOS (idUsuario) ON DELETE CASCADE,
    FOREIGN KEY (idSala) REFERENCES SALAS (idSala),
    FOREIGN KEY (idRutina) REFERENCES RUTINAS (idRutina) ON DELETE CASCADE,
    CONSTRAINT PK_ACTIVIDADES PRIMARY KEY (nombreActividad, fecha),
    CONSTRAINT UC_ACTIVIDADES UNIQUE (fecha, idSala)
);

CREATE TABLE MATERIALES(
	idMaterial INT(3) NOT NULL PRIMARY KEY,
	nombreMaterial VARCHAR(50) NOT NULL,
    fechaAlta DATE NOT NULL,
    fechaBaja DATE,
    idSala INT(2) NOT NULL,
    FOREIGN KEY (idSala) REFERENCES SALAS (idSala)
);

CREATE TABLE MATERIALESEJERCICIOS(
	idMaterial INT(3) NOT NULL,
    idEjercicio INT(3) NOT NULL,
    FOREIGN KEY (idMaterial) REFERENCES MATERIALES (idMaterial),
    FOREIGN KEY (idEjercicio) REFERENCES EJERCICIOS (idEjercicio),
    CONSTRAINT PK_MATERIALESEJERCICIOS PRIMARY KEY (idMaterial, idEjercicio)
);

CREATE TABLE PAGOS(
	idEmpleado INT(6) NOT NULL,
    fechaPago DATE NOT NULL,
    idSalario INT(2) NOT NULL,
    FOREIGN KEY (idEmpleado) REFERENCES USUARIOS (idUsuario),
    FOREIGN KEY (idSalario) REFERENCES SALARIOS (idSalario),
    CONSTRAINT PK_PAGOS PRIMARY KEY (idEmpleado, fechaCobro)
);

CREATE TABLE COBROS(
	idCliente INT(6) NOT NULL,
    fechaCobro DATE NOT NULL,
    idTarifa INT(2) NOT NULL,
    FOREIGN KEY (idCliente) REFERENCES USUARIOS (idUsuario),
    FOREIGN KEY (idTarifa) REFERENCES TARIFAS (idTarifa),
    CONSTRAINT PK_COBROS PRIMARY KEY (idCliente, fechaPago)
);

CREATE TABLE RUTINASCLIENTES(
	idCliente INT(6) NOT NULL,
    idRutina INT(4) NOT NULL,
    FOREIGN KEY (idCliente) REFERENCES USUARIOS (idUsuario) ON DELETE CASCADE,
    FOREIGN KEY (idRutina) REFERENCES RUTINAS (idRutina) ON DELETE CASCADE,
    CONSTRAINT PK_RUTINASCLIENTES PRIMARY KEY (idCliente, idRutina)
);

CREATE TABLE DIETASCLIENTES(
	idCliente INT(6) NOT NULL,
    idDieta INT(4) NOT NULL,
    FOREIGN KEY (idCliente) REFERENCES USUARIOS (idUsuario) ON DELETE CASCADE,
    FOREIGN KEY (idDieta) REFERENCES DIETAS (idDieta) ON DELETE CASCADE,
    CONSTRAINT PK_DIETASCLIENTES PRIMARY KEY (idCliente, idDieta)
);

CREATE TABLE ACTIVIDADESCLIENTES(
	idCliente INT(6) NOT NULL,
    nombreActividad VARCHAR(50) NOT NULL,
    fecha TIMESTAMP NOT NULL,
    FOREIGN KEY (idCliente) REFERENCES USUARIOS (idUsuario) ON DELETE CASCADE,
    FOREIGN KEY (nombreActividad) REFERENCES ACTIVIDADES (nombreActividad) ON DELETE CASCADE,
    FOREIGN KEY (fecha) REFERENCES ACTIVIDADES (fecha) ON DELETE CASCADE,
    CONSTRAINT PK_ACTIVIDADESCLIENTES PRIMARY KEY (idCliente, nombreActividad, fecha)
);