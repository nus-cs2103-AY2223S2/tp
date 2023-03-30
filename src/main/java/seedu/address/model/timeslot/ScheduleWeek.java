package seedu.address.model.timeSlot;

import static java.util.Arrays.asList;

import java.time.DayOfWeek;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class to generate the entire week of schedule
 */
public class ScheduleWeek {
    private final int DAYS_IN_A_WEEK = 7;
    private static final ObservableList<ScheduleDay> internalList = FXCollections.observableArrayList();

    public void setInternalList(ArrayList<ArrayList<Integer>> timetable, DayOfWeek startDay) {
        internalList.clear();

        ArrayList<String> allDays = new ArrayList<>(asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
                "Saturday", "Sunday"));

        int zeroBasedStartIndex = startDay.getValue() - 1;
        for (int i = 0; i < DAYS_IN_A_WEEK; i++) {
            int currentDayIndex = (zeroBasedStartIndex + i) % DAYS_IN_A_WEEK;
            ScheduleDay scheduleDay = new ScheduleDay(allDays.get(currentDayIndex),
                    populateData(timetable.get(currentDayIndex)));
            internalList.add(scheduleDay);
        }

    }

    /**
     * Generating data for a single day. If the index matches with the index given in freeTimeSlots, this means that
     * the timeslot is marked as free. Otherwise, it can be assumed the timeslot is marked as busy
     * @param freeTimeSlots all index in which the status is free
     * @return a populated array of data in a single day
     */
    public ArrayList<Status> populateData(ArrayList<Integer> freeTimeSlots) {
        ArrayList<Status> timeSlot = new ArrayList<>();

        int count = 0;
        for (int i = 0; i < 24; i++) {
            int currFreeTimeSlotId = freeTimeSlots.get(count);
            if (currFreeTimeSlotId == i) {
                timeSlot.add(Status.FREE);
                if (count < freeTimeSlots.size() - 1) {
                    count++;
                }
            } else {
                timeSlot.add(Status.BUSY);
            }
        }

//        System.out.println(timeSlot.toString());
        return timeSlot;
    }

    public ObservableList<ScheduleDay> getInternalList() {
        return internalList;
    }

}
