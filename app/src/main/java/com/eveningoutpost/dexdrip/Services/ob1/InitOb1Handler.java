package com.eveningoutpost.dexdrip.Services.ob1;

import com.eveningoutpost.dexdrip.UtilityModels.Pref;
import com.eveningoutpost.dexdrip.UtilityModels.RxBleProvider;

import static com.eveningoutpost.dexdrip.Services.ob1.Ob1State.SCAN;

public class InitOb1Handler extends AbstractOb1StateHandler {

    @Override
    public void execute() {

        Ob1TransitionManager.get().setConnectionState(0);
        Ob1TransitionManager.get().getRxBleClient();
        Ob1TransitionManager.get().setTransmitterId(Pref.getString("dex_txid", "NULL"));
        exit(SCAN);
    }
}
