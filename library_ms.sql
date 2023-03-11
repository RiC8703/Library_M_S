-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 10, 2023 at 09:15 AM
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
-- Database: `library_ms`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `name`, `password`) VALUES
(1, 'Admin', 'Admin@123'),
(1, 'Admin', 'Admin@123');

-- --------------------------------------------------------

--
-- Table structure for table `book_details`
--

CREATE TABLE `book_details` (
  `book_id` int(11) NOT NULL,
  `book_name` varchar(100) NOT NULL,
  `author` varchar(50) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `book_details`
--

INSERT INTO `book_details` (`book_id`, `book_name`, `author`, `quantity`) VALUES
(1001, 'Data Structures', 'RS Salaria', 5),
(1002, 'Digital Electronics', 'A. Anand Kumar', 2),
(1003, 'Advanced Engineering Mathematics', 'Chandrika Prasad & Reena Garg', 2),
(1004, 'Operating Systems', 'Ekta Walia', 1),
(1005, 'Database Management Systems,', 'R.P. Mahapatra & Govind Verma', 4),
(1006, 'Object Oriented Programming in C++ and Java', ' D.Samantha', 6),
(1007, 'Software Engineering', 'Nasib Singh Gill', 4),
(1008, 'Artificial Intelligence and Machine Learning', 'Chandra S.S. & H.S. Anand ', 2),
(1009, 'Data Science & Analytics', 'V.K. Jain', 6),
(1010, 'Introduction to Embedded Systems', 'Raj Kamal', 2),
(1011, 'Engineering Drawing and Computer Graphics', 'Shah', 5),
(1012, 'Computer Concepts and Programming in C', 'R.S. Salaria', 3),
(1013, 'Communication Skills', ' Pushplata, Sanjay Kumar', 3);

-- --------------------------------------------------------

--
-- Table structure for table `issue_book_details`
--

CREATE TABLE `issue_book_details` (
  `id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `book_name` varchar(100) NOT NULL,
  `student_id` bigint(20) NOT NULL,
  `student_name` varchar(50) NOT NULL,
  `issue_date` date NOT NULL,
  `due_date` date NOT NULL,
  `status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `issue_book_details`
--

INSERT INTO `issue_book_details` (`id`, `book_id`, `book_name`, `student_id`, `student_name`, `issue_date`, `due_date`, `status`) VALUES
(1, 1001, 'Data Structures', 210110101016, 'Kunj Faladu', '2022-11-01', '2022-11-30', 'Returned'),
(2, 1010, 'Introduction to Embedded Systems', 210110101016, 'Kunj Faladu', '2022-11-01', '2022-11-27', 'Returned'),
(3, 1007, 'Software Engineering', 210110101019, 'Rishi ', '2022-10-07', '2022-11-03', 'Returned'),
(4, 1009, 'Data Science & Analytics', 210110101043, 'Dhruvin Patel', '2022-10-19', '2022-11-03', 'Pending'),
(5, 1003, 'Advanced Engineering Mathematics', 210110101028, 'Makwana Rohit', '2022-10-13', '2022-11-30', 'Pending'),
(6, 1003, 'Advanced Engineering Mathematics', 210110101059, 'Vora Yash', '2022-10-31', '2022-11-30', 'Pending'),
(7, 1006, 'Object Oriented Programming in C++ and Java', 210113131313, 'TestUser', '2022-11-02', '2022-11-04', 'Pending'),
(8, 1011, 'Engineering Drawing and Computer Graphics', 210110101020, 'Shivam', '2022-12-08', '2022-11-01', 'Pending'),
(9, 1005, 'Database Management Systems,', 210110101001, 'Priyanshu', '2022-10-01', '2022-11-01', 'Pending'),
(10, 1008, 'Artificial Intelligence and Machine Learning', 210110101016, 'Kunj Faladu', '2022-10-04', '2022-11-07', 'Pending'),
(11, 1001, 'Data Structures', 210110101016, 'Kunj Faladu', '2022-11-06', '2022-11-30', 'Pending'),
(12, 1003, 'Advanced Engineering Mathematics', 210113131313, 'TestUser', '2022-11-01', '2022-11-30', 'Pending');

-- --------------------------------------------------------

--
-- Table structure for table `student_details`
--

CREATE TABLE `student_details` (
  `student_id` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `course` varchar(50) NOT NULL,
  `branch` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student_details`
--

INSERT INTO `student_details` (`student_id`, `name`, `course`, `branch`) VALUES
(210110101001, 'Priyanshu', 'B.Tech', 'CSE'),
(210110101013, 'Prakshal ', 'B.Tech', 'CSE'),
(210110101016, 'Kunj Faladu', 'B.Tech', 'CSE'),
(210110101019, 'Rishi ', 'B.Tech', 'CSE'),
(210110101020, 'Shivam', 'B.Tech', 'CSE'),
(210110101027, 'Dhruv Chalse', 'B.Tech', 'CSE'),
(210110101028, 'Makwana Rohit', 'B.Tech', 'CSE'),
(210110101033, 'Ajay ', 'B.Tech', 'CSE'),
(210110101043, 'Dhruvin Patel', 'M.Sc', 'Microbiology'),
(210110101059, 'Vora Yash', 'B.Tech', 'CSE'),
(210113131313, 'TestUser', 'Ph.D', 'CSE');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `PASSWORD` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `contact` varchar(10) DEFAULT NULL,
  `Student_ID` bigint(20) DEFAULT NULL,
  `course` varchar(50) DEFAULT NULL,
  `branch` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `PASSWORD`, `email`, `contact`, `Student_ID`, `course`, `branch`) VALUES
(4, 'kunj', '123', 'kunj13@gmail.com', '99799', 210113131313, 'M.Sc', 'CSE'),
(5, 'Kunj ', 'k1u2n3j4', 'kunj123@gmail.com', '81415', 210110101016, 'B.Tech', 'IT'),
(6, 'Rishi', 'rishi123', 'Rishi8703@gmail.com', '0987654321', 210110101019, 'B.Tech', 'CSE'),
(7, 'dhruvin', '1234', 'dhruvin123@gmail.com', '1234567890', 210110101043, 'M.Sc', 'Microbiology'),
(8, 'Ajay', '1234', 'Ajay123@gmail.com', '1230981139', 210110101033, 'B.Tech', 'CSE'),
(9, 'Test', '123', 'test@123gmail.com', '9876543210', 21011313131, 'B.Tech', 'CBE');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book_details`
--
ALTER TABLE `book_details`
  ADD PRIMARY KEY (`book_id`);

--
-- Indexes for table `issue_book_details`
--
ALTER TABLE `issue_book_details`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student_details`
--
ALTER TABLE `student_details`
  ADD PRIMARY KEY (`student_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Student_ID` (`Student_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `issue_book_details`
--
ALTER TABLE `issue_book_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
