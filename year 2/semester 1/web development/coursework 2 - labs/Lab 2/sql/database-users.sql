ALTER TABLE users_movies DROP FOREIGN KEY users_movies_ibfk_1;
ALTER TABLE users_movies DROP FOREIGN KEY users_movies_ibfk_2;

DROP TABLE users;
DROP TABLE Movies;
DROP TABLE users_movies;

CREATE TABLE users(
	userID INT PRIMARY KEY AUTO_INCREMENT,
	username varchar(45),
	password varchar(45)
)ENGINE=INNODB;

INSERT INTO users VALUES (null, "Frodo", "qwerty123");
INSERT INTO users VALUES (null, "Pippin", "qwerty123");
INSERT INTO users VALUES (null, "Merry", "qwerty123");
INSERT INTO users VALUES (null, "Sam", "qwerty123");
INSERT INTO users VALUES (null, "Gimli", "qwerty123");

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

CREATE TABLE users_movies(
	userID INT NOT NULL,
	movieID INT NOT NULL,
	FOREIGN KEY (userID) REFERENCES users(userID),
	FOREIGN KEY (movieID) REFERENCES movies(movieID),
	PRIMARY KEY(userID, movieID)
)ENGINE=INNODB;

INSERT INTO users_movies VALUES (1, 1);
INSERT INTO users_movies VALUES (2, 1);
INSERT INTO users_movies VALUES (3, 1);
INSERT INTO users_movies VALUES (4, 1);
INSERT INTO users_movies VALUES (5, 1);
INSERT INTO users_movies VALUES (1, 2);
INSERT INTO users_movies VALUES (2, 3);
INSERT INTO users_movies VALUES (3, 4);
INSERT INTO users_movies VALUES (4, 5);



/*select username, movieTitle, movieGenre, movieRating from users,users_movies,movies where users.userId=users_movies.userID and movies.movieID=users_movies.MovieID;*/

/*SELECT username, movieTitle, movieRating, movieType FROM users as u inner join users_movies as u_m on u_m.userID=u.userID inner join movies on u_m.movieID=movies.movieID;*/