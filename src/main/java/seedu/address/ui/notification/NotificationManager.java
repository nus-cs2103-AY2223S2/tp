package seedu.address.ui.notification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

import org.controlsfx.control.Notifications;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.util.Duration;
import seedu.address.logic.Logic;
import seedu.address.model.Model;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.model.jobs.DeliveryList;
import seedu.address.model.jobs.sorters.SortbyTimeAndEarn;
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
        this.model = logic.getModel();
    }

    /**
     * Method to show Notification built from the default constructor
     */
    public void testNotification() {
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
                if (!r.getHasShown()) {
                    String des = (i + 1) + ". " + r.getDescription();
                    String remind = "Remind at: " + r.reminderDateTimeToString();
                    this.model.setHasShown(i, true);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            show(des, remind, Pos.TOP_RIGHT);
                        }
                    });
                }
            }
        }
    }

    private DeliveryList getDeliveryList() {
        this.model.updateFocusDate(LocalDate.now());
        this.model.updateSortedDeliveryJobList(new SortbyTimeAndEarn());
        this.model.updateSortedDeliveryJobListByDate();
        this.model.updateWeekDeliveryJobList(LocalDate.now());
        return this.model.getSortedDeliveryJobListByDate().get(LocalDate.now());
    }

    /**
     * Method to check the current schedule in the timetable for any ongoing jobs. Creates a notification should a job
     * exists.
     */
    public void checkNowSchedule() {
        DeliveryList deliveryList = getDeliveryList();
        List<DeliveryJob> jobList;
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        if (deliveryList != null) {
            switch (hour) {
            case 10:
                jobList = deliveryList.get(0);
                break;
            case 11:
                jobList = deliveryList.get(1);
                break;
            case 13:
                jobList = deliveryList.get(2);
                break;
            case 14:
                jobList = deliveryList.get(3);
                break;
            case 15:
                jobList = deliveryList.get(4);
                break;
            default:
                //nothing scheduled at the moment
                jobList = null;
            }
            if (jobList != null) {
                for (DeliveryJob d : jobList) {
                    String des = d.toString();
                    show("Current Job(s)!", des, Pos.TOP_LEFT);
                }
            }
        }
    }

    /**
     * Method to check if there are any upcoming jobs in the next scheduled slot in the timetable. Creates a
     * notification should an upcoming job exists.
     */
    public void checkNextSchedule() {
        DeliveryList deliveryList = getDeliveryList();
        List<DeliveryJob> jobList;
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        if (deliveryList != null) {
            switch (hour) {
            case 10:
                jobList = deliveryList.get(1);
                break;
            case 11:
                jobList = deliveryList.get(2);
                break;
            case 13:
                jobList = deliveryList.get(3);
                break;
            case 14:
                jobList = deliveryList.get(4);
                break;
            default:
                //nothing scheduled at the moment
                jobList = null;
            }
            if (hour < 10) {
                jobList = deliveryList.get(0);
            }
            if (jobList != null) {
                for (DeliveryJob d : jobList) {
                    String des = d.toString();
                    show("Upcoming Job(s)!", des, Pos.TOP_LEFT);
                }
            }
        }
    }

    /**
     * Method to show the Notification on particular side of screen.
     * @param title
     * @param text
     */
    public void show(String title, String text, Pos pos) {
        //show notifications
        Notifications notificationBuilder = Notifications.create()
                .title(title)
                .text(text)
                .hideAfter(duration)
                .position(pos);
        notificationBuilder.showConfirm();
    }
}
