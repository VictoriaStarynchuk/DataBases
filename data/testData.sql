USE starynchuk;

INSERT INTO owner ( id, name, surname, email) VALUES
                                                  (1, 'Tom', 'Ford', 'tom_ford@gmail.com'),
                                                  (2, 'Anna', 'Greys', 'anna_greys@gmail.com'),
                                                  (3, 'Fillip', 'Crage', 'fillip_crage@gmail.com'),
                                                  (4, 'Kevin', 'Nord', 'kevin_nord@gmail.com'),
                                                  (5, 'Lily', 'James', 'lily_james@gmail.com');

INSERT INTO `starynchuk`.`country` (`id`, `name`, `continent_id`) VALUES (1, 'Ukraine', 2);
INSERT INTO `starynchuk`.`country` (`id`, `name`, `continent_id`) VALUES (2, 'France', 2);
INSERT INTO `starynchuk`.`country` (`id`, `name`, `continent_id`) VALUES (3, 'Italy', 2);
INSERT INTO `starynchuk`.`country` (`id`, `name`, `continent_id`) VALUES (4, 'Norway', 2);
INSERT INTO `starynchuk`.`country` (`id`, `name`, `continent_id`) VALUES (5, 'Germany', 2);


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

# INSERT INTO panel(id, type, power, duration_time, tilt_angel, production_power) VALUES
#                                                                                     (1, 'solar', 1500, 5, '45', 800),
#                                                                                     (2, 'solar', 1500, 5, '120', 800),
#                                                                                     (3, 'solar', 1000, 2, '120', 400);

INSERT INTO `starynchuk`.`panel` (`id`, `type`, `power`, `duration_time`, `tilt_angel`, `production_power`) VALUES (1, 'solar', 1500, 5, '45', 800);
INSERT INTO `starynchuk`.`panel` (`id`, `type`, `power`, `duration_time`, `tilt_angel`, `production_power`) VALUES (2, 'solar', 1500, 5, '120', 800);
INSERT INTO `starynchuk`.`panel` (`id`, `type`, `power`, `duration_time`, `tilt_angel`, `production_power`) VALUES (3, 'solar', 1000, 2, '120', 400);

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

INSERT INTO `starynchuk`.`continent` (`id`, `name`) VALUES (1, 'Asia'),
                                                           (2, 'Europe'),
                                                           (3, 'America'),
                                                           (4, 'Australia'),
                                                           (5, 'Africa'),
                                                           (6, 'Antarctica');
