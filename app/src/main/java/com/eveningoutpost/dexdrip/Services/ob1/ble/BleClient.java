package com.eveningoutpost.dexdrip.Services.ob1.ble;

import com.polidea.rxandroidble2.RxBleClient;

/**
 * Lightweight wrapper providing us with a common API for the inevitable ANT/Zigbee
 * and others supporting vendors.
 */
public class BleClient {

    private RxBleClient client;

    public BleClient(RxBleClient client) {
        this.client = client;

    }

    public boolean isReady() {
        return this.client.getState() == RxBleClient.State.READY;
    }

    public void startScan() {
        if (!isReady()) {

        }
    }
}
