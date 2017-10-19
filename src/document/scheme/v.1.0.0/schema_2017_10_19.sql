DROP TABLE LECTURE_PAYMENT_LOG;
CREATE TABLE LECTURE_PAYMENT_LOG (lecture_payment_log_id bigint NOT NULL AUTO_INCREMENT, lecture_name varchar(20) NOT NULL, payment_price int(30) unsigned NOT NULL, student_name varchar(20) NOT NULL, member_name varchar(20) NOT NULL, create_date datetime DEFAULT CURRENT_TIMESTAMP NOT NULL, PRIMARY KEY (lecture_payment_log_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE MEMBER_LOGIN_LOG;
CREATE TABLE MEMBER_LOGIN_LOG (member_login_log_id bigint NOT NULL AUTO_INCREMENT, member_id bigint NOT NULL, member_name varchar(20) NOT NULL, create_date datetime DEFAULT CURRENT_TIMESTAMP NOT NULL, PRIMARY KEY (member_login_log_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
