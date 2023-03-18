package seedu.address.model.service.appointment;

import java.util.function.Predicate;

/**
 * Tests that a {@code Appointment}'s id matches the id given.
 */
public class AppointmentIdPredicate implements Predicate<Appointment> {
    private final int id;

    public AppointmentIdPredicate(int id) {
        this.id = id;
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

