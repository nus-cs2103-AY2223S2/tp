package seedu.address.commons.core;

import java.util.Timer;
import java.util.TimerTask;

public class Cron {
    private static Timer timer;

    private Cron() {}

    public static void addTask(TimerTask task, long seconds) {
        if (timer == null) {
            timer = new Timer();
        }

        timer.scheduleAtFixedRate(task, 0, seconds * 1000);
    }

    public static void stop() {
        timer.cancel();
    }
}
