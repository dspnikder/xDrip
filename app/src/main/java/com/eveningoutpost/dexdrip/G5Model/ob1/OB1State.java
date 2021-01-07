package com.eveningoutpost.dexdrip.G5Model.ob1;

/**
 * Generally speaking a state machine should have
 * a singular mean to determine the current state
 * and the state transitions possible.
 */
public enum OB1State {
    NO_CONNECTION(),
    SCAN(),
    BOND(),
    PAIR(),
    AUTHENTICATE(),
    DISCONNECT(),
    DISCONNECTED(),
    SESSION();


    enum SessionStates {

    }
}
