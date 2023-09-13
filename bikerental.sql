-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 02, 2023 at 10:43 PM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bikerental`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`name`, `password`) VALUES
('admin', 'admin'),
('asdf', 'asdf');

-- --------------------------------------------------------

--
-- Table structure for table `bike`
--

CREATE TABLE `bike` (
  `bikeId` varchar(10) NOT NULL,
  `model` varchar(20) NOT NULL,
  `build` varchar(20) NOT NULL,
  `color` varchar(10) NOT NULL,
  `available` varchar(5) NOT NULL,
  `type` varchar(10) NOT NULL,
  `price` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bike`
--

INSERT INTO `bike` (`bikeId`, `model`, `build`, `color`, `available`, `type`, `price`) VALUES
('B001', 'Mountain Bike', 'Aluminum', 'Black', 'false', 'mechanical', 250),
('B002', 'Road Bike', 'Carbon Fiber', 'Red', 'false', 'mechanical', 500),
('B003', 'Electric Bike', 'Aluminum', 'Silver', 'false', 'electrical', 1000),
('B004', 'BMX Bike', 'Steel', 'Blue', 'false', 'mechanical', 150),
('B005', 'Cruiser Bike', 'Aluminum', 'Green', 'true', 'mechanical', 200),
('B006', 'City Bike', 'Steel', 'White', 'true', 'mechanical', 300),
('B007', 'Mountain Bike', 'Carbon Fiber', 'Orange', 'true', 'mechanical', 700),
('B008', 'Electric Bike', 'Carbon Fiber', 'Black', 'true', 'electrical', 1500),
('B009', 'Hybrid Bike', 'Aluminum', 'Gray', 'true', 'mechanical', 350),
('B010', 'Mountain Bike', 'Steel', 'Yellow', 'true', 'mechanical', 175);

-- --------------------------------------------------------

--
-- Table structure for table `car`
--

CREATE TABLE `car` (
  `carId` varchar(10) NOT NULL,
  `model` varchar(20) NOT NULL,
  `build` varchar(20) NOT NULL,
  `color` varchar(10) NOT NULL,
  `available` varchar(5) NOT NULL,
  `type` varchar(10) NOT NULL,
  `price` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `car`
--

INSERT INTO `car` (`carId`, `model`, `build`, `color`, `available`, `type`, `price`) VALUES
('c001', 'asdf', 'fdsa', 'red', 'true', 'electrical', 1000);

-- --------------------------------------------------------

--
-- Table structure for table `rental`
--

CREATE TABLE `rental` (
  `rental_id` int(11) NOT NULL,
  `bikeId` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `rentDate` varchar(50) DEFAULT NULL,
  `duration` double DEFAULT NULL,
  `returned` tinyint(1) DEFAULT NULL,
  `price` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `rental`
--

INSERT INTO `rental` (`rental_id`, `bikeId`, `username`, `rentDate`, `duration`, `returned`, `price`) VALUES
(1, 'B001', 'nagaraj', '2023-04-27 23:05:59', 1.2, 0, 450),
(2, 'B002', 'nagaraj', '2023-04-27 23:45:54', 1, 0, 650),
(3, 'B003', 'nagaraj', '2023-04-27 23:49:10', 1.2, 0, 1350),
(4, 'B004', 'nagaraj', '2023-04-27 23:58:29', 2, 0, 450),
(5, 'B005', 'qwer', '2023-05-03 01:59:00', 2, 1, 550);

-- --------------------------------------------------------

--
-- Table structure for table `rentalcar`
--

CREATE TABLE `rentalcar` (
  `rental_id` int(11) NOT NULL,
  `carId` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `rentDate` varchar(50) DEFAULT NULL,
  `duration` double DEFAULT NULL,
  `returned` tinyint(1) DEFAULT NULL,
  `price` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `rentalcar`
--

INSERT INTO `rentalcar` (`rental_id`, `carId`, `username`, `rentDate`, `duration`, `returned`, `price`) VALUES
(1, 'c001', 'qwer', '2023-05-03 02:07:37', 3, 1, 3100);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userName` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `emailId` varchar(50) NOT NULL,
  `phoneNumber` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userName`, `name`, `emailId`, `phoneNumber`, `password`) VALUES
('abcd', 'abcd', 'abcd@gmail.com', '1234567890', 'abcd'),
('nagaraj', 'Nagaraj', 'nagaraj@gmail.com', '8989898', 'nagaraj'),
('qwer', 'qwer', 'qwer@gmail.com', '9876567890', 'qwer'),
('shreyas', 'shreyas', 'shreyas@gmail.com', '9090909090', 'shreyas');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`name`);

--
-- Indexes for table `bike`
--
ALTER TABLE `bike`
  ADD PRIMARY KEY (`bikeId`);

--
-- Indexes for table `car`
--
ALTER TABLE `car`
  ADD PRIMARY KEY (`carId`);

--
-- Indexes for table `rental`
--
ALTER TABLE `rental`
  ADD PRIMARY KEY (`rental_id`),
  ADD KEY `bikeId` (`bikeId`),
  ADD KEY `username` (`username`);

--
-- Indexes for table `rentalcar`
--
ALTER TABLE `rentalcar`
  ADD PRIMARY KEY (`rental_id`),
  ADD KEY `carId` (`carId`),
  ADD KEY `username` (`username`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userName`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `rental`
--
ALTER TABLE `rental`
  MODIFY `rental_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `rentalcar`
--
ALTER TABLE `rentalcar`
  MODIFY `rental_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `rental`
--
ALTER TABLE `rental`
  ADD CONSTRAINT `rental_ibfk_1` FOREIGN KEY (`bikeId`) REFERENCES `bike` (`bikeId`),
  ADD CONSTRAINT `rental_ibfk_2` FOREIGN KEY (`username`) REFERENCES `user` (`userName`);

--
-- Constraints for table `rentalcar`
--
ALTER TABLE `rentalcar`
  ADD CONSTRAINT `rentalcar_ibfk_1` FOREIGN KEY (`carId`) REFERENCES `car` (`carId`),
  ADD CONSTRAINT `rentalcar_ibfk_2` FOREIGN KEY (`username`) REFERENCES `user` (`userName`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
