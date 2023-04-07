package seedu.address.model.time;

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

    /**
     * Constructor for ScheduleDay
     * @param time an array of status for each timeslot for the given day
     */
    public ScheduleDay(String day, ArrayList<Status> time) {
        this.day = day;
        this.time0 = time.get(0);
        this.time1 = time.get(1);
        this.time2 = time.get(2);
        this.time3 = time.get(3);
        this.time4 = time.get(4);
        this.time5 = time.get(5);
        this.time6 = time.get(6);
        this.time7 = time.get(7);
        this.time8 = time.get(8);
        this.time9 = time.get(9);
        this.time10 = time.get(10);
        this.time11 = time.get(11);
        this.time12 = time.get(12);
        this.time13 = time.get(13);
        this.time14 = time.get(14);
        this.time15 = time.get(15);
        this.time16 = time.get(16);
        this.time17 = time.get(17);
        this.time18 = time.get(18);
        this.time19 = time.get(19);
        this.time20 = time.get(20);
        this.time21 = time.get(21);
        this.time22 = time.get(22);
        this.time23 = time.get(23);

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

    public Status getTime5() {
        return time5;
    }

    public Status getTime6() {
        return time6;
    }

    public Status getTime7() {
        return time7;
    }

    public Status getTime8() {
        return time8;
    }

    public Status getTime9() {
        return time9;
    }

    public Status getTime10() {
        return time10;
    }

    public Status getTime11() {
        return time11;
    }

    public Status getTime12() {
        return time12;
    }

    public Status getTime13() {
        return time13;
    }

    public Status getTime14() {
        return time14;
    }

    public Status getTime15() {
        return time15;
    }

    public Status getTime16() {
        return time16;
    }

    public Status getTime17() {
        return time17;
    }

    public Status getTime18() {
        return time18;
    }

    public Status getTime19() {
        return time19;
    }

    public Status getTime20() {
        return time20;
    }

    public Status getTime21() {
        return time21;
    }

    public Status getTime22() {
        return time22;
    }

    public Status getTime23() {
        return time23;
    }

}
