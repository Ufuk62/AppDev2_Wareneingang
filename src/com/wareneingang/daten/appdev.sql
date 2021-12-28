-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 28, 2021 at 06:14 PM
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
-- Table structure for table `abgelehnt`
--

CREATE TABLE `abgelehnt` (
  `lieferungsnummer` int(11) NOT NULL,
  `warennummer` int(11) NOT NULL,
  `stueckzahl` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `abgelehnt`
--

INSERT INTO `abgelehnt` (`lieferungsnummer`, `warennummer`, `stueckzahl`) VALUES
(1, 9, 16),
(1, 15, 4);

-- --------------------------------------------------------

--
-- Table structure for table `angenommen`
--

CREATE TABLE `angenommen` (
  `lieferungsnummer` int(11) NOT NULL,
  `warennummer` int(11) NOT NULL,
  `stueckzahl` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `angenommen`
--

INSERT INTO `angenommen` (`lieferungsnummer`, `warennummer`, `stueckzahl`) VALUES
(1, 4, 1),
(1, 6, 19);

-- --------------------------------------------------------

--
-- Table structure for table `enthaelt`
--

CREATE TABLE `enthaelt` (
  `Lieferungsnummer` int(11) NOT NULL,
  `Warennummer` int(11) NOT NULL,
  `Stueckzahl` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `enthaelt`
--

INSERT INTO `enthaelt` (`Lieferungsnummer`, `Warennummer`, `Stueckzahl`) VALUES
(1, 4, 1),
(1, 6, 19),
(1, 9, 16),
(1, 15, 4),
(2, 2, 13),
(2, 6, 17),
(2, 8, 8),
(2, 9, 6),
(2, 10, 5),
(2, 11, 8),
(2, 15, 20),
(2, 18, 8),
(2, 19, 18),
(2, 20, 5),
(2, 21, 19),
(2, 22, 18),
(2, 23, 1),
(3, 1, 5),
(3, 2, 7),
(3, 4, 3),
(3, 5, 5),
(3, 7, 4),
(3, 11, 18),
(3, 14, 3),
(3, 17, 19),
(3, 18, 17),
(3, 19, 7),
(3, 20, 7),
(4, 5, 12),
(4, 6, 11),
(4, 9, 9),
(4, 12, 1),
(4, 13, 15),
(4, 14, 4),
(4, 15, 14),
(4, 20, 14),
(4, 21, 18),
(5, 2, 2),
(5, 3, 12),
(5, 4, 3),
(5, 8, 14),
(5, 10, 6),
(5, 12, 14),
(5, 14, 5),
(5, 16, 19),
(5, 18, 6),
(5, 19, 16),
(5, 21, 5),
(5, 22, 13),
(6, 2, 4),
(6, 4, 14),
(6, 8, 19),
(6, 9, 17),
(6, 13, 18),
(6, 15, 20),
(7, 2, 12),
(7, 3, 18),
(7, 4, 9),
(7, 5, 15),
(7, 8, 7),
(7, 10, 19),
(7, 14, 1),
(7, 15, 10),
(7, 16, 13),
(7, 19, 14),
(7, 21, 20),
(7, 22, 1),
(8, 1, 1),
(8, 2, 11),
(8, 3, 10),
(8, 4, 6),
(8, 5, 14),
(8, 7, 5),
(8, 8, 2),
(8, 10, 4),
(8, 12, 5),
(8, 14, 12),
(8, 16, 14),
(8, 20, 5),
(8, 21, 16),
(8, 22, 8),
(9, 1, 11),
(9, 8, 11),
(9, 12, 11),
(9, 13, 11),
(9, 18, 19),
(9, 19, 5),
(10, 1, 6),
(10, 2, 11),
(10, 3, 20),
(10, 4, 11),
(10, 6, 13),
(10, 8, 10),
(10, 11, 10),
(10, 13, 1),
(10, 15, 7),
(10, 18, 1),
(10, 19, 3),
(10, 21, 20),
(10, 24, 1),
(11, 2, 20),
(11, 4, 9),
(11, 5, 1),
(11, 9, 3),
(11, 12, 15),
(11, 13, 14),
(11, 24, 19),
(12, 2, 14),
(12, 5, 10),
(12, 6, 4),
(12, 7, 18),
(12, 8, 19),
(12, 9, 3),
(12, 10, 1),
(12, 11, 10),
(12, 15, 9),
(12, 18, 8),
(12, 19, 1),
(12, 21, 1),
(12, 22, 4),
(12, 23, 2),
(12, 24, 17),
(13, 2, 7),
(13, 7, 4),
(13, 8, 19),
(13, 11, 3),
(13, 15, 1),
(13, 16, 2),
(13, 17, 18),
(13, 20, 12),
(13, 22, 14),
(13, 23, 19),
(14, 1, 20),
(14, 2, 8),
(14, 3, 6),
(14, 4, 1),
(14, 5, 15),
(14, 7, 12),
(14, 8, 19),
(14, 9, 10),
(14, 14, 14),
(14, 19, 2),
(14, 22, 10),
(14, 23, 9),
(15, 6, 1),
(15, 20, 18),
(16, 23, 2),
(17, 1, 15),
(17, 5, 11),
(17, 12, 10),
(17, 18, 6),
(17, 19, 10),
(17, 24, 4),
(18, 20, 16),
(18, 23, 17),
(19, 2, 1),
(19, 3, 3),
(19, 11, 5),
(19, 19, 9),
(19, 22, 16),
(19, 23, 4),
(19, 24, 2),
(20, 2, 1),
(20, 3, 1),
(20, 4, 9),
(20, 7, 15),
(20, 9, 7),
(20, 14, 19),
(20, 17, 13),
(20, 19, 3),
(20, 20, 19),
(20, 23, 1),
(21, 1, 15),
(21, 6, 7),
(21, 8, 7),
(21, 9, 20),
(21, 11, 3),
(21, 12, 12),
(21, 13, 13),
(21, 15, 8),
(22, 1, 19),
(22, 2, 1),
(22, 3, 16),
(22, 5, 6),
(22, 8, 19),
(22, 12, 2),
(22, 13, 2),
(22, 14, 5),
(22, 15, 11),
(22, 17, 11),
(22, 20, 18),
(22, 21, 17),
(22, 24, 8),
(23, 5, 4),
(23, 8, 3),
(23, 9, 5),
(23, 10, 12),
(23, 11, 1),
(23, 15, 2),
(23, 19, 8),
(23, 21, 10),
(23, 23, 16),
(24, 2, 1),
(24, 6, 2),
(24, 9, 1),
(24, 10, 18),
(24, 13, 15),
(24, 15, 20),
(24, 16, 7),
(24, 17, 2),
(24, 18, 4),
(24, 20, 18),
(24, 21, 15),
(24, 23, 12),
(25, 8, 8),
(25, 14, 13),
(25, 16, 6),
(25, 19, 17),
(25, 23, 7),
(26, 5, 13),
(26, 8, 2),
(26, 12, 19),
(26, 21, 6),
(27, 1, 5),
(27, 4, 2),
(27, 5, 7),
(27, 7, 9),
(27, 9, 3),
(27, 11, 17),
(27, 17, 12),
(27, 20, 10),
(27, 21, 6),
(27, 23, 15),
(27, 24, 15),
(28, 6, 11),
(28, 11, 19),
(28, 12, 4),
(28, 24, 16),
(29, 2, 3),
(29, 3, 12),
(29, 8, 6),
(29, 9, 1),
(29, 12, 7),
(29, 13, 8),
(29, 14, 2),
(29, 16, 7),
(29, 18, 2),
(29, 20, 8),
(29, 21, 14);

-- --------------------------------------------------------

--
-- Table structure for table `fuehrt_auf`
--

CREATE TABLE `fuehrt_auf` (
  `Lieferscheinnummer` int(11) NOT NULL,
  `Warennummer` int(11) NOT NULL,
  `Stueckzahl` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `fuehrt_auf`
--

INSERT INTO `fuehrt_auf` (`Lieferscheinnummer`, `Warennummer`, `Stueckzahl`) VALUES
(1, 4, 2),
(1, 6, 19),
(1, 9, 16),
(1, 15, 4),
(2, 2, 13),
(2, 6, 17),
(2, 8, 8),
(2, 9, 6),
(2, 10, 5),
(2, 11, 8),
(2, 15, 20),
(2, 18, 8),
(2, 19, 18),
(2, 20, 13),
(2, 21, 19),
(2, 22, 18),
(2, 23, 20),
(3, 1, 5),
(3, 2, 7),
(3, 4, 3),
(3, 5, 5),
(3, 7, 4),
(3, 11, 18),
(3, 14, 3),
(3, 17, 19),
(3, 18, 17),
(3, 19, 11),
(3, 20, 7),
(4, 5, 12),
(4, 6, 11),
(4, 9, 9),
(4, 12, 1),
(4, 13, 15),
(4, 14, 7),
(4, 15, 14),
(4, 20, 14),
(4, 21, 18),
(5, 2, 2),
(5, 3, 12),
(5, 4, 3),
(5, 8, 14),
(5, 10, 6),
(5, 12, 14),
(5, 14, 5),
(5, 16, 19),
(5, 18, 8),
(5, 19, 16),
(5, 21, 5),
(5, 22, 16),
(6, 2, 4),
(6, 4, 14),
(6, 8, 19),
(6, 9, 17),
(6, 13, 18),
(6, 15, 20),
(7, 2, 16),
(7, 3, 18),
(7, 4, 9),
(7, 5, 15),
(7, 8, 7),
(7, 10, 19),
(7, 14, 2),
(7, 15, 10),
(7, 16, 13),
(7, 19, 14),
(7, 21, 20),
(7, 22, 1),
(8, 1, 1),
(8, 2, 11),
(8, 3, 10),
(8, 4, 6),
(8, 5, 14),
(8, 7, 5),
(8, 8, 2),
(8, 10, 8),
(8, 12, 5),
(8, 14, 19),
(8, 16, 14),
(8, 20, 5),
(8, 21, 16),
(8, 22, 8),
(9, 1, 11),
(9, 8, 11),
(9, 12, 11),
(9, 13, 11),
(9, 18, 19),
(9, 19, 6),
(10, 1, 18),
(10, 2, 11),
(10, 3, 20),
(10, 4, 15),
(10, 6, 13),
(10, 8, 10),
(10, 11, 15),
(10, 13, 14),
(10, 15, 8),
(10, 18, 6),
(10, 19, 3),
(10, 21, 20),
(10, 24, 1),
(11, 2, 20),
(11, 4, 12),
(11, 5, 1),
(11, 9, 3),
(11, 12, 15),
(11, 13, 14),
(11, 24, 19),
(12, 2, 14),
(12, 5, 10),
(12, 6, 4),
(12, 7, 18),
(12, 8, 19),
(12, 9, 3),
(12, 10, 12),
(12, 11, 10),
(12, 15, 9),
(12, 18, 8),
(12, 19, 1),
(12, 21, 1),
(12, 22, 5),
(12, 23, 2),
(12, 24, 17),
(13, 2, 7),
(13, 7, 4),
(13, 8, 19),
(13, 11, 15),
(13, 15, 1),
(13, 16, 12),
(13, 17, 18),
(13, 20, 12),
(13, 22, 14),
(13, 23, 19),
(14, 1, 20),
(14, 2, 16),
(14, 3, 6),
(14, 4, 2),
(14, 5, 15),
(14, 7, 12),
(14, 8, 19),
(14, 9, 10),
(14, 14, 14),
(14, 19, 2),
(14, 22, 10),
(14, 23, 9),
(15, 6, 1),
(15, 20, 18),
(16, 23, 4),
(17, 1, 15),
(17, 5, 11),
(17, 12, 10),
(17, 18, 13),
(17, 19, 10),
(17, 24, 19),
(18, 20, 16),
(18, 23, 17),
(19, 2, 2),
(19, 3, 3),
(19, 11, 13),
(19, 19, 11),
(19, 22, 16),
(19, 23, 4),
(19, 24, 4),
(20, 2, 1),
(20, 3, 1),
(20, 4, 9),
(20, 7, 15),
(20, 9, 7),
(20, 14, 19),
(20, 17, 13),
(20, 19, 3),
(20, 20, 19),
(20, 23, 1),
(21, 1, 15),
(21, 6, 7),
(21, 8, 7),
(21, 9, 20),
(21, 11, 3),
(21, 12, 12),
(21, 13, 13),
(21, 15, 8),
(22, 1, 19),
(22, 2, 1),
(22, 3, 16),
(22, 5, 6),
(22, 8, 19),
(22, 12, 12),
(22, 13, 2),
(22, 14, 5),
(22, 15, 11),
(22, 17, 11),
(22, 20, 18),
(22, 21, 17),
(22, 24, 8),
(23, 5, 4),
(23, 8, 7),
(23, 9, 5),
(23, 10, 12),
(23, 11, 8),
(23, 15, 3),
(23, 19, 8),
(23, 21, 10),
(23, 23, 16),
(24, 2, 1),
(24, 6, 2),
(24, 9, 16),
(24, 10, 18),
(24, 13, 15),
(24, 15, 20),
(24, 16, 7),
(24, 17, 2),
(24, 18, 4),
(24, 20, 18),
(24, 21, 15),
(24, 23, 12),
(25, 8, 8),
(25, 14, 13),
(25, 16, 6),
(25, 19, 17),
(25, 23, 7),
(26, 5, 15),
(26, 8, 2),
(26, 12, 19),
(26, 21, 6),
(27, 1, 6),
(27, 4, 3),
(27, 5, 7),
(27, 7, 9),
(27, 9, 5),
(27, 11, 17),
(27, 17, 12),
(27, 20, 14),
(27, 21, 6),
(27, 23, 15),
(27, 24, 15),
(28, 6, 11),
(28, 11, 19),
(28, 12, 4),
(28, 24, 16),
(29, 2, 3),
(29, 3, 15),
(29, 8, 6),
(29, 9, 10),
(29, 12, 7),
(29, 13, 8),
(29, 14, 2),
(29, 16, 7),
(29, 18, 8),
(29, 20, 12),
(29, 23, 16);

-- --------------------------------------------------------

--
-- Table structure for table `kunde`
--

CREATE TABLE `kunde` (
  `Kundennummer` int(11) NOT NULL,
  `vorname` text NOT NULL,
  `nachname` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kunde`
--

INSERT INTO `kunde` (`Kundennummer`, `vorname`, `nachname`) VALUES
(1, 'Adamczyk', 'Gustaw'),
(2, 'Seward', 'Yusef'),
(3, 'Sharma', 'Georgios'),
(4, 'Van der Vennen', 'Clementina'),
(5, 'Cracchiolo', 'Ilinka'),
(6, 'Anker', 'Skenandoa');

-- --------------------------------------------------------

--
-- Table structure for table `lieferschein`
--

CREATE TABLE `lieferschein` (
  `Lieferscheinnummer` int(11) NOT NULL,
  `Versanddatum` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `lieferschein`
--

INSERT INTO `lieferschein` (`Lieferscheinnummer`, `Versanddatum`) VALUES
(1, '2021-11-28'),
(2, '2021-01-20'),
(3, '2021-05-17'),
(4, '2021-12-05'),
(5, '2021-09-21'),
(6, '2021-07-30'),
(7, '2021-03-17'),
(8, '2021-05-29'),
(9, '2021-11-17'),
(10, '2021-12-22'),
(11, '2021-03-21'),
(12, '2021-06-30'),
(13, '2021-12-03'),
(14, '2021-01-20'),
(15, '2021-05-06'),
(16, '2021-08-10'),
(17, '2021-02-12'),
(18, '2021-01-18'),
(19, '2021-07-11'),
(20, '2021-03-30'),
(21, '2021-05-18'),
(22, '2021-12-22'),
(23, '2021-06-04'),
(24, '2021-08-21'),
(25, '2021-08-27'),
(26, '2021-08-31'),
(27, '2021-02-24'),
(28, '2021-02-03'),
(29, '2021-03-05');

-- --------------------------------------------------------

--
-- Table structure for table `lieferung`
--

CREATE TABLE `lieferung` (
  `Lieferungsnummer` int(11) NOT NULL,
  `Kundennummer` int(11) NOT NULL,
  `Lieferscheinnummer` int(11) NOT NULL,
  `Eingangsdatum` date NOT NULL,
  `Abgeschlossen` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `lieferung`
--

INSERT INTO `lieferung` (`Lieferungsnummer`, `Kundennummer`, `Lieferscheinnummer`, `Eingangsdatum`, `Abgeschlossen`) VALUES
(1, 1, 1, '2021-12-12', 0),
(2, 2, 2, '2021-09-07', 0),
(3, 2, 3, '2021-05-24', 0),
(4, 2, 4, '2021-12-11', 0),
(5, 2, 5, '2021-12-03', 0),
(6, 2, 6, '2021-09-01', 0),
(7, 2, 7, '2021-04-18', 0),
(8, 2, 8, '2021-10-13', 0),
(9, 2, 9, '2021-12-24', 0),
(10, 2, 10, '2021-12-22', 0),
(11, 3, 11, '2021-06-11', 0),
(12, 3, 12, '2021-12-16', 0),
(13, 3, 13, '2021-12-16', 0),
(14, 3, 14, '2021-04-25', 0),
(15, 3, 15, '2021-05-25', 0),
(16, 3, 16, '2021-11-06', 0),
(17, 3, 17, '2021-07-19', 0),
(18, 3, 18, '2021-10-18', 0),
(19, 3, 19, '2021-08-15', 0),
(20, 3, 20, '2021-05-12', 0),
(21, 4, 21, '2021-09-24', 0),
(22, 4, 22, '2021-12-28', 0),
(23, 4, 23, '2021-07-05', 0),
(24, 4, 24, '2021-08-26', 0),
(25, 4, 25, '2021-10-22', 0),
(26, 4, 26, '2021-10-17', 0),
(27, 4, 27, '2021-04-07', 0),
(28, 5, 28, '2021-09-06', 0),
(29, 6, 29, '2021-10-25', 0);

-- --------------------------------------------------------

--
-- Table structure for table `ware`
--

CREATE TABLE `ware` (
  `Warennummer` int(11) NOT NULL,
  `bezeichnung` text NOT NULL,
  `preis` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ware`
--

INSERT INTO `ware` (`Warennummer`, `bezeichnung`, `preis`) VALUES
(1, 'Hohlblockstein', 7.49),
(2, '30 Nadeln', 3.99),
(3, 'Kommode', 113),
(4, 'Monitor', 164.9),
(5, 'USB-Stick 64GB', 7.99),
(6, 'Universalfernbedienung', 12.49),
(7, 'Jagdmesser', 39.9),
(8, 'Brille', 22.99),
(9, 'Fertighaus (Holz)', 175000),
(10, 'Deo', 2.55),
(11, 'Gesichtspeeling', 3.99),
(12, 'Haarbuerste', 9.99),
(13, 'Blumenvase', 32.26),
(14, 'Gummibänder', 2.95),
(15, 'Schlüsselanhänger', 3.85),
(16, '20 Kleiderbügel', 31.99),
(17, 'Duftbaum', 5.05),
(18, 'Bodenwischer', 26.99),
(19, 'Mauspad XXL', 11.75),
(20, 'Schüsselset', 54.9),
(21, 'Portemonnaie', 12.99),
(22, '1L Sojasauce', 8.49),
(23, 'RFH Pullover', 200),
(24, '4x50mm Schrauben', 8.39);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `abgelehnt`
--
ALTER TABLE `abgelehnt`
  ADD PRIMARY KEY (`lieferungsnummer`,`warennummer`),
  ADD KEY `abWarennummer` (`warennummer`);

--
-- Indexes for table `angenommen`
--
ALTER TABLE `angenommen`
  ADD PRIMARY KEY (`lieferungsnummer`,`warennummer`),
  ADD KEY `anWarennummer` (`warennummer`);

--
-- Indexes for table `enthaelt`
--
ALTER TABLE `enthaelt`
  ADD PRIMARY KEY (`Lieferungsnummer`,`Warennummer`),
  ADD KEY `Lieferung_Ware` (`Warennummer`);

--
-- Indexes for table `fuehrt_auf`
--
ALTER TABLE `fuehrt_auf`
  ADD PRIMARY KEY (`Lieferscheinnummer`,`Warennummer`),
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
-- Constraints for table `abgelehnt`
--
ALTER TABLE `abgelehnt`
  ADD CONSTRAINT `abLiefernummer` FOREIGN KEY (`lieferungsnummer`) REFERENCES `lieferung` (`Lieferungsnummer`),
  ADD CONSTRAINT `abWarennummer` FOREIGN KEY (`warennummer`) REFERENCES `ware` (`Warennummer`);

--
-- Constraints for table `angenommen`
--
ALTER TABLE `angenommen`
  ADD CONSTRAINT `anLieferungsnummer` FOREIGN KEY (`Lieferungsnummer`) REFERENCES `lieferung` (`Lieferungsnummer`),
  ADD CONSTRAINT `anWarennummer` FOREIGN KEY (`warennummer`) REFERENCES `ware` (`Warennummer`);

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
