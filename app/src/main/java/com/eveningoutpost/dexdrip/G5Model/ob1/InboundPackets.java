package com.eveningoutpost.dexdrip.G5Model.ob1;

/**
 * Content cobbled together from things like
 * private enum PACKET {
 *         NULL,
 *         UNKNOWN,
 *         AuthChallengeRxMessage,
 *         AuthStatusRxMessage,
 *         SensorRxMessage,
 *         VersionRequestRxMessage,
 *         VersionRequest1RxMessage,
 *         VersionRequest2RxMessage,
 *         BatteryInfoRxMessage,
 *         SessionStartRxMessage,
 *         SessionStopRxMessage,
 *         GlucoseRxMessage,
 *         EGlucoseRxMessage,
 *         CalibrateRxMessage,
 *         BackFillRxMessage,
 *         TransmitterTimeRxMessage,
 *         BondRequestRxMessage,
 *         F2DUnknownRxMessage,
 *         InvalidRxMessage,
 *
 *     }
 *
 *     O
 */
public enum InboundPackets {
        NULL((byte)0),
        UNKNOWN((byte)0),
        // Looks like an authorization block
        AUTH_CHALLENGE_RX_MESSAGE((byte)0x03),
        AUTH_STATUS_RX_MESSAGE((byte)0x05),
        BOND_REQUEST_RX_MESSAGE((byte)0x07),


        VERSION_REQUEST_RX_MESSAGE((byte)0x21),
        BATTERY_INFO_RX_MESSAGE ((byte)0x23),
        TRANSMITTER_TIME_RX_MESSAGE ((byte)0x25),
        SESSION_START_RX_MESSAGE ((byte)0x27),
        SESSION_STOP_RX_MESSAGE ((byte)0x29),
        //odd code in terms of placement
        SENSOR_RX_MESSAGE((byte)0x2f),
        GLUCOSE_RX_MESSAGE ((byte)0x31),
        //is 0x33 in use elsewhere? so they
        //shifted things around. Unclear
        CALIBRATE_RX_MESSAGE ((byte)0x35),

        //either I'm guessing later additions
        //or placed like that for some other reason
        VERSION_REQUEST1_RX_MESSAGE((byte)0x4b),
        EGLUCOSE_RX_MESSAGE ((byte)0x4f),

        //Same here and no real stringency in the codes
        BACKFILL_RX_MESSAGE ((byte)0x51),
        VERSION_REQUEST2_RX_MESSAGE ((byte)0x53),

        F2D_UNKNOWN_RX_MESSAGE ((byte)0xD0),
        INVALID_RX_MESSAGE ((byte)0xFF);

        final byte opCode;

        InboundPackets(byte id) {
                opCode = id;
        }

        public byte getOpCode() {
                return this.opCode;
        }
        public static InboundPackets getPackerFor(byte op) {
                for(int i = 0; i < values().length;i++) {
                        if (values()[i].opCode == op) {
                                return values()[1];
                        }
                }
                //This would be a very good spot to look at
                // where it is placed in the existing hierarchy
                return InboundPackets.UNKNOWN;
        }
}
