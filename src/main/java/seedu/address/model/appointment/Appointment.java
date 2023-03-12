package seedu.address.model.appointment;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.person.Name;

public class Appointment {
    private final Name name; // to update to NRIC
    private final Booking booking;
    // to add another Dr name field to link appmt to a doctor)
    public Appointment(Name name, Booking booking) {
        requireAllNonNull(name, booking);
        this.name = name;
        this.booking = booking;
    }

}
