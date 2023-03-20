package seedu.patientist.model.person.patient;

import java.util.List;
import java.util.function.Predicate;

import seedu.patientist.commons.util.StringUtil;
import seedu.patientist.model.person.Person;
import seedu.patientist.model.tag.Tag;

/**
 * Tests that a {@code Patient}'s {@code Name} matches any of the keywords given.
 */
public class PatientNameContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PatientNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }
    @Override
    public boolean test(Person person) {
        if (!(person.getTags().contains(new Tag("Patient")))) {
            return false;
        }
        Patient patient = (Patient) person;

        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(patient.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
               || (other instanceof PatientNameContainsKeywordsPredicate // instanceof handles nulls
                   && keywords.equals(((PatientNameContainsKeywordsPredicate) other).keywords)); // state check
    }
}
