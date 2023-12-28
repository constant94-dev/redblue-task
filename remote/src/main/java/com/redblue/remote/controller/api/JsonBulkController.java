package com.redblue.remote.controller.api;

import com.redblue.remote.service.ReservationService;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Objects;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bulk")
public class JsonBulkController {
    private final Logger LOGGER = LoggerFactory.getLogger(JsonBulkController.class);
    private final ReservationService reservationService;

    @Autowired
    public JsonBulkController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    /* TODO: Json 데이터를 파싱해 500개씩 끊어 DB에 insert */
    @GetMapping("/insert")
    public void bulkInsert() {
        LOGGER.info("bulkInsert 호출!!");
        Object obj = getJsonData();
        JSONArray jsonArray = (JSONArray) obj;

        LOGGER.info("Json 데이터 크기 {}", Objects.requireNonNull(jsonArray).size());

        reservationService.bulkSave(jsonArray);
    }

    /* Json 데이터 파싱 */
    private Object getJsonData() {
        try {
            Reader reader = new FileReader("jsonbulk/generated.json");
            JSONParser parser = new JSONParser();
            return parser.parse(reader);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
