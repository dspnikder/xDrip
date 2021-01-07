package com.eveningoutpost.dexdrip.G5Model.ob1;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.time.OffsetDateTime;

import lombok.Data;

@Data
public abstract class AbstractMessage {
    private static ByteOrder order = ByteOrder.LITTLE_ENDIAN;
    private OffsetDateTime created;
    private MessageDirection direction;
    private final int opCode;
    private ByteBuffer data;
    private byte[] raw;


    public AbstractMessage(int opCode, MessageDirection direction) {
        this.opCode = opCode;
        this.direction = direction;
    }






}
