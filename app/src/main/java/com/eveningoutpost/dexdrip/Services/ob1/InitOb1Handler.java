package com.eveningoutpost.dexdrip.Services.ob1;

import com.eveningoutpost.dexdrip.UtilityModels.Pref;
import com.eveningoutpost.dexdrip.UtilityModels.RxBleProvider;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

import static com.eveningoutpost.dexdrip.Services.ob1.Ob1State.INIT;
import static com.eveningoutpost.dexdrip.Services.ob1.Ob1State.SCAN;

/**
 * InitOb1Handler is the s0 of the finite state machine representing the
 * high level interaction with G5 and G6 series devices. It ensures
 * that the CONNECTION_STATE is null, retrieves the (singleton) instance
 * of the RxBleClient and sets any configured TransmitterId
 * @see AbstractOb1StateHandler
 */
public class InitOb1Handler extends AbstractOb1StateHandler {
    protected InitOb1Handler() {
        super(INIT, Lists.newArrayList(SCAN));
    }

    @Override
    public void execute() {

        Ob1TransitionManager.get().unset(Ob1TransitionManager.CONNECTION_STATE);
        Ob1TransitionManager.get().getRxBleClient();
        Ob1TransitionManager.get().setTransmitterId(Pref.getString("dex_txid", "NULL"));
        exit(SCAN);
    }
}
