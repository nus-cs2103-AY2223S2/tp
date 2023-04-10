package seedu.address.model.patient;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Tests that a {@code Patient}'s {@code Name}, {@code Phone}, {@code Email}, {@code Address} or {@code Tag}s matches
 * any of the keywords given.
 */
public class DetailsContainKeywordsPredicate implements Predicate<Patient> {
    private final List<String> keywords;

    public DetailsContainKeywordsPredicate(List<String> keywords) {
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
            || (other instanceof DetailsContainKeywordsPredicate // instanceof handles nulls
            && keywords.equals(((DetailsContainKeywordsPredicate) other).keywords)); // state check
    }

}
