package edu.isep.algoprog.util;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class TimeUtils {
    private ThreadMXBean threadMXBean;
    private long startTime;
    private long endTime;

    public TimeUtils() {
        this.threadMXBean = ManagementFactory.getThreadMXBean();
    }

    public void startTimer() {
        this.startTime = getCpuTime();
    }

    public void endTimer() {
        this.endTime = getCpuTime();
    }

    public long getDuration() {
        return endTime - startTime;
    }

    public long getCpuTime() {
        return threadMXBean.isCurrentThreadCpuTimeSupported() ? threadMXBean.getCurrentThreadCpuTime() : 0L;
    }
}
