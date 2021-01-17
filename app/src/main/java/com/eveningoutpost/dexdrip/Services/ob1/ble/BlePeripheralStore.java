package com.eveningoutpost.dexdrip.Services.ob1.ble;

import com.activeandroid.query.From;
import com.activeandroid.query.Select;

import java.util.List;

public class BlePeripheralStore {
    private static final BlePeripheralStore peripherals = new BlePeripheralStore();
    private BlePeripheralStore() {

    }

    public static BlePeripheralStore get() {
        return peripherals;
    }

    public List<BlePeripheral> getKnownPeripherals(PeripheralType tyoe, boolean hasMac) {
        From w = new Select().from(BlePeripheral.class)
                .where("periphealType = ",tyoe.name());
        if (hasMac) {
            w.and("lastKnownMac NOT NULL");
        }
        return w.execute();
    }
    public BlePeripheral deviceLocated(String name, String manufacturer) {
        return new BlePeripheral(name,manufacturer);
    }
    public void expired(BlePeripheral p) {
    }
}
