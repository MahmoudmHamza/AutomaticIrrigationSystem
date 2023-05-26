package com.automaticirrigation.automaticirrigation.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "plot_configurations")
public class PlotConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "water_amount", nullable = false)
    private float waterAmount;

    @OneToOne
    @JoinColumn(name = "irrigation_slot_id")
    private IrrigationSlot irrigationSlot;
}
