CREATE DATABASE f1;


CREATE TABLE mood (
mood_id INT,
mood_title VARCHAR(100),
PRIMARY KEY (mood_id));	


CREATE TABLE category (
category_id INT,
category_title VARCHAR(100),
PRIMARY KEY (category_id));


CREATE TABLE account (
account_id INT AUTO_INCREMENT,
email VARCHAR(100) NOT NULL UNIQUE,
password VARCHAR(255) NOT NULL,
nickname VARCHAR(100) NOT NULL,
category_id INT,
goal_detail VARCHAR(200),
created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
login_at DATETIME,
consecutive_logins INT DEFAULT 1,
PRIMARY KEY(account_id),
FOREIGN KEY(category_id)REFERENCES category(category_id));


CREATE TABLE tasks (
task_id INT AUTO_INCREMENT,
account_id INT,
title VARCHAR(100) NOT NULL,
time_span INT NOT NULL,
mood_id INT,
category_id INT,
created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
is_private BOOLEAN DEFAULT FALSE,
PRIMARY KEY (task_id),
FOREIGN KEY(account_id)REFERENCES account(account_id),
FOREIGN KEY(category_id)REFERENCES category(category_id),
FOREIGN KEY(mood_id)REFERENCES mood(mood_id));


CREATE TABLE log (
log_id INT AUTO_INCREMENT,
account_id INT,
task_title VARCHAR(100),
mood_id INT,
category_id INT,
log_time DATETIME NOT NULL,
duration INT NOT NULL,
satisfaction_level INT,
PRIMARY KEY (log_id),
FOREIGN KEY(account_id)REFERENCES account(account_id),
FOREIGN KEY(mood_id)REFERENCES mood(mood_id),
FOREIGN KEY(category_id)REFERENCES category(category_id));


CREATE TABLE master_tasks (
master_task_id INT,
title VARCHAR (100) NOT NULL,
time_span INT NOT NULL,
mood_id INT,
category_id INT,
PRIMARY KEY (master_task_id),
FOREIGN KEY(category_id)REFERENCES category(category_id),
FOREIGN KEY(mood_id)REFERENCES mood(mood_id));