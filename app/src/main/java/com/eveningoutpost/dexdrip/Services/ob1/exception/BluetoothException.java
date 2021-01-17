package com.eveningoutpost.dexdrip.Services.ob1.exception;

import androidx.annotation.NonNull;

public class BluetoothException extends Exception {
    private byte[] data;
    public BluetoothException(String message) {
        super(message);
    }
    public BluetoothException(String message,Throwable t) {
        super(message, t);
    }
    public BluetoothException(String message, byte[] data) {
        this.data = data;
    }

    @NonNull
    @Override
    public String toString() {
        if (this.data != null) {

        }
        return super.toString();
    }
}
