package seedu.address.ui.notification;

import java.time.LocalDateTime;
import java.util.List;

import org.controlsfx.control.Notifications;

import javafx.geometry.Pos;
import javafx.util.Duration;
import seedu.address.logic.Logic;
import seedu.address.model.Model;
import seedu.address.model.reminder.Reminder;

/**
 * Provides basic functionality for using the Notification function
 */
public class NotificationManager {
    //initialisation
    private Logic logic;
    private Model model;

    //notification settings
    private Duration duration = Duration.INDEFINITE;
    private Pos position = Pos.TOP_RIGHT;


    /**
     * Constructor to create a Notification.
     */
    public NotificationManager() {
    }

    /**
     * Constructor to create a Notification from data stored in Logic. Used for notifying reminders
     * @param logic
     */
    public NotificationManager(Logic logic) {
        this.logic = logic;
    }

    /**
     * Constructor to create a Notification from data stored in Model. Used for notifying reminders
     * @param model
     */
    public NotificationManager(Model model) {
        this.model = model;
    }

    public void applySettings() {

    }

    /**
     * Method to show Notification built from the default constructor
     */
    public void testNotificatoin() {
        //show notifications
        Notifications notificationBuilder = Notifications.create()
                .title("Test title")
                .text("Test text")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(position);
        notificationBuilder.showConfirm();
    }

    /**
     * Method to display Reminders at the start of the app.
     */
    public void checkReminderList() {
        List<Reminder> reminderList = this.logic.getReminderList();
        LocalDateTime now = LocalDateTime.now();
        for (int i = 0; i < reminderList.size(); i++) {
            Reminder r = reminderList.get(i);
            if (now.isAfter(r.getReminderDateTime())) {
                String des = (i + 1) + ". " + r.getDescription();
                String remind = "Remind at: " + r.reminderDateTimeToString();
                show(des, remind);
            }
        }
    }

    /**
     * Method to show the Notification. Used for notifying reminders
     * @param title
     * @param text
     */
    public void show(String title, String text) {
        //show notifications
        Notifications notificationBuilder = Notifications.create()
                .title(title)
                .text(text)
                .hideAfter(duration)
                .position(position);
        notificationBuilder.showConfirm();
    }
}
