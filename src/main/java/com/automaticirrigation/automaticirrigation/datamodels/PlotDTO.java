package com.automaticirrigation.automaticirrigation.datamodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlotDTO {
    private String plotName;
    private float plotArea;
}
