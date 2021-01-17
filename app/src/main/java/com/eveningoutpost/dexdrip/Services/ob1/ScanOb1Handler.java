package com.eveningoutpost.dexdrip.Services.ob1;

import com.eveningoutpost.dexdrip.Services.ob1.ble.BleManager;
import com.eveningoutpost.dexdrip.Services.ob1.ble.BlePeripheralStore;
import com.eveningoutpost.dexdrip.Services.ob1.ble.PeripheralType;
import com.eveningoutpost.dexdrip.Services.ob1.exception.BluetoothException;
import com.eveningoutpost.dexdrip.Services.ob1.exception.MissingPermissionException;

import java.util.List;

public class ScanOb1Handler extends AbstractOb1StateHandler {
    protected ScanOb1Handler(Ob1State state, List<Ob1State> legalTransitions) {
        super(state, legalTransitions);
    }

    @Override
    protected void checkPreconditions() {
        if (is(AVOID_SCAN)) {
            log(INFO,"Skipping Scanning! : Changing state due to minimize_scanning flags");
            exit(Ob1State.CONNECT_NOW);
        }
        //ensure we can activate
        if (is(BLE_ADAPTER_AVAILABLE)) {
            if (!is(BLE_ADAPTER_ENABLED)) {
                try {
                    BleManager.get().start();

                } catch (BluetoothException ble) {
                    log(ERROR,"Failed to start Bluetooth adapter",ble);
                } catch (MissingPermissionException mpe) {
                    log(WARN, "We are missing one or more permissions", mpe);
                    //TODO ensure that relevant entity can inform user to give
                    // consent for required permissions
                    exit(Ob1State.INIT);
                }
            }
        }
    }

    @Override
    public void execute() {
        checkPreconditions();
        String id = (is(PERIPHERAL_ID_KNOWN)? BlePeripheralStore.get().getKnownPeripherals(PeripheralType.UNCLASSIFIED,true).toString():null);
        String mac = null;
    }
}
