package seedu.address.model.timeslot;

import java.util.ArrayList;

/**
 * Represents the status of each timeslots of the day.
 */
public class ScheduleDay {

    private String day;
    private Status time0;
    private Status time1;
    private Status time2;
    private Status time3;
    private Status time4;
    private Status time5;
    private Status time6;
    private Status time7;
    private Status time8;
    private Status time9;
    private Status time10;
    private Status time11;
    private Status time12;
    private Status time13;
    private Status time14;
    private Status time15;
    private Status time16;
    private Status time17;
    private Status time18;
    private Status time19;
    private Status time20;
    private Status time21;
    private Status time22;
    private Status time23;

    private ArrayList<Status> timeSlots;

    /**
     * Constructor for ScheduleDay
     * @param time an array of status for each timeslot
     */
    public ScheduleDay(String day, ArrayList<Status> time) {
        this.day = day;
        this.time0 = time.get(0);
        this.time1 = time.get(1);
        this.time2 = time.get(2);
        this.time3 = time.get(3);
        this.time4 = time.get(4);

        this.timeSlots = time;
    }

    public String getDay() {
        return day;
    }

    public Status getTime0() {
        return time0;
    }

    public Status getTime1() {
        return time1;
    }

    public Status getTime2() {
        return time2;
    }

    public Status getTime3() {
        return time3;
    }

    public Status getTime4() {
        return time4;
    }

    public Status getTime(int id) {
        return timeSlots.get(id);
    }
}
