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
@Table(name = "plots")
public class Plot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "plot_name", nullable = false)
    private String plotName;

    @Column(name = "plot_area", nullable = false)
    private float plotArea;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "plot_configuration_id")
    private PlotConfiguration plotConfiguration;
}