package seedu.patientist.model.person.patient;

import java.util.List;
import java.util.function.Predicate;

import seedu.patientist.commons.util.StringUtil;
import seedu.patientist.model.person.Person;
import seedu.patientist.model.tag.Tag;

/**
 * Tests that a {@code Patient}'s {@code PatientIdNumber} matches any of the keywords given.
 */
public class PidContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PidContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        if (!(person.getTags().contains(new Tag("Patient")))) {
            return false;
        }
        Patient patient = (Patient) person;

        return keywords.stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(patient.getPatientIdNumber().getIdNumber(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof PidContainsKeywordsPredicate)
                && keywords.equals(((PidContainsKeywordsPredicate) other).keywords);
    }
}
