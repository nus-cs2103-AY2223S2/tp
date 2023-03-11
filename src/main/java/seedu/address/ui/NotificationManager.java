package seedu.address.ui;

import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import seedu.address.logic.Logic;
import seedu.address.model.Model;
import seedu.address.model.reminder.Reminder;

import java.time.LocalDateTime;
import java.util.List;

public class NotificationManager {

    private Logic logic;
    private Model model;
    private List<Reminder> reminderList;
    private String title = "";
    private String text = "";
    private Duration duration;
    private Pos position = Pos.TOP_RIGHT;

    private Notifications notification;

    public NotificationManager(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public NotificationManager(Logic logic) {
        this.logic = logic;
        this.reminderList = logic.getReminderList();
    }

    public NotificationManager(Model model) {
        this.model = model;
        this.reminderList = model.getReminderList();
    }

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

    public void listReminders() {
        for (int i = 0; i < reminderList.size(); i++) {
            Reminder r = reminderList.get(i);
            String des = (i + 1) + ". " + r.getDescription();
            String remind = "Remind at: " + r.reminderDateTimeToString();
            show(des, remind);
        }
    }

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