package hello.controller.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.entity.DataFromRaspberry;
import hello.entity.RConfig;
import hello.repository.DataFromRaspberryRepository;
import hello.repository.RConfigRepository;
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
    private RConfigRepository configRepository;

    @Autowired
    public DataFromRaspberryController(SimpMessagingTemplate template) {
        this.template = template;
    }


    @RequestMapping(value = "/add_data", method = RequestMethod.POST)
    @ResponseBody
    DataFromRaspberry addData(@RequestBody String input) throws IOException {
        HashMap<String, Object> result =
                new ObjectMapper().readValue(input, HashMap.class);
        String temperature = String.valueOf(result.get("temperature"));
        String humidity = String.valueOf(result.get("humidity"));
        DataFromRaspberry dataFromRaspberry = new DataFromRaspberry()
                .setHumidity(humidity)
                .setTemperature(temperature)
                .setHeatingStatus((Boolean) result.get("heating"))
                .setSyncTime(new Date());
        dataFromRaspberryRepository.save(dataFromRaspberry);
        template.convertAndSend("/topic/greetings", dataFromRaspberry);
        return dataFromRaspberry;

    }

    @RequestMapping("/get_heating_data")
    @ResponseBody
    public RConfig getHeatingData() {
        return configRepository.findOne(1L);


    }


}
