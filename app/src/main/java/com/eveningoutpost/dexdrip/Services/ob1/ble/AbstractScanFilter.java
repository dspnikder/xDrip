package com.eveningoutpost.dexdrip.Services.ob1.ble;

import com.eveningoutpost.dexdrip.utils.validation.Ensure;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractScanFilter<T>implements Filterable<T> {
    private Filterable<T> subFilter;
    @Override
    public void addFilter(Filterable<T> filter) {
        Ensure.notNull(filter);
        if (filter == this) {
            return;
        }
        if (subFilter == null) {
            subFilter = filter;
        } else if (filter!=this && subFilter != filter) {
            subFilter.addFilter(filter);
        }
    }

    @Override
    public final boolean matches(T value) {
        return match(value);
    }

    protected abstract boolean match(T value);


    protected boolean and(T value) {
        return subFilter== null || subFilter.matches(value);
    }
    protected boolean or(T value) {
        return subFilter!=null && subFilter.matches(value);
    }
}
