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
-- Table `velexauto`.`companies`
-- -----------------------------------------------------

DROP TABLE IF EXISTS velexauto.companies;

CREATE TABLE companies (
  company_id         INTEGER(11) NOT NULL AUTO_INCREMENT,
  name               CHAR(255)   NOT NULL,
  registration_nr    CHAR(50)    NOT NULL,
  vat_nr             CHAR(50)    NOT NULL,
  establishment_date DATE        NOT NULL,
  registration_date  DATE,
  address            CHAR(255)   NOT NULL,
  legal_address      CHAR(255)   NOT NULL,
  telephone_nr       CHAR(30),
  e_mail             CHAR(30),
  web                CHAR(200),
  PRIMARY KEY (company_id)
);

-- -----------------------------------------------------
-- Table `velexauto`.`employees`
-- -----------------------------------------------------

DROP TABLE IF EXISTS velexauto.employees;

CREATE TABLE employees (
  employee_id   INTEGER(11) NOT NULL AUTO_INCREMENT,
  company_id    INTEGER(11),
  name          CHAR(50)    NOT NULL,
  surname       CHAR(50)    NOT NULL,
  passport_nr   CHAR(100)   NOT NULL,
  personal_code CHAR(50),
  e_mail        CHAR(100),
  telephone_nr  CHAR(50),
  is_active     CHAR(1)     NOT NULL,
  PRIMARY KEY (employee_id)
);

-- -----------------------------------------------------
-- Table `velexauto`.`agreement`
-- -----------------------------------------------------

DROP TABLE IF EXISTS velexauto.agreement;

CREATE TABLE agreement (
  agreement_id              INTEGER(11) NOT NULL AUTO_INCREMENT,
  employee_id               INTEGER(11),
  company_id                INTEGER(11),
  agreement_nr              CHAR(200)            DEFAULT 'empty',
  invoice_nr                CHAR(200)            DEFAULT 'empty',
  client_name               CHAR(200)            DEFAULT 'empty',
  loading_date              DATE                 DEFAULT '1971.01.01',
  loading_address           CHAR(200)            DEFAULT 'empty',
  unloading_date            DATE                 DEFAULT '1971.01.01',
  unloading_address         CHAR(200)            DEFAULT 'empty',
  driver                    CHAR(200)            DEFAULT 'empty',
  plate_nr                  CHAR(200)            DEFAULT 'empty',
  price                     FLOAT(11, 2)         DEFAULT '-1',
  value_added_tax           FLOAT(11, 2)         DEFAULT '-1',
  payment_term              INTEGER(11)          DEFAULT '-1',
  invoice_send_date         DATE                 DEFAULT '1971.01.01',
  estimated_date_of_payment DATE                 DEFAULT '1971.01.01',
  on_behalf_of              CHAR(200)            DEFAULT 'empty',
  file_link_agreement       TEXT,
  file_link_invoice         TEXT,
  notes                     TEXT,
  is_paid                   CHAR(1)              DEFAULT 'T',
  PRIMARY KEY (agreement_id)
);

-- -----------------------------------------------------
-- Table `velexauto`.`users`
-- -----------------------------------------------------

DROP TABLE IF EXISTS velexauto.users;

CREATE TABLE users (
  user_id     INTEGER(11) NOT NULL AUTO_INCREMENT,
  employee_id INTEGER(11),
  login       CHAR(255),
  password    CHAR(255),
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

ALTER TABLE employees ADD FOREIGN KEY (company_id) REFERENCES companies (company_id);
ALTER TABLE agreement ADD FOREIGN KEY (employee_id) REFERENCES employees (employee_id);
ALTER TABLE agreement ADD FOREIGN KEY (company_id) REFERENCES companies (company_id);
ALTER TABLE users ADD FOREIGN KEY (employee_id) REFERENCES employees (employee_id);
ALTER TABLE user_roles ADD FOREIGN KEY (user_id) REFERENCES users (user_id)
  ON DELETE CASCADE;

-- -----------------------------------------------------
-- Table Properties
-- -----------------------------------------------------

ALTER TABLE companies ENGINE = InnoDB, DEFAULT CHARACTER SET = utf8;
ALTER TABLE employees ENGINE = InnoDB, DEFAULT CHARACTER SET = utf8;
ALTER TABLE agreement ENGINE = InnoDB, DEFAULT CHARACTER SET = utf8;
ALTER TABLE users ENGINE = InnoDB, DEFAULT CHARACTER SET = utf8;
ALTER TABLE user_roles ENGINE = InnoDB, DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Index, Unique
-- -----------------------------------------------------

ALTER TABLE users ADD UNIQUE (login);
ALTER TABLE users ADD UNIQUE (password);

-- -----------------------------------------------------
-- END
-- -----------------------------------------------------

SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;

