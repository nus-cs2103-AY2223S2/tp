package seedu.vms.model.appointment.predicates;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import seedu.vms.model.appointment.Appointment;

/**
 * Tests that the given time is not before the {@code Appointment}'s ending time.
 */
public class EndTimePredicate implements Predicate<Appointment> {
    private final LocalDateTime endTime;

    public EndTimePredicate(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean test(Appointment appointment) {
        return !endTime.isBefore(appointment.getAppointmentEndTime());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EndTimePredicate // instanceof handles nulls
                        && endTime.equals(((EndTimePredicate) other).endTime)); // state check
    }

}
