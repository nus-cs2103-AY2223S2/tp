package ezschedule.slots;

/**
 * Represent a timeslot.
 */
public abstract class Slot {

    private String date;
    private String startTime;
    private String endTime;

    Slot(String date, String startTime, String endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public String getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

}
