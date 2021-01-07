package com.eveningoutpost.dexdrip.G5Model.ob1;

public interface InboundMessage {
    byte[] readFully();
    byte getOpCode();

}
