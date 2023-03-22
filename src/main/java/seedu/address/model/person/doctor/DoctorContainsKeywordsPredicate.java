package seedu.address.model.person.doctor;

import java.util.function.Predicate;

/**
 * Tests that a {@code Doctor's fields} matches the {@code DoctorFilter} given.
 */
public class DoctorContainsKeywordsPredicate implements Predicate<Doctor> {

    private final DoctorFilter doctorFilter;

    public DoctorContainsKeywordsPredicate(DoctorFilter doctorFilter) {
        this.doctorFilter = doctorFilter;
    }

    @Override
    public boolean test(Doctor doctor) {
        return doctorFilter.isMatch((Doctor) doctor);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DoctorContainsKeywordsPredicate // instanceof handles nulls
                && doctorFilter.equals(((DoctorContainsKeywordsPredicate) other).doctorFilter)); // state check
    }

}
