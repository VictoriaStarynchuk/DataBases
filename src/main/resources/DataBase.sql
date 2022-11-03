CREATE SCHEMA IF NOT EXISTS starynchuk;
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

CREATE TABLE owner (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(20) NOT NULL,
  surname VARCHAR(25) NOT NULL,
  email VARCHAR(45) NOT NULL UNIQUE);

CREATE TABLE country (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(20) NOT NULL);

CREATE TABLE city (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(45) NOT NULL,
  country_id INT NOT NULL,
  CONSTRAINT fk_city_country1 FOREIGN KEY (country_id) REFERENCES country(id));

CREATE TABLE business_land (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  address VARCHAR(45) NOT NULL,
  quantity_station INT NOT NULL,
  city_id INT NOT NULL,
  CONSTRAINT fk_business_land_city1 FOREIGN KEY (city_id) REFERENCES city(id))
ENGINE = InnoDB;

CREATE TABLE owner_businessland (
  owner_id INT NOT NULL PRIMARY KEY,
  business_land_id INT NOT NULL,
  quantity_land INT NOT NULL,
  KEY owner_businessland_business_land(business_land_id),
  CONSTRAINT fk_owner_has_business_land_owner FOREIGN KEY (owner_id) REFERENCES owner(id),
  CONSTRAINT fk_owner_has_business_land_business_land1 FOREIGN KEY (business_land_id) REFERENCES business_land(id));

CREATE TABLE energy(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  solar_amount DOUBLE NULL,
  use_now DOUBLE NULL,
  exporting DOUBLE NULL)
ENGINE = InnoDB;

CREATE TABLE panel (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  type VARCHAR(10) NOT NULL,
  power INT NOT NULL,
  duration_time INT NOT NULL,
  tilt_angel VARCHAR(8) NOT NULL,
  production_power INT NOT NULL);

CREATE TABLE battery (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  type VARCHAR(10) NOT NULL,
  capacity INT NOT NULL,
  duration_time INT NOT NULL,
  charge_level VARCHAR(5) NOT NULL,
  power INT NOT NULL);

CREATE TABLE element (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  panel_quantity INT NULL,
  battery_quantity INT NULL,
  panel_id INT NOT NULL,
  battery_id INT NOT NULL,
  KEY element_panel(panel_id),
  KEY element_battery(battery_id),
  CONSTRAINT fk_element_panel1 FOREIGN KEY (panel_id) REFERENCES panel(id),
  CONSTRAINT fk_element_battery1 FOREIGN KEY (battery_id) REFERENCES battery(id));

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
  CONSTRAINT fk_station_business_land1 FOREIGN KEY (business_land_id) REFERENCES business_land (id));

CREATE TABLE energy_market (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  price VARCHAR(45) NOT NULL,
  date DATE NOT NULL,
  time TIME NOT NULL,
  energy_id INT NOT NULL,
  CONSTRAINT fk_energy_market_energy1 FOREIGN KEY (energy_id) REFERENCES energy(id));


INSERT INTO owner ( id, name, surname, email) VALUES
(1, 'Tom', 'Ford', 'tom_ford@gmail.com'),
(2, 'Anna', 'Greys', 'anna_greys@gmail.com'),
(3, 'Fillip', 'Crage', 'fillip_crage@gmail.com'),
(4, 'Kevin', 'Nord', 'kevin_nord@gmail.com'),
(5, 'Lily', 'James', 'lily_james@gmail.com');

INSERT INTO country (id, name) VALUES
(1, 'Ukraine'),
(2, 'France'),
(3, 'Italy'),
(4, 'Norway'),
(5, 'Germany');

INSERT INTO city (id, name, country_id) VALUES
(1, 'Kyiv', 1),
(2, 'Kharkiv', 1),
(3, 'Monaco', 2),
(4, 'Genova', 3),
(5, 'Lafoten', 4);

INSERT INTO business_land (id, address, quantity_station, city_id) VALUES
(1, 'Svobody prospekt, 18', 5, 1),
(2, 'Shine street, 5', 1, 2),
(3, 'Teodors street, 1', 2, 5),
(4, 'White street, 10', 1, 4),
(5, 'Valley, 11', 4, 3);

INSERT INTO owner_businessland(owner_id, business_land_id, quantity_land) VALUES
(1, 1, 1),
(2, 3, 1),
(3, 2, 1),
(4, 5, 2),
(5, 4, 2);

INSERT INTO energy (id, solar_amount, use_now, exporting) VALUES
(1, 1500, 1000, 500),
(2, 25600, 15000, 16000),
(3, 3000, NULL, NULL),
(4, 8000, 2000, 6000),
(5, 1800, 1000, 800);

INSERT INTO panel(id, type, power, duration_time, tilt_angel, production_power) VALUES
(1, 'solar', 1500, 5, '45', 800),
(2, 'solar', 1500, 5, '120', 800),
(3, 'solar', 1000, 2, '120', 400);

INSERT INTO battery(id, type, capacity, duration_time, charge_level, power) VALUES
(1, 'solar', 200, 1, '100', 800),
(2, 'solar', 400, 2, '100', 800),
(3, 'solar', 600, 5, '200', 1600);

INSERT INTO element(id, panel_quantity, battery_quantity, panel_id, battery_id) VALUES
(1, 12, 6, 1, 2),
(2, 5, 5, 1, 2),
(3, 2, 4, 3, 1),
(4, 100, 50, 2, 1),
(5, 50, 40, 2, 3);

INSERT INTO station(id, area_sq_km, energy_id, element_id, business_land_id) VALUES
(1, 1000, 1, 1, 5),
(2, 20000, 5, 4, 2),
(3, 500, 4, 3, 1),
(4, 280, 3, 2, 3),
(5, 100, 2, 1, 4);

INSERT INTO energy_market(id, price, date, time, energy_id) VALUES
(1, '1000', '2021-05-10', '19:00', 1),
(2, '800', '2022-10-09', '21:00', 2);