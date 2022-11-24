CREATE DATABASE IF NOT EXISTS starynchuk;
USE starynchuk;
DROP TABLE IF EXISTS energy_market;
DROP TABLE IF EXISTS owner_businessland;
DROP TABLE IF EXISTS station;
DROP TABLE IF EXISTS element;
DROP TABLE IF EXISTS battery;
DROP TABLE IF EXISTS energy;
DROP TABLE IF EXISTS owner;
DROP TABLE IF EXISTS business_land;
DROP TABLE IF EXISTS panel;
DROP TABLE IF EXISTS city;
DROP TABLE IF EXISTS country;

DROP TABLE IF EXISTS `starynchuk`.`continent` ;

CREATE TABLE IF NOT EXISTS `starynchuk`.`continent` (
                                                        `id` INT NOT NULL AUTO_INCREMENT,
                                                        `name` VARCHAR(45) NOT NULL,
                                                        PRIMARY KEY (`id`));

CREATE TABLE owner (
                       id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(20) NOT NULL,
                       surname VARCHAR(25) NOT NULL,
                       email VARCHAR(45) NOT NULL UNIQUE)
;

CREATE TABLE country (
                         `id` INT NOT NULL AUTO_INCREMENT,
                         `name` VARCHAR(20) NOT NULL,
                         `continent_id` INT NOT NULL,
                         PRIMARY KEY (`id`),
                         INDEX `fk_country_continent1_idx` (`continent_id` ASC) VISIBLE,
                         CONSTRAINT `fk_country_continent1`
                             FOREIGN KEY (`continent_id`)
                                 REFERENCES `starynchuk`.`continent` (`id`)
                                 ON DELETE NO ACTION
                                 ON UPDATE NO ACTION)
;

CREATE TABLE city (
                      id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(45) NOT NULL,
                      country_id INT NOT NULL,
                      CONSTRAINT fk_city_country1 FOREIGN KEY (country_id) REFERENCES country(id))
;

CREATE TABLE business_land (
                               id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                               address VARCHAR(45) NOT NULL,
                               quantity_station INT NOT NULL,
                               city_id INT NOT NULL,
                               CONSTRAINT fk_business_land_city1 FOREIGN KEY (city_id) REFERENCES city(id))
;

CREATE TABLE owner_businessland (
                                    owner_id INT NOT NULL PRIMARY KEY,
                                    business_land_id INT NOT NULL,
                                    quantity_land INT NOT NULL,
                                    KEY owner_businessland_business_land(business_land_id),
                                    CONSTRAINT fk_owner_has_business_land_owner FOREIGN KEY (owner_id) REFERENCES owner(id),
                                    CONSTRAINT fk_owner_has_business_land_business_land1 FOREIGN KEY (business_land_id) REFERENCES business_land(id))
;

CREATE TABLE energy(
                       id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       solar_amount DOUBLE NULL,
                       use_now DOUBLE NULL,
                       exporting DOUBLE NULL)
;

CREATE TABLE panel (
                       id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       type VARCHAR(10) NOT NULL,
                       power INT NOT NULL,
                       duration_time INT NOT NULL,
                       tilt_angel VARCHAR(8) NOT NULL,
                       production_power INT NOT NULL)
;

CREATE TABLE battery (
                         id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         type VARCHAR(10) NOT NULL,
                         capacity INT NOT NULL,
                         duration_time INT NOT NULL,
                         charge_level VARCHAR(5) NOT NULL,
                         power INT NOT NULL)
;

CREATE TABLE element (
                         id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
                         panel_quantity INT NULL,
                         battery_quantity INT NULL,
                         panel_id INT NOT NULL,
                         battery_id INT NOT NULL,
                         KEY element_panel(panel_id),
                         KEY element_battery(battery_id),
                         CONSTRAINT fk_element_panel1 FOREIGN KEY (panel_id) REFERENCES panel(id),
                         CONSTRAINT fk_element_battery1 FOREIGN KEY (battery_id) REFERENCES battery(id))
;

CREATE TABLE station(
                        id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        area_sq_km FLOAT NOT NULL,
                        energy_id INT NOT NULL,
                        element_id INT NOT NULL,
                        business_land_id INT NOT NULL,
                        KEY station_energy(energy_id),
                        KEY station_element(element_id),
                        KEY station_business_land(business_land_id),
                        CONSTRAINT fk_station_energy1 FOREIGN KEY (energy_id) REFERENCES energy(id),
                        CONSTRAINT fk_station_element1 FOREIGN KEY (element_id) REFERENCES element(id),
                        CONSTRAINT fk_station_business_land1 FOREIGN KEY (business_land_id) REFERENCES business_land (id))
;

CREATE TABLE energy_market (
                               id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                               price VARCHAR(45) NOT NULL,
                               date DATE NOT NULL,
                               time TIME NOT NULL,
                               energy_id INT NOT NULL,
                               CONSTRAINT fk_energy_market_energy1 FOREIGN KEY (energy_id) REFERENCES energy(id))
;

