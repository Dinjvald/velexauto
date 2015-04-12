SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- START
-- -----------------------------------------------------

CREATE SCHEMA IF NOT EXISTS `velexauto`
  DEFAULT CHARACTER SET utf8;
USE velexauto;

-- -----------------------------------------------------
-- Table `velexauto`.`agreement`
-- -----------------------------------------------------

DROP TABLE IF EXISTS velexauto.agreement;

CREATE TABLE agreement (
  agreement_id      INTEGER(11) NOT NULL AUTO_INCREMENT,
  agreement_nr      CHAR(15),
  loading_date      CHAR(10),
  loading_address   CHAR(100),
  unloading_date    CHAR(10),
  unloading_address CHAR(100),
  price             FLOAT(6, 2),
  value_added_tax   FLOAT(6, 2),
  PRIMARY KEY (agreement_id)
);

-- -----------------------------------------------------
-- Table `velexauto`.`users`
-- -----------------------------------------------------

DROP TABLE IF EXISTS velexauto.users;

CREATE TABLE users (
  user_id  INTEGER(11) NOT NULL AUTO_INCREMENT,
  login    CHAR(255),
  password CHAR(255),
  name     CHAR(255),
  surname  CHAR(255),
  PRIMARY KEY (user_id)
);

-- -----------------------------------------------------
-- Table `velexauto`.`user_roles`
-- -----------------------------------------------------

DROP TABLE IF EXISTS velexauto.user_roles;

CREATE TABLE user_roles (
  user_role_id INTEGER(11) NOT NULL AUTO_INCREMENT,
  user_id      INTEGER(11),
  role         CHAR(45),
  PRIMARY KEY (`user_role_id`)
);

-- -----------------------------------------------------
-- Foreign Keys
-- -----------------------------------------------------

ALTER TABLE user_roles ADD FOREIGN KEY (user_id) REFERENCES users (user_id)
  ON DELETE CASCADE;

-- -----------------------------------------------------
-- Table Properties
-- -----------------------------------------------------

ALTER TABLE agreement ENGINE = InnoDB, DEFAULT CHARACTER SET = utf8;
ALTER TABLE users ENGINE = InnoDB, DEFAULT CHARACTER SET = utf8;
ALTER TABLE user_roles ENGINE = InnoDB, DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Index, Unique
-- -----------------------------------------------------

ALTER TABLE users ADD UNIQUE (password);

-- -----------------------------------------------------
-- END
-- -----------------------------------------------------

SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
