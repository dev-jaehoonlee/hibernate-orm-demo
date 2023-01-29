USE demo;

DROP TABLE IF EXISTS user_skills CASCADE;
DROP TABLE IF EXISTS user_details CASCADE;
DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE IF NOT EXISTS users
(
    user_id   BIGINT NOT NULL AUTO_INCREMENT,
    user_name VARCHAR(100),

    PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS user_details
(
    detail_id BIGINT NOT NULL AUTO_INCREMENT,
    user_id   BIGINT NOT NULL,
    city      VARCHAR(100),
    is_active BOOLEAN,

    PRIMARY KEY (detail_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE IF NOT EXISTS user_skills
(
    skill_id    BIGINT NOT NULL AUTO_INCREMENT,
    user_id     BIGINT NOT NULL,
    skill_name  VARCHAR(100),
    has_deleted BOOLEAN,

    PRIMARY KEY (skill_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);