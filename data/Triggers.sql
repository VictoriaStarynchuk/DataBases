USE `starynchuk`;

DELIMITER //
DROP TRIGGER IF EXISTS CheckIfContinentExistsBeforeCreate //
CREATE TRIGGER CheckIfContinentExistsBeforeCreat
    BEFORE INSERT
    ON `starynchuk`.`country`
    FOR EACH ROW
BEGIN
    IF
    (NOT EXISTS(
            SELECT id FROM `starynchuk`.`continent`
            WHERE id = NEW.continent_id
        ))
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Foreign key error: No continent with such id';
END IF;
END //


DROP TRIGGER IF EXISTS CheckIfContinentExistsBeforeUpdate //
CREATE TRIGGER CheckIfContinentExistsBeforeUpdate
    BEFORE UPDATE
    ON country
    FOR EACH ROW
BEGIN
    IF
    (NOT EXISTS(
            SELECT id FROM `starynchuk`.`continent`
            WHERE id = NEW.continent_id
        ))
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Foreign key error: No continent with such id';
END IF;
END //


DROP TRIGGER IF EXISTS CheckIfContinentIdUsedBeforeUpdate //
CREATE TRIGGER CheckIfContinentIdUsedBeforeUpdate
    BEFORE UPDATE
    ON `starynchuk`.`continent`
    FOR EACH ROW
BEGIN
    IF
    (EXISTS(
            SELECT continent_id FROM `starynchuk`.`country`
            WHERE country.continent_id = OLD.id
        ))
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Foreign key error: Can`t update row with record present in related table';
END IF;
END //


DROP TRIGGER IF EXISTS CheckIfContinentIdUsedBeforeDelete //
CREATE TRIGGER CheckIfContinentIdUsedBeforeDelete
    BEFORE DELETE
    ON `starynchuk`.`continent`
    FOR EACH ROW
BEGIN
    IF
    (EXISTS(
            SELECT continent_id FROM `starynchuk`.`country`
            WHERE country.continent_id = OLD.id
        ))
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Foreign key error: Can`t delete row with record present in related table';
END IF;
END //

#check min cardinality of rows as 6
DROP TRIGGER IF EXISTS ContinentCardinality //
CREATE TRIGGER ContinentCardinality
    AFTER  INSERT
    ON `starynchuk`.`continent`
    FOR EACH ROW
BEGIN
    -- DECLARE amount_rows int;
    SET @amount_rows := (SELECT COUNT(*) FROM continent);
    IF  @amount_rows>6
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'You can  insert row because of range cardinality';
    END IF;
END //;

# forbid to delete strings in  table
DROP TRIGGER IF EXISTS forbidDelete;
DELIMITER //
CREATE TRIGGER forbidDelete
    BEFORE DELETE
    ON `starynchuk`.`continent`
    FOR EACH ROW
BEGIN
    SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Deletion of the continent has been prevented`';
END //

# only certain input mode
DROP TRIGGER IF EXISTS checkInput //
CREATE TRIGGER checkInput
    BEFORE INSERT
    ON `starynchuk`.`owner`
    FOR EACH ROW
BEGIN
    IF NEW.name NOT IN ('Svitlana', 'Petro', 'Olha', 'Taras') THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Owner name is not Svitlana, Petro, Olha, Taras';
    END IF;
END //