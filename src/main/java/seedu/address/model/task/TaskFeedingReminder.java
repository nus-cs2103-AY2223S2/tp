package seedu.address.model.task;

import java.util.ArrayList;

import seedu.address.model.tank.Tank;

/**
 *  Represents a Feeding Reminder for the user.
 *  Automatically sets description to fish that have not been fed
 *  This should be instantiated only for tanks with fish that are not fed yet
 */
public class TaskFeedingReminder extends Task {
    /**
     * Constructor for when creating a new TaskFeedingReminder when hungry fish
     * detected
     * @param t {@code Tank} with hungry fish
     */
    public TaskFeedingReminder(Tank t) {
        super(new Description("Tank feeding reminder \n" + t.getUnfedFishesString()), t, new Priority("high"));
        this.isReminder = true;
    }

    /**
     * constructor for instantiating from Json memory
     * @param d {@code Descriptor}
     * @param t Tank associated with this reminder
     */
    public TaskFeedingReminder(Description d, Tank t) {
        super(d, t, new Priority("high"));
        this.isReminder = true;
    }

    /**
     * Returns a list of Feeding reminders
     * @param tanks Tanks which have hungry fishes
     * @return a list of Feeding reminders
     */
    public static ArrayList<TaskFeedingReminder> createListOfFeedingReminders(ArrayList<Tank> tanks) {
        ArrayList<TaskFeedingReminder> ret = new ArrayList<>();
        for (Tank t : tanks) {
            ret.add(new TaskFeedingReminder(t));
        }
        return ret;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Feeding Reminder for ");
        builder.append(getTank());

        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Task)) {
            return false;
        }
        if (other instanceof TaskFeedingReminder) {
            TaskFeedingReminder otherTask = (TaskFeedingReminder) other;
            return getTank().equals(otherTask.getTank());
        }
        return false;
    }
}
