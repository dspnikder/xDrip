package com.eveningoutpost.dexdrip.Services.ob1;

public class ScanOb1Handler extends AbstractOb1StateHandler {
    @Override
    public void execute() {
        if (Ob1TransitionManager.get().isSet(Ob1TransitionManager.AVOID_SCANNING)) {
            log(INFO,"Skipping Scanning! : Changing state due to minimize_scanning flags");
            exit(Ob1State.CONNECT_NOW);
        } else {

        }
    }
}
