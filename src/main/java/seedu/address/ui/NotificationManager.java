package seedu.address.ui;

import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import seedu.address.logic.Logic;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.reminder.Reminder;

import java.time.LocalDateTime;
import java.util.List;

public class NotificationManager {

    private Logic logic;
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


    public void showReminders() {
        ReadOnlyAddressBook addressBook = logic.getAddressBook();
        List<Reminder> reminderList = addressBook.getReminderList();
        LocalDateTime now = LocalDateTime.now();
        for (int i = 0; i< reminderList.size();i++) {
            Reminder r = reminderList.get(i);
            if (now.isAfter(r.getReminderDateTime())) {
                String des = (i+1) + ". " + r.getDescription();
                String remind = "Remind at: " + r.reminderDateTimeToString();
                show(des, remind);
            }
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
