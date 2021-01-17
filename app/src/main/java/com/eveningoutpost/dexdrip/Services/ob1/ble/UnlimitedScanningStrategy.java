package com.eveningoutpost.dexdrip.Services.ob1.ble;

/**
 * This strategy will utilize every available resource in order to scan
 * a transport layer for a specific remote peripheral in order to connect
 * or reconnect. Highly aggressive and most likely anything but energy
 * efficient but in some cases necessary.
 * Tests would be required in order to show which environments and
 * peripherals where the best for any strategy but none more so than for
 * this one.
 * @Since 2020-01-15
 */
public class UnlimitedScanningStrategy implements ScanningStrategy {
    @Override
    public void scan() {

    }
}
