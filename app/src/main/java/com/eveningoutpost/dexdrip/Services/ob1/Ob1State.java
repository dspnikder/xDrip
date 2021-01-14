package com.eveningoutpost.dexdrip.Services.ob1;

// Internal process state tracking
public enum Ob1State {
    INIT(0,"Initializing"),
    SCAN(1,"Scanning"),
    CONNECT(2,"Waiting connect"),
    CONNECT_NOW(3,"Power connect"),
    DISCOVER(4,"Examining"),
    CHECK_AUTH(5,"Checking Auth"),
    PREBOND(6,"Bond Prepare"),
    BOND(7,"Bonding"),
    UNBOND(8,"UnBonding"),
    RESET(9,"Reseting"),
    GET_DATA(10,"Getting Data"),
    CLOSE(11,"Sleeping"),
    CLOSED(12,"Deep Sleeping");


    private String str;
    private int stateId;
    /*Ob1State(String custom) {
        this.str = custom;
    }*/

    Ob1State(int id, String description) {
        this.str = description;
        this.stateId = id;
    }

    public String getString() {
        return str;
    }

    public int getStateId() {
        return this.stateId;
    }
}
