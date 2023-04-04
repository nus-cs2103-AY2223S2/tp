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
    private final Logic logic;
    private final Model model;
    private final Runnable reminderWindow;
    private final Runnable timetableWindow;

    //notification settings
    private final Duration duration = Duration.INDEFINITE;

    //reminders
    private boolean isDismissed = false;

    /**
     * Constructor to create a Notification.
     * @param logic Stores information from the app
     * @param runnableList Required for respective notifications to open their corresponding windows.
     */
    public NotificationManager(Logic logic, List<Runnable> runnableList) {
        this.logic = logic;
        this.model = logic.getModel();
        this.reminderWindow = runnableList.get(0);
        this.timetableWindow = runnableList.get(1);
    }

    /**
     * Method to display Reminders at the start of the app.
     */
    public void checkReminderList() {
        List<Reminder> reminderList = this.logic.getReminderList();
        LocalDateTime now = LocalDateTime.now();
        int shownReminderCount = 0;
        int newReminderCount = 0;
        for (Reminder r : reminderList) {
            if (now.isAfter(r.getReminderDateTime())) {
                if (r.getHasShown()) {
                    shownReminderCount++;
                } else {
                    r.setHasShown(true);
                    newReminderCount++;
                }
            }
        }
        if (!isDismissed) {
            final int i = shownReminderCount + newReminderCount;
            Platform.runLater(() -> showReminderNotification(i));
        } else {
            if (newReminderCount > 0) {
                isDismissed = false;
                final int i = newReminderCount + shownReminderCount;
                Platform.runLater(() -> showReminderNotification(i));
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
                if (jobList.size() > 0) {
                    showScheduleNotification(jobList.size(), "now");
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
            case 12:
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
                if (jobList.size() > 0) {
                    showScheduleNotification(jobList.size(), "next");
                }
            }
        }
    }

    /**
     * Displays notification that shows users how many reminders that have at the moment
     * @param i Number of active reminders
     */
    public void showReminderNotification(int i) {
        Notifications notif = Notifications.create()
                .title("You have " + i + " active reminder(s)!")
                .text("Click here to snooze and view your list of Reminder(s)")
                .hideAfter(duration)
                .position(Pos.TOP_RIGHT)
                .hideCloseButton()
                .onAction(event -> {
                    isDismissed = true;
                    reminderWindow.run();
                });
        Calendar now = Calendar.getInstance();
        int t = 60 - now.get(Calendar.SECOND);
        notif.hideAfter(Duration.seconds(t));
        notif.showInformation();
    }

    /**
     * Displays notification that shows users how many scheduled jobs they have at the moment or in the next timetable
     * slot.
     * @param i Number of jobs
     * @param when indicates what string to show, depending on looking a current or next timetable slot
     */
    public void showScheduleNotification(int i, String when) {
        Calendar now = Calendar.getInstance();
        Notifications notif = Notifications.create();
        switch (when) {
        case "now":
            notif.title("You have " + i + " job(s) in this scheduled slot. (" + now.getTime() + ")");
            notif.hideAfter(Duration.minutes(60 - now.get(Calendar.MINUTE)));
            break;
        case "next":
            notif.title("You have " + i + " upcoming job(s) from " + nextSlotTime());
            double d;
            if (now.get(Calendar.MINUTE) >= 40) {
                d = 40 + (60 - now.get(Calendar.MINUTE));
            } else {
                d = 40 - now.get(Calendar.MINUTE);
            }
            notif.hideAfter(Duration.minutes(d));
            break;
        default:
            break;
        }
        notif.text("Click here to view more")
            .hideAfter(duration)
            .position(Pos.TOP_LEFT);
        notif.onAction(event -> timetableWindow.run());
        notif.showInformation();
    }
    private String nextSlotTime() {
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        if (hour < 10) {
            return "10:00 - 11:00";
        }
        switch (hour) {
        case 10:
            return "11:00 - 12:00";
        case 12:
            return "13:00 - 14:00";
        case 13:
            return "14:00 - 15:00";
        case 14:
            return "15:00 - 16:00";
        default:
            return "";
        }
    }
}
