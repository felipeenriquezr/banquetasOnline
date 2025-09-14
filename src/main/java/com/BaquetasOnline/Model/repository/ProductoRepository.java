package com.BaquetasOnline.Model.repository;

import org.hibernate.sql.results.jdbc.internal.JdbcValuesMappingProducerStandard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<JdbcValuesMappingProducerStandard, Long> {
}
