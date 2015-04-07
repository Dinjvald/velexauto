SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

create schema if not exists `velexauto` default character set utf8;
use `velexauto` ;

-- -----------------------------------------------------
-- Table `velexauto`.`agreement`
-- -----------------------------------------------------

drop table if exists `velexauto`.`agreement`;

create table `agreement` (
`agreement_id` integer(11) not null auto_increment,
`agreement_nr` char(15),
`loading_date` char(10),
`loading_address` char(100),
`unloading_date` char(10),
`unloading_address` char(100),
`price` float (6,2),
`value_added_tax` float (6,2),
primary key (`agreement_id`)
);

-- -----------------------------------------------------
-- Table Properties
-- -----------------------------------------------------
alter table `agreement` engine = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
