CREATE TABLE IF NOT EXISTS question(
    questionid INT AUTO_INCREMENT,
    userid INT,
    title VARCHAR(32) NOT NULL,
    text VARCHAR(64) NOT NULL,
    creationdate DATETIME NOT NULL,
    PRIMARY KEY (questionid)
);

CREATE TABLE IF NOT EXISTS tag(
    tagid INT AUTO_INCREMENT,
    title VARCHAR(32) NOT NULL,
    PRIMARY KEY (tagid)
);

CREATE TABLE IF NOT EXISTS question_tag(
    id INT AUTO_INCREMENT,
    tagid INT NOT NULL,
    questionid INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS answer(
    answerid INT AUTO_INCREMENT,
    questionid INT,
    userid INT,
    text VARCHAR(64) NOT NULL,
    creationdate DATETIME NOT NULL,
    PRIMARY KEY(answerid)
);

CREATE TABLE IF NOT EXISTS user(
    userid INT AUTO_INCREMENT,
    username VARCHAR(32) NOT NULL,
    password VARCHAR(32) NOT NULL,
    usertype VARCHAR(32) NOT NULL,
    score INT,
    PRIMARY KEY(userid)
);

CREATE TABLE IF NOT EXISTS questionvote(
    userid INT NOT NULL,
    questionid INT NOT NULL,
    votetype VARCHAR(32) NOT NULL
);

CREATE TABLE IF NOT EXISTS answervote(
    userid INT NOT NULL,
    answerid INT NOT NULL,
    votetype VARCHAR(32) NOT NULL
);

ALTER TABLE question
ADD FOREIGN KEY (userid) REFERENCES user(userid);

ALTER TABLE answer
ADD FOREIGN KEY (questionid) REFERENCES question(questionid);
ALTER TABLE answer
ADD FOREIGN KEY (userid) REFERENCES user(userid);

ALTER TABLE questionvote
ADD FOREIGN KEY (questionid) REFERENCES question(questionid);
ALTER TABLE questionvote
ADD FOREIGN KEY (userid) REFERENCES user(userid);

ALTER TABLE answervote
ADD FOREIGN KEY (answerid) REFERENCES answer(answerid);
ALTER TABLE answervote
ADD FOREIGN KEY (userid) REFERENCES user(userid);



