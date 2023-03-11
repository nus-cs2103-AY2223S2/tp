package seedu.address.model.schedule;

import java.util.List;

public class ScheduleUtil {


    public Schedule mergeSchedules(List<Schedule> schedules) {
        return schedules.stream().reduce(Schedule::mergeSchedule).orElse(new Schedule());
    }
}
