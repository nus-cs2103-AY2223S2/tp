package seedu.address.model.timeSlot;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.event.IsolatedEvent;
import seedu.address.model.event.IsolatedEventList;
import seedu.address.model.event.RecurringEvent;

public class TimeMask {
    // One day 24 hours -> 24 bits
    // If 0.5 hour granularity is desired, required to split into two separate ints
    private static final int WINDOW_RANGE = 7;
    private static final int SLOTS_PER_DAY = 24;
    // 0's represent free slot
    private final int[] weeklyOccupancy;

    public TimeMask() {
        weeklyOccupancy = new int[]{0, 0, 0, 0, 0, 0, 0};
    }

    public int getDayMask(int dayIndex) {
        checkValidDayIndex(dayIndex);
        return weeklyOccupancy[dayIndex];
    }

    private void mergeSingleDay(int dayIndex, int dayOccupancy) {
        checkValidDayIndex(dayIndex);
        weeklyOccupancy[dayIndex] = weeklyOccupancy[dayIndex] | dayOccupancy;
    }

    public void mergeMask(TimeMask other) {
        if (other == null) {
            // TODO: refactor
            throw new RuntimeException("Empty time mask!");
        }
        for (int i =0; i < WINDOW_RANGE; i++) {
            mergeSingleDay(i, other.weeklyOccupancy[i]);
        }
    }

    public void mergeIsolatedEvents(IsolatedEventList isolatedEventList) {
        for (int i = 0; i < isolatedEventList.getSize(); i++) {
            IsolatedEvent event = isolatedEventList.getIsolatedEvent(i);

            int startIndex = event.getStartDayValue();
            int endIndex = event.getEndDayValue();
            int startTime = event.getStartDate().getHour();
            int endTime = event.getEndDate().getHour();

            if (startIndex != endIndex) {
                occupySlots(startIndex, startTime, endTime);
                occupySlots(endIndex, 0, endTime);
            } else {
                occupySlots(startIndex, startTime, endTime);
            }
        }
    }

    private void freeSlots(int dayIndex, int startHourIndex, int endHourIndex) {
        checkValidDayIndex(dayIndex);
        checkValidHourIndexes(startHourIndex, endHourIndex);

        // TODO: Check, requires JDK 11
        int startBits = Integer.parseInt("1".repeat(endHourIndex - startHourIndex + 1));
        int mask = ~(startBits << startHourIndex);

        weeklyOccupancy[dayIndex] = weeklyOccupancy[dayIndex] & mask;
    }

    private void occupySlots(int dayIndex, int startHourIndex, int endHourIndex) {
        checkValidDayIndex(dayIndex);
        checkValidHourIndexes(startHourIndex, endHourIndex);

        int startBits = Integer.parseInt("1".repeat(endHourIndex - startHourIndex + 1));
        int mask = startBits << startHourIndex;
        weeklyOccupancy[dayIndex] = weeklyOccupancy[dayIndex] | mask;
    }

    private void checkValidDayIndex(int dayIndex) {
        if (dayIndex < 0 || dayIndex > WINDOW_RANGE) {
            // TODO: refactor
            throw new RuntimeException("Invalid day index!");
        }
    }

    private void checkValidHourIndexes(int ...hourIndexes) {
        for (int hour : hourIndexes) {
            if (hour < 0 || hour > 24) {
                // TODO: refactor
                throw new RuntimeException("Invalid hour index!");
            }
        }
    }

    public void modifyOccupancy(RecurringEvent recurringEvent, boolean toOccupy) {
        int dayIndex = recurringEvent.getDayValue();
        int startHourIndex = recurringEvent.getStartTime().getHour();
        int endHourIndex = recurringEvent.getEndTime().getHour();
        if (toOccupy) {
            occupySlots(dayIndex, startHourIndex, endHourIndex);
        } else {
            freeSlots(dayIndex, startHourIndex, endHourIndex);
        }
    }

    public static ObservableList<String> getTimetable(DayOfWeek startDay, TimeMask timeMask) {
        ObservableList<String> linearTimetable = FXCollections.observableArrayList();
        for (int offset = 0; offset < WINDOW_RANGE; offset++) {
            int dayIndex = (startDay.getValue() + offset) % (WINDOW_RANGE);
            linearTimetable.addAll(getTimeSlots(timeMask.getDayMask(dayIndex), dayIndex));
        }
        return linearTimetable;
    }

    private static List<String> getTimeSlots(int mask, int dayIndex) {
        String prefix = "DAY " + dayIndex + ": ";
        ArrayList<String> slotsInDay = new ArrayList<>();
        int slots = 0;
        while (slots < SLOTS_PER_DAY) {
            if (mask % 2 == 0) {
                slotsInDay.add(prefix + slots + ":00 HOURS\n");
            }
            mask = mask >> 1;
            slots++;
        }
        return slotsInDay;
    }
}
