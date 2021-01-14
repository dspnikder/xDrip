package com.eveningoutpost.dexdrip.Services.ob1;

import com.eveningoutpost.dexdrip.UtilityModels.BroadcastGlucose;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InitStateChangeListener implements StateChangeListener{

    private boolean androidWear = false;
    private boolean wearBroadcast = false;


    @Override
    public void stateChanged(long timestamp, Ob1State newState) {
        if (androidWear && wearBroadcast) {
            BroadcastGlucose.sendLocalBroadcast(null);
        }
    }
}
