package com.eveningoutpost.dexdrip.Services.ob1;

public interface StateChangeListener {
    void stateChanged(long timestamp, Ob1State newState);
}
