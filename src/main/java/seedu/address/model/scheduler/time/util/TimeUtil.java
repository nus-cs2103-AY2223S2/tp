package seedu.address.model.scheduler.time.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.LocalTime;

import seedu.address.model.scheduler.Timetable;
import seedu.address.model.scheduler.time.Day;
import seedu.address.model.scheduler.time.TimeBlock;
import seedu.address.model.scheduler.time.TimePeriod;
import seedu.address.model.scheduler.time.HourBlock;


/**
 * Contains utils functions for Timetable related jobs.
 */
public class TimeUtil {
    /**
     * Gets a list of free intervals for which a participant is free for the specific school day.
     */
    public static List<HourBlock> getFreeCommonIntervals(Day schoolDay, List<Timetable> schedules) {
        List<ArrayList<HourBlock>> daySchedules = obtainAllSchedulesForDay(schoolDay, schedules);
        if (daySchedules.isEmpty()) {
            return new ArrayList<>();
        }
        List<HourBlock> commonFreeHourBlocks = new ArrayList<>();
        int numberOfTimeSlots = daySchedules.get(0).size();
        for (int i = 0; i < numberOfTimeSlots; i++) {
            boolean isFreeForAll = true;
            for (ArrayList<HourBlock> daySchedule : daySchedules) {
                isFreeForAll &= daySchedule.get(i).isFree();
            }
            if (isFreeForAll) {
                HourBlock sampleSlot = daySchedules.get(daySchedules.size() - 1).get(i);
                commonFreeHourBlocks.add(new HourBlock(sampleSlot));
            }
        }
        return commonFreeHourBlocks;
    }

    /**
     * Retrieves all row schedules from all timetables for a specific day
     */
    public static List<ArrayList<HourBlock>> obtainAllSchedulesForDay(Day schoolDay, List<Timetable> schedules) {
        return schedules.stream()
                .map(timetable -> timetable.getSchedule().get(schoolDay))
                .collect(Collectors.toList());
    }

    /**
     * Reduces the number of TimePeriods by merging consecutive time slots.
     */
    public static List<TimePeriod> mergeTimeSlots(List<HourBlock> hourBlocks) {
        if (hourBlocks.isEmpty()) {
            return new ArrayList<>();
        }
        TimePeriod block = null;
        List<TimePeriod> timePeriods = new ArrayList<>();
        for (int i = 0; i < hourBlocks.size(); i++) {
            HourBlock hourBlock = hourBlocks.get(i);
            if (i == 0) {
                block = hourBlock;
            } else if (hourBlock.isConsecutiveWith(block)) {
                block = block.merge(hourBlock);
            } else {
                timePeriods.add(new TimeBlock(block));
                block = hourBlock;
            }
        }
        if (block != null) {
            timePeriods.add(new TimeBlock(block));
        }
        return timePeriods;
    }

    /**
     * Formats the LocalTime to a more reader-friendly format.
     * @param time LocalTime
     * @return Reader-friendly string format.
     */
    public static String formatLocalTime(LocalTime time) {
        int hourOfDay = time.getHourOfDay();
        if (hourOfDay < 12) {
            return String.format("%d AM", hourOfDay);
        } else if (hourOfDay == 12) {
            return "12 PM";
        } else {
            return String.format("%d PM", hourOfDay % 12);
        }
    }
}
