package com.eveningoutpost.dexdrip.Services.ob1;

import java.util.List;

public class PrebondOb1Handler extends AbstractOb1StateHandler  {
    protected PrebondOb1Handler(Ob1State state, List<Ob1State> legalTransitions) {
        super(state, legalTransitions);
    }

    @Override
    public void execute() {

    }
}
