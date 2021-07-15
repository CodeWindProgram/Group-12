-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 15, 2021 at 04:15 PM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 5.6.37

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `omkar`
--

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `student_id` varchar(45) NOT NULL,
  `student_name` varchar(45) DEFAULT NULL,
  `exam_type` varchar(45) NOT NULL,
  `sub1` float DEFAULT NULL,
  `sub2` float DEFAULT NULL,
  `sub3` float DEFAULT NULL,
  `sub4` float DEFAULT NULL,
  `total` float DEFAULT NULL,
  `percentage` float DEFAULT NULL,
  `remarks` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`student_id`, `student_name`, `exam_type`, `sub1`, `sub2`, `sub3`, `sub4`, `total`, `percentage`, `remarks`) VALUES
('SD0002', 'Shraddha Desai', 'Mid Term', 70, 90, 90, 90, 340, 85, 'Good'),
('SSV001', 'SSV', 'SM', 50, 60, 5, 10, 125, 31.25, 'GOOD'),
('V0001', 'SSD', 'SEM', 50, 60, 70, 80, 260, 65, 'GOOD'),
('VA0003', 'Vaishnav Desai', 'sem', 50, 60, 80, 90, 280, 70, 'v good'),
('VA001', 'VRD', 'End Term', 80, 90, 90, 90, 350, 87.5, 'Very Good');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`student_id`,`exam_type`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
