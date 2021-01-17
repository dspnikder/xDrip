package com.eveningoutpost.dexdrip.Services.ob1.ble;

import com.eveningoutpost.dexdrip.utils.validation.Ensure;

public class Scanner {
    private ScanningStrategy scanningStrategy;



    public void scan() {
        Ensure.notNull(scanningStrategy);
        scanningStrategy.scan();
    }

}
