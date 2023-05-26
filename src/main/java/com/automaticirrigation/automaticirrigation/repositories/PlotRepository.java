package com.automaticirrigation.automaticirrigation.repositories;

import com.automaticirrigation.automaticirrigation.entities.Plot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlotRepository extends JpaRepository<Plot, Long> {
}

