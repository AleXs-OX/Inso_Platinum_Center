-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         8.0.28 - MySQL Community Server - GPL
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para platinumcenter
CREATE DATABASE IF NOT EXISTS `platinumcenter` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `platinumcenter`;

-- Volcando estructura para tabla platinumcenter.actividades
CREATE TABLE IF NOT EXISTS `actividades` (
    `nombreActividad` varchar(50) NOT NULL,
    `fecha` timestamp NOT NULL,
    `idEmpleado` int NOT NULL,
    `idSala` int NOT NULL,
    `idRutina` int NOT NULL,
    `descripcion` varchar(500) NOT NULL,
    `duracion` time NOT NULL,
    PRIMARY KEY (`nombreActividad`,`fecha`),
    UNIQUE KEY `UC_ACTIVIDADES` (`fecha`,`idSala`),
    KEY `idEmpleado` (`idEmpleado`),
    KEY `idSala` (`idSala`),
    KEY `idRutina` (`idRutina`),
    CONSTRAINT `actividades_ibfk_1` FOREIGN KEY (`idEmpleado`) REFERENCES `usuarios` (`idUsuario`) ON DELETE CASCADE,
    CONSTRAINT `actividades_ibfk_2` FOREIGN KEY (`idSala`) REFERENCES `salas` (`idSala`),
    CONSTRAINT `actividades_ibfk_3` FOREIGN KEY (`idRutina`) REFERENCES `rutinas` (`idRutina`) ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla platinumcenter.actividades: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `actividades` DISABLE KEYS */;
INSERT INTO `actividades` (`nombreActividad`, `fecha`, `idEmpleado`, `idSala`, `idRutina`, `descripcion`, `duracion`) VALUES
                                                                                                                          ('Bicileta', '2022-02-22 13:32:57', 658487, 101, 1325, 'Uso de bicicleta estatica', '01:00:00'),
                                                                                                                          ('Body Combat', '2022-02-16 13:58:14', 658487, 103, 1042, 'Diversos ejercicios de zumba y pierna', '01:00:00'),
                                                                                                                          ('Dance Boxing', '2022-02-15 13:34:24', 658487, 105, 5482, 'Diversos ejercicios de cardio y boxeo', '01:00:00'),
                                                                                                                          ('Pilates', '2022-02-22 13:30:02', 658487, 103, 6584, 'Diversos ejercicios de pilates', '01:00:00'),
                                                                                                                          ('Spinning', '2022-02-13 12:35:15', 658487, 104, 1042, 'Diversos ejercicios para ejercitar piernas', '01:00:00');
/*!40000 ALTER TABLE `actividades` ENABLE KEYS */;

-- Volcando estructura para tabla platinumcenter.actividadesclientes
CREATE TABLE IF NOT EXISTS `actividadesclientes` (
                                                     `idCliente` int NOT NULL,
                                                     `nombreActividad` varchar(50) NOT NULL,
    `fecha` timestamp NOT NULL,
    PRIMARY KEY (`idCliente`,`nombreActividad`,`fecha`),
    KEY `nombreActividad` (`nombreActividad`),
    KEY `fecha` (`fecha`),
    CONSTRAINT `actividadesclientes_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `usuarios` (`idUsuario`) ON DELETE CASCADE,
    CONSTRAINT `actividadesclientes_ibfk_2` FOREIGN KEY (`nombreActividad`) REFERENCES `actividades` (`nombreActividad`) ON DELETE CASCADE,
    CONSTRAINT `actividadesclientes_ibfk_3` FOREIGN KEY (`fecha`) REFERENCES `actividades` (`fecha`) ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla platinumcenter.actividadesclientes: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `actividadesclientes` DISABLE KEYS */;
INSERT INTO `actividadesclientes` (`idCliente`, `nombreActividad`, `fecha`) VALUES
                                                                                (615895, 'Spinning', '2022-02-13 12:35:15'),
                                                                                (547623, 'Body Combat', '2022-02-16 13:58:14'),
                                                                                (547623, 'Pilates', '2022-02-22 13:30:02'),
                                                                                (652486, 'Bicileta', '2022-02-22 13:32:57');
/*!40000 ALTER TABLE `actividadesclientes` ENABLE KEYS */;

-- Volcando estructura para tabla platinumcenter.alimentos
CREATE TABLE IF NOT EXISTS `alimentos` (
                                           `idAlimento` int NOT NULL,
                                           `nombreAlimento` varchar(50) NOT NULL,
    `descripcion` varchar(500) NOT NULL,
    `calorias` int NOT NULL,
    `image` varchar(300) NOT NULL,
    PRIMARY KEY (`idAlimento`),
    UNIQUE KEY `nombreAlimento` (`nombreAlimento`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla platinumcenter.alimentos: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `alimentos` DISABLE KEYS */;
INSERT INTO `alimentos` (`idAlimento`, `nombreAlimento`, `descripcion`, `calorias`, `image`) VALUES
                                                                                                 (1, 'Batido proteico', 'Batido de leche con proteina de vainilla', 300, 'https://t1.uc.ltmcdn.com/images/0/0/4/6_batidos_de_proteinas_caseros_para_aumentar_masa_muscular_45400_orig.jpg'),
                                                                                                 (2, 'Tortitas de avena', 'Tortitas bajas en calorias', 100, 'https://www.todosacomer.net/wp-content/uploads/2019/10/tortitas-avena-3.jpg'),
                                                                                                 (3, 'Arroz y Huevo', '100gr de arroz con dos huevos a la plancha', 350, 'https://t1.uc.ltmcdn.com/images/5/6/4/img_como_hacer_arroz_blanco_con_huevos_465_600_square.jpg'),
                                                                                                 (4, 'Pan Integral con pavo', '50gr de pan integral con 20gr de pavo', 75, 'https://www.dietfarma.com/sites/default/files/tostada_con_margarina_y_pechuga_de_pavo.r_0.jpg'),
                                                                                                 (5, 'Solomillo Iberico', '250gr de solomillo bajo en grasa', 500, 'https://elgourmet.s3.amazonaws.com/recetas/cover/solom_VBNv4TjYIgtGOSJy82lphEeQwkfoxz.png');
/*!40000 ALTER TABLE `alimentos` ENABLE KEYS */;

-- Volcando estructura para tabla platinumcenter.alimentosdietas
CREATE TABLE IF NOT EXISTS `alimentosdietas` (
                                                 `idDieta` int NOT NULL,
                                                 `idAlimento` int NOT NULL,
                                                 PRIMARY KEY (`idDieta`,`idAlimento`),
    KEY `idAlimento` (`idAlimento`),
    CONSTRAINT `alimentosdietas_ibfk_1` FOREIGN KEY (`idDieta`) REFERENCES `dietas` (`idDieta`) ON DELETE CASCADE,
    CONSTRAINT `alimentosdietas_ibfk_2` FOREIGN KEY (`idAlimento`) REFERENCES `alimentos` (`idAlimento`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla platinumcenter.alimentosdietas: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `alimentosdietas` DISABLE KEYS */;
INSERT INTO `alimentosdietas` (`idDieta`, `idAlimento`) VALUES
                                                            (2, 1),
                                                            (1, 2),
                                                            (2, 3),
                                                            (3, 3),
                                                            (1, 4),
                                                            (3, 4),
                                                            (2, 5);
/*!40000 ALTER TABLE `alimentosdietas` ENABLE KEYS */;

-- Volcando estructura para tabla platinumcenter.cobros
CREATE TABLE IF NOT EXISTS `cobros` (
                                        `idCliente` int NOT NULL,
                                        `fechaCobro` date NOT NULL,
                                        `idTarifa` int NOT NULL,
                                        PRIMARY KEY (`idCliente`,`fechaCobro`),
    KEY `idTarifa` (`idTarifa`),
    CONSTRAINT `cobros_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `usuarios` (`idUsuario`) ON DELETE CASCADE,
    CONSTRAINT `cobros_ibfk_2` FOREIGN KEY (`idTarifa`) REFERENCES `tarifas` (`idTarifa`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla platinumcenter.cobros: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `cobros` DISABLE KEYS */;
INSERT INTO `cobros` (`idCliente`, `fechaCobro`, `idTarifa`) VALUES
                                                                 (547623, '2022-01-11', 1),
                                                                 (652486, '2022-01-11', 2),
                                                                 (615895, '2022-01-11', 3);
/*!40000 ALTER TABLE `cobros` ENABLE KEYS */;

-- Volcando estructura para tabla platinumcenter.dietas
CREATE TABLE IF NOT EXISTS `dietas` (
                                        `idDieta` int NOT NULL,
                                        `idCreador` int NOT NULL,
                                        `nombreDieta` varchar(50) NOT NULL,
    `descripcion` varchar(500) DEFAULT NULL,
    `calorias` int NOT NULL DEFAULT '0',
    PRIMARY KEY (`idDieta`),
    UNIQUE KEY `nombreDieta` (`nombreDieta`),
    KEY `idCreador` (`idCreador`),
    CONSTRAINT `dietas_ibfk_1` FOREIGN KEY (`idCreador`) REFERENCES `usuarios` (`idUsuario`) ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla platinumcenter.dietas: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `dietas` DISABLE KEYS */;
INSERT INTO `dietas` (`idDieta`, `idCreador`, `nombreDieta`, `descripcion`, `calorias`) VALUES
                                                                                            (1, 658487, 'Definicion', 'Dieta para reducir grasas', 1800),
                                                                                            (2, 658487, 'Volumen', 'Dieta para aumentar volumen', 3000),
                                                                                            (3, 658487, 'Mantenerse', 'Dieta para mantener consntante el pelo', 2100);
/*!40000 ALTER TABLE `dietas` ENABLE KEYS */;

-- Volcando estructura para tabla platinumcenter.dietasclientes
CREATE TABLE IF NOT EXISTS `dietasclientes` (
                                                `idCliente` int NOT NULL,
                                                `idDieta` int NOT NULL,
                                                PRIMARY KEY (`idCliente`,`idDieta`),
    KEY `idDieta` (`idDieta`),
    CONSTRAINT `dietasclientes_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `usuarios` (`idUsuario`) ON DELETE CASCADE,
    CONSTRAINT `dietasclientes_ibfk_2` FOREIGN KEY (`idDieta`) REFERENCES `dietas` (`idDieta`) ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla platinumcenter.dietasclientes: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `dietasclientes` DISABLE KEYS */;
INSERT INTO `dietasclientes` (`idCliente`, `idDieta`) VALUES
                                                          (547623, 1),
                                                          (615895, 2),
                                                          (652486, 3);
/*!40000 ALTER TABLE `dietasclientes` ENABLE KEYS */;

-- Volcando estructura para tabla platinumcenter.ejercicios
CREATE TABLE IF NOT EXISTS `ejercicios` (
                                            `idEjercicio` int NOT NULL,
                                            `nombreEjercicio` varchar(50) NOT NULL,
    `descripcion` varchar(500) NOT NULL,
    `calorias` int NOT NULL,
    `image` varchar(300) NOT NULL,
    PRIMARY KEY (`idEjercicio`),
    UNIQUE KEY `nombreEjercicio` (`nombreEjercicio`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla platinumcenter.ejercicios: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `ejercicios` DISABLE KEYS */;
INSERT INTO `ejercicios` (`idEjercicio`, `nombreEjercicio`, `descripcion`, `calorias`, `image`) VALUES
                                                                                                    (1, 'Curl de biceps', 'Ejercicio traccion de bicep', 50, 'https://i.blogs.es/220bb2/1366_2000-2-/1366_2000.jpg'),
                                                                                                    (2, 'Dominada', 'Desarollo de espalda y hombros', 30, 'https://i.blogs.es/b536e3/1366_2000/450_1000.jpg'),
                                                                                                    (3, 'Press de Banca', 'Desarollo de pecho tronco superior', 70, 'https://i.blogs.es/2604fe/press-banca/450_1000.jpeg'),
                                                                                                    (4, 'Poleas', 'Desarollo de triceps en polea extensible', 25, 'https://i.ytimg.com/vi/cGEPFQ99pyQ/maxresdefault.jpg'),
                                                                                                    (5, 'Multipower', 'Desarollo de biceps en maquina polea', 15, 'https://www.technogym.com/media/catalog/product/cache/1/image/602f0fa2c1f0d1ba5e241f914e856ff9/m/b/mb82_multipower_hero.jpg'),
                                                                                                    (6, 'Estiramiento Espalda', 'Estirar espalda en pelota goma', 5, 'https://www.tucanaldesalud.es/es/teinteresa/consejos-saludables-trabajar-frente-pantalla/ejercicios-estiramientos-espalda.ficheros/1638514-ejercicios-estiramientos-espalda.jpg?width=480&height=288&aspectRatio=true'),
                                                                                                    (7, 'Reposo cuclillas', 'Reposo en cuclillas para la relajacion muscular', 2, 'https://minimalstudioalicante.com/wp-content/uploads/2019/10/Sentadilla-profunda.jpg'),
                                                                                                    (8, 'Estiramiento de cuello', 'Estiramiento de cuello con rotaciones', 2, 'https://www.hogarmania.com/archivos/201710/ejercicios-cuello-1280x720x80xX.jpg'),
                                                                                                    (9, 'Remo', 'Desarollo de pecho en polea usando Remo', 10, 'https://i.ytimg.com/vi/gTCmsy45usM/maxresdefault.jpg');
/*!40000 ALTER TABLE `ejercicios` ENABLE KEYS */;

-- Volcando estructura para tabla platinumcenter.ejerciciosrutinas
CREATE TABLE IF NOT EXISTS `ejerciciosrutinas` (
                                                   `idRutina` int NOT NULL,
                                                   `idEjercicio` int NOT NULL,
                                                   PRIMARY KEY (`idRutina`,`idEjercicio`),
    KEY `idEjercicio` (`idEjercicio`),
    CONSTRAINT `ejerciciosrutinas_ibfk_1` FOREIGN KEY (`idRutina`) REFERENCES `rutinas` (`idRutina`) ON DELETE CASCADE,
    CONSTRAINT `ejerciciosrutinas_ibfk_2` FOREIGN KEY (`idEjercicio`) REFERENCES `ejercicios` (`idEjercicio`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla platinumcenter.ejerciciosrutinas: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `ejerciciosrutinas` DISABLE KEYS */;
INSERT INTO `ejerciciosrutinas` (`idRutina`, `idEjercicio`) VALUES
                                                                (5685, 1),
                                                                (3658, 3),
                                                                (5685, 4),
                                                                (6584, 6),
                                                                (6584, 7),
                                                                (6584, 8),
                                                                (3658, 9);
/*!40000 ALTER TABLE `ejerciciosrutinas` ENABLE KEYS */;

-- Volcando estructura para tabla platinumcenter.materiales
CREATE TABLE IF NOT EXISTS `materiales` (
                                            `idMaterial` int NOT NULL,
                                            `nombreMaterial` varchar(50) NOT NULL,
    `fechaAlta` date NOT NULL,
    `fechaBaja` date DEFAULT NULL,
    `idSala` int NOT NULL,
    PRIMARY KEY (`idMaterial`),
    KEY `idSala` (`idSala`),
    CONSTRAINT `materiales_ibfk_1` FOREIGN KEY (`idSala`) REFERENCES `salas` (`idSala`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla platinumcenter.materiales: ~7 rows (aproximadamente)
/*!40000 ALTER TABLE `materiales` DISABLE KEYS */;
INSERT INTO `materiales` (`idMaterial`, `nombreMaterial`, `fechaAlta`, `fechaBaja`, `idSala`) VALUES
                                                                                                  (2202, 'Comba', '2022-02-10', '2022-02-10', 101),
                                                                                                  (2203, 'Bicicleta Eliptica', '2022-02-10', NULL, 104),
                                                                                                  (2204, 'Mancuerna 1kg', '2022-02-10', NULL, 103),
                                                                                                  (2205, 'Colchoneta', '2022-02-10', NULL, 101),
                                                                                                  (2206, 'Pelota 3kg', '2022-02-10', '2022-02-10', 101),
                                                                                                  (2207, 'Pelota 5kg', '2022-02-10', NULL, 101),
                                                                                                  (2209, 'Guantes', '2022-02-10', NULL, 105),
                                                                                                  (3548, 'Arnes', '2022-02-11', NULL, 102),
                                                                                                  (3645, 'Cuerdas', '2022-02-11', NULL, 102);
/*!40000 ALTER TABLE `materiales` ENABLE KEYS */;

-- Volcando estructura para tabla platinumcenter.materialesejercicios
CREATE TABLE IF NOT EXISTS `materialesejercicios` (
                                                      `idMaterial` int NOT NULL,
                                                      `idEjercicio` int NOT NULL,
                                                      PRIMARY KEY (`idMaterial`,`idEjercicio`),
    KEY `idEjercicio` (`idEjercicio`),
    CONSTRAINT `materialesejercicios_ibfk_1` FOREIGN KEY (`idMaterial`) REFERENCES `materiales` (`idMaterial`),
    CONSTRAINT `materialesejercicios_ibfk_2` FOREIGN KEY (`idEjercicio`) REFERENCES `ejercicios` (`idEjercicio`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla platinumcenter.materialesejercicios: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `materialesejercicios` DISABLE KEYS */;
INSERT INTO `materialesejercicios` (`idMaterial`, `idEjercicio`) VALUES
                                                                     (2204, 1),
                                                                     (3548, 4),
                                                                     (2202, 5),
                                                                     (2203, 5),
                                                                     (2209, 5),
                                                                     (2206, 6),
                                                                     (3645, 6),
                                                                     (2205, 8);
/*!40000 ALTER TABLE `materialesejercicios` ENABLE KEYS */;

-- Volcando estructura para tabla platinumcenter.pagos
CREATE TABLE IF NOT EXISTS `pagos` (
                                       `idEmpleado` int NOT NULL,
                                       `fechaPago` date NOT NULL,
                                       `idSalario` int NOT NULL,
                                       PRIMARY KEY (`idEmpleado`,`fechaPago`),
    KEY `idSalario` (`idSalario`),
    CONSTRAINT `pagos_ibfk_1` FOREIGN KEY (`idEmpleado`) REFERENCES `usuarios` (`idUsuario`) ON DELETE CASCADE,
    CONSTRAINT `pagos_ibfk_2` FOREIGN KEY (`idSalario`) REFERENCES `salarios` (`idSalario`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla platinumcenter.pagos: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `pagos` DISABLE KEYS */;
INSERT INTO `pagos` (`idEmpleado`, `fechaPago`, `idSalario`) VALUES
                                                                 (160208, '2021-12-25', 1),
                                                                 (160208, '2022-01-13', 1),
                                                                 (648216, '2021-12-25', 2),
                                                                 (648216, '2022-01-12', 2),
                                                                 (658487, '2021-12-26', 3),
                                                                 (658487, '2021-12-27', 3);
/*!40000 ALTER TABLE `pagos` ENABLE KEYS */;

-- Volcando estructura para tabla platinumcenter.rutinas
CREATE TABLE IF NOT EXISTS `rutinas` (
                                         `idRutina` int NOT NULL,
                                         `idCreador` int NOT NULL,
                                         `nombreRutina` varchar(50) NOT NULL,
    `descripcion` varchar(500) NOT NULL,
    `calorias` int NOT NULL DEFAULT '0',
    PRIMARY KEY (`idRutina`),
    UNIQUE KEY `nombreRutina` (`nombreRutina`),
    KEY `idCreador` (`idCreador`),
    CONSTRAINT `rutinas_ibfk_1` FOREIGN KEY (`idCreador`) REFERENCES `usuarios` (`idUsuario`) ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla platinumcenter.rutinas: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `rutinas` DISABLE KEYS */;
INSERT INTO `rutinas` (`idRutina`, `idCreador`, `nombreRutina`, `descripcion`, `calorias`) VALUES
                                                                                               (1042, 160208, 'RutinaPierna', 'Realizar Lunes y miercoles', 120),
                                                                                               (1325, 658487, 'RutinaCardio', 'Realizar una vez al dia', 220),
                                                                                               (3658, 658487, 'RutinaPecho', 'Realizar Martes y Jueves', 100),
                                                                                               (5482, 658487, 'Rutina Power', 'Realizar Todos los dias', 1000),
                                                                                               (5685, 658487, 'RutinaBrazo', 'Realizar Lunes y miercoles', 300),
                                                                                               (6584, 658487, 'Pilates tradicional', 'Conjunto ejercicios de pilates', 300),
                                                                                               (6845, 658487, 'RutinaGAP', 'Realizar Cada dos dias', 80);
/*!40000 ALTER TABLE `rutinas` ENABLE KEYS */;

-- Volcando estructura para tabla platinumcenter.rutinasclientes
CREATE TABLE IF NOT EXISTS `rutinasclientes` (
                                                 `idCliente` int NOT NULL,
                                                 `idRutina` int NOT NULL,
                                                 PRIMARY KEY (`idCliente`,`idRutina`),
    KEY `idRutina` (`idRutina`),
    CONSTRAINT `rutinasclientes_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `usuarios` (`idUsuario`) ON DELETE CASCADE,
    CONSTRAINT `rutinasclientes_ibfk_2` FOREIGN KEY (`idRutina`) REFERENCES `rutinas` (`idRutina`) ON DELETE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla platinumcenter.rutinasclientes: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `rutinasclientes` DISABLE KEYS */;
INSERT INTO `rutinasclientes` (`idCliente`, `idRutina`) VALUES
                                                            (615895, 5482),
                                                            (652486, 5685),
                                                            (547623, 6584);
/*!40000 ALTER TABLE `rutinasclientes` ENABLE KEYS */;

-- Volcando estructura para tabla platinumcenter.salarios
CREATE TABLE IF NOT EXISTS `salarios` (
                                          `idSalario` int NOT NULL,
                                          `nombreSalario` varchar(50) NOT NULL,
    `cantidad` float(3,2) NOT NULL,
    `descripcion` varchar(500) NOT NULL,
    PRIMARY KEY (`idSalario`),
    UNIQUE KEY `nombreSalario` (`nombreSalario`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla platinumcenter.salarios: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `salarios` DISABLE KEYS */;
INSERT INTO `salarios` (`idSalario`, `nombreSalario`, `cantidad`, `descripcion`) VALUES
                                                                                     (0, 'null', 0.00, 'null'),
                                                                                     (1, 'Dueno', 9.00, 'Salario del dueno'),
                                                                                     (2, 'Jefe de Seccion', 6.00, 'Salario de jefe de seccion'),
                                                                                     (3, 'Empleado estandar', 3.00, 'Salario empleado estandar');
/*!40000 ALTER TABLE `salarios` ENABLE KEYS */;

-- Volcando estructura para tabla platinumcenter.salas
CREATE TABLE IF NOT EXISTS `salas` (
                                       `idSala` int NOT NULL,
                                       `nombreSala` varchar(50) NOT NULL,
    `aforo` int NOT NULL,
    `apertura` time NOT NULL,
    `cierre` time NOT NULL,
    PRIMARY KEY (`idSala`),
    UNIQUE KEY `nombreSala` (`nombreSala`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla platinumcenter.salas: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `salas` DISABLE KEYS */;
INSERT INTO `salas` (`idSala`, `nombreSala`, `aforo`, `apertura`, `cierre`) VALUES
                                                                                (101, 'Cardio', 10, '10:00:00', '20:00:00'),
                                                                                (102, 'Rocodromo', 35, '10:00:00', '20:00:00'),
                                                                                (103, 'Zumba', 8, '14:00:00', '20:00:00'),
                                                                                (104, 'Spinning', 15, '10:00:00', '13:00:00'),
                                                                                (105, 'Boxeo', 8, '15:00:00', '17:00:00');
/*!40000 ALTER TABLE `salas` ENABLE KEYS */;

-- Volcando estructura para tabla platinumcenter.tarifas
CREATE TABLE IF NOT EXISTS `tarifas` (
                                         `idTarifa` int NOT NULL,
                                         `nombreTarifa` varchar(50) NOT NULL,
    `importe` float NOT NULL,
    `descripcion` varchar(500) NOT NULL,
    PRIMARY KEY (`idTarifa`),
    UNIQUE KEY `nombreTarifa` (`nombreTarifa`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla platinumcenter.tarifas: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `tarifas` DISABLE KEYS */;
INSERT INTO `tarifas` (`idTarifa`, `nombreTarifa`, `importe`, `descripcion`) VALUES
                                                                                 (0, 'null', 0, 'null'),
                                                                                 (1, 'mensual', 3, 'tarifa pago mensual'),
                                                                                 (2, 'trimestral', 6, 'tarifa pago trimestral'),
                                                                                 (3, 'anual', 9, 'tarifa pago anual');
/*!40000 ALTER TABLE `tarifas` ENABLE KEYS */;

-- Volcando estructura para tabla platinumcenter.usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
                                          `idUsuario` int NOT NULL,
                                          `nombreCompleto` varchar(100) NOT NULL,
    `nombreUsuario` varchar(20) NOT NULL,
    `contrasena` varchar(20) NOT NULL,
    `fechaNacimiento` date NOT NULL,
    `CIF` varchar(9) NOT NULL,
    `telefono` varchar(9) NOT NULL,
    `email` varchar(50) NOT NULL,
    `IBAN` varchar(24) NOT NULL,
    `direccion` varchar(60) NOT NULL,
    `fechaAlta` date NOT NULL,
    `idTarifa` int DEFAULT '0',
    `idSalario` int DEFAULT '0',
    `tipoUsuario` int NOT NULL,
    PRIMARY KEY (`idUsuario`),
    UNIQUE KEY `nombreUsuario` (`nombreUsuario`),
    KEY `idTarifa` (`idTarifa`),
    KEY `idSalario` (`idSalario`),
    CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`idTarifa`) REFERENCES `tarifas` (`idTarifa`),
    CONSTRAINT `usuarios_ibfk_2` FOREIGN KEY (`idSalario`) REFERENCES `salarios` (`idSalario`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla platinumcenter.usuarios: ~6 rows (aproximadamente)
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` (`idUsuario`, `nombreCompleto`, `nombreUsuario`, `contrasena`, `fechaNacimiento`, `CIF`, `telefono`, `email`, `IBAN`, `direccion`, `fechaAlta`, `idTarifa`, `idSalario`, `tipoUsuario`) VALUES
                                                                                                                                                                                                                   (160208, 'root', 'root', 'ordenador', '1999-12-20', '7147', '658945698', 'admin@platinumcenter.es', '562548', 'leon', '2022-01-06', 0, 1, 2),
                                                                                                                                                                                                                   (547623, 'Ana Arias', 'Ana', 'limon', '1994-03-08', '6988', '635482489', 'ana@ule.es', '698425', 'Leon', '2022-02-10', 2, 0, 0),
                                                                                                                                                                                                                   (615895, 'Dani Guzman', 'Dani', 'hola', '1998-10-13', '8095', '245873549', 'dani@gmail.es', '214825', 'padreisla', '2022-01-06', 3, 0, 0),
                                                                                                                                                                                                                   (648216, 'Alberto Garcia', 'Alberto', 'pass', '1998-04-11', '3287', '365189658', 'alberto@platinumcenter.es', '653489', 'palomera', '2022-01-06', 0, 2, 1),
                                                                                                                                                                                                                   (652486, 'Fernando Blanco', 'Fernando', 'facil', '1995-10-11', '2188', '624868957', 'fernando@ayuntamiento.es', '679548', 'Madrid', '2021-05-07', 2, 0, 0),
                                                                                                                                                                                                                   (658487, 'Pepe Navarro', 'Pepe', 'platinumcenter', '1985-07-12', '2486', '356742684', 'pepe@platinumcenter.es', '682487', 'Leon', '2022-01-10', 0, 3, 1);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
