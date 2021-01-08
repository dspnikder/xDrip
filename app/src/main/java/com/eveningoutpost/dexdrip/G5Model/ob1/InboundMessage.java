package com.eveningoutpost.dexdrip.G5Model.ob1;

import java.math.BigInteger;
import java.nio.ByteOrder;

import lombok.NonNull;

public interface InboundMessage {
    byte[] asRaw();
    @NonNull
    ByteOrder order();
    @NonNull
    ByteOrder order(@NonNull ByteOrder order);
    int skip(int skipBytes);
    int position();
    int position(int position);
    int mark();
    int mark(int mark);
    byte get();
    short getUnsigned();
    byte[] get(int count);
    boolean crc16();
    boolean crc(int bits);
    short getShort();
    int getUnsignedShort();
    int getInt();
    long getUnsignedInt();
    long getLong();
    BigInteger getUnsignedLong();



}
