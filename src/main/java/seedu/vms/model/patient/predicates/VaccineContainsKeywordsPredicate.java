package seedu.vms.model.patient.predicates;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.vms.commons.util.StringUtil;
import seedu.vms.model.GroupName;
import seedu.vms.model.patient.Patient;

/**
 * Tests that a {@code Patient}'s {@code Vaccine} matches any of the keywords given.
 */
public class VaccineContainsKeywordsPredicate implements Predicate<Patient> {
    private final List<String> keywords;

    /**
     * Creates a Predicate that takes in groupNames that will be converted to a list of keywords to match
     *
     * @param groupNames
     */
    public VaccineContainsKeywordsPredicate(Set<GroupName> groupNames) {
        this.keywords = groupNames.stream().map(GroupName::toString).collect(Collectors.toList());
    }

    @Override
    public boolean test(Patient patient) {
        return patient.getVaccineAsString().stream()
                .anyMatch(vaccine -> StringUtil.isMatching(vaccine, keywords));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VaccineContainsKeywordsPredicate // instanceof handles nulls
                        && keywords.equals(((VaccineContainsKeywordsPredicate) other).keywords)); // state check
    }

}
