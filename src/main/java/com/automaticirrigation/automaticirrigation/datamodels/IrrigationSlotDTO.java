package com.automaticirrigation.automaticirrigation.datamodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IrrigationSlotDTO {
    private LocalTime startTime;
    private LocalTime endTime;
    private String plotStatus;
}
