package com.automaticirrigation.automaticirrigation.services.Plot;

import com.automaticirrigation.automaticirrigation.datamodels.PlotConfigurationDTO;
import com.automaticirrigation.automaticirrigation.datamodels.PlotDTO;
import com.automaticirrigation.automaticirrigation.entities.IrrigationSlot;
import com.automaticirrigation.automaticirrigation.entities.Plot;
import com.automaticirrigation.automaticirrigation.entities.PlotConfiguration;

import java.util.List;

public interface IPlotLandService {
    Plot savePlot(PlotDTO plot);
    List<Plot> findAllPlots();
    Plot findPlotById(Long plotId);
    Plot updatePlot(Long id, PlotDTO plot);
    Plot configurePlot(Long id, PlotConfigurationDTO plotConfiguration);
    IrrigationSlot findIrrigationSlotById(Long irrigationSlotId);
    IrrigationSlot updateIrrigationSlot(Long irrigationSlotId);
    PlotConfiguration savePlotConfigurations(PlotConfigurationDTO plotConfiguration);
    PlotConfiguration findPlotConfigurationById(Long configurationId);

}
