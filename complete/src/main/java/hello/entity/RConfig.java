package hello.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by pavelognianyk on 10/24/16.
 */
@Entity
public class RConfig implements Serializable{

    @Id
    private Long id;

    @Column(nullable = false)
    private boolean heatingStatus;
    @Column(nullable = false)
    private Date changeTime;
    @Column(nullable = false)
    private boolean enablePeriodicalHeating;
    @Column(nullable = false)
    private Long periodInSeconds;
    @Column(nullable = false)
    private Long heatingDurationInSeconds;

    public RConfig() {
    }


    public boolean isHeatingStatus() {
        return heatingStatus;
    }

    public RConfig setHeatingStatus(boolean heatingStatus) {
        this.heatingStatus = heatingStatus;
        return this;
    }

    public RConfig setId(Long id) {
        this.id = id;
        return this;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public RConfig setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
        return this;
    }

    public boolean isEnablePeriodicalHeating() {
        return enablePeriodicalHeating;
    }

    public RConfig setEnablePeriodicalHeating(boolean enablePeriodicalHeating) {
        this.enablePeriodicalHeating = enablePeriodicalHeating;
        return this;
    }

    public Long getPeriodInSeconds() {
        return periodInSeconds;
    }

    public RConfig setPeriodInSeconds(Long periodInSeconds) {
        this.periodInSeconds = periodInSeconds;
        return this;
    }

    public Long getHeatingDurationInSeconds() {
        return heatingDurationInSeconds;
    }

    public RConfig setHeatingDurationInSeconds(Long heatingDurationInSeconds) {
        this.heatingDurationInSeconds = heatingDurationInSeconds;
        return this;
    }
}
