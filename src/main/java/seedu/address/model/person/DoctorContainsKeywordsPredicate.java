package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Doctor's fields} matches the {@code DoctorFilter} given.
 */
public class DoctorContainsKeywordsPredicate implements Predicate<Person> {

    private final DoctorFilter doctorFilter;

    public DoctorContainsKeywordsPredicate(DoctorFilter doctorFilter) {
        this.doctorFilter = doctorFilter;
    }

    @Override
    public boolean test(Person person) {
        return (person instanceof Doctor) && doctorFilter.isMatch((Doctor) person);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DoctorContainsKeywordsPredicate // instanceof handles nulls
                && doctorFilter.equals(((DoctorContainsKeywordsPredicate) other).doctorFilter)); // state check
    }
}
