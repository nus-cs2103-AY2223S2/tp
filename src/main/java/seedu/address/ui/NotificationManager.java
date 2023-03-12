package seedu.address.ui;

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

    private Logic logic;
    private Model model;
    private List<Reminder> reminderList;
    private String title = "";
    private String text = "";
    private Duration duration;
    private Pos position = Pos.TOP_RIGHT;

    private Notifications notification;

    /**
     * Constructor to create a Notification with specified input. Default constructor.
     * @param title Main title of the Notification
     * @param text Additional information for the Notification
     */
    public NotificationManager(String title, String text) {
        this.title = title;
        this.text = text;
    }

    /**
     * Constructor to create a Notification from data stored in Logic. Used for notifying reminders
     * @param logic
     */
    public NotificationManager(Logic logic) {
        this.logic = logic;
        this.reminderList = logic.getReminderList();
    }

    /**
     * Constructor to create a Notification from data stored in Model. Used for notifying reminders
     * @param model
     */
    public NotificationManager(Model model) {
        this.model = model;
        this.reminderList = model.getReminderList();
    }

    /**
     * Method to show Notification built from the default constructor
     */
    public void showDefault() {
        //show notifications
        Notifications notificationBuilder = Notifications.create()
                .title(title)
                .text(text)
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(position);
        notificationBuilder.showConfirm();
    }

    /**
     * Method to display Reminders at the start of the app.
     */
    public void checkReminders() {
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
     * Method for the "list_reminder" command
     */
    public void listReminders() {
        for (int i = 0; i < reminderList.size(); i++) {
            Reminder r = reminderList.get(i);
            String des = (i + 1) + ". " + r.getDescription();
            String remind = "Remind at: " + r.reminderDateTimeToString();
            show(des, remind);
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
                .graphic(null)
                .hideAfter(Duration.INDEFINITE)
                .position(position);
        notificationBuilder.showConfirm();
    }
}
