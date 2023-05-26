package com.automaticirrigation.automaticirrigation.services.Sensor;


import com.automaticirrigation.automaticirrigation.entities.Sensor;
import com.automaticirrigation.automaticirrigation.exceptions.ExceededMaxRetriesException;

public interface ISensorService {
    boolean irrigatePlot(Sensor sensor);
    boolean sendIrrigateRequest(Long plotId) throws ExceededMaxRetriesException;
}
