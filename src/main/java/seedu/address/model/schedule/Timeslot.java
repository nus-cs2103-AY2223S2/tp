package seedu.address.model.schedule;

public class Timeslot {
    private final boolean isBusy;

    public Timeslot(boolean isBusy) {
        this.isBusy = isBusy;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public Timeslot mergeTimeslot(Timeslot otherTimeslot) {
        return new Timeslot(isBusy || otherTimeslot.isBusy());
    }
}
