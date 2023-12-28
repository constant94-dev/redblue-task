package com.redblue.remote.repository;

import com.redblue.remote.data.entity.ReservationEntity;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<ReservationEntity, BigInteger> {
}
