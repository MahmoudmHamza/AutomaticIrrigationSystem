package com.automaticirrigation.automaticirrigation.repositories;

import com.automaticirrigation.automaticirrigation.entities.PlotConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlotConfigurationsRepository extends JpaRepository<PlotConfiguration, Long> {
}
