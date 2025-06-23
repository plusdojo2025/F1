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


INSERT INTO mood (mood_id,mood_title) 
VALUES (0,"リラックス"),
       (1,"集中"),
       (2,"アクティブ"),
       (3,"リフレッシュ"),
       (4,"悲しい");


INSERT INTO category (category_id,category_title) 
VALUES (0,"運動・ストレッチ"),
       (1,"セルフケア"),
       (2,"スキルアップ"),
       (3,"環境リセット"),
       (4,"趣味"),
       (5,"キャリアアップ");


INSERT INTO master_tasks (master_task_id, title, time_span, mood_id, category_id) VALUES 
(1,"master_軽いストレッチ", 5, 3, 0),
(2,"master_瞑想でリラックス", 10, 0, 1),
(3,"master_短い英単語の復習", 8, 1, 2),
(4,"master_さっと片付け", 7, 2, 3), 
(5,"master_好きな音楽を1曲聴く", 4, 4, 4),
(6,"master_明日の計画を考える", 3, 1, 5); 


INSERT INTO account (email,password,nickname,category_id,goal_detail,created_at,login_at,consecutive_logins) 
VALUES ('dojouser1@plusdojo.jp', '5d1a82dba8cefa593f1f9ad97bf98050816e147543ce31f3ca5b15ac3905ca14', 'PlusDojo', 1, 'フィットネス向上', '2025-06-23 00:00:00', '2025-06-23 08:00:00', 1),
       ('dojouser2@plusdojo.jp', '5d1a82dba8cefa593f1f9ad97bf98050816e147543ce31f3ca5b15ac3905ca14', 'PlusDojo', 2, '新しいスキル習得', '2025-06-23 00:00:00', '2025-06-23 08:00:00', 1),
       ('dojouser3@plusdojo.jp', '5d1a82dba8cefa593f1f9ad97bf98050816e147543ce31f3ca5b15ac3905ca14', 'PlusDojo', 3, '本を30冊読む', '2025-06-23 00:00:00', '2025-06-23 08:00:00', 1),
       ('dojouser4@plusdojo.jp', '5d1a82dba8cefa593f1f9ad97bf98050816e147543ce31f3ca5b15ac3905ca14', 'PlusDojo', 4, '英語を流暢に話す', '2025-06-23 00:00:00', '2025-06-23 08:00:00', 1),
       ('dojouser5@plusdojo.jp', '5d1a82dba8cefa593f1f9ad97bf98050816e147543ce31f3ca5b15ac3905ca14', 'PlusDojo', 5, '料理の技術向上', '2025-06-23 00:00:00', '2025-06-23 08:00:00', 1);
