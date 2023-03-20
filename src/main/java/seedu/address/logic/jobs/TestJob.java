package seedu.address.logic.jobs;

import java.util.TimerTask;

public class TestJob extends TimerTask {
    public static long FREQUENCY = 10;

    @Override
    public void run() {
        System.out.println("Hi see you after 10 seconds");
    }
}
