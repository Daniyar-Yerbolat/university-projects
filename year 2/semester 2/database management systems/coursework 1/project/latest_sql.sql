CREATE DATABASE Gym;

USE Gym;

SET FOREIGN_KEY_CHECKS=0;
/*
ALTER TABLE Staff DROP FOREIGN KEY Staff_ibfk_1;
ALTER TABLE Staff DROP FOREIGN KEY Staff_ibfk_2;
ALTER TABLE Member DROP FOREIGN KEY Member_ibfk_1;
ALTER TABLE Room DROP FOREIGN KEY Room_ibfk_1;
ALTER TABLE Equipment DROP FOREIGN KEY Equipment_ibfk_1;
ALTER TABLE Bookings_Equipment DROP FOREIGN KEY Bookings_Equipment_ibfk_1;
ALTER TABLE Bookings_Equipment DROP FOREIGN KEY Bookings_Equipment_ibfk_2;
ALTER TABLE Bookings_Room DROP FOREIGN KEY Bookings_Room_ibfk_1;
ALTER TABLE Bookings_Room DROP FOREIGN KEY Bookings_Room_ibfk_2;
ALTER TABLE Program DROP FOREIGN KEY Program_ibfk_1;
ALTER TABLE User DROP FOREIGN KEY User_ibfk_1;
ALTER TABLE User DROP FOREIGN KEY User_ibfk_2;
*/


DROP TABLE IF EXISTS Department;
DROP TABLE IF EXISTS Staff;
DROP TABLE IF EXISTS Member;
DROP TABLE IF EXISTS Room;
DROP TABLE IF EXISTS Equipment;
DROP TABLE IF EXISTS Bookings_Equipment;
DROP TABLE IF EXISTS Bookings_Room;
DROP TABLE IF EXISTS Program;
DROP TABLE IF EXISTS program_member;

#TABLES
CREATE TABLE Department(
deptID INT(2) AUTO_INCREMENT PRIMARY KEY,
deptName VARCHAR(15) NOT NULL,
headID INT(2), # can be NULL, since head can fired, transfered, promoted.
FOREIGN KEY (headID) REFERENCES Staff(staffID) ON DELETE SET NULL ON UPDATE CASCADE
)ENGINE=INNODB;

CREATE TABLE Staff(
staffID INT(2) AUTO_INCREMENT PRIMARY KEY,
firstname VARCHAR(20) NOT NULL,
lastname VARCHAR(20) NOT NULL,
gender TINYINT(1),
dateOfBirth DATE NOT NULL,
deptID INT(2),
supervisorID INT(2),
FOREIGN KEY (deptID) REFERENCES Department(deptID) ON DELETE SET NULL ON UPDATE CASCADE, 
FOREIGN KEY (supervisorID) REFERENCES Staff(staffID) ON DELETE SET NULL ON UPDATE CASCADE
)ENGINE=INNODB;

CREATE TABLE Program(
programID CHAR(5) PRIMARY KEY NOT NULL,
programName VARCHAR(25) NOT NULL,
trainerID INT(2),
available BIT(1), # 1 for yes, 0 for no
FOREIGN KEY (trainerID) REFERENCES Staff(staffID) ON DELETE SET NULL ON UPDATE CASCADE
)ENGINE=INNODB;

CREATE TABLE Member(
memberID INT(2) AUTO_INCREMENT PRIMARY KEY,
firstname VARCHAR(20) NOT NULL,
lastname VARCHAR(20) NOT NULL,
gender BIT(1), # 1 for male, 0 for female
dateOfBirth DATE NOT NULL # to derive age, TRUNCATE(DATEDIFF(CURDATE(),'1996-08-18')/365, 0)
)ENGINE=INNODB;

# NOTE TO TEAM - made an extra table since the relation is many to many.
CREATE TABLE program_member(
memberID INT(2),
programID CHAR(5),
completed TINYINT(1), # 1 for completed, 0 for ongoing
FOREIGN KEY (programID) REFERENCES Program(programID) ON DELETE NO ACTION ON UPDATE CASCADE,
FOREIGN KEY (memberID) REFERENCES Member(memberID) ON DELETE NO ACTION ON UPDATE CASCADE,
PRIMARY KEY(memberID, programID)
)ENGINE=INNODB;

CREATE TABLE Room(
roomID INT(2) AUTO_INCREMENT PRIMARY KEY,
roomName VARCHAR(15) NOT NULL,
roomSize INT(2),
maintainerID INT(2),
available TINYINT(1), # 1 for yes, 0 for no 
FOREIGN KEY (maintainerID) REFERENCES Staff(staffID) ON DELETE SET NULL ON UPDATE CASCADE
)ENGINE=INNODB;

CREATE TABLE Equipment(
equipmentID INT(2) AUTO_INCREMENT PRIMARY KEY,
equipmentName VARCHAR(15) NOT NULL,
roomID INT(2),
FOREIGN KEY (roomID) REFERENCES Room(roomID) ON DELETE SET NULL ON UPDATE CASCADE
)ENGINE=INNODB;

CREATE TABLE Bookings_Equipment(
equipmentID INT(2) NOT NULL,
start_time DATETIME NOT NULL,
end_time DATETIME NOT NULL,
memberID INT(2) NOT NULL,
FOREIGN KEY (equipmentID) REFERENCES Equipment(equipmentID) ON DELETE NO ACTION ON UPDATE CASCADE,
FOREIGN KEY (memberID) REFERENCES Member(memberID) ON DELETE NO ACTION ON UPDATE CASCADE,
PRIMARY KEY(equipmentID, start_time)
)ENGINE=INNODB;

CREATE TABLE Bookings_Room(
roomID INT(2) NOT NULL,
start_time DATETIME NOT NULL,
end_time DATETIME NOT NULL,
memberID INT(2) NOT NULL,
FOREIGN KEY (roomID) REFERENCES Room(roomID) ON DELETE NO ACTION ON UPDATE CASCADE,
FOREIGN KEY (memberID) REFERENCES member(memberID) ON DELETE NO ACTION ON UPDATE CASCADE,
PRIMARY KEY (roomID, start_time, memberID)
)ENGINE=INNODB;

/*
SYNTAX

ALTER TABLE profiles
   ADD CONSTRAINT `fk_test`
   FOREIGN KEY (`member_id` )
   REFERENCES `members` (`member_id` )
   ON DELETE CASCADE

*/
#DATA-LOADING
LOAD DATA INFILE 'program_member.txt' INTO TABLE program_member;
LOAD DATA INFILE 'bookings_equipment.txt' INTO TABLE bookings_equipment;
LOAD DATA INFILE 'bookings_room.txt' INTO TABLE bookings_room;
LOAD DATA INFILE 'department.txt' INTO TABLE department;
LOAD DATA INFILE 'equipment.txt' INTO TABLE equipment;
LOAD DATA INFILE 'member.txt' INTO TABLE member;
LOAD DATA INFILE 'program.txt' INTO TABLE program;
LOAD DATA INFILE 'room.txt' INTO TABLE room;
LOAD DATA INFILE 'staff.txt' INTO TABLE staff;


#VIEWS
CREATE VIEW `departmentview`  
AS  SELECT `d`.`deptName` AS `deptName`,`d`.`headID` AS `headID`,`s`.`firstname` AS `firstname`,`s`.`lastname` AS `lastname` 
FROM (`department` `d` join `staff` `s`) 
WHERE (`d`.`headID` = `s`.`staffID`);

CREATE VIEW `equipmentview`  
AS  SELECT `m`.`firstname` AS `firstname`,`m`.`lastname` AS `lastname`,`e`.`equipmentName` AS `equipmentName`,`r`.`roomName` AS `roomName`,`b`.`start_time` AS `start_time`,`b`.`end_time` AS `end_time` 
FROM (((`member` `m` join `equipment` `e`) join `bookings_equipment` `b`) join `room` `r`) 
WHERE ((`b`.`memberID` = `m`.`memberID`) and (`b`.`equipmentID` = `e`.`equipmentID`) and (`e`.`roomID` = `r`.`roomID`));

CREATE VIEW `roomview`  
AS  SELECT `m`.`firstname` AS `firstName`,`m`.`lastname` AS `lastName`,`r`.`roomName` AS `roomName`,`b`.`start_time` AS `start_time`,`b`.`end_time` AS `end_time` 
FROM ((`bookings_room` `b` join `room` `r`) join `member` `m`) 
WHERE ((`b`.`roomID` = `r`.`roomID`) and (`b`.`memberID` = `m`.`memberID`));

CREATE VIEW `trainersprograms`  
AS  SELECT `s`.`staffID` AS `staffID`,`s`.`firstname` AS `firstname`,`s`.`lastname` AS `lastname`,`p`.`programName` AS `programName` 
FROM (`staff` `s` join `program` `p`) 
WHERE (`s`.`staffID` = `p`.`trainerID`);

CREATE VIEW `maintainerview`  
AS  SELECT `r`.`roomID` AS `roomID`,`r`.`roomName` AS `roomName`,`r`.`maintainerID` AS `maintainerID`,`s`.`firstname` AS `firstname`,`s`.`lastname` AS `lastname`,`s`.`supervisorID` AS `supervisorID` 
FROM (`room` `r` join `staff` `s`) 
WHERE (`r`.`maintainerID` = `s`.`staffID`);


SET FOREIGN_KEY_CHECKS=1;

#INDEXES
CREATE INDEX staff_names ON staff(firstname,lastname);
CREATE INDEX member_names ON member(firstname,lastname);
CREATE INDEX program_trainer ON program(programName,trainerID);
CREATE INDEX equipment_room ON equipment(equipmentName,roomID);
CREATE INDEX equipment_member ON bookings_equipment(equipmentID,memberID);
CREATE INDEX room_member ON bookings_room(roomID,memberID);

#QUERIES
#employee.staffID, employee.firstname, employee.lastname, employee.supervisorID
#Shows all supervisors employees
SELECT employee.staffID, employee.firstname, employee.lastname, employee.supervisorID, supervisor.supervisorID, supervisor.staffID, supervisor.firstname, supervisor.lastname
FROM staff as employee INNER JOIN staff as supervisor ON employee.supervisorID=supervisor.staffID
WHERE employee.supervisorID!=0;

SELECT * FROM staff, department WHERE staff.deptID=department.deptID;

SELECT * FROM bookings_room WHERE bookings_room.roomID=X AND bookings_room.startdate>Y AND bookings_room.startdate<Z;

SELECT * FROM program WHERE program.userID=X AND program.completed=0;

SELECT DISTINCT staff.firstname, staff.lastname FROM program INNER JOIN staff ON staff.staffID=program.trainerID WHERE program.available=0;

# changing 1 and 0 to male and female
SELECT (CASE WHEN staff.gender = 1 THEN 'MALE' WHEN staff.gender = 0 THEN 'FEMALE' END) AS sex FROM staff;

# derive age
SELECT firstname, lastname, TRUNCATE(DATEDIFF(CURDATE(),dateOfBirth)/365, 0) FROM staff;

# (@row_number:=@row_number+1) ... (SELECT @row_number:=0) AS t to create row numbers
SELECT (@row_number:=@row_number+1) AS number, staff.firstname, staff.lastname FROM staff INNER JOIN department ON staff.staffID = department.headID,(SELECT @row_number:=0) AS t;

# list of all maintainers
SELECT DISTINCT staff.firstname, staff.lastname FROM staff INNER JOIN room ON staff.staffID = room.maintainerID;

# all rooms and their maintainers
SELECT staff.firstname, staff.lastname, room.roomName FROM staff INNER JOIN room ON staff.staffID = room.maintainerID;

SELECT room.roomID, roomName, equipmentID, equipmentName FROM room INNER JOIN equipment ON room.roomID=equipment.roomID;

SELECT staff.id FROM staff WHERE staff.firstname=X AND staff.lastname=Y;

SELECT * FROM bookings_equipment WHERE bookings_equipment.equipmentID=X AND bookings_equipment.startdate>Y AND bookings_equipment.startdate<Z;

SELECT COUNT(rooms) FROM (SELECT * FROM bookings_rooms INNER JOIN room ON bookings_rooms.roomID=room.roomID GROUP BY bookings_room WHERE bookings_rooms.start_time>NOW()) as rooms;

select roomID, count(roomID) from bookings_room WHERE roomid=X AND start_time>NOW() GROUP BY roomID;