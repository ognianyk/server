package ognianyk.pavel.controller.web;

import ognianyk.pavel.entity.DataFromRaspberry;
import ognianyk.pavel.repository.DataFromRaspberryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pavelognianyk on 10/24/16.
 */
@Controller
public class WebSiteLiveData {
    @Autowired
    private DataFromRaspberryRepository dataFromRaspberryRepository;


    @RequestMapping("/get_data")
    @ResponseBody
    public List<DataFromRaspberry> getAllData(@RequestParam(value = "page", required = false) Integer page) throws Exception {
        if (page == null) {
            page = 0;
        }
        PageRequest pageable = new PageRequest(page, 10, Sort.Direction.DESC, "Id");
        List<DataFromRaspberry> list = toList(
                dataFromRaspberryRepository.findAll(pageable));
        return list;

    }

    public static <E> List<E> toList(Iterable<E> iterable) {
        ArrayList<E> list = new ArrayList<E>();
        if (iterable != null) {
            iterable.forEach(list::add);
        }
        return list;
    }
}
