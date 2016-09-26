-- MySQL Script generated by MySQL Workbench
-- Пан 26 Врс 2016 10:56:45
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema bankdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `bankdb` ;

-- -----------------------------------------------------
-- Schema bankdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bankdb` DEFAULT CHARACTER SET utf8 ;
SHOW WARNINGS;
USE `bankdb` ;

-- -----------------------------------------------------
-- Table `bankdb`.`currency`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bankdb`.`currency` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `bankdb`.`currency` (
  `curr_id` INT NOT NULL AUTO_INCREMENT,
  `curr_code` VARCHAR(10) NOT NULL,
  `curr_name` VARCHAR(50) NOT NULL,
  `curr_rate` DOUBLE NOT NULL,
  PRIMARY KEY (`curr_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;
CREATE UNIQUE INDEX `curr_id_unique` ON `bankdb`.`currency` (`curr_id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `bankdb`.`client`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bankdb`.`client` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `bankdb`.`client` (
  `client_id` VARCHAR(16) NOT NULL,
  `client_name` VARCHAR(100) NOT NULL,
  `client_amount` DOUBLE NOT NULL,
  `client_curr_id` INT NOT NULL,
  `client_cvv` VARCHAR(3) NULL,
  PRIMARY KEY (`client_id`),
  CONSTRAINT `fk_client_currency`
    FOREIGN KEY (`client_curr_id`)
    REFERENCES `bankdb`.`currency` (`curr_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;
CREATE UNIQUE INDEX `client_id_unique` ON `bankdb`.`client` (`client_id` ASC);

SHOW WARNINGS;
CREATE INDEX `fk_client_curr_id_idx` ON `bankdb`.`client` (`client_curr_id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `bankdb`.`transaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bankdb`.`transaction` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `bankdb`.`transaction` (
  `trans_id` INT NOT NULL AUTO_INCREMENT,
  `trans_amount` DOUBLE NOT NULL,
  `trans_date` DATETIME NOT NULL,
  `trans_client_from` VARCHAR(16) NOT NULL,
  `trans_client_to` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`trans_id`),
  CONSTRAINT `fk_trans_client_from`
    FOREIGN KEY (`trans_client_from`)
    REFERENCES `bankdb`.`client` (`client_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_trans_client_to`
    FOREIGN KEY (`trans_client_to`)
    REFERENCES `bankdb`.`client` (`client_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;
CREATE INDEX `fk_trans_client_from_idx` ON `bankdb`.`transaction` (`trans_client_from` ASC);

SHOW WARNINGS;
CREATE INDEX `fk_trans_client_to_idx` ON `bankdb`.`transaction` (`trans_client_to` ASC);

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
