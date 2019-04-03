DROP TABLE Movies;

CREATE TABLE Movies(
	movieID INT PRIMARY KEY AUTO_INCREMENT,
	movieGenre VARCHAR(30),
	movieTitle VARCHAR(200),
	movieType boolean,
	movieRating DECIMAL(5,2)
)ENGINE=INNODB;

INSERT INTO Movies VALUES (null, "fantasy", "The Lord of The Rings: The Fellowship of the Ring", true, 10);
INSERT INTO Movies VALUES (null, "action", "Black Hawk Down", true, 8);
INSERT INTO Movies VALUES (null, "action", "Saving Private Ryan", true, 9);
INSERT INTO Movies VALUES (null, "historical", "Gladiator", true, 9);
INSERT INTO Movies VALUES (null, "sci-fi", "Alien", true, 9);
INSERT INTO Movies VALUES (null, null, "The Room", true, 1);