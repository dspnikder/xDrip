package com.eveningoutpost.dexdrip.G5Model.ob1.sensor;

import android.util.SparseArray;

import com.eveningoutpost.dexdrip.G5Model.DexSessionKeeper;
import com.eveningoutpost.dexdrip.Models.UserError;
import com.google.common.collect.ImmutableSet;


import static com.eveningoutpost.dexdrip.Services.G5CollectionService.TAG;

/**
 * Replace ment for G5Model.CalibrationState whis is just
 * not even near where it should be. Quick rundown of
 * some of the issues:
 * Using Lombok for two methods? No.
 * Combining separate state levels in the same state
 * descriptor? No. No no.
 * Raw text rather than using the enum itself as key?
 * Just. Why?
 *
 *
 */
public enum CalibrationState {
    UNKNOWN (new byte[]{0x00}),
    STOPPED (new byte[]{0x01}),
    WARMING_UP (new byte[]{0x02}),
    EXCESS_NOISE (new byte[]{0x03}),
    NEEDS_CALIBRATION (new byte[]{0x04,0x05,0x07,0x08,0x09,0x0a,0x0d,0x0e}),
    OK (new byte[]{0x06}),
    SENSOR_FAILED (new byte[]{0x0b,0x0c,0x10,0x13,0x14,0x15,0x16}),
    ENDED (new byte[]{0x0f}),
    TRANSMITTER_PROBLEM (new byte[]{0x11}),
    ERRORS (new byte[]{0x12}),
    SENSOR_FAILED_START (new byte[]{0x16});

    public final byte[] codes;
    //public final String text;


    private static final SparseArray<CalibrationState> lookup = new SparseArray<>();
    private static final ImmutableSet<CalibrationState> failed =
            ImmutableSet.of(SENSOR_FAILED,SENSOR_FAILED_START);
    private static final ImmutableSet<CalibrationState> stopped =
            ImmutableSet.of(STOPPED, ENDED, SENSOR_FAILED,SENSOR_FAILED_START);


    CalibrationState(byte[] values) {
        this.codes = values;
    }

    static {
        for (CalibrationState state : values()) {
            for (byte code: state.codes) {
                lookup.put(code, state);
            }

        }
    }

    public static CalibrationState parse(byte state) {
        final CalibrationState result = lookup.get(state);
        if (result == null) UserError.Log.e(TAG, "Unknown calibration state: " + state);
        return result != null ? result : UNKNOWN;
    }

    public static CalibrationState parse(int state) {
        return parse((byte) state);
    }
    public String getText() {
        return this.text;
    }
    public boolean usableGlucose() {
        return this == OK
                || this == NEEDS_CALIBRATION;
    }

    public boolean insufficientCalibration() {
        return this == NEEDS_CALIBRATION;
    }

    public boolean readyForCalibration() {
        return this ==
                || needsCalibration();
    }

    public boolean needsCalibration() {
        return this == NEEDS_CALIBRATION;
    }

    public boolean sensorStarted() {
        return !stopped.contains(this);
    }

    public boolean sensorFailed() {
        return this == SENSOR_FAILED || this == SENSOR_FAILED_START;
    }

    public boolean ended() {
        return this == ENDED;
    }

    public boolean warmingUp() {
        return this == WARMING_UP;
    }

    public boolean ok() {
        return this == OK;
    }

    public boolean readyForBackfill() {
        switch(this) {
            case WARMING_UP:
            case STOPPED:
            case UNKNOWN:
            case NEEDS_CALIBRATION:
            case ERRORS:
                return false;
            default:
                return true;
        }
    }

    public String getExtendedText() {
        switch (this) {
            case OK:
                if (DexSessionKeeper.isStarted()) {
                    return getText() + " " + DexSessionKeeper.prettyTime();
                } else {
                    return getText() + " time?";
                }
            case WARMING_UP:
                if (DexSessionKeeper.isStarted()) {
                    if (DexSessionKeeper.warmUpTimeValid()) {
                        return getText() + "\n" + DexSessionKeeper.prettyTime() + " left";
                    } else {
                        return getText();
                    }
                } else {
                    return getText();
                }

            default:
                return getText();
        }
    }
}
