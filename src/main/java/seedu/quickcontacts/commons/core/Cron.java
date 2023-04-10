package seedu.quickcontacts.commons.core;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Singleton CRON engine used for jobs.
 */
public class Cron {
    private static Cron engine;
    private static ScheduledExecutorService executorService;
    private static final int POOL_SIZE = 2;

    /**
     * Prevent multiple instantiation of CRON engines in the app.
     */
    private Cron() {
        if (executorService == null) {
            executorService = Executors.newScheduledThreadPool(POOL_SIZE);
        }
    }

    /**
     * Adds a job to the CRON to be executed per time interval.
     *
     * @param task Job to be executed by CRON engine.
     * @param seconds Time interval in seconds between every execution.
     */
    public void addTask(TimerTask task, long seconds) {
        addTask(task, seconds, 0);
    }

    /**
     * Adds a job to the CRON to be executed per time interval.
     *
     * @param task Job to be executed by CRON engine.
     * @param seconds Time interval in seconds between every execution.
     * @param initialDelay Number of seconds before executing the job.
     */
    public void addTask(TimerTask task, long seconds, long initialDelay) {
        executorService.scheduleAtFixedRate(task, initialDelay, seconds, TimeUnit.SECONDS);
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
     * Get number of tasks blocked in the CRON queue awaiting to be executed.
     *
     * @return Size of the blocked queue.
     */
    public int size() {
        return ((ThreadPoolExecutor) executorService).getQueue().size();
    }

    /**
     * Returns the state of the CRON engine.
     *
     * @return True if CRON engine is running, false otherwise.
     */
    public boolean isRunning() {
        if (executorService == null) {
            return false;
        }

        return !executorService.isShutdown();
    }

    /**
     * Stops and kills the CRON engine.
     */
    public void stop() {
        executorService.shutdown();
        engine = null;
        executorService = null;
    }
}
