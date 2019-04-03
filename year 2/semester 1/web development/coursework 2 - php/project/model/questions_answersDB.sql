ALTER TABLE answers DROP FOREIGN KEY answers_ibfk_1;
ALTER TABLE answers DROP FOREIGN KEY answers_ibfk_2;
ALTER TABLE questions DROP FOREIGN KEY questions_ibfk_1;

DROP TABLE questions;
DROP TABLE answers;
DROP TABLE users;

CREATE TABLE users(
	userID INT PRIMARY KEY AUTO_INCREMENT,
	username varchar(45) NOT NULL,
	password varchar(200) NOT NULL
)ENGINE=INNODB;

CREATE TABLE questions(
	questionID INT PRIMARY KEY AUTO_INCREMENT,
	userID INT NOT NULL,
	question varchar(25),
	description varchar(1000),
	solved boolean,
	FOREIGN KEY (userID) REFERENCES users(userID)
)ENGINE=INNODB;

CREATE TABLE answers(
	answerID INT PRIMARY KEY AUTO_INCREMENT,
	userID INT NOT NULL,
	questionID INT NOT NULL,
	content varchar(1000) NOT NULL,
	correct boolean,
	FOREIGN KEY (questionID) REFERENCES questions(questionID),
	FOREIGN KEY (userID) REFERENCES users(userID)
)ENGINE=INNODB;

/* Do not use those
** passwords have to be hashed
** And other tables require for a user field to be there.
** manually create accounts and questions.

INSERT INTO users VALUES (null, "Daniel", "qwerty");
INSERT INTO users VALUES (null, "Danny", "qwerty");
INSERT INTO users VALUES (null, "Daniyar", "qwerty");

INSERT INTO questions VALUES (null, 1, "How to concatinate?", "-", true);
INSERT INTO questions VALUES (null, 1, "How to print hello world?", "-", false);
INSERT INTO questions VALUES (null, 1, "How declare a variable?", "-", false);

INSERT INTO answers VALUES (null, 2, 1, "use the + (plus) sign.", false);
INSERT INTO answers VALUES (null, 3, 2, "System.out.println()", false);
INSERT INTO answers VALUES (null, 3, 2, "var x;", false);
INSERT INTO answers VALUES (null, 2, 1, "correct", true);*/