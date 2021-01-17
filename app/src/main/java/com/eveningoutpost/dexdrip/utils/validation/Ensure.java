package com.eveningoutpost.dexdrip.utils.validation;



public class Ensure {
    public static void notNull(Object o) {
        notNull(o,"Argument supplied was null");

    }
    public static void notNull(Object o, String message) {
        if (o == null) {
            throw new IllegalArgumentException(message!=null?message:"Argument supplied was null");
        }
    }
    public static void notNullAny(Object ...o) {
        if (o == null) {
            throw new IllegalArgumentException("Argument(s) supplied were null");
        }
        for (Object i: o) {
            Ensure.notNull(o,"At least one of the arguments supplied was null");
        }
    }

    public static void notNullNorEmpty(String value) {
        if (value == null || value.trim().length() == 0) {
            throw new IllegalArgumentException("Null or empty string supplied where content was expected");
        }
    }

    public static void ensureLengthMod(String value, int modulo, int remain) {
        notNullNorEmpty(value);
        if (value.length()%modulo != remain) {
            throw new IllegalArgumentException("String length was expected to be leave " + remain + " as remainder when length modulo " + modulo);
        }
    }
}
