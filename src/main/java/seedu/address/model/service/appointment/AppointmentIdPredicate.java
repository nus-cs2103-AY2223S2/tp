package seedu.address.model.service.appointment;

import seedu.address.commons.core.index.Index;
import seedu.address.model.service.Vehicle;

import java.util.function.Predicate;

/**
 * Tests that a {@code Appointment}'s id matches the id given.
 */
public class AppointmentIdPredicate implements Predicate<Appointment> {
    private final int id;

    public AppointmentIdPredicate(Index id) {
        this.id = id.getZeroBased();  // starts from 1 but getOne adds 1.
    }

    @Override
    public boolean test(Appointment appointment) {
        return appointment.getId() == id;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentIdPredicate // instanceof handles nulls
                && id == ((AppointmentIdPredicate) other).id); // state check
    }

}

