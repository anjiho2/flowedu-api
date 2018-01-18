DROP TABLE
    LECTURE_PAYMENT_LOG;
CREATE TABLE
    LECTURE_PAYMENT_LOG
    (
        lecture_payment_log_id bigint NOT NULL AUTO_INCREMENT,
        lecture_rel_id bigint NOT NULL,
        lecture_name VARCHAR(20) NOT NULL,
        payment_price INT(30) unsigned NOT NULL,
        student_name VARCHAR(20) NOT NULL,
        member_name VARCHAR(20) NOT NULL,
        create_date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
        cat_id bigint NOT NULL,
        card_no bigint NOT NULL,
        install_ment VARCHAR(5) NOT NULL,
        trans_amt INT(30) unsigned NOT NULL,
        auth_no bigint NOT NULL,
        reply_date bigint NOT NULL,
        accepter_code VARCHAR(5) NOT NULL,
        issure_code VARCHAR(5) NOT NULL,
        issure_name VARCHAR(20) NOT NULL,
        trans_no VARCHAR(10) NOT NULL,
        merchant_reg_no bigint NOT NULL,
        auth_type VARCHAR(10) NOT NULL,
        cancel_yn TINYINT(1) DEFAULT '0' NOT NULL,
        PRIMARY KEY (lecture_payment_log_id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE
    MEMBER_LOGIN_LOG;
CREATE TABLE
    MEMBER_LOGIN_LOG
    (
        member_login_log_id bigint NOT NULL AUTO_INCREMENT,
        member_id bigint NOT NULL,
        member_name VARCHAR(20) NOT NULL,
        connect_ip VARCHAR(30) NOT NULL,
        create_date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
        PRIMARY KEY (member_login_log_id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;
