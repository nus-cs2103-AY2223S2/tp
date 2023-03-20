package seedu.patientist.model.person.patient;

import java.util.List;
import java.util.function.Predicate;

import seedu.patientist.model.person.Person;
import seedu.patientist.model.tag.Tag;

/**
 * THIS HAS BEEN DEPRECATED
 * Checks the tag of all personnel to see if they belong to a particular ward
 */
public class PatientInWardPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PatientInWardPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        if (!(person instanceof Patient)) {
            return false;
        }
        return keywords.stream()
                .anyMatch(keyword -> person.getTags().contains(new Tag(keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PatientInWardPredicate // instanceof handles nulls
                && keywords.equals(((PatientInWardPredicate) other).keywords));
    }
}
