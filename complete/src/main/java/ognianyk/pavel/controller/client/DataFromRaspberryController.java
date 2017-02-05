package ognianyk.pavel.controller.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import ognianyk.pavel.entity.AdditionalSensorEntity;
import ognianyk.pavel.entity.DataFromRaspberry;
import ognianyk.pavel.entity.RConfig;
import ognianyk.pavel.repository.AdditionalSensorRepository;
import ognianyk.pavel.repository.DataFromRaspberryRepository;
import ognianyk.pavel.repository.RConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

@Controller
public class DataFromRaspberryController {

    private final SimpMessagingTemplate template;
    @Autowired
    private DataFromRaspberryRepository dataFromRaspberryRepository;
    @Autowired
    private AdditionalSensorRepository additionalSensorRepository;

    @Autowired
    private RConfigRepository configRepository;

    @Autowired
    public DataFromRaspberryController(SimpMessagingTemplate template) {
        this.template = template;
    }


    @RequestMapping(value = "/add_data", method = RequestMethod.POST)
    @ResponseBody
    DataFromRaspberry addData(@RequestBody String input) throws IOException {
        DataFromRaspberry dataFromRaspberry =
                new ObjectMapper().readValue(input, DataFromRaspberry.class);

        dataFromRaspberry
                .setSyncTime(new Date());
        DataFromRaspberry dataFromRaspberrySaved = dataFromRaspberryRepository.save(dataFromRaspberry);
        for (AdditionalSensorEntity additionalSensorEntity : dataFromRaspberry.getSensorEntityList()) {
            additionalSensorEntity.setDataFromRaspberry(dataFromRaspberrySaved);
            additionalSensorRepository.save(additionalSensorEntity);
        }
        DataFromRaspberry dataFromRaspberryFull = dataFromRaspberryRepository.findOne(dataFromRaspberrySaved.getId());
        template.convertAndSend("/topic/greetings", dataFromRaspberry);
        return dataFromRaspberry;

    }

    @RequestMapping("/get_heating_data")
    @ResponseBody
    public RConfig getHeatingData() {
        return configRepository.findOne(1L);


    }


}
