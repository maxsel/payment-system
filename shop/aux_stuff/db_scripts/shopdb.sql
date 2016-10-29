-- MySQL Script generated by MySQL Workbench
-- Суб 29 Кст 2016 11:31:09
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema shopdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `shopdb` ;

-- -----------------------------------------------------
-- Schema shopdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `shopdb` DEFAULT CHARACTER SET utf8 ;
SHOW WARNINGS;
USE `shopdb` ;

-- -----------------------------------------------------
-- Table `shopdb`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shopdb`.`user` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `shopdb`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user_login` VARCHAR(45) NOT NULL,
  `user_password` VARCHAR(45) NOT NULL,
  `card_id` VARCHAR(16) NULL,
  `card_cvv` VARCHAR(3) NULL,
  `discount` INT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `shopdb`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shopdb`.`role` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `shopdb`.`role` (
  `role_id` INT NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `shopdb`.`item_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shopdb`.`item_category` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `shopdb`.`item_category` (
  `cat_id` INT NOT NULL AUTO_INCREMENT,
  `cat_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`cat_id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `shopdb`.`item_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shopdb`.`item_status` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `shopdb`.`item_status` (
  `status_id` INT NOT NULL AUTO_INCREMENT,
  `status_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`status_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `shopdb`.`item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shopdb`.`item` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `shopdb`.`item` (
  `item_id` INT NOT NULL AUTO_INCREMENT,
  `item_title` VARCHAR(45) NOT NULL,
  `item_desc` VARCHAR(1000) NULL,
  `item_price` INT NOT NULL,
  `item_img` BLOB NULL,
  `item_cat_id` INT NOT NULL,
  `item_status_id` INT NOT NULL,
  PRIMARY KEY (`item_id`),
  CONSTRAINT `fk_item_item_category1`
    FOREIGN KEY (`item_cat_id`)
    REFERENCES `shopdb`.`item_category` (`cat_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_item_state1`
    FOREIGN KEY (`item_status_id`)
    REFERENCES `shopdb`.`item_status` (`status_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;
CREATE INDEX `fk_item_cat_id_idx` ON `shopdb`.`item` (`item_cat_id` ASC);

SHOW WARNINGS;
CREATE INDEX `fk_item_state_id_idx` ON `shopdb`.`item` (`item_status_id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `shopdb`.`order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shopdb`.`order` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `shopdb`.`order` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `status_id` VARCHAR(45) NOT NULL,
  `user_id` INT NOT NULL,
  `unique_code` VARCHAR(45) NOT NULL,
  `instant_discount` INT NULL,
  PRIMARY KEY (`order_id`),
  CONSTRAINT `fk_order_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `shopdb`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;
CREATE INDEX `fk_order_user1_idx` ON `shopdb`.`order` (`user_id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `shopdb`.`order_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shopdb`.`order_status` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `shopdb`.`order_status` (
  `status_id` INT NOT NULL AUTO_INCREMENT,
  `status_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`status_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `shopdb`.`order_history`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shopdb`.`order_history` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `shopdb`.`order_history` (
  `order_id` INT NOT NULL,
  `status_id` INT NOT NULL,
  `change_date` DATETIME NULL,
  PRIMARY KEY (`order_id`, `status_id`),
  CONSTRAINT `fk_order_status_has_order_order_status1`
    FOREIGN KEY (`status_id`)
    REFERENCES `shopdb`.`order_status` (`status_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_status_has_order_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `shopdb`.`order` (`order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;
CREATE INDEX `fk_order_status_has_order_order1_idx` ON `shopdb`.`order_history` (`order_id` ASC);

SHOW WARNINGS;
CREATE INDEX `fk_order_status_has_order_order_status1_idx` ON `shopdb`.`order_history` (`status_id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `shopdb`.`order_has_item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shopdb`.`order_has_item` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `shopdb`.`order_has_item` (
  `order_id` INT NOT NULL,
  `item_id` INT NOT NULL,
  `amount` INT NULL,
  `instant_price` INT NULL,
  PRIMARY KEY (`order_id`, `item_id`),
  CONSTRAINT `fk_order_has_item_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `shopdb`.`order` (`order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_has_item_item1`
    FOREIGN KEY (`item_id`)
    REFERENCES `shopdb`.`item` (`item_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;
CREATE INDEX `fk_order_has_item_item1_idx` ON `shopdb`.`order_has_item` (`item_id` ASC);

SHOW WARNINGS;
CREATE INDEX `fk_order_has_item_order1_idx` ON `shopdb`.`order_has_item` (`order_id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `shopdb`.`user_has_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shopdb`.`user_has_role` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `shopdb`.`user_has_role` (
  `user_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  CONSTRAINT `fk_user_has_role_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `shopdb`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_role_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `shopdb`.`role` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;
CREATE INDEX `fk_user_has_role_role1_idx` ON `shopdb`.`user_has_role` (`role_id` ASC);

SHOW WARNINGS;
CREATE INDEX `fk_user_has_role_user1_idx` ON `shopdb`.`user_has_role` (`user_id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `shopdb`.`item-in-cart`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shopdb`.`item-in-cart` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `shopdb`.`item-in-cart` (
  `item_id` INT NOT NULL,
  `item_in_cart_id` INT NOT NULL AUTO_INCREMENT,
  `amount` INT NOT NULL,
  PRIMARY KEY (`item_in_cart_id`),
  CONSTRAINT `fk_item-in-cart_item1`
    FOREIGN KEY (`item_id`)
    REFERENCES `shopdb`.`item` (`item_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;
CREATE INDEX `fk_item-in-cart_item1_idx` ON `shopdb`.`item-in-cart` (`item_id` ASC);

SHOW WARNINGS;
CREATE UNIQUE INDEX `item_in_cart_id_UNIQUE` ON `shopdb`.`item-in-cart` (`item_in_cart_id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `shopdb`.`user_has_item-in-cart`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shopdb`.`user_has_item-in-cart` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `shopdb`.`user_has_item-in-cart` (
  `user_user_id` INT NOT NULL,
  `item-in-cart_item_in_cart_id` INT NOT NULL,
  PRIMARY KEY (`user_user_id`, `item-in-cart_item_in_cart_id`),
  CONSTRAINT `fk_user_has_item-in-cart_user1`
    FOREIGN KEY (`user_user_id`)
    REFERENCES `shopdb`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_item-in-cart_item-in-cart1`
    FOREIGN KEY (`item-in-cart_item_in_cart_id`)
    REFERENCES `shopdb`.`item-in-cart` (`item_in_cart_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;
CREATE INDEX `fk_user_has_item-in-cart_item-in-cart1_idx` ON `shopdb`.`user_has_item-in-cart` (`item-in-cart_item_in_cart_id` ASC);

SHOW WARNINGS;
CREATE INDEX `fk_user_has_item-in-cart_user1_idx` ON `shopdb`.`user_has_item-in-cart` (`user_user_id` ASC);

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
