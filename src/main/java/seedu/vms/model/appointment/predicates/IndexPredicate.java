package seedu.vms.model.appointment.predicates;

import java.util.function.Predicate;

import seedu.vms.commons.core.index.Index;
import seedu.vms.model.appointment.Appointment;

/**
 * Tests that a {@code Appointment}'s patient id matches the patient id given.
 */
public class IndexPredicate implements Predicate<Appointment> {
    private final Index index;

    public IndexPredicate(Index index) {
        this.index = index;
    }

    @Override
    public boolean test(Appointment appointment) {
        return appointment.getPatient().equals(index);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IndexPredicate // instanceof handles nulls
                && index.equals(((IndexPredicate) other).index)); // state check
    }

}
