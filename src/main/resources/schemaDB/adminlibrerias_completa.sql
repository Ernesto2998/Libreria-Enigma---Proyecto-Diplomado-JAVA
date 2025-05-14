-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 13, 2025 at 07:07 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `adminlibrerias`
--

-- --------------------------------------------------------

--
-- Table structure for table `autor`
--

CREATE TABLE `autor` (
  `id_autor` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `apellido_1` varchar(255) NOT NULL,
  `apellido_2` varchar(255) NOT NULL,
  `id_nacionalidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `autor`
--

INSERT INTO `autor` (`id_autor`, `nombre`, `apellido_1`, `apellido_2`, `id_nacionalidad`) VALUES
(1, 'Gabriel', 'García', 'Márquez', 3),
(2, 'Isabel', 'Allendee', 'Lona', 4),
(3, 'Jorge', 'Luis', 'Borges', 2),
(4, 'Mario', 'Vargas', 'Llosa', 5),
(5, 'Julio', 'Cortázar', 'Scott', 2),
(6, 'Carlos', 'Fuentes', 'Macías', 1),
(7, 'Pablo', 'Neruda', 'Reyes', 4),
(8, 'Laura', 'Esquivel', 'Valdés', 1),
(9, 'Octavio', 'Paz', 'Lozano', 1),
(10, 'Juan', 'Rulfo', 'Vizcaíno', 1),
(11, 'Luisa', 'Valenzuela', 'Costamagna', 2),
(12, 'Roberto', 'Bolaño', 'Ávalos', 4),
(13, 'Elena', 'Poniatowska', 'Amor', 1),
(14, 'José', 'Martí', 'Pérez', 6),
(15, 'Rosario', 'Castellanos', 'Figueroa', 1),
(18, 'Ernesto', 'Velasco', 'Arciniega', 28),
(26, 'Axel', 'Castillo', 'Sanchez', 5),
(29, 'Nombre', 'Peterno', 'Materno', 28);

-- --------------------------------------------------------

--
-- Table structure for table `clasificacion`
--

CREATE TABLE `clasificacion` (
  `id_clasificacion` int(11) NOT NULL,
  `tipo_clasificacion` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `clasificacion`
--

INSERT INTO `clasificacion` (`id_clasificacion`, `tipo_clasificacion`) VALUES
(11, 'Aventura'),
(16, 'Biografía'),
(12, 'Ciencia ficción'),
(17, 'Clásicos'),
(20, 'Comedia'),
(24, 'Crónica'),
(5, 'Cuentos'),
(19, 'Drama'),
(30, 'Educativa'),
(4, 'Ensayo'),
(13, 'Fantasía'),
(7, 'Ficción latinoamericana'),
(27, 'Historias de amor'),
(31, 'Histórica'),
(22, 'Infantil'),
(28, 'Inspiracional'),
(21, 'Juvenil'),
(18, 'Literatura contemporánea'),
(8, 'Literatura experimental'),
(32, 'Misterio'),
(26, 'Mitología'),
(10, 'Narrativa sociale'),
(2, 'Novela histórica'),
(36, 'Novela ligera japonesa'),
(23, 'Novela negra'),
(9, 'Novela psicológica'),
(6, 'Novela romántica'),
(3, 'Poesía'),
(1, 'Realismo mágico'),
(25, 'Relatos cortos'),
(15, 'Suspenso'),
(14, 'Terror'),
(29, 'Viajes');

-- --------------------------------------------------------

--
-- Table structure for table `editorial`
--

CREATE TABLE `editorial` (
  `id_editorial` int(11) NOT NULL,
  `editorial` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `editorial`
--

INSERT INTO `editorial` (`id_editorial`, `editorial`) VALUES
(18, 'Alfred A. Knopf'),
(12, 'Cambridge University Press'),
(15, 'Farrar, Straus and Giroux'),
(5, 'Hachette Livre'),
(2, 'HarperCollins'),
(14, 'Harvard University Press'),
(19, 'Knopf Doubleday Publishing Group'),
(3, 'Macmillan'),
(16, 'McGraw-Hill Education'),
(8, 'Oxford University Press'),
(6, 'Pearson'),
(13, 'Penguin Books'),
(1, 'Penguin Random House'),
(17, 'Princeton University Press'),
(11, 'Routledge'),
(9, 'Scholastic'),
(4, 'Simon & Schuster'),
(10, 'Springer'),
(20, 'The MIT Press'),
(7, 'Wiley');

-- --------------------------------------------------------

--
-- Table structure for table `empleado`
--

CREATE TABLE `empleado` (
  `num_empleado` int(11) NOT NULL,
  `id_sucursal` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido_1` varchar(50) NOT NULL,
  `apellido_2` varchar(50) NOT NULL,
  `genero` enum('F','M') NOT NULL,
  `nivel_acceso` enum('Admin','User') NOT NULL,
  `contrasenia` varchar(255) NOT NULL CHECK (char_length(`contrasenia`) >= 8)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `empleado`
--

INSERT INTO `empleado` (`num_empleado`, `id_sucursal`, `nombre`, `apellido_1`, `apellido_2`, `genero`, `nivel_acceso`, `contrasenia`) VALUES
(1, 1, 'Admin', 'Admin', 'Admin', 'M', 'Admin', '$2a$12$GrVpe9mwk7g/aPaDPj.mletIkTZvwa3yQLVCi4wH1JbweWeF864SW'),
(2, 1, 'María', 'López', 'Fernández', 'F', 'User', 'MariaL2024@'),
(3, 2, 'Carlos', 'Martínez', 'Sánchez', 'M', 'User', 'CarlosM2024#'),
(4, 2, 'Ana', 'Rodríguez', 'Díaz', 'F', 'User', 'AnaRodr2024$'),
(5, 3, 'Luis', 'Hernández', 'Torres', 'M', 'User', 'LuisHern2024!'),
(6, 3, 'Laura', 'Ramírez', 'Pérez', 'F', 'User', 'LauraRP2024%'),
(7, 4, 'José', 'González', 'Flores', 'M', 'User', 'JoseGonz2024*'),
(8, 4, 'Marta', 'Jiménez', 'Rivas', 'F', 'User', 'MartaJim2024+'),
(9, 5, 'Pedro', 'Suárez', 'Molina', 'M', 'User', 'PedroSuar2024-'),
(10, 5, 'Lucía', 'Navarro', 'Romero', 'F', 'User', 'LuciaNav2024!'),
(11, 6, 'Miguel', 'García', 'Gil', 'M', 'User', 'MiguelGar2024@'),
(12, 6, 'Isabel', 'Ruiz', 'Soler', 'F', 'User', 'IsabelRui2024#'),
(13, 7, 'Fernando', 'Gutiérrez', 'Ramos', 'M', 'User', 'FerGutier2024$'),
(14, 7, 'Elena', 'Prieto', 'Castro', 'F', 'User', 'ElenaPri2024%'),
(15, 8, 'Ricardo', 'Santos', 'Duarte', 'M', 'User', 'RicardoSan2024&'),
(16, 8, 'Sofía', 'Núñez', 'Delgado', 'F', 'User', 'SofiaNun2024*'),
(17, 9, 'Jorge', 'Alonso', 'Blanco', 'M', 'User', 'JorgeAlo2024@'),
(18, 9, 'Carmen', 'Ortega', 'Campos', 'F', 'User', 'CarmenOrt2024#'),
(19, 10, 'David', 'Serrano', 'Cano', 'M', 'User', 'DavidSer2024$'),
(20, 10, 'Patricia', 'Castillo', 'Álvarez', 'F', 'User', 'PatriciaCas2024%'),
(21, 11, 'Adrián', 'Aguilar', 'Vega', 'M', 'User', 'AdrianAgui2024&'),
(22, 11, 'Clara', 'Montes', 'Marín', 'F', 'User', 'ClaraMont2024!'),
(23, 12, 'Víctor', 'Pascual', 'Herrera', 'M', 'User', 'VictorPas2024@'),
(24, 12, 'Teresa', 'Sierra', 'Morales', 'F', 'User', 'TeresaSie2024#'),
(25, 13, 'Francisco', 'Reyes', 'Luna', 'M', 'User', 'FranciscoRey2024$'),
(26, 13, 'Pilar', 'Méndez', 'Reina', 'F', 'User', 'PilarMen2024%'),
(27, 14, 'Tomás', 'Roca', 'Campos', 'M', 'User', 'TomasRoca2024&'),
(28, 14, 'Cristina', 'Mora', 'Gil', 'F', 'User', 'CristinaMora2024*'),
(29, 15, 'Emilio', 'Nieto', 'Soto', 'M', 'User', 'EmilioNie2024@');

-- --------------------------------------------------------

--
-- Table structure for table `inventario`
--

CREATE TABLE `inventario` (
  `id_inventario` int(11) NOT NULL,
  `id_libro` int(11) NOT NULL,
  `id_sucursal` int(11) NOT NULL,
  `stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `inventario`
--

INSERT INTO `inventario` (`id_inventario`, `id_libro`, `id_sucursal`, `stock`) VALUES
(1, 16, 1, 18),
(2, 16, 4, 18),
(3, 16, 2, 35),
(4, 16, 13, 19),
(5, 16, 3, 42),
(6, 16, 5, 1),
(7, 16, 7, 27),
(8, 16, 10, 32),
(9, 16, 6, 30),
(10, 16, 12, 3),
(11, 16, 9, 23),
(12, 16, 8, 6),
(13, 16, 14, 9),
(14, 16, 11, 27),
(15, 16, 15, 8),
(16, 23, 1, 7),
(17, 23, 4, 12),
(18, 23, 2, 37),
(19, 23, 13, 48),
(20, 23, 3, 29),
(21, 23, 5, 50),
(22, 23, 7, 11),
(23, 23, 10, 7),
(24, 23, 6, 1),
(25, 23, 12, 34),
(26, 23, 9, 17),
(27, 23, 8, 34),
(28, 23, 14, 16),
(29, 23, 11, 29),
(30, 23, 15, 45),
(31, 2, 1, 39),
(32, 2, 4, 10),
(33, 2, 2, 31),
(34, 2, 13, 25),
(35, 2, 3, 30),
(36, 2, 5, 26),
(37, 2, 7, 37),
(38, 2, 10, 10),
(39, 2, 6, 38),
(40, 2, 12, 8),
(41, 2, 9, 26),
(42, 2, 8, 7),
(43, 2, 14, 6),
(44, 2, 11, 6),
(45, 2, 15, 14),
(46, 15, 1, 49),
(47, 15, 4, 6),
(48, 15, 2, 29),
(49, 15, 13, 29),
(50, 15, 3, 8),
(51, 15, 5, 50),
(52, 15, 7, 25),
(53, 15, 10, 26),
(54, 15, 6, 4),
(55, 15, 12, 43),
(56, 15, 9, 3),
(57, 15, 8, 33),
(58, 15, 14, 9),
(59, 15, 11, 42),
(60, 15, 15, 35),
(61, 5, 1, 46),
(62, 5, 4, 25),
(63, 5, 2, 36),
(64, 5, 13, 6),
(65, 5, 3, 19),
(66, 5, 5, 30),
(67, 5, 7, 41),
(68, 5, 10, 12),
(69, 5, 6, 40),
(70, 5, 12, 12),
(71, 5, 9, 38),
(72, 5, 8, 3),
(73, 5, 14, 3),
(74, 5, 11, 3),
(75, 5, 15, 8),
(76, 14, 1, 31),
(77, 14, 4, 31),
(78, 14, 2, 10),
(79, 14, 13, 8),
(80, 14, 3, 9),
(81, 14, 5, 21),
(82, 14, 7, 28),
(83, 14, 10, 24),
(84, 14, 6, 38),
(85, 14, 12, 19),
(86, 14, 9, 27),
(87, 14, 8, 29),
(88, 14, 14, 14),
(89, 14, 11, 31),
(90, 14, 15, 12),
(91, 12, 1, 18),
(92, 12, 4, 4),
(93, 12, 2, 14),
(94, 12, 13, 10),
(95, 12, 3, 7),
(96, 12, 5, 4),
(97, 12, 7, 47),
(98, 12, 10, 22),
(99, 12, 6, 20),
(100, 12, 12, 32),
(101, 12, 9, 49),
(102, 12, 8, 50),
(103, 12, 14, 1),
(104, 12, 11, 4),
(105, 12, 15, 18),
(106, 21, 1, 28),
(107, 21, 4, 37),
(108, 21, 2, 49),
(109, 21, 13, 32),
(110, 21, 3, 13),
(111, 21, 5, 19),
(112, 21, 7, 4),
(113, 21, 10, 13),
(114, 21, 6, 4),
(115, 21, 12, 29),
(116, 21, 9, 31),
(117, 21, 8, 21),
(118, 21, 14, 8),
(119, 21, 11, 28),
(120, 21, 15, 15),
(121, 29, 1, 39),
(122, 29, 4, 50),
(123, 29, 2, 35),
(124, 29, 13, 23),
(125, 29, 3, 8),
(126, 29, 5, 23),
(127, 29, 7, 38),
(128, 29, 10, 21),
(129, 29, 6, 43),
(130, 29, 12, 49),
(131, 29, 9, 17),
(132, 29, 8, 35),
(133, 29, 14, 26),
(134, 29, 11, 22),
(135, 29, 15, 34),
(136, 19, 1, 1),
(137, 19, 4, 3),
(138, 19, 2, 13),
(139, 19, 13, 3),
(140, 19, 3, 28),
(141, 19, 5, 29),
(142, 19, 7, 10),
(143, 19, 10, 12),
(144, 19, 6, 30),
(145, 19, 12, 16),
(146, 19, 9, 36),
(147, 19, 8, 34),
(148, 19, 14, 12),
(149, 19, 11, 9),
(150, 19, 15, 5),
(151, 28, 1, 49),
(152, 28, 4, 31),
(153, 28, 2, 7),
(154, 28, 13, 41),
(155, 28, 3, 32),
(156, 28, 5, 36),
(157, 28, 7, 34),
(158, 28, 10, 13),
(159, 28, 6, 10),
(160, 28, 12, 13),
(161, 28, 9, 32),
(162, 28, 8, 24),
(163, 28, 14, 20),
(164, 28, 11, 28),
(165, 28, 15, 32),
(166, 8, 1, 23),
(167, 8, 4, 20),
(168, 8, 2, 31),
(169, 8, 13, 45),
(170, 8, 3, 29),
(171, 8, 5, 11),
(172, 8, 7, 17),
(173, 8, 10, 3),
(174, 8, 6, 11),
(175, 8, 12, 46),
(176, 8, 9, 48),
(177, 8, 8, 3),
(178, 8, 14, 17),
(179, 8, 11, 26),
(180, 8, 15, 30),
(181, 24, 1, 21),
(182, 24, 4, 16),
(183, 24, 2, 15),
(184, 24, 13, 26),
(185, 24, 3, 36),
(186, 24, 5, 1),
(187, 24, 7, 48),
(188, 24, 10, 34),
(189, 24, 6, 26),
(190, 24, 12, 29),
(191, 24, 9, 14),
(192, 24, 8, 34),
(193, 24, 14, 29),
(194, 24, 11, 42),
(195, 24, 15, 23),
(196, 18, 1, 37),
(197, 18, 4, 14),
(198, 18, 2, 11),
(199, 18, 13, 14),
(200, 18, 3, 34),
(201, 18, 5, 30),
(202, 18, 7, 46),
(203, 18, 10, 41),
(204, 18, 6, 15),
(205, 18, 12, 3),
(206, 18, 9, 19),
(207, 18, 8, 37),
(208, 18, 14, 28),
(209, 18, 11, 27),
(210, 18, 15, 49),
(211, 3, 1, 14),
(212, 3, 4, 21),
(213, 3, 2, 14),
(214, 3, 13, 7),
(215, 3, 3, 41),
(216, 3, 5, 36),
(217, 3, 7, 7),
(218, 3, 10, 25),
(219, 3, 6, 3),
(220, 3, 12, 40),
(221, 3, 9, 39),
(222, 3, 8, 28),
(223, 3, 14, 19),
(224, 3, 11, 11),
(225, 3, 15, 48),
(226, 25, 1, 6),
(227, 25, 4, 34),
(228, 25, 2, 2),
(229, 25, 13, 9),
(230, 25, 3, 39),
(231, 25, 5, 17),
(232, 25, 7, 19),
(233, 25, 10, 43),
(234, 25, 6, 5),
(235, 25, 12, 47),
(236, 25, 9, 20),
(237, 25, 8, 8),
(238, 25, 14, 28),
(239, 25, 11, 18),
(240, 25, 15, 6),
(241, 20, 1, 23),
(242, 20, 4, 48),
(243, 20, 2, 20),
(244, 20, 13, 4),
(245, 20, 3, 10),
(246, 20, 5, 39),
(247, 20, 7, 13),
(248, 20, 10, 47),
(249, 20, 6, 45),
(250, 20, 12, 35),
(251, 20, 9, 40),
(252, 20, 8, 46),
(253, 20, 14, 6),
(254, 20, 11, 44),
(255, 20, 15, 1),
(256, 10, 1, 21),
(257, 10, 4, 1),
(258, 10, 2, 44),
(259, 10, 13, 14),
(260, 10, 3, 38),
(261, 10, 5, 49),
(262, 10, 7, 31),
(263, 10, 10, 7),
(264, 10, 6, 41),
(265, 10, 12, 33),
(266, 10, 9, 44),
(267, 10, 8, 17),
(268, 10, 14, 5),
(269, 10, 11, 21),
(270, 10, 15, 42),
(271, 13, 1, 45),
(272, 13, 4, 49),
(273, 13, 2, 10),
(274, 13, 13, 1),
(275, 13, 3, 23),
(276, 13, 5, 15),
(277, 13, 7, 3),
(278, 13, 10, 21),
(279, 13, 6, 47),
(280, 13, 12, 19),
(281, 13, 9, 5),
(282, 13, 8, 15),
(283, 13, 14, 11),
(284, 13, 11, 10),
(285, 13, 15, 14),
(286, 26, 1, 43),
(287, 26, 4, 20),
(288, 26, 2, 21),
(289, 26, 13, 43),
(290, 26, 3, 3),
(291, 26, 5, 37),
(292, 26, 7, 23),
(293, 26, 10, 4),
(294, 26, 6, 1),
(295, 26, 12, 41),
(296, 26, 9, 4),
(297, 26, 8, 47),
(298, 26, 14, 23),
(299, 26, 11, 24),
(300, 26, 15, 50),
(301, 9, 1, 27),
(302, 9, 4, 35),
(303, 9, 2, 43),
(304, 9, 13, 10),
(305, 9, 3, 21),
(306, 9, 5, 25),
(307, 9, 7, 9),
(308, 9, 10, 22),
(309, 9, 6, 30),
(310, 9, 12, 33),
(311, 9, 9, 28),
(312, 9, 8, 38),
(313, 9, 14, 5),
(314, 9, 11, 11),
(315, 9, 15, 38),
(316, 30, 1, 7),
(317, 30, 4, 23),
(318, 30, 2, 42),
(319, 30, 13, 39),
(320, 30, 3, 20),
(321, 30, 5, 30),
(322, 30, 7, 42),
(323, 30, 10, 21),
(324, 30, 6, 25),
(325, 30, 12, 12),
(326, 30, 9, 37),
(327, 30, 8, 47),
(328, 30, 14, 25),
(329, 30, 11, 32),
(330, 30, 15, 34),
(331, 1, 1, 25),
(332, 1, 4, 22),
(333, 1, 2, 35),
(334, 1, 13, 10),
(335, 1, 3, 41),
(336, 1, 5, 28),
(337, 1, 7, 14),
(338, 1, 10, 34),
(339, 1, 6, 30),
(340, 1, 12, 47),
(341, 1, 9, 43),
(342, 1, 8, 23),
(343, 1, 14, 35),
(344, 1, 11, 7),
(345, 1, 15, 28),
(346, 22, 1, 18),
(347, 22, 4, 5),
(348, 22, 2, 19),
(349, 22, 13, 33),
(350, 22, 3, 8),
(351, 22, 5, 37),
(352, 22, 7, 13),
(353, 22, 10, 3),
(354, 22, 6, 24),
(355, 22, 12, 12),
(356, 22, 9, 38),
(357, 22, 8, 4),
(358, 22, 14, 4),
(359, 22, 11, 7),
(360, 22, 15, 25),
(361, 27, 1, 1),
(362, 27, 4, 29),
(363, 27, 2, 42),
(364, 27, 13, 22),
(365, 27, 3, 34),
(366, 27, 5, 6),
(367, 27, 7, 24),
(368, 27, 10, 5),
(369, 27, 6, 49),
(370, 27, 12, 33),
(371, 27, 9, 15),
(372, 27, 8, 26),
(373, 27, 14, 35),
(374, 27, 11, 44),
(375, 27, 15, 18),
(376, 11, 1, 5),
(377, 11, 4, 23),
(378, 11, 2, 46),
(379, 11, 13, 14),
(380, 11, 3, 29),
(381, 11, 5, 3),
(382, 11, 7, 26),
(383, 11, 10, 22),
(384, 11, 6, 33),
(385, 11, 12, 47),
(386, 11, 9, 37),
(387, 11, 8, 41),
(388, 11, 14, 44),
(389, 11, 11, 47),
(390, 11, 15, 4),
(391, 4, 1, 28),
(392, 4, 4, 28),
(393, 4, 2, 3),
(394, 4, 13, 32),
(395, 4, 3, 1),
(396, 4, 5, 7),
(397, 4, 7, 32),
(398, 4, 10, 37),
(399, 4, 6, 41),
(400, 4, 12, 44),
(401, 4, 9, 48),
(402, 4, 8, 4),
(403, 4, 14, 26),
(404, 4, 11, 19),
(405, 4, 15, 15),
(406, 17, 1, 17),
(407, 17, 4, 39),
(408, 17, 2, 45),
(409, 17, 13, 9),
(410, 17, 3, 7),
(411, 17, 5, 6),
(412, 17, 7, 11),
(413, 17, 10, 37),
(414, 17, 6, 1),
(415, 17, 12, 42),
(416, 17, 9, 9),
(417, 17, 8, 17),
(418, 17, 14, 8),
(419, 17, 11, 37),
(420, 17, 15, 13),
(421, 7, 1, 2),
(422, 7, 4, 19),
(423, 7, 2, 39),
(424, 7, 13, 38),
(425, 7, 3, 23),
(426, 7, 5, 48),
(427, 7, 7, 23),
(428, 7, 10, 18),
(429, 7, 6, 23),
(430, 7, 12, 9),
(431, 7, 9, 24),
(432, 7, 8, 46),
(433, 7, 14, 4),
(434, 7, 11, 34),
(435, 7, 15, 8),
(436, 6, 1, 35),
(437, 6, 4, 4),
(438, 6, 2, 12),
(439, 6, 13, 47),
(440, 6, 3, 1),
(441, 6, 5, 13),
(442, 6, 7, 12),
(443, 6, 10, 20),
(444, 6, 6, 13),
(445, 6, 12, 5),
(446, 6, 9, 35),
(447, 6, 8, 9),
(448, 6, 14, 41),
(449, 6, 11, 28),
(450, 6, 15, 16);

-- --------------------------------------------------------

--
-- Table structure for table `libro`
--

CREATE TABLE `libro` (
  `id_libro` int(11) NOT NULL,
  `id_editorial` int(11) NOT NULL,
  `titulo` varchar(255) NOT NULL,
  `tipo_pasta` varchar(255) NOT NULL,
  `sinopsis` varchar(255) NOT NULL,
  `precio` float DEFAULT NULL,
  `descuento` int(11) NOT NULL CHECK (`descuento` >= 0 and `descuento` <= 100)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `libro`
--

INSERT INTO `libro` (`id_libro`, `id_editorial`, `titulo`, `tipo_pasta`, `sinopsis`, `precio`, `descuento`) VALUES
(1, 19, 'El Secretos', 'Blanda', 'Un libro sobre cómo alcanzar el éxito personal.', 19.99, 21),
(2, 18, 'Cuentos de la Abuela', 'Dura', 'Recopilación de cuentos para niños de todas las edades.', 15.5, 15),
(3, 15, 'La Ciencia del Futuro', 'Blanda', 'Exploración sobre las teorías científicas que modelarán el futuro.', 25, 15),
(4, 5, 'La Historia del Mundo', 'Dura', 'Un análisis profundo de los eventos más relevantes de la historia mundial.', 30.99, 20),
(5, 2, 'Filosofía para Todos', 'Blanda', 'Una introducción accesible a los grandes pensadores de la filosofía.', 12.5, 5),
(6, 14, 'Biografía de un Genio', 'Dura', 'La vida y obra de un destacado científico del siglo XXI.', 40, 10),
(7, 19, 'El Hombre que Sabía Demasiado', 'Blanda', 'Un thriller psicológico con giros sorprendentes.', 18.75, 8),
(8, 3, 'El Viaje Imposible', 'Dura', 'Una novela de ciencia ficción que trasciende los límites del tiempo.', 22, 12),
(9, 16, 'El Poder del Amor', 'Blanda', 'Una historia romántica entre dos mundos opuestos.', 14.99, 15),
(10, 8, 'Secretos de la Naturaleza', 'Dura', 'Un libro sobre los misterios aún no resueltos de nuestro planeta.', 28.99, 10),
(11, 12, 'Bajo la Luna', 'Blanda', 'Poesía que explora la conexión del ser humano con el universo.', 9.99, 5),
(12, 6, 'Vivir con Propósito', 'Dura', 'Consejos prácticos para vivir una vida más plena y significativa.', 21.5, 18),
(13, 13, 'Crónicas de Terror', 'Blanda', 'Relatos escalofriantes que te mantendrán despierto por la noche.', 13, 0),
(14, 1, 'Aventuras Inesperadas', 'Dura', 'Una serie de cuentos de aventuras en lugares exóticos.', 17.99, 10),
(15, 11, 'En la Mente del Ladrón', 'Blanda', 'Un misterioso relato sobre crimen y justicia.', 16.75, 25),
(16, 9, 'El Libro de los Sueños', 'Dura', 'Una obra literaria que explora la relación entre los sueños y la realidad.', 23.99, 15),
(17, 9, 'Los Últimos Días del Imperio', 'Blanda', 'Análisis histórico sobre el colapso de civilizaciones antiguas.', 20, 5),
(18, 4, 'El Arte de la Guerra', 'Dura', 'Un tratado clásico sobre estrategia y liderazgo, actualizado para los tiempos modernos.', 18, 20),
(19, 10, 'En Busca de la Verdad', 'Blanda', 'Un viaje filosófico hacia el entendimiento de la realidad.', 17.5, 12),
(20, 20, 'Economía en Tiempos Modernos', 'Dura', 'Estudio de las tendencias económicas actuales en el mundo globalizado.', 29.99, 10),
(21, 7, 'Cuentos de Terror', 'Blanda', 'Una serie de relatos macabros para los amantes del género.', 10, 5),
(22, 12, 'El Arte de Vivir', 'Dura', 'Guía para vivir una vida más feliz y equilibrada.', 24.5, 18),
(23, 15, 'La Magia del Universo', 'Blanda', 'Un libro sobre los secretos cósmicos que rigen nuestra existencia.', 20.75, 8),
(24, 5, 'El Último Suspiro', 'Dura', 'Una novela de suspenso que te atrapará hasta la última página.', 27, 5),
(25, 2, 'La Fuerza de la Mente', 'Blanda', 'Cómo utilizar el poder de la mente para alcanzar tus objetivos.', 11.99, 10),
(26, 14, 'Lágrimas de Sangre', 'Dura', 'Un thriller de acción que desafía las convenciones del género.', 30, 12),
(27, 19, 'Bajo el Sol', 'Blanda', 'Relatos de superación y esperanza en tiempos difíciles.', 14, 15),
(28, 3, 'La Última Frontera', 'Dura', 'Una novela de ciencia ficción que cuestiona los límites del espacio y la mente humana.', 26.5, 8),
(29, 3, 'Amor a Primera Vista', 'Blanda', 'Historia romántica ambientada en una ciudad vibrante y llena de vida.', 13.5, 0),
(30, 20, 'Los Misterios del Océano', 'Dura', 'Un libro sobre los misterios más profundos del océano y sus criaturas.', 22.5, 20),
(33, 7, 'Re:Zero', 'Blanda', 'Ya me quiero dormir. Será que por fin lo logre ... hora 12.57am D:', 10.26, 10),
(34, 18, 'Re:Zero', 'Dura', 'Este es un libro de prieba. Creo ya quedo el ADD de libros 🙌', 350, 29),
(41, 10, 'Nuevo Libro Editado', 'Blanda', 'Resumen de nuevo libro editado', 150, 5),
(45, 1, 'Libro Nuevo', 'Dura', 'Una nueva sinopsis', 99.99, 25);

-- --------------------------------------------------------

--
-- Table structure for table `libro_autor`
--

CREATE TABLE `libro_autor` (
  `id_libro_autor` int(11) NOT NULL,
  `id_autor` int(11) NOT NULL,
  `id_libro` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `libro_autor`
--

INSERT INTO `libro_autor` (`id_libro_autor`, `id_autor`, `id_libro`) VALUES
(160, 1, 3),
(8, 1, 13),
(187, 1, 45),
(146, 2, 1),
(88, 2, 2),
(161, 2, 3),
(162, 2, 4),
(167, 2, 9),
(173, 2, 11),
(175, 2, 30),
(188, 2, 45),
(165, 3, 7),
(171, 3, 10),
(9, 4, 13),
(181, 4, 28),
(168, 5, 9),
(169, 6, 9),
(170, 7, 9),
(2, 7, 13),
(182, 9, 27),
(183, 10, 27),
(166, 11, 8),
(184, 11, 27),
(179, 13, 29),
(178, 13, 33),
(164, 14, 6),
(172, 14, 10),
(185, 14, 12),
(4, 14, 13),
(180, 14, 29),
(176, 14, 41),
(163, 15, 5),
(174, 15, 11),
(3, 15, 13),
(186, 15, 14),
(177, 15, 41),
(139, 18, 34);

-- --------------------------------------------------------

--
-- Table structure for table `libro_clasificacion`
--

CREATE TABLE `libro_clasificacion` (
  `id_libro_clasificacion` int(11) NOT NULL,
  `id_clasificacion` int(11) NOT NULL,
  `id_libro` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `libro_clasificacion`
--

INSERT INTO `libro_clasificacion` (`id_libro_clasificacion`, `id_clasificacion`, `id_libro`) VALUES
(662, 1, 45),
(297, 2, 13),
(663, 2, 45),
(653, 7, 29),
(635, 11, 3),
(109, 11, 13),
(611, 12, 1),
(637, 12, 4),
(644, 12, 10),
(646, 12, 11),
(659, 12, 12),
(612, 13, 1),
(638, 14, 5),
(599, 14, 34),
(650, 15, 41),
(536, 16, 2),
(636, 16, 3),
(642, 16, 9),
(657, 16, 27),
(648, 16, 30),
(645, 17, 10),
(658, 17, 27),
(655, 17, 28),
(649, 17, 30),
(643, 18, 9),
(660, 18, 12),
(652, 20, 33),
(640, 21, 7),
(661, 21, 14),
(647, 22, 11),
(374, 22, 13),
(11, 24, 13),
(656, 24, 28),
(639, 25, 6),
(259, 26, 13),
(654, 27, 29),
(641, 28, 8),
(651, 29, 41);

-- --------------------------------------------------------

--
-- Table structure for table `nacionalidad`
--

CREATE TABLE `nacionalidad` (
  `id_nacionalidad` int(11) NOT NULL,
  `nacionalidad` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nacionalidad`
--

INSERT INTO `nacionalidad` (`id_nacionalidad`, `nacionalidad`) VALUES
(23, 'Alemana'),
(3, 'Argentina'),
(9, 'Boliviana'),
(20, 'Brasileña'),
(26, 'Británica'),
(22, 'Canadiense'),
(5, 'Chilena'),
(27, 'China'),
(4, 'Colombiana'),
(29, 'Coreana'),
(18, 'Costarricense'),
(12, 'Cubana'),
(13, 'Dominicana'),
(8, 'Ecuatoriana'),
(35, 'Egipcia'),
(2, 'Española'),
(21, 'Estadounidense'),
(34, 'Filipina'),
(24, 'Francesa'),
(16, 'Guatemalteca'),
(15, 'Hondureña'),
(30, 'India'),
(25, 'Italiana'),
(28, 'Japonesa'),
(36, 'Marrroquí'),
(1, 'Mexicana'),
(51, 'Nacionalidad Test'),
(17, 'Nicaragüense'),
(19, 'Panameña'),
(10, 'Paraguaya'),
(6, 'Peruana'),
(32, 'Portuguesa'),
(31, 'Rusa'),
(14, 'Salvadoreña'),
(37, 'Sudafricana'),
(33, 'Turca'),
(11, 'Uruguaya'),
(7, 'Venezolana');

-- --------------------------------------------------------

--
-- Table structure for table `pais`
--

CREATE TABLE `pais` (
  `id_pais` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pais`
--

INSERT INTO `pais` (`id_pais`, `nombre`) VALUES
(24, 'Alemania'),
(1, 'Argentina'),
(33, 'Australia'),
(2, 'Bolivia'),
(3, 'Brasil'),
(4, 'Canadá'),
(5, 'Chile'),
(30, 'China'),
(6, 'Colombia'),
(7, 'Costa Rica'),
(8, 'Cuba'),
(9, 'Ecuador'),
(10, 'El Salvador'),
(11, 'España'),
(12, 'Estados Unidos'),
(25, 'Francia'),
(13, 'Guatemala'),
(14, 'Honduras'),
(32, 'India'),
(26, 'Italia'),
(31, 'Japón'),
(15, 'México'),
(16, 'Nicaragua'),
(17, 'Panamá'),
(18, 'Paraguay'),
(19, 'Perú'),
(27, 'Portugal'),
(20, 'Puerto Rico'),
(28, 'Reino Unido'),
(21, 'República Dominicana'),
(29, 'Rusia'),
(34, 'Sudáfrica'),
(22, 'Uruguay'),
(23, 'Venezuela');

-- --------------------------------------------------------

--
-- Table structure for table `sucursal`
--

CREATE TABLE `sucursal` (
  `id_sucursal` int(11) NOT NULL,
  `calle` varchar(150) NOT NULL,
  `numero_exterior` int(11) NOT NULL,
  `numero_interior` varchar(10) DEFAULT NULL,
  `colonia` varchar(100) NOT NULL,
  `municipio` varchar(100) NOT NULL,
  `codigo_postal` int(11) NOT NULL,
  `id_pais` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sucursal`
--

INSERT INTO `sucursal` (`id_sucursal`, `calle`, `numero_exterior`, `numero_interior`, `colonia`, `municipio`, `codigo_postal`, `id_pais`) VALUES
(1, 'Av. Siempre Viva', 742, 'A', 'Springfield', 'Springfield', 12345, 1),
(2, 'Calle Falsa', 123, 'B', 'Centro', 'Ciudad', 54321, 2),
(3, 'Boulevard del Sol', 5678, 'C', 'Mirador', 'Metropolis', 67890, 3),
(4, 'Av. Principal', 1011, 'D', 'Oasis', 'Villa', 11111, 4),
(5, 'Callejón Oscuro', 2122, 'E', 'Villa Nova', 'Pueblo', 22222, 5),
(6, 'Via Láctea', 3434, 'F', 'Galaxia', 'Cosmos', 33333, 6),
(7, 'Calle de la Luna', 4545, 'G', 'Luna Nueva', 'Selenópolis', 44444, 7),
(8, 'Camino Real', 5656, 'H', 'Antiguo', 'Ciudad Vieja', 55555, 8),
(9, 'Plaza Mayor', 6767, 'I', 'Histórica', 'Capital', 66666, 9),
(10, 'Ruta Nacional', 7878, 'J', 'Patagonia', 'Andes', 77777, 4),
(11, 'Pasaje del Sol', 8989, 'K', 'Verde', 'Paraíso', 88888, 10),
(12, 'Calle del Bosque', 9090, 'L', 'Arboleda', 'Ciudad Jardín', 99999, 11),
(13, 'Av. de las Estrellas', 1111, 'M', 'Cielo Abierto', 'Cosmópolis', 10101, 12),
(14, 'Calle del Río', 2222, 'N', 'Riberas', 'Agua Clara', 20202, 13),
(15, 'Paseo del Mar', 3333, 'O', 'Playas', 'Costera', 30303, 14),
(16, 'New calle', 258, 'E', 'New Colonia', 'New Municipio', 8524, 7);

-- --------------------------------------------------------

--
-- Table structure for table `telefono`
--

CREATE TABLE `telefono` (
  `id_telefono` int(11) NOT NULL,
  `num_empleado` int(11) NOT NULL,
  `num_telefono` varchar(18) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `telefono`
--

INSERT INTO `telefono` (`id_telefono`, `num_empleado`, `num_telefono`) VALUES
(1, 1, '+52 55 9230700'),
(2, 2, '+52 55 1666750'),
(3, 3, '+52 55 0641653'),
(4, 4, '+52 55 8208013'),
(5, 5, '+52 55 9115110'),
(6, 6, '+52 55 0951509'),
(7, 7, '+52 55 7412218'),
(8, 8, '+52 55 4206562'),
(9, 9, '+52 55 8796159'),
(10, 10, '+52 55 1361108'),
(11, 11, '+52 55 0417064'),
(12, 12, '+52 55 8001997'),
(13, 13, '+52 55 8758794'),
(14, 14, '+52 55 9787978'),
(15, 15, '+52 55 2663508'),
(16, 16, '+52 55 3953610'),
(17, 17, '+52 55 1777527'),
(18, 18, '+52 55 7026804'),
(19, 19, '+52 55 9801439'),
(20, 20, '+52 55 7926784'),
(21, 21, '+52 55 0229604'),
(22, 22, '+52 55 7367671'),
(23, 23, '+52 55 6149541'),
(24, 24, '+52 55 8644694'),
(25, 25, '+52 55 4774847'),
(26, 26, '+52 55 7940154'),
(27, 27, '+52 55 5376232'),
(28, 28, '+52 55 3060696'),
(29, 29, '+52 55 9174787');

-- --------------------------------------------------------

--
-- Table structure for table `ventas`
--

CREATE TABLE `ventas` (
  `num_venta` int(11) NOT NULL,
  `num_empleado` int(11) NOT NULL,
  `fecha_venta` datetime NOT NULL,
  `metodo_pago` enum('Efectivo','Tarjeta') NOT NULL,
  `total` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ventas`
--

INSERT INTO `ventas` (`num_venta`, `num_empleado`, `fecha_venta`, `metodo_pago`, `total`) VALUES
(1, 6, '2025-03-14 00:00:00', 'Efectivo', 162.93),
(2, 4, '2025-02-23 00:00:00', 'Efectivo', 199.09),
(3, 21, '2025-03-13 00:00:00', 'Tarjeta', 428.10),
(4, 18, '2025-03-10 00:00:00', 'Efectivo', 272.37),
(5, 17, '2025-03-15 00:00:00', 'Efectivo', 130.67),
(6, 26, '2025-03-01 00:00:00', 'Efectivo', 499.33),
(7, 16, '2025-03-01 00:00:00', 'Efectivo', 318.22),
(8, 8, '2025-02-28 00:00:00', 'Efectivo', 375.67),
(9, 5, '2025-03-05 00:00:00', 'Efectivo', 362.06),
(10, 15, '2025-02-28 00:00:00', 'Efectivo', 221.32),
(11, 10, '2025-02-25 00:00:00', 'Efectivo', 330.28),
(12, 23, '2025-02-20 00:00:00', 'Tarjeta', 346.37),
(13, 7, '2025-03-16 00:00:00', 'Efectivo', 191.87),
(14, 29, '2025-02-17 00:00:00', 'Tarjeta', 397.62),
(15, 9, '2025-02-23 00:00:00', 'Tarjeta', 529.12),
(16, 2, '2025-02-19 00:00:00', 'Tarjeta', 493.95),
(17, 25, '2025-02-18 00:00:00', 'Tarjeta', 129.91),
(18, 14, '2025-02-21 00:00:00', 'Tarjeta', 456.44),
(19, 1, '2025-03-15 00:00:00', 'Tarjeta', 407.33),
(20, 13, '2025-02-28 00:00:00', 'Tarjeta', 162.50),
(21, 22, '2025-02-24 00:00:00', 'Tarjeta', 401.81),
(22, 20, '2025-02-28 00:00:00', 'Tarjeta', 157.36),
(23, 28, '2025-02-24 00:00:00', 'Tarjeta', 139.69),
(24, 27, '2025-03-08 00:00:00', 'Efectivo', 326.53),
(25, 12, '2025-03-04 00:00:00', 'Tarjeta', 250.39),
(26, 3, '2025-03-06 00:00:00', 'Tarjeta', 114.69),
(27, 24, '2025-02-24 00:00:00', 'Efectivo', 314.98),
(28, 11, '2025-03-10 00:00:00', 'Tarjeta', 501.91),
(29, 19, '2025-03-09 00:00:00', 'Tarjeta', 548.15);

-- --------------------------------------------------------

--
-- Table structure for table `venta_libro`
--

CREATE TABLE `venta_libro` (
  `id_venta_libro` int(11) NOT NULL,
  `num_venta` int(11) NOT NULL,
  `id_libro` int(11) NOT NULL,
  `cantidad_libros` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `venta_libro`
--

INSERT INTO `venta_libro` (`id_venta_libro`, `num_venta`, `id_libro`, `cantidad_libros`) VALUES
(1, 3, 17, 6),
(2, 19, 17, 7),
(3, 17, 17, 7),
(4, 8, 17, 3),
(5, 4, 17, 4),
(6, 11, 17, 10),
(7, 16, 17, 7),
(8, 23, 17, 6),
(9, 28, 17, 10),
(10, 14, 17, 9),
(11, 27, 17, 4),
(12, 5, 17, 4),
(13, 6, 17, 8),
(14, 9, 17, 7),
(15, 29, 17, 8),
(16, 21, 17, 1),
(17, 20, 17, 10),
(18, 13, 17, 7),
(19, 1, 17, 4),
(20, 12, 17, 9),
(21, 10, 17, 4),
(22, 25, 17, 3),
(23, 7, 17, 10),
(24, 26, 17, 1),
(25, 18, 17, 5),
(26, 22, 17, 3),
(27, 2, 17, 9),
(28, 24, 17, 5),
(29, 15, 17, 7),
(30, 3, 26, 10),
(31, 19, 26, 1),
(32, 17, 26, 1),
(33, 8, 26, 2),
(34, 4, 26, 5),
(35, 11, 26, 10),
(36, 16, 26, 5),
(37, 23, 26, 5),
(38, 28, 26, 8),
(39, 14, 26, 6),
(40, 27, 26, 4),
(41, 5, 26, 2),
(42, 6, 26, 10),
(43, 9, 26, 10),
(44, 29, 26, 10),
(45, 21, 26, 1),
(46, 20, 26, 2),
(47, 13, 26, 8),
(48, 1, 26, 2),
(49, 12, 26, 8),
(50, 10, 26, 3),
(51, 25, 26, 1),
(52, 7, 26, 4),
(53, 26, 26, 7),
(54, 18, 26, 5),
(55, 22, 26, 1),
(56, 2, 26, 8),
(57, 24, 26, 8),
(58, 15, 26, 7),
(59, 3, 28, 9),
(60, 19, 28, 3),
(61, 17, 28, 8),
(62, 8, 28, 2),
(63, 4, 28, 6),
(64, 11, 28, 5),
(65, 16, 28, 3),
(66, 23, 28, 10),
(67, 28, 28, 1),
(68, 14, 28, 3),
(69, 27, 28, 1),
(70, 5, 28, 6),
(71, 6, 28, 9),
(72, 9, 28, 5),
(73, 29, 28, 6),
(74, 21, 28, 5),
(75, 20, 28, 8),
(76, 13, 28, 4),
(77, 1, 28, 6),
(78, 12, 28, 8),
(79, 10, 28, 1),
(80, 25, 28, 10),
(81, 7, 28, 8),
(82, 26, 28, 1),
(83, 18, 28, 7),
(84, 22, 28, 4),
(85, 2, 28, 9),
(86, 24, 28, 2),
(87, 15, 28, 4),
(88, 3, 16, 2),
(89, 19, 16, 8),
(90, 17, 16, 3),
(91, 8, 16, 1),
(92, 4, 16, 6),
(93, 11, 16, 7),
(94, 16, 16, 6),
(95, 23, 16, 9),
(96, 28, 16, 6),
(97, 14, 16, 3),
(98, 27, 16, 8),
(99, 5, 16, 8),
(100, 6, 16, 7),
(101, 9, 16, 10),
(102, 29, 16, 10),
(103, 21, 16, 8),
(104, 20, 16, 10),
(105, 13, 16, 5),
(106, 1, 16, 3),
(107, 12, 16, 10),
(108, 10, 16, 10),
(109, 25, 16, 9),
(110, 7, 16, 4),
(111, 26, 16, 5),
(112, 18, 16, 9),
(113, 22, 16, 3),
(114, 2, 16, 7),
(115, 24, 16, 3),
(116, 15, 16, 4),
(117, 3, 24, 2),
(118, 19, 24, 9),
(119, 17, 24, 5),
(120, 8, 24, 10),
(121, 4, 24, 6),
(122, 11, 24, 7),
(123, 16, 24, 8),
(124, 23, 24, 6),
(125, 28, 24, 6),
(126, 14, 24, 3),
(127, 27, 24, 5),
(128, 5, 24, 4),
(129, 6, 24, 7),
(130, 9, 24, 1),
(131, 29, 24, 5),
(132, 21, 24, 1),
(133, 20, 24, 1),
(134, 13, 24, 8),
(135, 1, 24, 10),
(136, 12, 24, 2),
(137, 10, 24, 3),
(138, 25, 24, 5),
(139, 7, 24, 8),
(140, 26, 24, 3),
(141, 18, 24, 2),
(142, 22, 24, 10),
(143, 2, 24, 3),
(144, 24, 24, 6),
(145, 15, 24, 10),
(146, 3, 1, 3),
(147, 19, 1, 3),
(148, 17, 1, 5),
(149, 8, 1, 5),
(150, 4, 1, 1),
(151, 11, 1, 7),
(152, 16, 1, 4),
(153, 23, 1, 7),
(154, 28, 1, 3),
(155, 14, 1, 4),
(156, 27, 1, 9),
(157, 5, 1, 3),
(158, 6, 1, 6),
(159, 9, 1, 10),
(160, 29, 1, 5),
(161, 21, 1, 1),
(162, 20, 1, 10),
(163, 13, 1, 7),
(164, 1, 1, 6),
(165, 12, 1, 7),
(166, 10, 1, 6),
(167, 25, 1, 7),
(168, 7, 1, 10),
(169, 26, 1, 7),
(170, 18, 1, 4),
(171, 22, 1, 1),
(172, 2, 1, 1),
(173, 24, 1, 10),
(174, 15, 1, 7),
(175, 3, 7, 4),
(176, 19, 7, 8),
(177, 17, 7, 7),
(178, 8, 7, 2),
(179, 4, 7, 9),
(180, 11, 7, 9),
(181, 16, 7, 8),
(182, 23, 7, 3),
(183, 28, 7, 9),
(184, 14, 7, 6),
(185, 27, 7, 4),
(186, 5, 7, 9),
(187, 6, 7, 2),
(188, 9, 7, 4),
(189, 29, 7, 5),
(190, 21, 7, 10),
(191, 20, 7, 7),
(192, 13, 7, 1),
(193, 1, 7, 6),
(194, 12, 7, 8),
(195, 10, 7, 9),
(196, 25, 7, 10),
(197, 7, 7, 3),
(198, 26, 7, 4),
(199, 18, 7, 1),
(200, 22, 7, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `autor`
--
ALTER TABLE `autor`
  ADD PRIMARY KEY (`id_autor`),
  ADD UNIQUE KEY `uq_Autor` (`nombre`,`apellido_1`,`apellido_2`),
  ADD KEY `id_nacionalidad` (`id_nacionalidad`);

--
-- Indexes for table `clasificacion`
--
ALTER TABLE `clasificacion`
  ADD PRIMARY KEY (`id_clasificacion`),
  ADD UNIQUE KEY `uq_Clasificacion` (`tipo_clasificacion`);

--
-- Indexes for table `editorial`
--
ALTER TABLE `editorial`
  ADD PRIMARY KEY (`id_editorial`),
  ADD UNIQUE KEY `uq_Editorial` (`editorial`);

--
-- Indexes for table `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`num_empleado`),
  ADD UNIQUE KEY `uq_Empleado` (`nombre`,`apellido_1`,`apellido_2`),
  ADD KEY `fk_EmpleadoSucursal` (`id_sucursal`);

--
-- Indexes for table `inventario`
--
ALTER TABLE `inventario`
  ADD PRIMARY KEY (`id_inventario`),
  ADD UNIQUE KEY `uq_Inventario` (`id_libro`,`id_sucursal`),
  ADD KEY `fk_InventarioSucursal` (`id_sucursal`);

--
-- Indexes for table `libro`
--
ALTER TABLE `libro`
  ADD PRIMARY KEY (`id_libro`),
  ADD UNIQUE KEY `uq_Libro` (`titulo`,`id_editorial`,`tipo_pasta`),
  ADD KEY `fk_LibroEditorial` (`id_editorial`);

--
-- Indexes for table `libro_autor`
--
ALTER TABLE `libro_autor`
  ADD PRIMARY KEY (`id_libro_autor`),
  ADD UNIQUE KEY `uq_AutorLibro` (`id_autor`,`id_libro`),
  ADD KEY `fk_LibroAutor_Libro` (`id_libro`);

--
-- Indexes for table `libro_clasificacion`
--
ALTER TABLE `libro_clasificacion`
  ADD PRIMARY KEY (`id_libro_clasificacion`),
  ADD UNIQUE KEY `uq_LibroClasificacion` (`id_clasificacion`,`id_libro`),
  ADD KEY `fk_LibroClasificacion_Libro` (`id_libro`);

--
-- Indexes for table `nacionalidad`
--
ALTER TABLE `nacionalidad`
  ADD PRIMARY KEY (`id_nacionalidad`),
  ADD UNIQUE KEY `nacionalidad` (`nacionalidad`);

--
-- Indexes for table `pais`
--
ALTER TABLE `pais`
  ADD PRIMARY KEY (`id_pais`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indexes for table `sucursal`
--
ALTER TABLE `sucursal`
  ADD PRIMARY KEY (`id_sucursal`),
  ADD UNIQUE KEY `uq_Sucursal` (`calle`,`numero_exterior`,`colonia`,`municipio`),
  ADD KEY `fk_sucursal_pais` (`id_pais`);

--
-- Indexes for table `telefono`
--
ALTER TABLE `telefono`
  ADD PRIMARY KEY (`id_telefono`),
  ADD UNIQUE KEY `uq_Telefono` (`num_telefono`),
  ADD KEY `fk_TelefonoEmpleado` (`num_empleado`);

--
-- Indexes for table `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`num_venta`),
  ADD UNIQUE KEY `uq_Ventas` (`num_empleado`,`fecha_venta`);

--
-- Indexes for table `venta_libro`
--
ALTER TABLE `venta_libro`
  ADD PRIMARY KEY (`id_venta_libro`),
  ADD UNIQUE KEY `uq_VentaLibro` (`num_venta`,`id_libro`),
  ADD KEY `fk_VentaLibro_Libro` (`id_libro`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `autor`
--
ALTER TABLE `autor`
  MODIFY `id_autor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `clasificacion`
--
ALTER TABLE `clasificacion`
  MODIFY `id_clasificacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `editorial`
--
ALTER TABLE `editorial`
  MODIFY `id_editorial` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `empleado`
--
ALTER TABLE `empleado`
  MODIFY `num_empleado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `inventario`
--
ALTER TABLE `inventario`
  MODIFY `id_inventario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=512;

--
-- AUTO_INCREMENT for table `libro`
--
ALTER TABLE `libro`
  MODIFY `id_libro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT for table `libro_autor`
--
ALTER TABLE `libro_autor`
  MODIFY `id_libro_autor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=189;

--
-- AUTO_INCREMENT for table `libro_clasificacion`
--
ALTER TABLE `libro_clasificacion`
  MODIFY `id_libro_clasificacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=664;

--
-- AUTO_INCREMENT for table `nacionalidad`
--
ALTER TABLE `nacionalidad`
  MODIFY `id_nacionalidad` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT for table `pais`
--
ALTER TABLE `pais`
  MODIFY `id_pais` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `sucursal`
--
ALTER TABLE `sucursal`
  MODIFY `id_sucursal` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `telefono`
--
ALTER TABLE `telefono`
  MODIFY `id_telefono` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `ventas`
--
ALTER TABLE `ventas`
  MODIFY `num_venta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `venta_libro`
--
ALTER TABLE `venta_libro`
  MODIFY `id_venta_libro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=256;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `autor`
--
ALTER TABLE `autor`
  ADD CONSTRAINT `autor_ibfk_1` FOREIGN KEY (`id_nacionalidad`) REFERENCES `nacionalidad` (`id_nacionalidad`);

--
-- Constraints for table `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `fk_EmpleadoSucursal` FOREIGN KEY (`id_sucursal`) REFERENCES `sucursal` (`id_sucursal`);

--
-- Constraints for table `inventario`
--
ALTER TABLE `inventario`
  ADD CONSTRAINT `fk_InventarioLibro` FOREIGN KEY (`id_libro`) REFERENCES `libro` (`id_libro`),
  ADD CONSTRAINT `fk_InventarioSucursal` FOREIGN KEY (`id_sucursal`) REFERENCES `sucursal` (`id_sucursal`);

--
-- Constraints for table `libro`
--
ALTER TABLE `libro`
  ADD CONSTRAINT `fk_LibroEditorial` FOREIGN KEY (`id_editorial`) REFERENCES `editorial` (`id_editorial`);

--
-- Constraints for table `libro_autor`
--
ALTER TABLE `libro_autor`
  ADD CONSTRAINT `fk_LibroAutor_Autor` FOREIGN KEY (`id_autor`) REFERENCES `autor` (`id_autor`),
  ADD CONSTRAINT `fk_LibroAutor_Libro` FOREIGN KEY (`id_libro`) REFERENCES `libro` (`id_libro`);

--
-- Constraints for table `libro_clasificacion`
--
ALTER TABLE `libro_clasificacion`
  ADD CONSTRAINT `fk_LibroClasificacion_Clasificacion` FOREIGN KEY (`id_clasificacion`) REFERENCES `clasificacion` (`id_clasificacion`),
  ADD CONSTRAINT `fk_LibroClasificacion_Libro` FOREIGN KEY (`id_libro`) REFERENCES `libro` (`id_libro`);

--
-- Constraints for table `sucursal`
--
ALTER TABLE `sucursal`
  ADD CONSTRAINT `fk_sucursal_pais` FOREIGN KEY (`id_pais`) REFERENCES `pais` (`id_pais`);

--
-- Constraints for table `telefono`
--
ALTER TABLE `telefono`
  ADD CONSTRAINT `fk_TelefonoEmpleado` FOREIGN KEY (`num_empleado`) REFERENCES `empleado` (`num_empleado`);

--
-- Constraints for table `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `fk_VentaEmpleado` FOREIGN KEY (`num_empleado`) REFERENCES `empleado` (`num_empleado`);

--
-- Constraints for table `venta_libro`
--
ALTER TABLE `venta_libro`
  ADD CONSTRAINT `fk_VentaLibro_Libro` FOREIGN KEY (`id_libro`) REFERENCES `libro` (`id_libro`),
  ADD CONSTRAINT `fk_VentaLibro_Venta` FOREIGN KEY (`num_venta`) REFERENCES `ventas` (`num_venta`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
