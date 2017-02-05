package ognianyk.pavel.entity;

import javax.persistence.*;

/**
 * Created by pavelognianyk on 2/2/17.
 */
@Entity
@Table(name = "additional_sensor")
public class AdditionalSensorEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "data_id")
    private DataFromRaspberry dataFromRaspberry;

    @Column(nullable = false)
    private String temperature;

    @Column(nullable = false)
    private String humidity;

    public Long getId() {
        return id;
    }

    public AdditionalSensorEntity setId(Long id) {
        this.id = id;
        return this;
    }


    public AdditionalSensorEntity setDataFromRaspberry(DataFromRaspberry dataFromRaspberry) {
        this.dataFromRaspberry = dataFromRaspberry;
        return this;
    }

    public String getTemperature() {
        return temperature;
    }

    public AdditionalSensorEntity setTemperature(String temperature) {
        this.temperature = temperature;
        return this;
    }

    public String getHumidity() {
        return humidity;
    }

    public AdditionalSensorEntity setHumidity(String humidity) {
        this.humidity = humidity;
        return this;
    }
}
