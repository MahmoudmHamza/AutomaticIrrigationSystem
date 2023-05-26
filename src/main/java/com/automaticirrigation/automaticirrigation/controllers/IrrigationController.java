package com.automaticirrigation.automaticirrigation.controllers;

import com.automaticirrigation.automaticirrigation.datamodels.PlotConfigurationDTO;
import com.automaticirrigation.automaticirrigation.datamodels.PlotDTO;
import com.automaticirrigation.automaticirrigation.entities.IrrigationSlot;
import com.automaticirrigation.automaticirrigation.entities.Plot;
import com.automaticirrigation.automaticirrigation.services.Plot.PlotService;
import com.automaticirrigation.automaticirrigation.services.Sensor.SensorService;
import lombok.extern.log4j.Log4j2;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/plots")
public class IrrigationController {

    private final PlotService plotService;
    private final SensorService sensorService;

    @PostMapping
    public ResponseEntity<Plot> addPlot(@RequestBody PlotDTO plot) {
        Plot createdPlot = plotService.savePlot(plot);
        return ResponseEntity.ok(createdPlot);
    }

    @GetMapping
    public ResponseEntity<List<Plot>> getAllPlots() {
        List<Plot> plots = plotService.findAllPlots();
        return ResponseEntity.ok(plots);
    }

    @GetMapping("/{plotId}")
    public ResponseEntity<Plot> getPlot(@PathVariable Long plotId) {
        Plot plot = plotService.findPlotById(plotId);
        return ResponseEntity.ok(plot);
    }

    @PostMapping("/{plotId}/configure")
    public ResponseEntity<Plot> configurePlot(@PathVariable Long plotId, @RequestBody PlotConfigurationDTO newConfiguration) {
        Plot updatedPlot = this.plotService.configurePlot(plotId, newConfiguration);
        return ResponseEntity.ok(updatedPlot);
    }

    @PutMapping("/{plotId}")
    public ResponseEntity<Plot> editPlot(@PathVariable Long plotId, @RequestBody PlotDTO plot) {
        Plot updatedPlot = this.plotService.updatePlot(plotId, plot);
        return ResponseEntity.ok(updatedPlot);
    }

    @PostMapping("/{plotId}/irrigate")
    public ResponseEntity<IrrigationSlot> irrigatePlot(@PathVariable Long plotId) throws Exception{
        boolean irrigationRequestSuccess = this.sensorService.sendIrrigateRequest(plotId);
        if (irrigationRequestSuccess) {
            IrrigationSlot updatedIrrigationSlot = this.plotService.updateIrrigationSlot(plotId);
            return ResponseEntity.ok(updatedIrrigationSlot);
        } else {
            throw new Exception("Sensor dedicated for the plot with id: " + plotId + " is not active at the moment");
        }
    }
}
