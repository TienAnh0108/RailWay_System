CREATE DATABASE IF NOT EXISTS `test` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
use `test`;

DROP TABLE IF EXISTS `trains`;
CREATE TABLE `trains` (
  `ID` int NOT NULL,
  `Name` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into `trains`(`ID`, `Name`) values
(1, "Train 1"), (2, "Train 2"), (3, "Train 3"), (4, "Train 4"), (5, "Train 5"),
(6, "Train 6"), (7, "Train 7"), (8, "Train 8"), (9, "Train 9"), (10, "Train 10");

DROP TABLE IF EXISTS `stations`;
CREATE TABLE `stations` (
  `ID` int NOT NULL,
  `Name` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into `stations`(`ID`, `Name`) values
(1, "Son La"), (2, "Ha Noi"), (3, "Hai Phong"), (4, "Vinh"), (5, "Hue"),
(6, "Thanh Hoa"), (7, "Da Nang"), (8, "Quy Nhon"), (9, "Da Lat"), (10, "Sai Gon");

DROP TABLE IF EXISTS `tracks`;
CREATE TABLE `tracks` (
  `ID` int NOT NULL,
  `StartStation` int NOT NULL,
  `EndStation` int not null,
  PRIMARY KEY (`ID`),
  foreign key (`StartStation`) references `stations`(`ID`),
  foreign key (`EndStation`) references `stations`(`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into `tracks`(`ID`, `StartStation`, `EndStation`) values
(1, 1, 2), (2, 2, 3), (3, 3, 4), (4, 4, 5), (5, 5, 6),
(6, 6, 7), (7, 7, 8), (8, 8, 9), (9, 9, 10),(10, 2, 10);

DROP TABLE IF EXISTS `schedules`;
CREATE TABLE `schedules` (
  `ID` int NOT NULL,
  `TrainID` int not null,
  `DepartureTime` varchar(50) NOT NULL,
  `ArriveTime` varchar(50) NOT NULL,
  `Start` int NOT NULL,
  `Destination` int not null,
  PRIMARY KEY (`ID`),
  foreign key (`TrainID`) references `trains`(`ID`),
  foreign key (`Start`) references `stations`(`ID`),
  foreign key (`Destination`) references `stations`(`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into `schedules`(`ID`, `TrainID`, `DepartureTime`, `ArriveTime`, `Start`, `Destination`) values
(1, 1, "06:00", "22:00", 1, 10),
(2, 2, "10:00", "12:00", 1, 2),
(3, 3, "06:00", "09:00", 2, 3),
(4, 4, "18:00", "22:00", 9, 10),
(5, 5, "08:00", "20:00", 5, 10),
(6, 6, "08:00", "15:00", 3, 5),
(7, 7, "15:00", "22:00", 8, 10),
(8, 8, "09:30", "21:30", 2, 9),
(9, 9, "08:00", "18:00", 3, 8),
(10, 10, "05:00", "22:00", 2, 10);

DROP TABLE IF EXISTS `sequences`;
CREATE TABLE `sequences` (
  `ID` int NOT NULL,
  `ScheduleID` int not null,
  `ArriveTime` varchar(50) NOT NULL,
  `DepartureTime` varchar(50) NOT NULL,
  `Number` int NOT NULL,
  `StationID` int not null,
  PRIMARY KEY (`ID`),
  foreign key (`StationID`) references `stations`(`ID`),
  foreign key (`ScheduleID`) references `schedules`(`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into `sequences`(`ID`, `ScheduleID`, `ArriveTime`, `DepartureTime`, `Number`, `StationID`)
values
(1, 1, "06:00", "06:00", 1, 1),
(2, 1, "08:00", "08:15", 2, 2),
(3, 1, "10:00", "10:15", 3, 3),
(4, 1, "11:00", "11:05", 4, 4),
(5, 1, "13:00", "13:10", 5, 5),
(6, 1, "15:00", "15:20", 6, 6),
(7, 1, "17:00", "17:30", 7, 7),
(8, 1, "18:00", "18:15", 8, 8),
(9, 1, "20:00", "20:05", 9, 9),
(10, 1, "22:00", "22:00", 10, 10),

(11, 2, "10:00", "10:00", 1, 1),
(12, 2, "12:00", "12:00", 2, 2),

(13, 3, "06:00", "06:00", 1, 2),
(14, 3, "09:00", "09:00", 2, 3),

(15, 4, "18:00", "18:00", 1, 9),
(16, 4, "22:00", "22:00", 2, 10),

(17, 10, "05:00", "05:00", 1, 2),
(18, 10, "22:00", "22:00", 2, 10),

(19, 5, "08:00", "08:00", 1, 5),
(20, 5, "10:00", "10:15", 2, 6),
(21, 5, "12:00", "12:25", 3, 7),
(22, 5, "15:00", "15:30", 4, 8),
(23, 5, "18:00", "18:05", 5, 9),
(24, 5, "20:00", "20:00", 6, 10),

(25, 6, "08:00", "08:00", 1, 3),
(26, 6, "11:00", "12:00", 2, 4),
(27, 6, "15:00", "15:00", 3, 5),

(28, 7, "15:00", "15:00", 1, 8),
(29, 7, "18:00", "19:00", 2, 9),
(30, 7, "22:00", "22:00", 3, 10),

(31, 8, "09:30", "09:30", 1, 2),
(32, 8, "11:00", "11:27", 2, 3),
(33, 8, "12:50", "13:00", 3, 4),
(34, 8, "14:25", "14:38", 4, 5),
(35, 8, "16:04", "16:23", 5, 6),
(36, 8, "18:00", "18:22", 6, 7),
(37, 8, "20:00", "20:06", 7, 8),
(38, 8, "21:30", "21:30", 8, 9),

(39, 9, "08:00", "08:00", 1, 3),
(40, 9, "10:30", "10:45", 2, 4),
(41, 9, "12:30", "12:45", 3, 5),
(42, 9, "14:30", "14:45", 4, 6),
(43, 9, "16:30", "16:45", 5, 7),
(44, 9, "18:00", "18:00", 6, 8);