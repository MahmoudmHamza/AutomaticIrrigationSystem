package com.automaticirrigation.automaticirrigation.services.Plot;

import com.automaticirrigation.automaticirrigation.datamodels.PlotConfigurationDTO;
import com.automaticirrigation.automaticirrigation.datamodels.PlotDTO;
import com.automaticirrigation.automaticirrigation.entities.IrrigationSlot;
import com.automaticirrigation.automaticirrigation.entities.Plot;
import com.automaticirrigation.automaticirrigation.entities.PlotConfiguration;
import com.automaticirrigation.automaticirrigation.entities.Sensor;
import com.automaticirrigation.automaticirrigation.enums.IrrigationSlotStatus;
import com.automaticirrigation.automaticirrigation.repositories.IrrigationSlotRepository;
import com.automaticirrigation.automaticirrigation.repositories.PlotConfigurationsRepository;
import com.automaticirrigation.automaticirrigation.repositories.PlotRepository;
import com.automaticirrigation.automaticirrigation.repositories.SensorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
@Service
public class PlotService implements IPlotLandService {

    private final PlotRepository plotRepository;
    private final IrrigationSlotRepository irrigationSlotRepository;
    private final PlotConfigurationsRepository plotConfigurationsRepository;
    private final SensorRepository sensorRepository;

    @Override
    public Plot savePlot(PlotDTO plot) {
        Plot newPlot = Plot.builder().plotName(plot.getPlotName()).plotArea(plot.getPlotArea()).build();
        return plotRepository.save(newPlot);
    }

    @Override
    public Plot updatePlot(Long id, PlotDTO plot){
        Plot updatedPlot = this.findPlotById(id);
        updatedPlot.setPlotName(plot.getPlotName());
        updatedPlot.setPlotArea(plot.getPlotArea());
        return plotRepository.save(updatedPlot);
    }

    @Override
    public Plot configurePlot(Long id, PlotConfigurationDTO plotConfiguration){
        Plot plot = findPlotById(id);
        PlotConfiguration savedPlotConfigurations = this.savePlotConfigurations(plotConfiguration);
        plot.setPlotConfiguration(savedPlotConfigurations);
        this.assignSensorToPlot(plotConfiguration.getSensorId(), plot);
        return plotRepository.save(plot);
    }

    private void assignSensorToPlot(Long sensorId, Plot plot){
        Optional<Sensor> updatedSensor = sensorRepository.findById(sensorId);
        updatedSensor.get().setPlot(plot);
        sensorRepository.save(updatedSensor.get());
    }

    @Override
    public List<Plot> findAllPlots() {
        return plotRepository.findAll();
    }

    @Override
    public Plot findPlotById(Long plotId) {
        return plotRepository.findById(plotId).
                orElseThrow(() -> new EntityNotFoundException("Couldn't find plot with id: [" + plotId + "]"));
    }

    @Override
    public IrrigationSlot findIrrigationSlotById(Long irrigationSlotId) {
        return irrigationSlotRepository.findById(irrigationSlotId)
                .orElseThrow(() -> new EntityNotFoundException("Couldn't find irrigation slot with id: [" + irrigationSlotId + "]"));
    }

    @Override
    public IrrigationSlot updateIrrigationSlot(Long irrigationSlotId){
        IrrigationSlot irrigationSlot = this.findIrrigationSlotById(irrigationSlotId);
        irrigationSlot.setSlotStatus(IrrigationSlotStatus.IN_PROGRESS.toString());
        return irrigationSlotRepository.save(irrigationSlot);
    }

    @Override
    public PlotConfiguration savePlotConfigurations(PlotConfigurationDTO plotConfiguration) {
        Optional<IrrigationSlot> irrigationSlot = irrigationSlotRepository.findById(plotConfiguration.getIrrigationSlotId());
        PlotConfiguration newPlotConfiguration = PlotConfiguration.builder()
                .waterAmount(plotConfiguration.getWaterAmount()).irrigationSlot(irrigationSlot.get()).build();
        return plotConfigurationsRepository.save(newPlotConfiguration);
    }

    @Override
    public PlotConfiguration findPlotConfigurationById(Long configurationId) {
        return plotConfigurationsRepository.findById(configurationId)
                .orElseThrow( () -> new EntityNotFoundException("Couldn't find plot configuration with id: [" + configurationId +"]"));
    }
}
