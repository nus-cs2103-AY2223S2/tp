package seedu.address.model.schedule;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DaySchedule {
    private final Timeslot[] timeslots;

    public DaySchedule() {
        timeslots = new Timeslot[10];
    }

    public DaySchedule(Timeslot[] timeslots) {
        this.timeslots = timeslots;
    }

    public Timeslot[] getTimeslots() {
        return timeslots;
    }

    public boolean isBusy() {
        return Arrays.stream(timeslots).allMatch(Timeslot::isBusy);
    }

    public List<? extends Timeslot> getFreeTimeslots() {
        return Arrays.stream(timeslots).filter(t -> !t.isBusy()).collect(Collectors.toList());
    }

    public DaySchedule mergeDaySchedule(DaySchedule otherDaySchedule) {
        Timeslot[] newTimeslots = new Timeslot[10];
        Arrays.setAll(newTimeslots, i -> timeslots[i].mergeTimeslot(otherDaySchedule.timeslots[i]));
        return new DaySchedule(newTimeslots);
    }
}
