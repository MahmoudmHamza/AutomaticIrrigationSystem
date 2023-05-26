package com.automaticirrigation.automaticirrigation.repositories;

import com.automaticirrigation.automaticirrigation.entities.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
    Optional<Sensor> findByPlotId(Long plotId);
}
