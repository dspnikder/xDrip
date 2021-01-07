package com.eveningoutpost.dexdrip.G5Model.ob1.util;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;


public class ValueRange<T extends Number> {
    private T min;
    private T max;
    public static final int INCLUDE_MIN_MAX = 0;
    public static final int INCLUDE_MIN_EXCLUDE_MAX = 1;
    public static final int EXLUDE_MIN_INCLUDcE_MAX = 2;
    public static final int EXCLUDE_MIN_MAX = 3;

    private static final int INT_VALUE = 10;
    private static final int SHORT_VALUE = 11;
    private static final int BYTE_VALUE = 12;
    private static final int DOUBLE_VALUE = 13;
    private static final int FLOAT_VALUE = 14;
    private static final int LONG_VALUE = 16;
    private static final Map<Class<? extends Number>, Integer> typeMap = new HashMap<>();

    static {

        typeMap.put(Long.class, LONG_VALUE);
        typeMap.put(Integer.class, INT_VALUE);
        typeMap.put(Short.class, SHORT_VALUE);
        typeMap.put(Byte.class, BYTE_VALUE);
        typeMap.put(Double.class, DOUBLE_VALUE);
        typeMap.put(Float.class, FLOAT_VALUE);
    }


    private int primitive = INT_VALUE;


    private final int mode;

    public ValueRange(T exact) {
        this(exact,exact,INCLUDE_MIN_MAX);

    }
    public ValueRange(T min, T max) {
        this(min,max,INCLUDE_MIN_MAX);
    }
    public ValueRange(T min, T max, int mode) {
        this.mode = mode;
        this.min = min;
        this.max = max;
        this.primitive = typeMap.get(this.min.getClass());

    }
}
