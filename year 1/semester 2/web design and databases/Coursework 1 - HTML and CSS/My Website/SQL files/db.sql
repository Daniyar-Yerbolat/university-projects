ALTER TABLE Developer DROP FOREIGN KEY developer_ibfk_1;
ALTER TABLE Games DROP FOREIGN KEY games_ibfk_1;
ALTER TABLE Games DROP FOREIGN KEY games_ibfk_2;
ALTER TABLE Games DROP FOREIGN KEY games_ibfk_3;

drop table Publisher;
drop table Developer;
drop table Platform;
drop table Games;

create table Publisher(
	id INT primary key,
	name varchar(30) not null,
	founded date,
	defunct boolean,
	defunctDate date null,
	country varchar(20),
	state varchar(20) null,
	city varchar(20),
	image varchar(50),
	geoImage varchar(50)
)ENGINE=INNODB;



create table Developer(
	id INT primary key,
	name varchar(30) not null,
	founded date,
	parentCompanyId INT,
	defunct boolean,
	defunctDate date null,
	country varchar(20),
	state varchar(20) null,
	city varchar(20),
	image varchar(50),
	geoImage varchar(50),
	FOREIGN KEY (parentCompanyId) REFERENCES Publisher(id)
)ENGINE=INNODB;

create table Platform(
	platformId INT primary key,
	platform varchar(10)
)ENGINE=INNODB;



create table Games(
	productId INT,
	name varchar(20),
	year date,
	developerId INT NOT NULL,
	publisherId INT NOT NULL,
	platform INT,
	FOREIGN KEY (developerId) REFERENCES Developer(id),
	FOREIGN KEY (publisherId) REFERENCES Publisher(id),
	FOREIGN KEY (platform) REFERENCES Platform(platformId),
	PRIMARY KEY (name, platform)
)ENGINE=INNODB;

INSERT INTO Publisher VALUES (1, "Valve", "1996-08-24", false, null, "USA", "Washington", "Bellevue", "../images/valve-logo.png", "../images/usa.png");
INSERT INTO Publisher VALUES (2, "Sierra", "1979-01-01", true, "2008-01-01", "USA", "California", "Fresno", "../images/valve-logo.png", "../images/usa.png");
INSERT INTO Publisher VALUES (3, "1C Company", "1991-01-01", false, null, "Russia",  null, "Moscow", "", "");
INSERT INTO Publisher VALUES (4, "Activision", "1979-01-01", false, null, "USA", "California", "Santa Monica", "", "");
INSERT INTO Publisher VALUES (5, "2K Games", "2005-01-01", true, "2008-01-01", "USA", "California", "Fresno", "../images/valve-logo.png", "../images/usa.png");
INSERT INTO Publisher VALUES (6, "Bethesda Softworks", "1986-01-01", true, "2008-01-01", "USA", "California", "Fresno", "../images/valve-logo.png", "../images/usa.png");

INSERT INTO Developer VALUES (1, "Valve", "1996-08-24", 1, false, null, "USA", "Washington", "Bellevue", "../images/valve-logo.png", "../images/usa.png");

insert into Platform VALUES (1, "PC");
insert into Platform VALUES (2, "Xbox");
insert into Platform VALUES (3, "PS4");
insert into Platform VALUES (4, "Wii U");

insert into Games VALUES (1, "Dota 2", "2012-01-01", 1, 1, 1);

