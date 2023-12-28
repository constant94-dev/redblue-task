package com.redblue.remote.data.dao.impl;

import com.redblue.remote.data.dao.ReservationDAO;
import com.redblue.remote.data.entity.ReservationEntity;
import com.redblue.remote.repository.ReservationRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservationDAOImpl implements ReservationDAO {
    private final Logger LOGGER = LoggerFactory.getLogger(ReservationDAOImpl.class);
    private ReservationRepository repository;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ReservationDAOImpl(ReservationRepository repository, JdbcTemplate jdbcTemplate) {
        this.repository = repository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    @Override
    public void bulkSave(List<ReservationEntity> reservationEntities) {
        LOGGER.info("ReservationDAOImpl bulkSave 호출!!");
        batchInsert(500, reservationEntities);
    }

    private void batchInsert(int batchSize, List<ReservationEntity> reservationEntities) {
        long beforeTime = System.currentTimeMillis();

        int[][] results = jdbcTemplate.batchUpdate(
                " INSERT INTO reservation ( "
                        + "id, seq_ticket, reservation_unit, owner_id, used_number"
                        + ") values ("
                        + "?, ?, ?, ?, ?"
                        + ")",
                reservationEntities,
                batchSize,
                (ps, reservationEntity) -> {
                    ps.setObject(1, reservationEntity.getId());
                    ps.setObject(2, reservationEntity.getSeqTicket());
                    ps.setInt(3, reservationEntity.getReservationUnit());
                    ps.setString(4, reservationEntity.getOwnerId());
                    ps.setInt(5, reservationEntity.getUsedNumber());
                }
        );

        long afterTime = System.currentTimeMillis();
        long secDiffTime = (afterTime - beforeTime) / 1000;
        LOGGER.info("소요된 시간(m): {}", secDiffTime);
    }
}
