package org.launchcode.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * Created by jeannie on 5/31/17.
 */
public class LogTime {

    @NotNull
    @Max(500)
    private int timeToLog;

    public LogTime() {

    }

    public int getTimeToLog() {
        return timeToLog;
    }

    public void setTimeToLog(int timeToLog) {
        this.timeToLog = timeToLog;
    }
}
