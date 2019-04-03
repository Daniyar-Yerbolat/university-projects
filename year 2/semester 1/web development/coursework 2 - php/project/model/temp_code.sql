ALTER TABLE questions_answers DROP FOREIGN KEY questions_answers_ibfk_1;
ALTER TABLE questions_answers DROP FOREIGN KEY questions_answers_ibfk_2;

ALTER TABLE users_answers DROP FOREIGN KEY users_answers_ibfk_1;
ALTER TABLE users_answers DROP FOREIGN KEY users_answers_ibfk_2;

ALTER TABLE users_questions DROP FOREIGN KEY users_questions_ibfk_1;
ALTER TABLE users_questions DROP FOREIGN KEY users_questions_ibfk_2;

CREATE TABLE questions_answers(
	questionID INT NOT NULL,
	answerID INT NOT NULL,
	correct boolean,
	FOREIGN KEY (questionID) REFERENCES questions(questionID),
	FOREIGN KEY (answerID) REFERENCES answers(answerID),
	PRIMARY KEY(questionID, answerID)
)ENGINE=INNODB;

CREATE TABLE users_answers(
	userID INT NOT NULL,
	answerID INT NOT NULL,
	FOREIGN KEY (userID) REFERENCES users(userID),
	FOREIGN KEY (answerID) REFERENCES answers(answerID),
	PRIMARY KEY(userID, answerID)
)ENGINE=INNODB;

CREATE TABLE users_questions(
	userID INT NOT NULL,
	questionID INT NOT NULL,
	FOREIGN KEY (userID) REFERENCES users(userID),
	FOREIGN KEY (questionID) REFERENCES questions(questionID),
	PRIMARY KEY(userID, questionID)
)ENGINE=INNODB;

ALTER TABLE correct_answer DROP FOREIGN KEY correct_answer_ibfk_1;
ALTER TABLE correct_answer DROP FOREIGN KEY correct_answer_ibfk_2;

DROP TABLE correct_answer;

CREATE TABLE correct_answer(
	questionID INT PRIMARY KEY,
	answerID INT,
	FOREIGN KEY (questionID) REFERENCES questions(questionID),
	FOREIGN KEY (answerID) REFERENCES answers(answerID)
)ENGINE=INNODB;