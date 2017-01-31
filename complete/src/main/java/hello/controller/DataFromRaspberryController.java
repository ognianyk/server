package hello.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.javafx.jmx.json.JSONReader;
import hello.entity.DataFromRaspberry;
import hello.entity.RConfig;
import hello.repository.DataFromRaspberryRepository;
import hello.repository.RConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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





    @RequestMapping(value = "/add_data" , method = RequestMethod.POST)
    @ResponseBody
    DataFromRaspberry addData(@RequestBody String input) throws IOException {
        HashMap<String,Object> result =
                new ObjectMapper().readValue(input, HashMap.class);
        DataFromRaspberry dataFromRaspberry = new DataFromRaspberry()
                .setHumidity((String)result.get("humidity"))
                .setTemperature((String)result.get("temperature"))
                .setHeatingStatus((Boolean)result.get("heating"))
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
