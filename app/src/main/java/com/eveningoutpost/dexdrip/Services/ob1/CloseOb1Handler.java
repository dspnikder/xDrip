package com.eveningoutpost.dexdrip.Services.ob1;

import java.util.List;

public class CloseOb1Handler extends AbstractOb1StateHandler  {
    protected CloseOb1Handler(Ob1State state, List<Ob1State> legalTransitions) {
        super(state, legalTransitions);
    }

    @Override
    public void execute() {

    }
}
