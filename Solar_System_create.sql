-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2022-09-11 19:06:34.215

-- tables
-- Table: battery
CREATE TABLE battery (
    type varchar(10) NOT NULL,
    capacity int NOT NULL,
    duration_time int NOT NULL,
    charge_level varchar(5) NOT NULL,
    power int NOT NULL,
    CONSTRAINT battery_pk PRIMARY KEY (type)
);

-- Table: business_land
CREATE TABLE business_land (
    id int NOT NULL AUTO_INCREMENT,
    quantity_station int NOT NULL,
    address varchar(20) NOT NULL COMMENT 'e.g. street, region',
    city_name varchar(15) NOT NULL,
    CONSTRAINT business_land_pk PRIMARY KEY (id)
);

-- Table: city
CREATE TABLE city (
    name varchar(15) NOT NULL,
    country_name varchar(15) NOT NULL,
    CONSTRAINT city_pk PRIMARY KEY (name)
);

-- Table: country
CREATE TABLE country (
    name varchar(15) NOT NULL,
    CONSTRAINT country_pk PRIMARY KEY (name)
);

-- Table: element
CREATE TABLE element (
    quantity int NOT NULL COMMENT 'e.g battery, panel',
    panel_type varchar(10) NOT NULL,
    battery_type varchar(10) NOT NULL,
    panel_quantity int NULL,
    battery_quantity int NULL,
    CONSTRAINT element_pk PRIMARY KEY (quantity)
) COMMENT '
';

-- Table: energy
CREATE TABLE energy (
    solar_amount int NOT NULL,
    use_now int NOT NULL,
    exporting int NULL,
    energy_market_id int NOT NULL,
    CONSTRAINT energy_pk PRIMARY KEY (solar_amount)
);

-- Table: energy_market
CREATE TABLE energy_market (
    id int NOT NULL AUTO_INCREMENT,
    price double(10,2) NOT NULL,
    date date NOT NULL,
    time time NOT NULL,
    CONSTRAINT energy_market_pk PRIMARY KEY (id)
);

-- Table: owner
CREATE TABLE owner (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(20) NOT NULL,
    surname varchar(30) NOT NULL,
    email varchar(20) NOT NULL,
    password varchar(20) NOT NULL,
    CONSTRAINT owner_pk PRIMARY KEY (id)
);

-- Table: owner_land
CREATE TABLE owner_land (
    owner_id int NOT NULL,
    business_land_id int NOT NULL,
    quantity int NOT NULL,
    CONSTRAINT owner_land_pk PRIMARY KEY (owner_id,business_land_id)
);

-- Table: panel
CREATE TABLE panel (
    type varchar(10) NOT NULL,
    power int NOT NULL,
    duration_time int NOT NULL,
    tilt_angel varchar(5) NOT NULL,
    production_power int NOT NULL,
    CONSTRAINT panel_pk PRIMARY KEY (type)
);

-- Table: station
CREATE TABLE station (
    id int NOT NULL AUTO_INCREMENT,
    business_land_id int NOT NULL,
    element_quantity int NOT NULL,
    energy_solar_amount int NOT NULL,
    CONSTRAINT station_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: business_land_city (table: business_land)
ALTER TABLE business_land ADD CONSTRAINT business_land_city FOREIGN KEY business_land_city (city_name)
    REFERENCES city (name);

-- Reference: city_country (table: city)
ALTER TABLE city ADD CONSTRAINT city_country FOREIGN KEY city_country (country_name)
    REFERENCES country (name);

-- Reference: element_battery (table: element)
ALTER TABLE element ADD CONSTRAINT element_battery FOREIGN KEY element_battery (battery_type)
    REFERENCES battery (type);

-- Reference: element_panel (table: element)
ALTER TABLE element ADD CONSTRAINT element_panel FOREIGN KEY element_panel (panel_type)
    REFERENCES panel (type);

-- Reference: energy_energy_market (table: energy)
ALTER TABLE energy ADD CONSTRAINT energy_energy_market FOREIGN KEY energy_energy_market (energy_market_id)
    REFERENCES energy_market (id);

-- Reference: owner_land_business_land (table: owner_land)
ALTER TABLE owner_land ADD CONSTRAINT owner_land_business_land FOREIGN KEY owner_land_business_land (business_land_id)
    REFERENCES business_land (id);

-- Reference: owner_land_owner (table: owner_land)
ALTER TABLE owner_land ADD CONSTRAINT owner_land_owner FOREIGN KEY owner_land_owner (owner_id)
    REFERENCES owner (id);

-- Reference: station_business_land (table: station)
ALTER TABLE station ADD CONSTRAINT station_business_land FOREIGN KEY station_business_land (business_land_id)
    REFERENCES business_land (id);

-- Reference: station_element (table: station)
ALTER TABLE station ADD CONSTRAINT station_element FOREIGN KEY station_element (element_quantity)
    REFERENCES element (quantity);

-- Reference: station_energy (table: station)
ALTER TABLE station ADD CONSTRAINT station_energy FOREIGN KEY station_energy (energy_solar_amount)
    REFERENCES energy (solar_amount);

-- End of file.

