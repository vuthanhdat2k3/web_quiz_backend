CREATE TABLE users
(
      username VARCHAR(50) PRIMARY KEY,
      password VARCHAR(500) NOT NULL,
      enabled BOOLEAN        NOT NULL
);

CREATE TABLE authorities
(
      id        SERIAL PRIMARY KEY,
      username  varchar(50) NOT NULL,
      authority varchar(50) NOT NULL,
      CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username) ON DELETE CASCADE
);

CREATE TABLE user
(
      id        SERIAL PRIMARY KEY,
      username  varchar(50) NOT NULL,
      email     varchar(50) NOT NULL,
      password varchar(500) NOT NULL,
      role varchar(50)      NOT NULL
);

CREATE TABLE exam
(
    id        SERIAL PRIMARY KEY,
    examName  varchar(50) NOT NULL,
    time     integer NOT NULL,
    type varchar(50) NOT NULL
);

CREATE TABLE question
(
    id        SERIAL PRIMARY KEY,
    content  varchar(500) NOT NULL,
    optionA  varchar(500) NOT NULL,
    optionB  varchar(500) NOT NULL,
    optionC  varchar(500) NOT NULL,
    optionD  varchar(500) NOT NULL,
    optionCorrect  varchar(50) NOT NULL
);



insert into user (id, username, email, password, role)
values (1, 'dat1','dat@gmail.com' '123', 'user')