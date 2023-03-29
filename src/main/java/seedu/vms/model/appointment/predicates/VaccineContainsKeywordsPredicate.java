package seedu.vms.model.appointment.predicates;

import java.util.Collections;
import java.util.function.Predicate;

import seedu.vms.commons.util.StringUtil;
import seedu.vms.model.GroupName;
import seedu.vms.model.appointment.Appointment;

/**
 * Tests that a {@code Appointment}'s {@code Vaccine} matches any of the keywords given.
 */
public class VaccineContainsKeywordsPredicate implements Predicate<Appointment> {
    private final String keyword;

    /**
     * Creates a Predicate that takes in groupNames that will be converted to a list of keywords to match
     *
     * @param groupName
     */
    public VaccineContainsKeywordsPredicate(GroupName groupName) {
        this.keyword = groupName.toString();
    }

    @Override
    public boolean test(Appointment appointment) {
        return StringUtil.isMatching(appointment.getVaccination().toString(), Collections.singleton(keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VaccineContainsKeywordsPredicate // instanceof handles nulls
                        && keyword.equals(((VaccineContainsKeywordsPredicate) other).keyword)); // state check
    }

}
