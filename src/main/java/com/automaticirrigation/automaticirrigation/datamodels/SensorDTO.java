package com.automaticirrigation.automaticirrigation.datamodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SensorDTO {
    private Long plotId;
    private String sensorStatus;
}
