USE starynchuk;

# parameterized insert of new values into table
DROP PROCEDURE IF EXISTS insertNewValues;
DELIMITER //
CREATE PROCEDURE insertNewValues(
    IN new_continent_name VARCHAR(20),
    OUT new_id INT
)
BEGIN
INSERT INTO starynchuk.continent (name) VALUE (new_continent_name);
SELECT id INTO new_id FROM starynchuk.continent WHERE name = new_continent_name;
END //
DELIMITER ;


# function - count avg value of power in panel table
DROP FUNCTION IF EXISTS getAvgPower;
DELIMITER //
CREATE FUNCTION getAvgPower()
    RETURNS DECIMAL DETERMINISTIC
BEGIN
    RETURN (SELECT AVG(power) FROM `panel` AS avrg_power);
END //
DELIMITER ;

# invoke func by procedure
DROP PROCEDURE IF EXISTS countAvgPower;
DELIMITER //
CREATE PROCEDURE countAvgPower()
BEGIN
    SELECT getAvgPower();
END //


# implement many to many relationship between two tables
DROP PROCEDURE IF EXISTS manyToManyRel //
CREATE PROCEDURE manyToManyRel(
    IN panel_type VARCHAR(50),
    IN battery_type VARCHAR(50))
BEGIN
    DECLARE panel_id, battery_id INT;
    SELECT id INTO panel_id FROM `panel` WHERE type = panel_type;
    SELECT id INTO battery_id FROM `battery` WHERE type = battery_type;
    IF (panel_id IS NULL)
    THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'No panel with such type';
    END IF;
    IF (battery_id IS NULL)
    THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'No battery with such type';
    END IF;
    INSERT INTO `element` (panel_id, battery_id) VALUES (panel_id, battery_id);
END //


# insert 10 string into table
DROP PROCEDURE IF EXISTS insertRows;
DELIMITER //
CREATE PROCEDURE insertRows()
BEGIN
    DECLARE number_of_inserts INT DEFAULT 1;
    WHILE number_of_inserts < 11
        DO
            INSERT INTO continent(name) VALUE (
                CONCAT('Europe', number_of_inserts)
                );
            SET number_of_inserts = number_of_inserts + 1;
        END WHILE;
END //

# create table with cursor
DROP PROCEDURE IF EXISTS CreateTablesWithCursor //
CREATE PROCEDURE CreateTablesWithCursor()
BEGIN
    DECLARE done BOOL DEFAULT false;
    DECLARE name VARCHAR(30);
    DECLARE my_cursor CURSOR FOR SELECT name FROM `continent`;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = true;
    OPEN my_cursor;
    loop1: LOOP
        FETCH my_cursor INTO name;
        IF (done = true) THEN LEAVE loop1;
        END IF;
        SET @temp_query = CONCAT('CREATE TABLE `', @table_name, '` (
                          id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(20) NOT NULL
);');
        PREPARE my_query FROM @temp_query;
        EXECUTE my_query;
        DEALLOCATE PREPARE my_query;
    END LOOP;
    CLOSE my_cursor;
END //
DELIMITER ;
