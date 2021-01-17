package com.eveningoutpost.dexdrip.Services.ob1.ble;

public enum PeripheralType {
    MISCELLANEOUS ((byte)0x00),
    UNCLASSIFIED ((byte)0x1F),
    AUDIO ((byte)0x04),
    PHONELIKE ((byte)0x02),
    GPC ((byte)0x01),
    PERIPHERAL ((byte)0x05),
    IMAGING ((byte)0x06),
    LAN_ACCESS_POINT ((byte)0x03);

    private byte type;

    PeripheralType(byte t) {
        this.type = t;
    }

    public byte getType() {
        return type;
    }
    public static PeripheralType byType(byte type) {
        for(PeripheralType t: values()) {
            if (t.getType() == type) {
                return t;
            }
        }
        return null;
    }

}
