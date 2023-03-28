package seedu.address.ui.notification;

import java.util.TimerTask;

import javafx.application.Platform;

/**
 * Runs a {@code TimerTask} to notify the user of upcoming reminders
 */
public class BackgroundReminderTask extends TimerTask {
    private NotificationManager notificationManager;

    /**
     * Creates a {@code BackgroundReminderTask} with the given {@code NotificationManager}.
     * @param notificationManager
     */
    public BackgroundReminderTask(NotificationManager notificationManager) {
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
                notificationManager.checkReminderList();
            }
        });
    }
}
