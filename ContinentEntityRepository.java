package com.iot.repository;

import com.iot.domain.ContinentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface ContinentEntityRepository extends JpaRepository<ContinentEntity, Integer> {
    @Procedure("continent_param_insert")
    Integer insertNewValues(String name);

    @Procedure("insert_10_rows")
    void insertRows();

    @Procedure("create_tables_using_cursor")
    void createTablesWithCursor();
}
