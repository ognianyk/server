package ognianyk.pavel.controller.transport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pavelognianyk on 2/2/17.
 */
public class TransportJson {
    private String temperature;
    private String humidity;
    private boolean heating;
    private List<AdditionalSensor> additionalSensorList;

    public String getTemperature() {
        return temperature;
    }

    public TransportJson setTemperature(String temperature) {
        this.temperature = temperature;
        return this;
    }

    public String getHumidity() {
        return humidity;
    }

    public TransportJson setHumidity(String humidity) {
        this.humidity = humidity;
        return this;
    }

    public boolean isHeating() {
        return heating;
    }

    public TransportJson setHeating(boolean heating) {
        this.heating = heating;
        return this;
    }

    public List<AdditionalSensor> getAdditionalSensorList() {
        if(additionalSensorList==null){
            additionalSensorList = new ArrayList<>();
        }
        return additionalSensorList;
    }

    public TransportJson setAdditionalSensorList(List<AdditionalSensor> additionalSensorList) {
        this.additionalSensorList = additionalSensorList;
        return this;
    }
}
