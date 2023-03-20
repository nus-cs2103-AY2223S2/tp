package seedu.address.commons.core;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Singleton CRON engine used for jobs.
 */
public class Cron {
    private static Cron engine;
    private static ScheduledExecutorService executorService;

    /**
     * Prevent multiple instantiation of CRON engines in the app.
     */
    private Cron() {
        if (executorService == null) {
            executorService = Executors.newSingleThreadScheduledExecutor();
        }
    }

    /**
     * Adds a job to the CRON to be executed per time interval.
     *
     * @param task Job to be executed by CRON engine.
     * @param seconds Time interval in seconds between every execution.
     */
    public void addTask(TimerTask task, long seconds) {
        executorService.scheduleAtFixedRate(task, 0, seconds, TimeUnit.SECONDS);
    }

    /**
     * Gets an instance of {@code Cron}.
     *
     * @return The {@code Cron} instance.
     */
    public static Cron getInstance() {
        if (engine == null) {
            engine = new Cron();
        }

        return engine;
    }

    /**
     * Returns the state of the CRON engine.
     *
     * @return True if CRON engine is running, false otherwise.
     */
    public boolean isRunning() {
        return !executorService.isShutdown();
    }

    /**
     * Stops and kills the CRON engine.
     */
    public void stop() {
        executorService.shutdown();
    }
}
