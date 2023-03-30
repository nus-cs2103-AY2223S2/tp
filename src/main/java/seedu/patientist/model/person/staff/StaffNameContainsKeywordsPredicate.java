package seedu.patientist.model.person.staff;

import java.util.List;
import java.util.function.Predicate;

import seedu.patientist.commons.util.StringUtil;
import seedu.patientist.model.person.Person;

/**
 * Tests that a {@code Patient}'s {@code Name} matches any of the keywords given.
 */
public class StaffNameContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public StaffNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }
    @Override
    public boolean test(Person person) {
        if (!(person instanceof Staff)) {
            return false;
        }
        Staff staff = (Staff) person;

        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(staff.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StaffNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((StaffNameContainsKeywordsPredicate) other).keywords)); // state check
    }
}
