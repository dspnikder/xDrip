package com.eveningoutpost.dexdrip.Services.ob1.ble;

import android.provider.BaseColumns;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;

@Table(name = "BlePeripheral", id = BaseColumns._ID)
public class BlePeripheral extends Model {

    @Expose
    @Column(name = "peripheralType")
    private PeripheralType peripheralType; // The type of peripheral

    @Expose
    @Column(name = "lastKnownMAC")
    private String lastKnownMaAC;

    @Expose
    @Column(name="deviceName")
    private String deviceName;

    @Expose
    @Column(name = "manufacturerId")
    private String manufacturerId;

    @Expose
    @Column(name= "model")
    private String model;

    @Expose
    @Column(name= "softRevision")
    private String firmware;

    @Expose
    @Column(name= "hardRevision")
    private String hardware;


    public BlePeripheral(String name, String manufacturer) {
        this.manufacturerId = manufacturer;
        this.deviceName = name;
    }
}
