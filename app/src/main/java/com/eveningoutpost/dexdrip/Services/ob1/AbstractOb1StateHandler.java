package com.eveningoutpost.dexdrip.Services.ob1;

import com.eveningoutpost.dexdrip.Services.ob1.ble.AbstractScanFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * The abstract superclass of all the Ob1StateHandlers, in a generalized
 * solution this could be easily extensible to any protocol which has a
 * clear call-response state flow and even more dynamic ones that have
 * async sections that can then be spun off and declar a Sate as closed
 * or finalized or some other appropriate term.
 * A State is entered together with a information about the priorSate
 * which is logged
 */
public abstract class AbstractOb1StateHandler extends LogAware{
    //for now these constants live here as public static final flags but
    //should be ordinals or rather parameters in an enum eventually
    public final static long CLEAR_ALL = 0xFFFFFFFFFFFFFFFFL;
    public final static long READY = 0x1L;
    public final static long BLE_ADAPTER_AVAILABLE = 0x2L;
    public final static long BLE_ADAPTER_ENABLED = 0x4L;
    public final static long BLE_LOCATION_PERMISSION_ENABLED = 0x8L;
    public final static long PERIPHERAL_ID_KNOWN = 0x10;
    public final static long LAST_PERIPHERAL_MAC_KNOWN = 0x20;
    public final static long AVOID_SCAN = 0x40;
    public final static long DELAY_RESPONSE = 0x80;

    private static long STATE_FLAGS = 0x0L;

    private Ob1State state;
    private Ob1State prior;
    private List<Ob1State> transitations = new ArrayList<>();
    private List<StateChangeListener> listeners = new ArrayList<>();

    protected AbstractOb1StateHandler(Ob1State state, List<Ob1State> legalTransitions) {
        this.state = state;
        this.transitations = legalTransitions;
    }

    /**
     * Ensure that tranition is legal in the graph
     * @param nextState
     * @return
     */
    protected boolean isTransitionLegal(Ob1State nextState) {
        return this.transitations.contains(nextState);
    }

    public void enter(Ob1State priorState) {
        this.prior = priorState;
        long timestamp = System.currentTimeMillis();
        for(StateChangeListener l:listeners) {
            l.stateChanged(timestamp, state);
        }
        execute();
    }

    /**
     * Normally a noop but can be overridden as a transition control point
     */
    protected void checkPreconditions() {

    }

    public abstract void execute();

    protected void exit(Ob1State exitState) {
        if (transitations.contains(exitState)) {
            log(DEBUG, "Exiting " + state.toString());
            Ob1TransitionManager.get().getHandler(exitState).enter(state);
        }
    }

    public boolean isRootState() {
        return this.prior == this.state && this.state.getStateId() == 0;
    }

    static void toggle(long flag) {
        AbstractOb1StateHandler.STATE_FLAGS^=flag;
    }
    protected boolean is(long flag) {
        return (STATE_FLAGS&flag) > 0L;
    }
}
