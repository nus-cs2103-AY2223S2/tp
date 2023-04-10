package seedu.address.model.time;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

import seedu.address.model.event.IsolatedEvent;
import seedu.address.model.event.IsolatedEventList;
import seedu.address.model.event.RecurringEvent;

/**
 * Class to generate the bit masking for the events.
 */
public class TimeMask {
    // One day 24 hours -> 24 bits
    // If 0.5 hour granularity is desired, required to split into two separate ints
    private static final int WINDOW_RANGE = 7;
    private static final int SLOTS_PER_DAY = 24;
    private static final int LAST_HOUR_INDEX = 23;
    private static final int LAST_DAY_INDEX = 6;

    // 0's represent free slot
    private final int[] weeklyOccupancy;

    public TimeMask() {
        weeklyOccupancy = new int[]{0, 0, 0, 0, 0, 0, 0};
    }

    public TimeMask(int[] weeklyOccupancy) {
        this.weeklyOccupancy = weeklyOccupancy;
    }

    public int getDayMask(DayOfWeek dayOfWeek) {
        return weeklyOccupancy[getZeroBasedDayIndex(dayOfWeek)];
    }

    private void mergeSingleDay(DayOfWeek dayOfWeek, int dayOccupancy) {
        int dayIndex = getZeroBasedDayIndex(dayOfWeek);
        weeklyOccupancy[dayIndex] = weeklyOccupancy[dayIndex] | dayOccupancy;
    }

    /**
     * Compare the base time mask with a time mask and update accordingly.
     * @param other TimeMask object
     */
    public void mergeMask(TimeMask other) {
        if (other == null) {
            throw new RuntimeException("Empty time mask!");
        }
        for (int i = 0; i < WINDOW_RANGE; i++) {
            mergeSingleDay(getDayFromZeroBasedIndex(i), other.weeklyOccupancy[i]);
        }
    }

    /**
     * Compare the time mask with the isolated events and update the time mask.
     * @param isolatedEventList IsolatedEventList
     */
    public void mergeIsolatedEvents(IsolatedEventList isolatedEventList, LocalDate startDate) {
        LocalDate dateLimit = startDate.plusDays(WINDOW_RANGE - 1);

        for (int i = 0; i < isolatedEventList.getSize(); i++) {
            IsolatedEvent event = isolatedEventList.getIsolatedEvent(i);

            // Stop traversing if event occurs further than a week from the start date
            if (event.getStartDate().toLocalDate().isAfter(dateLimit)) {
                return;
            }

            if (startDate.isAfter(event.getEndDate().toLocalDate())) {
                continue;
            }

            LocalDate start = event.getStartDate().toLocalDate();
            LocalDate end = event.getEndDate().toLocalDate();

            int startIndex = getZeroBasedDayIndex(event.getStartDate().getDayOfWeek());
            int startTime = event.getStartDate().getHour();
            // Shouldn't occupy the next slot
            int endTime = event.getEndDate().getHour() - 1;

            if (endTime < 0) {
                endTime = LAST_HOUR_INDEX;
                end = end.minusDays(1);
            }

            if (!start.isEqual(end)) {
                // check if end date after date limit
                boolean isAfterDateLimit = isEndDateAfterDateLimit(end, dateLimit);
                // get the days between the start and end/ start and date limit
                long daysBetween = getDaysBetween(start, end, dateLimit, startDate);
                // if end date after date limit, need to change end time of event
                int modifiedEndTime = getUpdatedEndTime(endTime, isAfterDateLimit);
                // if user indicated a start date that is after the event start date, need to change the start index
                int modifiedStartIndex = start.isBefore(startDate) ? startDate.getDayOfWeek().getValue() - 1
                        : startIndex;
                occupyMultipleDay(modifiedStartIndex, daysBetween, startTime, modifiedEndTime,
                        startIndex == modifiedStartIndex);
            } else {
                occupySlots(startIndex, startTime, endTime);
            }
        }
    }

    private int getUpdatedEndTime(int endTime, boolean isAfterDateLimit) {
        if (isAfterDateLimit) {
            return LAST_HOUR_INDEX;
        } else {
            return endTime;
        }
    }

    private boolean isEndDateAfterDateLimit(LocalDate endDate, LocalDate dateLimit) {
        return endDate.isAfter(dateLimit);
    }

    private long getDaysBetween(LocalDate start, LocalDate end, LocalDate dateLimit, LocalDate startDate) {
        long daysBetween;

        if (start.isBefore(startDate)) {
            start = startDate;
        }
        if (end.isAfter(dateLimit)) {
            daysBetween = DAYS.between(start, dateLimit);
        } else {
            daysBetween = DAYS.between(start, end);
        }
        return daysBetween;
    }

    private void freeSlots(int dayIndex, int startHourIndex, int endHourIndex) {
        checkValidDayIndex(dayIndex);
        checkValidHourIndexes(startHourIndex, endHourIndex);

        int startBits = Integer.parseInt("1".repeat(endHourIndex - startHourIndex + 1), 2);
        int mask = ~(startBits << startHourIndex);

        weeklyOccupancy[dayIndex] = weeklyOccupancy[dayIndex] & mask;
    }

    private void occupySlots(int dayIndex, int startHourIndex, int endHourIndex) {
        checkValidDayIndex(dayIndex);
        checkValidHourIndexes(startHourIndex, endHourIndex);

        int startBits = Integer.parseInt("1".repeat(endHourIndex - startHourIndex + 1), 2);
        int mask = startBits << startHourIndex;
        weeklyOccupancy[dayIndex] = weeklyOccupancy[dayIndex] | mask;
    }

    private void occupyMultipleDay(int startDay, long diff, int startHour, int endHour, boolean isEqualStartIndex) {
        int curr = startDay;
        int counter = 1;
        int startBits;
        int mask;

        // only do this if it is starDay = event start date
        if (isEqualStartIndex) {
            // find free time slots for the start date of the event
            startBits = Integer.parseInt("1".repeat(23 - startHour + 1), 2);
            mask = startBits << startHour;
            weeklyOccupancy[curr] = weeklyOccupancy[curr] | mask;
            curr = curr == LAST_DAY_INDEX ? 0 : curr + 1;
        } else {
            counter = 0;
        }

        // filled the in between days as busy for every time slots
        while (counter < diff) {
            startBits = Integer.parseInt("1".repeat(24), 2);
            weeklyOccupancy[curr] = weeklyOccupancy[curr] | startBits;
            curr = curr == LAST_DAY_INDEX ? 0 : curr + 1;
            counter = counter + 1;
        }
        // find free time slots for the end date of the event
        startBits = Integer.parseInt("1".repeat(endHour + 1), 2);
        weeklyOccupancy[curr] = weeklyOccupancy[curr] | startBits;
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
                throw new RuntimeException("Invalid hour index!");
            }
        }
    }

    /**
     * Update the bits in the TimeMask object according to the recurring event.
     * @param recurringEvent RecurringEvent
     * @param toOccupy boolean
     */
    public void modifyOccupancy(RecurringEvent recurringEvent, boolean toOccupy) {
        int dayIndex = getZeroBasedDayIndex(recurringEvent.getDayOfWeek());
        int startHourIndex = recurringEvent.getStartTime().getHour();
        // Shouldn't occupy the next slot
        int endHourIndex = recurringEvent.getEndTime().getHour() - 1;

        if (endHourIndex < 0) {
            endHourIndex = LAST_HOUR_INDEX;
        }

        if (toOccupy) {
            occupySlots(dayIndex, startHourIndex, endHourIndex);
        } else {
            freeSlots(dayIndex, startHourIndex, endHourIndex);
        }
    }

    public static ArrayList<ArrayList<Integer>> getTimeSlotIndexes(TimeMask mask) {
        ArrayList<ArrayList<Integer>> twoDimensionalSlotList = new ArrayList<>();
        for (DayOfWeek day : DayOfWeek.values()) {
            int dayMask = mask.getDayMask(day);
            ArrayList<Integer> daySlots = new ArrayList<>();
            int slots = 0;
            while (slots < SLOTS_PER_DAY) {
                if (dayMask % 2 == 0) {
                    daySlots.add(slots);
                }
                dayMask = dayMask >> 1;
                slots++;
            }
            twoDimensionalSlotList.add(daySlots);
        }
        return twoDimensionalSlotList;
    }

    private int getZeroBasedDayIndex(DayOfWeek day) {
        return day.getValue() - 1;
    }

    private DayOfWeek getDayFromZeroBasedIndex(int dayIndex) {
        return DayOfWeek.of(dayIndex + 1);
    }

}
