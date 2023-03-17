package seedu.patientist.model.person.staff;

import static seedu.patientist.model.person.staff.Staff.STAFF_TAG;

import java.util.function.Predicate;

import seedu.patientist.model.person.Person;

/**
 * Tests that a {@code Person} is a {@code Staff} member.
 */
public class IsStaffPredicate implements Predicate<Person> {
    @Override
    public boolean test(Person person) {
        return person.getTags().contains(STAFF_TAG);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IsStaffPredicate); // instanceof handles nulls
    }
}
