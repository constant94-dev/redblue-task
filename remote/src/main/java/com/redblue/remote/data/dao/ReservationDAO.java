package com.redblue.remote.data.dao;

import com.redblue.remote.data.entity.ReservationEntity;
import java.util.List;

public interface ReservationDAO {

    void bulkSave(List<ReservationEntity> reservationEntities);
}
