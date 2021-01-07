package com.eveningoutpost.dexdrip.G5Model.ob1;

import com.eveningoutpost.dexdrip.G5Model.Ob1G5StateMachine;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class InboundMessageFactory {

    private OB1State currentState;
    private final static InboundMessageFactory instance = new InboundMessageFactory();
    private InboundMessageFactory() {

    }

    private final static InboundMessageFactory build(OB1State state) {
        instance.currentState = state;
        return instance;
    }


}
