package seedu.address.ui.notification;

import java.util.TimerTask;

import javafx.application.Platform;

/**
 * Runs a {@code TimerTask} to notify the user of upcoming schedules
 */
public class BackgroundScheduleTask extends TimerTask {
    private NotificationManager notificationManager;

    /**
     * Creates a {@code BackgroundScheduleTask} with the given {@code NotificationManager}.
     * @param notificationManager
     */
    public BackgroundScheduleTask(NotificationManager notificationManager) {
        this.notificationManager = notificationManager;
    }

    /**
     * Changes the focus of the app to allow the notification to pop up even when the app is running in the
     * background
     */
    @Override
    public void run() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                notificationManager.checkNextSchedule();
            }
        });
    }
}
