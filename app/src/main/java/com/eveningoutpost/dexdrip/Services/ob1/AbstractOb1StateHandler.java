package com.eveningoutpost.dexdrip.Services.ob1;

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
