package seedu.vms.model.appointment.predicates;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import seedu.vms.model.appointment.Appointment;

/**
 * Tests that the given time is not after the {@code Appointment}'s starting time.
 */
public class StartTimePredicate implements Predicate<Appointment> {
    private final LocalDateTime startTime;

    public StartTimePredicate(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    @Override
    public boolean test(Appointment appointment) {
        return !startTime.isAfter(appointment.getAppointmentTime());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StartTimePredicate // instanceof handles nulls
                        && startTime.equals(((StartTimePredicate) other).startTime)); // state check
    }

}
