package seedu.address.model.employee;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Employee}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsAllKeywordsPredicate implements Predicate<Employee> {
    private final List<String> keywords;

    public NameContainsAllKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Employee employee) {
        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(employee.getName().fullName, keyword)
                        || StringUtil.containsFullWordIgnoreCase(employee.getDepartment().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsAllKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsAllKeywordsPredicate) other).keywords)); // state check
    }

}
