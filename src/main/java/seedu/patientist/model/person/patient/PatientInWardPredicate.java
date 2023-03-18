package seedu.patientist.model.person.patient;

import java.util.function.Predicate;

import seedu.patientist.model.person.Person;
import seedu.patientist.model.tag.Tag;

/**
 * Checks the tag of all personnel to see if they belong to a particular ward
 */
public class PatientInWardPredicate implements Predicate<Person> {
    private final Tag ward;

    public PatientInWardPredicate(String ward1) {
        ward = new Tag(ward1);
    }

    @Override
    public boolean test(Person person) {
        return person.getTags().contains(ward);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PatientInWardPredicate); // instanceof handles nulls

    }
}
