package ognianyk.pavel.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by pavelognianyk on 10/24/16.
 */
@Entity
public class DataFromRaspberry implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String temperature;

    @Column(nullable = false)
    private String humidity;

    @Column(nullable = false)
    private boolean heatingStatus;

    @Column(nullable = false)
    private Date syncTime;
    @OneToMany(mappedBy = "dataFromRaspberry", fetch = FetchType.EAGER)
    private List<AdditionalSensorEntity> sensorEntityList;

    public Long getId() {
        return id;
    }

    public DataFromRaspberry() {
    }

    public DataFromRaspberry(String temperature) {
        this.temperature = temperature;
    }

    public String getTemperature() {
        return temperature;
    }

    public DataFromRaspberry setTemperature(String temperature) {
        this.temperature = temperature;
        return this;
    }

    public String getHumidity() {
        return humidity;
    }

    public DataFromRaspberry setHumidity(String humidity) {
        this.humidity = humidity;
        return this;
    }

    public boolean isHeatingStatus() {
        return heatingStatus;
    }

    public DataFromRaspberry setHeatingStatus(boolean heatingStatus) {
        this.heatingStatus = heatingStatus;
        return this;
    }

    public Date getSyncTime() {
        return syncTime;
    }

    public DataFromRaspberry setSyncTime(Date syncTime) {
        this.syncTime = syncTime;
        return this;
    }

    public List<AdditionalSensorEntity> getSensorEntityList() {
        return sensorEntityList;
    }

    public DataFromRaspberry setSensorEntityList(List<AdditionalSensorEntity> sensorEntityList) {
        this.sensorEntityList = sensorEntityList;
        return this;
    }
}
