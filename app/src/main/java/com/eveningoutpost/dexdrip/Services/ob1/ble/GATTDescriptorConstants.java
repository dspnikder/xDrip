package com.eveningoutpost.dexdrip.Services.ob1.ble;

public enum GATTDescriptorConstants {
    CHARACTERISTIC_EXTENDED_PROPERTIES(0x2900,"Characteristic Extended Properties"),
    CHARACTERISTIC_USER_DESCRIPTION(0x2901,"Characteristic User Description"),
    CLIENT_CHARACTERISTIC_CONFIGURATION(0x2902,"Client Characteristic Configuration"),
    SERVER_CHARACTERISTIC_CONFIGURATION(0x2903,"Server Characteristic Configuration"),
    CHARACTERISTIC_PRESENTATION_FORMAT(0x2904,"Characteristic Presentation Format"),
    CHARACTERISTIC_AGGREGATE_FORMAT(0x2905,"Characteristic Aggregate Format"),
    VALID_RANGE(0x2906,"Valid Range"),
    EXTERNAL_REPORT_REFERENCE(0x2907,"External Report Reference"),
    REPORT_REFERENCE(0x2908,"Report Reference"),
    NUMBER_OF_DIGITALS(0x2909,"Number of Digitals"),
    VALUE_TRIGGER_SETTING(0x290A,"Value Trigger Setting"),
    ENVIRONMENTAL_SENSING_CONFIGURATION(0x290B,"Environmental Sensing Configuration"),
    ENVIRONMENTAL_SENSING_MEASUREMENT(0x290C,"Environmental Sensing Measurement"),
    ENVIRONMENTAL_SENSING_TRIGGER_SETTING(0x290D,"Environmental Sensing Trigger Setting"),
    TIME_TRIGGER_SETTING(0x290E,"Time Trigger Setting"),
    COMPLETE_BR_EDR_TRANSPORT_BLOCK_DATA(0x290F,"Complete BR-EDR_Transport Block Data");

    short value;
    String description;
    GATTDescriptorConstants(int v, String desc) {
        this.value = (short)v;
        this.description = desc;
    }
}
