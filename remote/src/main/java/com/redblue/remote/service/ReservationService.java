package com.redblue.remote.service;

import com.redblue.remote.data.dao.ReservationDAO;
import com.redblue.remote.data.entity.ReservationEntity;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    private final Logger LOGGER = LoggerFactory.getLogger(ReservationService.class);
    private final ReservationDAO reservationDAO;

    @Autowired
    public ReservationService(ReservationDAO reservationDAO) {
        this.reservationDAO = reservationDAO;
    }

    public void bulkSave(JSONArray jsonArray) {
        LOGGER.info("ReservationService#bulkSave 호출!!");
        List<ReservationEntity> reservationEntities = new ArrayList<>();

        LOGGER.info("Json 데이터 저장 전 크기: " + reservationEntities.size());

        for (Object o : Objects.requireNonNull(jsonArray)) {
            JSONObject jsonObject = (JSONObject) o;

            BigInteger seqTicket = new BigInteger(jsonObject.get("seq_ticket").toString());
            int reservationUnit = Integer.parseInt(jsonObject.get("reservation_unit").toString());
            String ownerId = String.valueOf(jsonObject.get("owner_id"));
            int usedNumber = Integer.parseInt(jsonObject.get("used_number").toString());

            reservationEntities.add(new ReservationEntity(
                    seqTicket,
                    seqTicket,
                    reservationUnit,
                    ownerId,
                    usedNumber));
        }

        LOGGER.info("Json 데이터 저장 후 크기: " + reservationEntities.size());
        reservationDAO.bulkSave(reservationEntities);
    }

}
