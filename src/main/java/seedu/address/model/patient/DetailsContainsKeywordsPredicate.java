package seedu.address.model.patient;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Tests that a {@code Patient}'s {@code Name}, {@code Phone}, {@code Email}, {@code Address} or {@code Tag}s matches
 * any of the keywords given.
 */
public class DetailsContainsKeywordsPredicate implements Predicate<Patient> {
    private final List<String> keywords;

    public DetailsContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Patient patient) {
        List<String> patientDetails = patient.getDetailsAsList();
        patientDetails.retainAll(this.keywords.stream().map(String::toLowerCase).collect(Collectors.toList()));
        return patientDetails.size() > 0;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DetailsContainsKeywordsPredicate // instanceof handles nulls
            && keywords.equals(((DetailsContainsKeywordsPredicate) other).keywords)); // state check
    }

}
