package com.automaticirrigation.automaticirrigation.datamodels;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlotConfigurationDTO {
    private float waterAmount;
    private Long irrigationSlotId;
    private Long sensorId;
}
