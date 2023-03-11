package seedu.address.model.schedule;

import java.util.Arrays;

public class Schedule {
    private final DaySchedule[] daySchedules;

    public Schedule() {
        daySchedules = new DaySchedule[5];
    }

    public Schedule(DaySchedule[] daySchedules) {
        this.daySchedules = daySchedules;
    }

    public DaySchedule[] getDaySchedules() {
        return daySchedules;
    }

    public Schedule mergeSchedule(Schedule otherSchedule) {
        DaySchedule[] newDaySchedules = new DaySchedule[5];
        Arrays.setAll(newDaySchedules, i -> daySchedules[i].mergeDaySchedule(otherSchedule.daySchedules[i]));
        return new Schedule(newDaySchedules);
    }
}
