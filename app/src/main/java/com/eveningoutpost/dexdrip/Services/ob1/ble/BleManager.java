package com.eveningoutpost.dexdrip.Services.ob1.ble;

import android.bluetooth.BluetoothAdapter;
import android.os.Build;

import com.eveningoutpost.dexdrip.Services.ob1.exception.BluetoothException;
import com.eveningoutpost.dexdrip.Services.ob1.exception.MissingPermissionException;
import com.eveningoutpost.dexdrip.Services.ob1.exception.RequiredPermissions;
import com.eveningoutpost.dexdrip.UtilityModels.RxBleProvider;
import com.polidea.rxandroidble2.RxBleClient;
import com.polidea.rxandroidble2.scan.ScanFilter;
import com.polidea.rxandroidble2.scan.ScanSettings;

/**
 * BleManager
 */
public class BleManager {
    private static final BleManager manager = new BleManager();
    private final RxBleClient rxBlieCLient;
    private final BleClient bleClient;
    private boolean active = false;

    private BleManager() {
        rxBlieCLient = RxBleProvider.getSingleton();
        bleClient = new BleClient(rxBlieCLient);
    }

    public static BleManager get() {
        return manager;
    }
    public static boolean isActive() {
        return manager.active;
    }
    public static boolean ableToActivate() {
        return manager.bleClient.isReady();
    }
    protected void ensurePossible() throws BluetoothException,MissingPermissionException {
        switch (rxBlieCLient.getState()) {
            case READY:
                return;
            case BLUETOOTH_NOT_ENABLED:
                BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
                if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
                    BluetoothAdapter.getDefaultAdapter().enable();
                }
                return;
            case BLUETOOTH_NOT_AVAILABLE:
                throw new BluetoothException("Unfortunately there no Bluetooth device available");
            case LOCATION_SERVICES_NOT_ENABLED:
                throw new MissingPermissionException(RequiredPermissions.LOCATION);
        }

    }
    public void start() throws BluetoothException, MissingPermissionException {
        ensurePossible();
        ScanSettings settings = new ScanSettings.Builder().setCallbackType(
                ScanSettings.CALLBACK_TYPE_FIRST_MATCH + ScanSettings.CALLBACK_TYPE_MATCH_LOST)
                .setScanMode(ScanSettings.SCAN_MODE_BALANCED)
                .build();
        rxBlieCLient.scanBleDevices(settings);

    }





}
