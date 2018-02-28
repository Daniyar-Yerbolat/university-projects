DROP TABLE Users;

CREATE TABLE Users(
	id INT PRIMARY KEY AUTO_INCREMENT,
	username varchar(20),
	email varchar(30),
	password varchar(30)
)ENGINE=INNODB;

INSERT INTO Users VALUES (NULL, 'Daniel', 'daniel@mail.com', '123456');