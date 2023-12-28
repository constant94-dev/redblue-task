package com.redblue.remote.data.dto;

import java.math.BigInteger;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReservationBulkDTO {
    private BigInteger seqTicket;
    private int reservationUnit;
    private String ownerId;
    private BigInteger usedNumber;
}
