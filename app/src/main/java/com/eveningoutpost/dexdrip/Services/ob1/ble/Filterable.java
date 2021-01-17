package com.eveningoutpost.dexdrip.Services.ob1.ble;

public interface Filterable<T> {
    void addFilter(Filterable<T> filter);
    boolean matches(T value);
}
