package seedu.address.model.time;

import static java.util.Arrays.asList;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class to generate the entire week of schedule
 */
public class ScheduleWeek {
    private static final int DAYS_IN_A_WEEK = 7;
    private static final ObservableList<ScheduleDay> internalList = FXCollections.observableArrayList();

    /**
     * Constructor for ScheduleWeek where cells will be filled with empty cells when first loaded
     */
    public ScheduleWeek() {
        internalList.clear();
        ArrayList<Status> emptyCell = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            emptyCell.add(Status.EMPTY);
        }

        internalList.add(new ScheduleDay("Monday", emptyCell));
        internalList.add(new ScheduleDay("Tuesday", emptyCell));
        internalList.add(new ScheduleDay("Wednesday", emptyCell));
        internalList.add(new ScheduleDay("Thursday", emptyCell));
        internalList.add(new ScheduleDay("Friday", emptyCell));
        internalList.add(new ScheduleDay("Saturday", emptyCell));
        internalList.add(new ScheduleDay("Sunday", emptyCell));

    }


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
        Set<Integer> freeSlots = new HashSet<>(freeTimeSlots);
        ArrayList<Status> timeSlot = new ArrayList<>();

        for (int i = 0; i < 24; i++) {
            if (freeSlots.contains(i)) {
                timeSlot.add(Status.FREE);
            } else {
                timeSlot.add(Status.BUSY);
            }
        }

        //  System.out.println(timeSlot.toString());
        return timeSlot;
    }

    public ObservableList<ScheduleDay> getInternalList() {
        return internalList;
    }

}
