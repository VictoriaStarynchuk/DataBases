-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema starynchuk
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema starynchuk
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `starynchuk` ;
USE `starynchuk` ;

-- -----------------------------------------------------
-- Table `starynchuk`.`owner`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `starynchuk`.`owner` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  `surname` VARCHAR(25) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `starynchuk`.`country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `starynchuk`.`country` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `starynchuk`.`city`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `starynchuk`.`city` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `country_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_city_country1_idx` (`country_id` ASC) VISIBLE,
  CONSTRAINT `fk_city_country1`
    FOREIGN KEY (`country_id`)
    REFERENCES `starynchuk`.`country` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `starynchuk`.`business_land`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `starynchuk`.`business_land` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(45) NOT NULL,
  `quantity_station` INT NOT NULL,
  `city_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_business_land_city1_idx` (`city_id` ASC) VISIBLE,
  CONSTRAINT `fk_business_land_city1`
    FOREIGN KEY (`city_id`)
    REFERENCES `starynchuk`.`city` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `starynchuk`.`owner_businessland`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `starynchuk`.`owner_businessland` (
  `owner_id` INT NOT NULL,
  `business_land_id` INT NOT NULL,
  `quantity_land` INT NOT NULL,
  PRIMARY KEY (`owner_id`, `business_land_id`),
  INDEX `fk_owner_has_business_land_business_land1_idx` (`business_land_id` ASC) VISIBLE,
  INDEX `fk_owner_has_business_land_owner_idx` (`owner_id` ASC) VISIBLE,
  CONSTRAINT `fk_owner_has_business_land_owner`
    FOREIGN KEY (`owner_id`)
    REFERENCES `starynchuk`.`owner` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_owner_has_business_land_business_land1`
    FOREIGN KEY (`business_land_id`)
    REFERENCES `starynchuk`.`business_land` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `starynchuk`.`energy`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `starynchuk`.`energy` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `solar_amount` DOUBLE NULL,
  `use_now` DOUBLE NULL,
  `exporting` DOUBLE NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `starynchuk`.`panel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `starynchuk`.`panel` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(10) NOT NULL,
  `power` INT NOT NULL,
  `duration_time` INT NOT NULL,
  `tilt_angel` VARCHAR(8) NOT NULL,
  `production_power` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `starynchuk`.`battery`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `starynchuk`.`battery` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(10) NOT NULL,
  `capacity` INT NOT NULL,
  `duration_time` INT NOT NULL,
  `charge_level` VARCHAR(5) NOT NULL,
  `power` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `starynchuk`.`element`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `starynchuk`.`element` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `panel_quantity` INT NULL,
  `battery_quantity` INT NULL,
  `panel_id` INT NOT NULL,
  `battery_id` INT NOT NULL,
  PRIMARY KEY (`id`, `panel_id`, `battery_id`),
  INDEX `fk_element_panel1_idx` (`panel_id` ASC) VISIBLE,
  INDEX `fk_element_battery1_idx` (`battery_id` ASC) VISIBLE,
  CONSTRAINT `fk_element_panel1`
    FOREIGN KEY (`panel_id`)
    REFERENCES `starynchuk`.`panel` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_element_battery1`
    FOREIGN KEY (`battery_id`)
    REFERENCES `starynchuk`.`battery` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `starynchuk`.`station`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `starynchuk`.`station` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `area_sq_km` FLOAT NOT NULL,
  `energy_id` INT NOT NULL,
  `element_id` INT NOT NULL,
  `business_land_id` INT NOT NULL,
  PRIMARY KEY (`id`, `energy_id`, `element_id`, `business_land_id`),
  INDEX `fk_station_energy1_idx` (`energy_id` ASC) VISIBLE,
  INDEX `fk_station_element1_idx` (`element_id` ASC) VISIBLE,
  INDEX `fk_station_business_land1_idx` (`business_land_id` ASC) VISIBLE,
  CONSTRAINT `fk_station_energy1`
    FOREIGN KEY (`energy_id`)
    REFERENCES `starynchuk`.`energy` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_station_element1`
    FOREIGN KEY (`element_id`)
    REFERENCES `starynchuk`.`element` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_station_business_land1`
    FOREIGN KEY (`business_land_id`)
    REFERENCES `starynchuk`.`business_land` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `starynchuk`.`energy_market`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `starynchuk`.`energy_market` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `price` VARCHAR(45) NOT NULL,
  `date` DATE NOT NULL,
  `time` TIME NOT NULL,
  `energy_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_energy_market_energy1_idx` (`energy_id` ASC) VISIBLE,
  CONSTRAINT `fk_energy_market_energy1`
    FOREIGN KEY (`energy_id`)
    REFERENCES `starynchuk`.`energy` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
