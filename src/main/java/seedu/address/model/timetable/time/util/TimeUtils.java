package seedu.address.model.timetable.time.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.model.timetable.Timetable;
import seedu.address.model.timetable.time.SchoolDay;
import seedu.address.model.timetable.time.TimeBlock;
import seedu.address.model.timetable.time.TimePeriod;
import seedu.address.model.timetable.time.TimeSlot;


/**
 * Contains utils functions for Timetable related jobs.
 */
public class TimeUtils {
    /**
     * Gets a list of free intervals for which a participant is free for the specific school day.
     */
    public static List<TimeSlot> getFreeCommonIntervals(SchoolDay schoolDay, List<Timetable> schedules) {
        List<ArrayList<TimeSlot>> daySchedules = obtainAllSchedulesForDay(schoolDay, schedules);
        if (daySchedules.isEmpty()) {
            return new ArrayList<>();
        }
        List<TimeSlot> commonFreeTimeSlots = new ArrayList<>();
        int numberOfTimeSlots = daySchedules.get(0).size();
        for (int i = 0; i < numberOfTimeSlots; i++) {
            boolean isFreeForAll = true;
            for (ArrayList<TimeSlot> daySchedule : daySchedules) {
                isFreeForAll &= daySchedule.get(i).isFree();
            }
            if (isFreeForAll) {
                TimeSlot sampleSlot = daySchedules.get(daySchedules.size() - 1).get(i);
                commonFreeTimeSlots.add(new TimeSlot(sampleSlot));
            }
        }
        return commonFreeTimeSlots;
    }

    /**
     * Retrieves all row schedules from all timetables for a specific day
     */
    public static List<ArrayList<TimeSlot>> obtainAllSchedulesForDay(SchoolDay schoolDay, List<Timetable> schedules) {
        return schedules.stream()
                .map(timetable -> timetable.getSchedule().get(schoolDay))
                .collect(Collectors.toList());
    }

    /**
     * Reduces the number of TimePeriods by merging consecutive time slots.
     */
    public static List<TimePeriod> mergeTimeSlots(List<TimeSlot> timeSlots) {
        if (timeSlots.isEmpty()) {
            return new ArrayList<>();
        }
        TimePeriod block = null;
        List<TimePeriod> timePeriods = new ArrayList<>();
        for (int i = 0; i < timeSlots.size(); i++) {
            TimeSlot timeSlot = timeSlots.get(i);
            if (i == 0) {
                block = timeSlot;
            } else if (timeSlot.isConsecutiveWith(block)) {
                block = block.merge(timeSlot);
            } else {
                timePeriods.add(new TimeBlock(block));
                block = timeSlot;
            }
        }
        if (block != null) {
            timePeriods.add(new TimeBlock(block));
        }
        return timePeriods;
    }
}
