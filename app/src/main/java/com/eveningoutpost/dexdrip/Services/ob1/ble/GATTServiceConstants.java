package com.eveningoutpost.dexdrip.Services.ob1.ble;

public enum GATTServiceConstants {
    GENERIC_ACCESS(0x1800,"Generic Access"),
    GENERIC_ATTRIBUTE(0x1801,"Generic Attribute"),
    IMMEDIATE_ALERT(0x1802,"Immediate Alert"),
    LINK_LOSS(0x1803,"Link Loss"),
    TX_POWER(0x1804,"Tx Power"),
    CURRENT_TIME(0x1805,"Current Time"),
    REFERENCE_TIME_UPDATE(0x1806,"Reference Time Update"),
    NEXT_DST_CHANGE(0x1807,"Next DST_Change"),
    GLUCOSE(0x1808,"Glucose"),
    HEALTH_THERMOMETER(0x1809,"Health Thermometer"),
    DEVICE_INFORMATION(0x180A,"Device Information"),
    HEART_RATE(0x180D,"Heart Rate"),
    PHONE_ALERT_STATUS(0x180E,"Phone Alert Status"),
    BATTERY(0x180F,"Battery"),
    BLOOD_PRESSURE(0x1810,"Blood Pressure"),
    ALERT_NOTIFICATION(0x1811,"Alert Notification"),
    HUMAN_INTERFACE_DEVICE(0x1812,"Human Interface Device"),
    SCAN_PARAMETERS(0x1813,"Scan Parameters"),
    RUNNING_SPEED_AND_CADENCE(0x1814,"Running Speed and Cadence"),
    AUTOMATION_IO(0x1815,"Automation IO"),
    CYCLING_SPEED_AND_CADENCE(0x1816,"Cycling Speed and Cadence"),
    CYCLING_POWER(0x1818,"Cycling Power"),
    LOCATION_AND_NAVIGATION(0x1819,"Location and Navigation"),
    ENVIRONMENTAL_SENSING(0x181A,"Environmental Sensing"),
    BODY_COMPOSITION(0x181B,"Body Composition"),
    USER_DATA(0x181C,"User Data"),
    WEIGHT_SCALE(0x181D,"Weight Scale"),
    BOND_MANAGEMENT(0x181E,"Bond Management"),
    CONTINUOUS_GLUCOSE_MONITORING(0x181F,"Continuous Glucose Monitoring"),
    INTERNET_PROTOCOL_SUPPORT(0x1820,"Internet Protocol Support"),
    INDOOR_POSITIONING(0x1821,"Indoor Positioning"),
    PULSE_OXIMETER(0x1822,"Pulse Oximeter"),
    HTTP_PROXY(0x1823,"HTTP_Proxy"),
    TRANSPORT_DISCOVERY(0x1824,"Transport Discovery"),
    OBJECT_TRANSFER(0x1825,"Object Transfer"),
    FITNESS_MACHINE(0x1826,"Fitness Machine"),
    MESH_PROVISIONING(0x1827,"Mesh Provisioning"),
    MESH_PROXY(0x1828,"Mesh Proxy"),
    RECONNECTION_CONFIGURATION(0x1829,"Reconnection Configuration"),
    INSULIN_DELIVERY(0x183A,"Insulin Delivery"),
    BINARY_SENSOR(0x183B,"Binary Sensor"),
    EMERGENCY_CONFIGURATION(0x183C,"Emergency Configuration"),
    PHYSICAL_ACTIVITY_MONITOR(0x183E,"Physical Activity Monitor"),
    AUDIO_INPUT_CONTROL(0x1843,"Audio Input Control"),
    VOLUME_CONTROL(0x1844,"Volume Control"),
    VOLUME_OFFSET_CONTROL(0x1845,"Volume Offset Control"),
    DEVICE_TIME(0x1847,"Device Time");

    short value;
    String description;
    GATTServiceConstants(int v, String desc) {
        this.value = (short)v;
        this.description = desc;
    }
}
