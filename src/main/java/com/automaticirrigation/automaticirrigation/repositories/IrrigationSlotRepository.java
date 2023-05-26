package com.automaticirrigation.automaticirrigation.repositories;

import com.automaticirrigation.automaticirrigation.entities.IrrigationSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IrrigationSlotRepository extends JpaRepository<IrrigationSlot, Long> {
}