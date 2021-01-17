package com.eveningoutpost.dexdrip.Services.ob1.ble;

public enum GATTDeclarationConstants {
    PRIMARY_SERVICE(0x2800,"Primary Service"),
    SECONDARY_SERVICE(0x2801,"Secondary Service"),
    INCLUDE(0x2802,"Include"),
    CHARACTERISTIC(0x2803,"Characteristic");

    short value;
    String description;
    GATTDeclarationConstants(int v, String desc) {
        this.value = (short)v;
        this.description = desc;
    }

}
