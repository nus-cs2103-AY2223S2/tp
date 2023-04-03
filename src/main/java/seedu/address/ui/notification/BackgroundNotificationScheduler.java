package seedu.address.ui.notification;

import java.util.Calendar;
import java.util.Timer;

/**
 * Runs Java Timer to notify the user of upcoming jobs or reminders
 */
public class BackgroundNotificationScheduler extends Timer {
    private final NotificationManager notificationManager;

    /**
     * Creates a {@code BackgroundNotificationScheduler} with the given {@code NotificationManager}.
     * @param notificationManager Notification Manager responsible for this app
     */
    public BackgroundNotificationScheduler(NotificationManager notificationManager) {
        this.notificationManager = notificationManager;
    }

    /**
     * Create a scheduled task with {@code Timer}.
     * A {@code BackgroundReminderTask} is scheduled to check every minute
     */
    public void backgroundReminder() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, 1);
        now.set(Calendar.SECOND, 0);

        new Timer().schedule(new BackgroundReminderTask(notificationManager), now.getTime(), 1000 * 60);
    }

    /**
     * Create a scheduled task with {@code Timer}.
     * A {@code } is scheduled 20 minutes before the closet upcoming hour, and
     * every subsequent hour
     */
    public void backgroundSchedule() {
        Calendar now = Calendar.getInstance();
        if (now.get(Calendar.MINUTE) >= 40) {
            now.add(Calendar.HOUR_OF_DAY, 1);
        }
        now.set(Calendar.MINUTE, 40);
        now.set(Calendar.SECOND, 0);

        new Timer().schedule(new BackgroundScheduleTask(notificationManager), now.getTime(), 1000 * 60 * 60);
    }

    /**
     * Create a scheduled task with {@code Timer}.
     * A {@code BackgroundReminderTask} is scheduled from the closet upcoming hour, and
     * every subsequent hour
     */
    public void run() {
        backgroundReminder();
        backgroundSchedule();
    }

}
