package com.eveningoutpost.dexdrip.Services.ob1;

import com.eveningoutpost.dexdrip.Models.usererror.UserErrorLog;
import com.eveningoutpost.dexdrip.UtilityModels.RxBleProvider;
import com.polidea.rxandroidble2.RxBleClient;

import java.util.concurrent.ConcurrentHashMap;

import static com.eveningoutpost.dexdrip.Services.ob1.Ob1State.INIT;

public class Ob1TransitionManager extends LogAware {
    public static final String CONNECTION_STATE = "CONNECTION";
    public static final String CLIENT = "CLIENT";
    public static final String TRANSMITTER_ID = "TRANSMITTER_ID";
    public static final String TRANSMITTER_MAC = "TRANSMITTER_MAC";
    public static final String DEVICE_MAC = "DEVICE_MAC";
    public static final String LAST_CONNECTED = "LAST_CONNECTED";
    public static final String AVOID_SCANNING = "AVOID_SCANNING";
    private static Ob1TransitionManager manager = new Ob1TransitionManager();
    private String connectionState = null;
    private ConcurrentHashMap<String,Object> settings = new ConcurrentHashMap<>();
    private final static ConcurrentHashMap<Ob1State,AbstractOb1StateHandler> handlers = new ConcurrentHashMap<>();
    private Ob1TransitionManager() {
        log(INFO,"Ob1TransitionManager instantiated");
        settings.put(CONNECTION_STATE,0);
    }

    public static Ob1TransitionManager get() {
        return manager;
    }
    public void setConnectionState(Object value) {
        this.settings.put(CONNECTION_STATE,value);
    }
    public void setTransmitterId(String id) {
        this.settings.put(TRANSMITTER_ID,id);
    }

    public AbstractOb1StateHandler getHandler(Ob1State state) {
        if (handlers.get(state) == null) {
            switch(state) {
                case INIT:
                    handlers.put(INIT, new InitOb1Handler());
                    break;
                case SCAN:
                    handlers.put(state, new ScanOb1Handler());
                    break;
            }
        }
        return handlers.get(state);
    }


    public RxBleClient getRxBleClient() {
        if (this.settings.get(CLIENT) == null) {
            this.settings.put(CLIENT, RxBleProvider.getSingleton());
        }
        return (RxBleClient)this.settings.get(CLIENT);
    }

    public void set(String key) {
        settings.put(key,"1");
    }
    public boolean isSet(String key) {
        return settings.containsKey(key);
    }
    public void unset(String key) {
        settings.remove(key);
    }
}
