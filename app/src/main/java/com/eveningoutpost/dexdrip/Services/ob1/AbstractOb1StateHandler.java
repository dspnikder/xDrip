package com.eveningoutpost.dexdrip.Services.ob1;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractOb1StateHandler extends LogAware{
    private Ob1State state;
    private Ob1State prior;
    private List<Ob1State> transitations = new ArrayList<>();
    private List<StateChangeListener> listeners = new ArrayList<>();

    public void enter(Ob1State priorState) {
        this.prior = priorState;
        long timestamp = System.currentTimeMillis();
        for(StateChangeListener l:listeners) {
            l.stateChanged(timestamp, state);
        }
        execute();
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
}
