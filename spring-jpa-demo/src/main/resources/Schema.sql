DROP TABLE IF EXISTS user;

CREATE TABLE user(
    ID INT(6) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    NAME CHAR(50) NULL,
    PASSWORD CHAR(50) NULL,
    Age INT(6) NOT NULL,
    Sex CHAR(50) NULL
)

