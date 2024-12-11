CREATE DATABASE IF NOT EXISTS `test` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
use `test`;

DROP TABLE IF EXISTS `trains`;
CREATE TABLE `trains` (
  `ID` int NOT NULL,
  `Name` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into `trains`(`ID`, `Name`) values (1, "A"), (2, "B");

DROP TABLE IF EXISTS `stations`;
CREATE TABLE `stations` (
  `ID` int NOT NULL,
  `Name` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into `stations`(`ID`, `Name`) values (1, "Ha Noi"), (2, "Sai Gon"), (3, "Hue");

DROP TABLE IF EXISTS `tracks`;
CREATE TABLE `tracks` (
  `ID` int NOT NULL,
  `StartStation` int NOT NULL,
  `EndStation` int not null,
  PRIMARY KEY (`ID`),
  foreign key (`StartStation`) references `stations`(`ID`),
  foreign key (`EndStation`) references `stations`(`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into `tracks`(`ID`, `StartStation`, `EndStation`) values (1, 1, 2), (2, 2, 3);

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

insert into `schedules`(`ID`, `TrainID`, `DepartureTime`, `ArriveTime`, `Start`, `Destination`)
values (1, 2, "10:10", "10:10", 1, 3), (2, 1, "20:20", "20:20", 1, 2);

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

DROP TABLE IF EXISTS `passenger`;
CREATE TABLE `passengers` (
  `ID` int NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Tel` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into `sequences`(`ID`, `ScheduleID`, `ArriveTime`, `DepartureTime`, `Number`, `StationID`)
values
(1, 1, "10:10", "10:10", 1, 1),
(2, 1, "15:10", "15:10", 2, 2),
(3, 2, "20:20", "20:20", 1, 1),
(4, 2, "21:20", "21:30", 2, 2),
(5, 2, "23:40", "23:40", 3, 3);