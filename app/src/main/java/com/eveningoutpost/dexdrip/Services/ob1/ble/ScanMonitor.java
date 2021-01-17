package com.eveningoutpost.dexdrip.Services.ob1.ble;

import com.polidea.rxandroidble2.RxBleClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for keeping track of the scanning state, any information discovered
 * that will make subsequent scans simpler, switching between scanning strategies as
 * appopriate etc
 */
public class ScanMonitor {
    private List<BlePeripheral> peripherals = new ArrayList<>();

    protected ScanningStrategy getScanningStrategy(BlePeripheral peripheral) {
        return new UnlimitedScanningStrategy();
    }

}
