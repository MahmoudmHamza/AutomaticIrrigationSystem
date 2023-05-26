package com.automaticirrigation.automaticirrigation.services.Sensor;

import com.automaticirrigation.automaticirrigation.entities.Sensor;
import com.automaticirrigation.automaticirrigation.enums.SensorStatus;
import com.automaticirrigation.automaticirrigation.exceptions.ExceededMaxRetriesException;
import com.automaticirrigation.automaticirrigation.repositories.SensorRepository;
import com.automaticirrigation.automaticirrigation.utilities.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class SensorService implements ISensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    /**
     * check if the sensor dedicated for irrigating this plot of land is active
     *
     * @param sensor
     * @return
     */
    @Override
    public boolean irrigatePlot(Sensor sensor) {
        return sensor.getSensorStatus().equals(SensorStatus.ACTIVE.toString());
    }

    @Override
    public boolean sendIrrigateRequest(Long plotId) throws ExceededMaxRetriesException {
        int retryCount = 0;
        boolean irrigationRequestSuccess = false;

        Sensor sensor = sensorRepository.findByPlotId(plotId)
                .orElseThrow(() -> new EntityNotFoundException("Couldn't find plot with id: " + plotId + " or it's not assigned to a sensor device"));

        while (!irrigationRequestSuccess) {
            if (retryCount < Constants.MAX_RETRY_ATTEMPTS) {
                irrigationRequestSuccess = this.irrigatePlot(sensor);
                retryCount++;
            } else {
                throw new ExceededMaxRetriesException("ALERT! the sensor not available and it exceeded maximum retry times");
            }
        }
        return true;
    }
}
