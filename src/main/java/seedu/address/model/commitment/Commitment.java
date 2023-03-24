package seedu.address.model.commitment;

import org.joda.time.LocalTime;

import seedu.address.model.location.Location;
import seedu.address.model.scheduler.time.Day;
import seedu.address.model.scheduler.time.HourBlock;
import seedu.address.model.scheduler.time.TimePeriod;

import java.util.ArrayList;
import java.util.Objects;

public class Commitment {
    protected final Location location;
    protected final TimePeriod timePeriod;

    public Commitment(Location location, TimePeriod timePeriod) {
        this.location = location;
        this.timePeriod = timePeriod;
    }

    public Location getLocation() {
        return location;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public Day getDay() {
        return timePeriod.getSchoolDay();
    }

    public LocalTime getStartTime() {
        return timePeriod.getStartTime();
    }

    public LocalTime getEndTime() {
        return timePeriod.getEndTime();
    }

    public boolean canFitIntoDaySchedule(ArrayList<HourBlock> slots) {
        boolean canFit = true;
        for (int i = getStartTime().getHourOfDay() - 8; i < getEndTime().getHourOfDay() - 8; i++) {
            canFit &= slots.get(i).isFree();
        }
        return canFit;
    }

    @Override
    public String toString() {
        return "Commitment{"
                + getDay()
                + ", " + getStartTime()
                + " to " + getEndTime()
                + " at " + location + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(timePeriod, location);
    }
}
