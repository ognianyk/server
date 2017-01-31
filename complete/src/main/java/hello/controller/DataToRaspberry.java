package hello.controller;

import hello.entity.RConfig;
import hello.repository.RConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by pavelognianyk on 10/24/16.
 */
@Controller
public class DataToRaspberry {

    @Autowired
    private RConfigRepository configRepository;

    @RequestMapping("/change_heating")
    @ResponseBody
    public RConfig addData(@RequestParam(value = "heating", required = true) boolean heating,
    @RequestParam(value = "periodicalHeating", required = true) boolean periodicalHeating,
    @RequestParam(value = "periodTimeout", required = true) Long periodTimeout,
    @RequestParam(value = "heatingDuration", required = true) Long heatingDuration){
        List<RConfig> configList = WebSiteLiveData.toList(configRepository.findAll());
        RConfig config;
        if (configList.size() < 1) {
            config = new RConfig().
                    setHeatingStatus(heating).
                    setId(1L).
                    setChangeTime(new Date()).
                    setPeriodInSeconds(periodTimeout).
                    setEnablePeriodicalHeating(periodicalHeating).
                    setHeatingDurationInSeconds(heatingDuration);
            configRepository.save(config);
        } else if (configList.size() == 1) {
            config = configList.get(0);
            if(heating!=config.isHeatingStatus()) {
                config.setHeatingStatus(heating).setChangeTime(new Date());
            }
            config.setEnablePeriodicalHeating(periodicalHeating)
                    .setPeriodInSeconds(periodTimeout)
                    .setHeatingDurationInSeconds(heatingDuration);
            configRepository.save(config);
        } else {
            throw new RuntimeException("config error!!!!!");
        }
        return config;

    }

}
