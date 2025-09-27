package com.BaquetasOnline.Model.repository;

import org.hibernate.sql.results.jdbc.internal.JdbcValuesMappingProducerStandard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface productsRepository extends JpaRepository<JdbcValuesMappingProducerStandard, Long> {
}

