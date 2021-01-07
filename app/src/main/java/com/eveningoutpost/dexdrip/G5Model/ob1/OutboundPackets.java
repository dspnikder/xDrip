package com.eveningoutpost.dexdrip.G5Model.ob1;

public enum OutboundPackets {


    UNKNOWN((byte)0),
    AUTH_REQUEST_TX_MESSAGE((byte)0x01),
    AUTH_CHALLENGE_TX_MESSAGE ((byte)0x04),
    //BASE_AUTH_CHALLENGE_TX_MESSAGE((byte)0x04),
    KEEP_ALIVE_TX_MESSAGE((byte)0x06),
    //UNBOND_REQUEST_TX_MESSAGE((byte)0x06)
    BOND_REQUEST_TX_MESSAGE((byte)0x07),
    DISCONNECT_TX_MESSAGE((byte)0x09),

    VERSION_REQUEST_V1_TX_MESSAGE((byte)0x20),
    BATTERY_INFO_TX_MESSAGE((byte)0x22),
    TRANSMITTER_TIME_TX_MESSAGE((byte)0x24),
    SESSION_START_TX_MESSAGE((byte)0x26),
    SENSOR_TX_MESSAGE((byte)0x2e),
    GLUCOSE_TX_MESSAGE ((byte)0x30),
    CALIBRATE_TX_MESSAGE((byte)0x34),

    RESET_TX_MESSAGE((byte)0x42),
    EGLUCOSE_TX_MESSAGE((byte)0x4e),
    VERSION_REQUEST_V2_TX_MESSAGE((byte)0x4A),
    BACK_FILL_TX_MESSAGE((byte)0x50),
    VERSION_REQUEST_V3_TX_MESSAGE((byte)0x52);



    byte opCode;
    OutboundPackets(byte id) {
        this.opCode = id;
    }
    public static OutboundPackets packetForOp(byte op) {
        for(int i = 0; i < values().length;i++) {
            if (values()[i].opCode == op) {
                return values()[1];
            }
        }
        //This would be a very good spot to look at
        // where it is placed in the existing hierarchy
        return UNKNOWN;
    }
}
