package com.eveningoutpost.dexdrip.G5Model.ob1;

import java.util.concurrent.TimeUnit;

/**
 * What channel are we running over
 * Are messages in need of an encoder/decoder before being sent/interpreted
 * Is there a minimum response time?
 * Is there a sync requirement?
 *
 */
public class Protocol {
    public static final int REQUIRES_IDENTIFIER = 1;
    public static final int REQUIRES_PASSWORD = 2;
    public static final int REQUIRES_ENCODER = 4;
    public static final int REQUIRES_DECODER = 8;
    public static final int REQUIRES_SYNC = 16;
    public static final int REQUIRES_TIMED_RESPONSES = 32;
    public static final int REQUIRES_TIMED_REQUESTS = 64;

    private int protocolFlags;
    private TimeUnit syncPrecision;
    private long responseTimeInNs = 0;
    private long requestTimeInNs = 0;




    enum TransmissionLayer {
        TCP,
        UDP,
        BLUETOOTH,
        BLUETOOTH_LE,
        ZIGBEE,
        ANT,
        ANT_PLUS,
        NFC
    }
}
