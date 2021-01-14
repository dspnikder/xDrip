package com.eveningoutpost.dexdrip.Services.ob1;

import com.eveningoutpost.dexdrip.Models.usererror.UserError;
import com.eveningoutpost.dexdrip.Models.usererror.UserErrorLog;

public abstract class LogAware {
    protected final static int DEBUG = 0;
    protected final static int INFO = 1;
    protected final static int WARN = 2;
    protected final static int ERROR = 3;
    protected final static int WHAT_THE_FUCK = 10;

    private String t = getTag();

    protected String getTag() {
        return getClass().getSimpleName();
    }

    protected void log(int level, String message) {
        switch(level) {
            case DEBUG:
                UserErrorLog.d(t, message);
                break;
            case INFO:
                UserErrorLog.i(t, message);
                break;
            case WARN:
                UserErrorLog.w(t, message);
                break;
            case ERROR:
                UserErrorLog.e(t, message);
                break;
            case WHAT_THE_FUCK:
                throw new IllegalArgumentException("Don't do this!");

        }
    }
    protected void log(int level, String message, Exception e) {
        switch(level) {
            case DEBUG:
                UserErrorLog.d(t, message);
                break;
            case INFO:
                UserErrorLog.i(t, message);
                break;
            case WARN:
                UserErrorLog.w(t, message, e);
                break;
            case ERROR:
                UserErrorLog.e(t, message,e);
                break;
            case WHAT_THE_FUCK:
                throw new IllegalArgumentException("Don't do this!");

        }
    }
}
