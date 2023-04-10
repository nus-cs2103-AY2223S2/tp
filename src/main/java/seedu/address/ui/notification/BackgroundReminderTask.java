package seedu.address.ui.notification;

import java.util.TimerTask;

import javafx.application.Platform;

/**
 * Runs a {@code TimerTask} to notify the user of upcoming reminders
 */
public class BackgroundReminderTask extends TimerTask {
    private final NotificationManager notificationManager;

    /**
     * Creates a {@code BackgroundReminderTask} with the given {@code NotificationManager}.
     * @param notificationManager Notification Manager responsible for this app
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
        Platform.runLater(notificationManager::checkReminderList);
    }
}
