-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 19, 2021 at 12:51 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `appdev`
--

-- --------------------------------------------------------

--
-- Table structure for table `enthaelt`
--

CREATE TABLE `enthaelt` (
  `id` int(11) NOT NULL,
  `Lieferungsnummer` int(11) NOT NULL,
  `Warennummer` int(11) NOT NULL,
  `Stueckzahl` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `enthaelt`
--

INSERT INTO `enthaelt` (`id`, `Lieferungsnummer`, `Warennummer`, `Stueckzahl`) VALUES
(1, 1, 13, 17),
(2, 1, 24, 3),
(3, 1, 11, 11),
(4, 2, 17, 15),
(5, 2, 7, 18),
(6, 2, 7, 13),
(7, 2, 12, 18),
(8, 2, 11, 12),
(9, 3, 5, 18),
(10, 3, 12, 12),
(11, 3, 5, 4),
(12, 3, 4, 20),
(13, 3, 24, 3),
(14, 3, 16, 9),
(15, 3, 7, 9),
(16, 3, 14, 7),
(17, 3, 8, 4),
(18, 3, 20, 14),
(19, 3, 2, 9),
(20, 3, 18, 7),
(21, 3, 14, 3),
(22, 3, 14, 6),
(23, 3, 23, 8),
(24, 3, 11, 6),
(25, 4, 2, 15),
(26, 4, 2, 15),
(27, 4, 7, 10),
(28, 4, 1, 14),
(29, 4, 19, 16),
(30, 4, 5, 1),
(31, 4, 2, 18),
(32, 4, 8, 19),
(33, 4, 15, 10),
(34, 4, 13, 8),
(35, 4, 5, 3),
(36, 4, 22, 9),
(37, 5, 7, 2),
(38, 6, 21, 8),
(39, 6, 10, 6),
(40, 6, 5, 10),
(41, 6, 18, 13),
(42, 6, 8, 18),
(43, 6, 6, 7),
(44, 6, 1, 6),
(45, 6, 14, 8),
(46, 6, 16, 1),
(47, 6, 19, 13),
(48, 6, 8, 10),
(49, 6, 10, 19),
(50, 6, 17, 19),
(51, 6, 16, 3),
(52, 6, 10, 11),
(53, 6, 12, 3),
(54, 6, 18, 5),
(55, 7, 4, 10),
(56, 7, 21, 2),
(57, 7, 15, 12),
(58, 7, 2, 1),
(59, 7, 18, 7),
(60, 8, 6, 12),
(61, 8, 13, 15),
(62, 8, 4, 13),
(63, 8, 13, 16),
(64, 8, 12, 15),
(65, 9, 9, 1),
(66, 9, 7, 11),
(67, 9, 10, 20),
(68, 9, 20, 16),
(69, 9, 24, 11),
(70, 9, 4, 4),
(71, 9, 8, 10),
(72, 10, 20, 1),
(73, 10, 14, 2),
(74, 10, 8, 11),
(75, 10, 18, 1),
(76, 10, 13, 8),
(77, 10, 21, 7),
(78, 10, 12, 4),
(79, 10, 24, 1),
(80, 10, 1, 1),
(81, 10, 24, 2),
(82, 10, 16, 9),
(83, 10, 21, 16),
(84, 11, 11, 8),
(85, 11, 9, 15),
(86, 11, 24, 5),
(87, 11, 4, 4),
(88, 11, 9, 5),
(89, 11, 24, 8),
(90, 11, 8, 2),
(91, 12, 1, 18),
(92, 12, 1, 7),
(93, 12, 23, 4),
(94, 12, 16, 1),
(95, 12, 10, 1),
(96, 12, 14, 13),
(97, 12, 9, 18),
(98, 12, 19, 9),
(99, 13, 8, 8),
(100, 13, 16, 3),
(101, 13, 17, 8),
(102, 13, 6, 6),
(103, 13, 12, 17),
(104, 13, 17, 7),
(105, 13, 8, 4),
(106, 13, 12, 4),
(107, 13, 21, 13),
(108, 13, 15, 11),
(109, 13, 8, 11),
(110, 13, 22, 19),
(111, 13, 7, 7),
(112, 13, 12, 6),
(113, 13, 7, 12),
(114, 13, 14, 1),
(115, 13, 7, 4),
(116, 13, 6, 12),
(117, 13, 23, 15),
(118, 14, 5, 12),
(119, 14, 14, 18),
(120, 14, 10, 15),
(121, 14, 10, 11),
(122, 14, 20, 6),
(123, 14, 5, 16),
(124, 14, 19, 14),
(125, 14, 17, 16),
(126, 14, 24, 9),
(127, 14, 23, 2),
(128, 14, 21, 8),
(129, 14, 19, 10),
(130, 14, 18, 12),
(131, 14, 6, 13),
(132, 14, 18, 1),
(133, 14, 11, 9),
(134, 14, 12, 20),
(135, 14, 9, 13),
(136, 14, 6, 3),
(137, 15, 7, 1),
(138, 15, 19, 7),
(139, 15, 7, 20),
(140, 15, 8, 12),
(141, 15, 21, 7),
(142, 15, 9, 8),
(143, 15, 3, 4),
(144, 15, 4, 5),
(145, 15, 21, 1),
(146, 15, 15, 4),
(147, 15, 20, 19),
(148, 15, 12, 10),
(149, 16, 19, 20),
(150, 16, 21, 6),
(151, 16, 21, 9),
(152, 16, 18, 1),
(153, 16, 22, 4),
(154, 16, 15, 1),
(155, 16, 6, 13),
(156, 16, 19, 8),
(157, 16, 3, 4),
(158, 16, 2, 9),
(159, 17, 23, 7),
(160, 17, 3, 12),
(161, 17, 5, 5),
(162, 17, 5, 15),
(163, 17, 11, 17),
(164, 18, 15, 2),
(165, 18, 7, 12),
(166, 18, 3, 19),
(167, 18, 22, 7),
(168, 18, 23, 4),
(169, 18, 1, 11),
(170, 18, 8, 12),
(171, 18, 11, 20),
(172, 18, 20, 1),
(173, 18, 22, 15),
(174, 18, 7, 5),
(175, 18, 19, 6),
(176, 18, 6, 10),
(177, 18, 22, 6),
(178, 19, 11, 19),
(179, 19, 14, 12),
(180, 19, 7, 13),
(181, 19, 12, 17),
(182, 19, 2, 16),
(183, 19, 4, 15),
(184, 19, 18, 7),
(185, 19, 15, 12),
(186, 19, 5, 2),
(187, 19, 24, 5),
(188, 19, 19, 18),
(189, 19, 13, 6),
(190, 19, 12, 14),
(191, 19, 14, 9),
(192, 19, 18, 7),
(193, 19, 23, 3),
(194, 19, 24, 5),
(195, 20, 16, 6),
(196, 20, 8, 9),
(197, 20, 14, 9),
(198, 20, 16, 2),
(199, 20, 9, 2),
(200, 20, 13, 9),
(201, 20, 22, 19),
(202, 20, 12, 8),
(203, 20, 16, 10),
(204, 20, 4, 17),
(205, 20, 2, 13),
(206, 20, 9, 19),
(207, 20, 11, 3),
(208, 20, 7, 2),
(209, 20, 2, 2),
(210, 20, 2, 2),
(211, 21, 8, 18),
(212, 21, 9, 3),
(213, 21, 14, 1),
(214, 21, 2, 3),
(215, 21, 5, 18),
(216, 21, 20, 14),
(217, 21, 3, 12),
(218, 21, 1, 3),
(219, 21, 15, 5),
(220, 21, 18, 6),
(221, 21, 7, 9),
(222, 21, 16, 16),
(223, 21, 17, 5),
(224, 21, 10, 10),
(225, 21, 10, 3),
(226, 22, 4, 13),
(227, 22, 21, 17),
(228, 23, 17, 3),
(229, 24, 5, 10),
(230, 24, 16, 7),
(231, 24, 10, 8),
(232, 24, 21, 14),
(233, 25, 23, 4),
(234, 25, 9, 10),
(235, 25, 17, 1),
(236, 25, 9, 16),
(237, 25, 24, 16),
(238, 25, 3, 13),
(239, 25, 8, 10),
(240, 25, 3, 12),
(241, 25, 19, 14);

-- --------------------------------------------------------

--
-- Table structure for table `fuehrt_auf`
--

CREATE TABLE `fuehrt_auf` (
  `id` int(11) NOT NULL,
  `Lieferscheinnummer` int(11) NOT NULL,
  `Warennummer` int(11) NOT NULL,
  `Stueckzahl` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `fuehrt_auf`
--

INSERT INTO `fuehrt_auf` (`id`, `Lieferscheinnummer`, `Warennummer`, `Stueckzahl`) VALUES
(1, 1, 13, 17),
(2, 1, 24, 5),
(3, 1, 11, 16),
(4, 2, 17, 15),
(5, 2, 7, 18),
(6, 2, 7, 13),
(7, 2, 12, 18),
(8, 2, 11, 16),
(9, 3, 5, 19),
(10, 3, 12, 12),
(11, 3, 5, 4),
(12, 3, 4, 20),
(13, 3, 24, 3),
(14, 3, 16, 9),
(15, 3, 7, 9),
(16, 3, 14, 7),
(17, 3, 8, 4),
(18, 3, 20, 14),
(19, 3, 2, 9),
(20, 3, 18, 7),
(21, 3, 14, 16),
(22, 3, 14, 6),
(23, 3, 23, 8),
(24, 3, 11, 6),
(25, 4, 2, 15),
(26, 4, 2, 15),
(27, 4, 7, 10),
(28, 4, 1, 14),
(29, 4, 19, 16),
(30, 4, 5, 1),
(31, 4, 2, 18),
(32, 4, 8, 19),
(33, 4, 15, 17),
(34, 4, 13, 8),
(35, 4, 5, 3),
(36, 4, 22, 9),
(37, 5, 7, 2),
(38, 6, 21, 8),
(39, 6, 10, 6),
(40, 6, 5, 10),
(41, 6, 18, 13),
(42, 6, 8, 18),
(43, 6, 6, 7),
(44, 6, 1, 6),
(45, 6, 14, 18),
(46, 6, 16, 1),
(47, 6, 19, 13),
(48, 6, 8, 10),
(49, 6, 10, 19),
(50, 6, 17, 19),
(51, 6, 16, 3),
(52, 6, 10, 11),
(53, 6, 12, 3),
(54, 6, 18, 5),
(55, 7, 4, 18),
(56, 7, 21, 2),
(57, 7, 15, 12),
(58, 7, 2, 1),
(59, 7, 18, 7),
(60, 8, 6, 19),
(61, 8, 13, 15),
(62, 8, 4, 13),
(63, 8, 13, 16),
(64, 8, 12, 15),
(65, 9, 9, 1),
(66, 9, 7, 15),
(67, 9, 10, 20),
(68, 9, 20, 16),
(69, 9, 24, 16),
(70, 9, 4, 4),
(71, 9, 8, 10),
(72, 10, 20, 1),
(73, 10, 14, 2),
(74, 10, 8, 11),
(75, 10, 18, 4),
(76, 10, 13, 18),
(77, 10, 21, 7),
(78, 10, 12, 13),
(79, 10, 24, 1),
(80, 10, 1, 1),
(81, 10, 24, 3),
(82, 10, 16, 9),
(83, 10, 21, 16),
(84, 11, 11, 8),
(85, 11, 9, 15),
(86, 11, 24, 5),
(87, 11, 4, 4),
(88, 11, 9, 5),
(89, 11, 24, 8),
(90, 11, 8, 2),
(91, 12, 1, 18),
(92, 12, 1, 7),
(93, 12, 23, 4),
(94, 12, 16, 2),
(95, 12, 10, 1),
(96, 12, 14, 13),
(97, 12, 9, 18),
(98, 12, 19, 9),
(99, 13, 8, 8),
(100, 13, 16, 3),
(101, 13, 17, 11),
(102, 13, 6, 9),
(103, 13, 12, 17),
(104, 13, 17, 7),
(105, 13, 8, 4),
(106, 13, 12, 4),
(107, 13, 21, 19),
(108, 13, 15, 11),
(109, 13, 8, 13),
(110, 13, 22, 19),
(111, 13, 7, 7),
(112, 13, 12, 6),
(113, 13, 7, 12),
(114, 13, 14, 9),
(115, 13, 7, 4),
(116, 13, 6, 12),
(117, 13, 23, 17),
(118, 14, 5, 12),
(119, 14, 14, 18),
(120, 14, 10, 15),
(121, 14, 10, 11),
(122, 14, 20, 6),
(123, 14, 5, 16),
(124, 14, 19, 14),
(125, 14, 17, 16),
(126, 14, 24, 9),
(127, 14, 23, 9),
(128, 14, 21, 8),
(129, 14, 19, 10),
(130, 14, 18, 12),
(131, 14, 6, 20),
(132, 14, 18, 1),
(133, 14, 11, 10),
(134, 14, 12, 20),
(135, 14, 9, 13),
(136, 14, 6, 12),
(137, 15, 7, 1),
(138, 15, 19, 7),
(139, 15, 7, 20),
(140, 15, 8, 12),
(141, 15, 21, 7),
(142, 15, 9, 11),
(143, 15, 3, 19),
(144, 15, 4, 5),
(145, 15, 21, 1),
(146, 15, 15, 19),
(147, 15, 20, 19),
(148, 15, 12, 10),
(149, 16, 19, 20),
(150, 16, 21, 6),
(151, 16, 21, 9),
(152, 16, 18, 4),
(153, 16, 22, 6),
(154, 16, 15, 1),
(155, 16, 6, 13),
(156, 16, 19, 8),
(157, 16, 3, 4),
(158, 16, 2, 9),
(159, 17, 23, 7),
(160, 17, 3, 12),
(161, 17, 5, 5),
(162, 17, 5, 15),
(163, 17, 11, 17),
(164, 18, 15, 2),
(165, 18, 7, 12),
(166, 18, 3, 19),
(167, 18, 22, 7),
(168, 18, 23, 4),
(169, 18, 1, 11),
(170, 18, 8, 12),
(171, 18, 11, 20),
(172, 18, 20, 2),
(173, 18, 22, 15),
(174, 18, 7, 5),
(175, 18, 19, 6),
(176, 18, 6, 10),
(177, 18, 22, 6),
(178, 19, 11, 19),
(179, 19, 14, 12),
(180, 19, 7, 13),
(181, 19, 12, 17),
(182, 19, 2, 16),
(183, 19, 4, 15),
(184, 19, 18, 7),
(185, 19, 15, 19),
(186, 19, 5, 2),
(187, 19, 24, 5),
(188, 19, 19, 18),
(189, 19, 13, 13),
(190, 19, 12, 14),
(191, 19, 14, 9),
(192, 19, 18, 13),
(193, 19, 23, 3),
(194, 19, 24, 8),
(195, 20, 16, 6),
(196, 20, 8, 9),
(197, 20, 14, 9),
(198, 20, 16, 2),
(199, 20, 9, 2),
(200, 20, 13, 9),
(201, 20, 22, 19),
(202, 20, 12, 11),
(203, 20, 16, 10),
(204, 20, 4, 17),
(205, 20, 2, 13),
(206, 20, 9, 19),
(207, 20, 11, 3),
(208, 20, 7, 2),
(209, 20, 2, 2),
(210, 20, 2, 2),
(211, 21, 8, 18),
(212, 21, 9, 17),
(213, 21, 14, 5),
(214, 21, 2, 3),
(215, 21, 5, 18),
(216, 21, 20, 20),
(217, 21, 3, 12),
(218, 21, 1, 8),
(219, 21, 15, 6),
(220, 21, 18, 6),
(221, 21, 7, 9),
(222, 21, 16, 16),
(223, 21, 17, 9),
(224, 21, 10, 10),
(225, 21, 10, 4),
(226, 22, 4, 13),
(227, 22, 21, 19),
(228, 23, 17, 3),
(229, 24, 5, 10),
(230, 24, 16, 7),
(231, 24, 10, 8),
(232, 24, 21, 14),
(233, 25, 23, 4),
(234, 25, 9, 10),
(235, 25, 17, 1),
(236, 25, 9, 16),
(237, 25, 24, 16),
(238, 25, 3, 13),
(239, 25, 8, 10),
(240, 25, 3, 12),
(241, 25, 19, 14);

-- --------------------------------------------------------

--
-- Table structure for table `kunde`
--

CREATE TABLE `kunde` (
  `Kundennummer` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kunde`
--

INSERT INTO `kunde` (`Kundennummer`) VALUES
(1),
(2),
(3),
(4),
(5),
(6);

-- --------------------------------------------------------

--
-- Table structure for table `lieferschein`
--

CREATE TABLE `lieferschein` (
  `Lieferscheinnummer` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `lieferschein`
--

INSERT INTO `lieferschein` (`Lieferscheinnummer`) VALUES
(1),
(2),
(3),
(4),
(5),
(6),
(7),
(8),
(9),
(10),
(11),
(12),
(13),
(14),
(15),
(16),
(17),
(18),
(19),
(20),
(21),
(22),
(23),
(24),
(25);

-- --------------------------------------------------------

--
-- Table structure for table `lieferung`
--

CREATE TABLE `lieferung` (
  `Lieferungsnummer` int(11) NOT NULL,
  `Kundennummer` int(11) NOT NULL,
  `Lieferscheinnummer` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `lieferung`
--

INSERT INTO `lieferung` (`Lieferungsnummer`, `Kundennummer`, `Lieferscheinnummer`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),
(4, 1, 4),
(5, 1, 5),
(6, 1, 6),
(7, 1, 7),
(8, 1, 8),
(9, 1, 9),
(10, 2, 10),
(11, 3, 11),
(12, 3, 12),
(13, 4, 13),
(14, 4, 14),
(15, 4, 15),
(16, 4, 16),
(17, 5, 17),
(18, 5, 18),
(19, 5, 19),
(20, 6, 20),
(21, 6, 21),
(22, 6, 22),
(23, 6, 23),
(24, 6, 24),
(25, 6, 25);

-- --------------------------------------------------------

--
-- Table structure for table `ware`
--

CREATE TABLE `ware` (
  `Warennummer` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ware`
--

INSERT INTO `ware` (`Warennummer`) VALUES
(1),
(2),
(3),
(4),
(5),
(6),
(7),
(8),
(9),
(10),
(11),
(12),
(13),
(14),
(15),
(16),
(17),
(18),
(19),
(20),
(21),
(22),
(23),
(24);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `enthaelt`
--
ALTER TABLE `enthaelt`
  ADD PRIMARY KEY (`id`),
  ADD KEY `Lieferung` (`Lieferungsnummer`),
  ADD KEY `Lieferung_Ware` (`Warennummer`);

--
-- Indexes for table `fuehrt_auf`
--
ALTER TABLE `fuehrt_auf`
  ADD PRIMARY KEY (`id`),
  ADD KEY `Lieferschein` (`Lieferscheinnummer`),
  ADD KEY `Lieferschein_Ware` (`Warennummer`);

--
-- Indexes for table `kunde`
--
ALTER TABLE `kunde`
  ADD PRIMARY KEY (`Kundennummer`);

--
-- Indexes for table `lieferschein`
--
ALTER TABLE `lieferschein`
  ADD PRIMARY KEY (`Lieferscheinnummer`);

--
-- Indexes for table `lieferung`
--
ALTER TABLE `lieferung`
  ADD PRIMARY KEY (`Lieferungsnummer`),
  ADD KEY `Kunde` (`Kundennummer`),
  ADD KEY `Lieferung_Lieferschein` (`Lieferscheinnummer`);

--
-- Indexes for table `ware`
--
ALTER TABLE `ware`
  ADD PRIMARY KEY (`Warennummer`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `enthaelt`
--
ALTER TABLE `enthaelt`
  ADD CONSTRAINT `Lieferung` FOREIGN KEY (`Lieferungsnummer`) REFERENCES `lieferung` (`Lieferungsnummer`),
  ADD CONSTRAINT `Lieferung_Ware` FOREIGN KEY (`Warennummer`) REFERENCES `ware` (`Warennummer`);

--
-- Constraints for table `fuehrt_auf`
--
ALTER TABLE `fuehrt_auf`
  ADD CONSTRAINT `Lieferschein` FOREIGN KEY (`Lieferscheinnummer`) REFERENCES `lieferschein` (`Lieferscheinnummer`),
  ADD CONSTRAINT `Lieferschein_Ware` FOREIGN KEY (`Warennummer`) REFERENCES `ware` (`Warennummer`);

--
-- Constraints for table `lieferung`
--
ALTER TABLE `lieferung`
  ADD CONSTRAINT `Kunde` FOREIGN KEY (`Kundennummer`) REFERENCES `kunde` (`Kundennummer`),
  ADD CONSTRAINT `Lieferung_Lieferschein` FOREIGN KEY (`Lieferscheinnummer`) REFERENCES `lieferschein` (`Lieferscheinnummer`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;