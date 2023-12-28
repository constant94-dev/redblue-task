package com.redblue.remote.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigInteger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservation")
public class ReservationEntity {
    @Id
    @GeneratedValue
    private BigInteger id;
    private BigInteger seqTicket;
    private int reservationUnit;
    private String ownerId;
    private int usedNumber;
}
