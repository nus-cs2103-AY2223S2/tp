package seedu.address.model.employee;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Employee}'s {@code Name} matches all of the keywords given.
 */
public class NameContainsAllKeywordsPredicate implements Predicate<Employee> {
    private final List<String> keywords;

    /**
     * Creates a {@code NameContainsAllKeywordsPredicate} object and initialises {@code keywords}
     * with the inputted keywords.
     * @param keywords keywords inputted by the user.
     */
    public NameContainsAllKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Sets up the {@code Predicate} to check that {@code Employee}'s {@code Name} matches all of the keywords given.
     * @param employee the employee to be checked.
     * @return whether the condition is satisfied.
     */
    @Override
    public boolean test(Employee employee) {
        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(employee.getName().fullName, keyword)
                        || StringUtil.containsFullWordIgnoreCase(employee.getDepartment().value, keyword));
    }

    /**
     * Checks if two instances of {@code NameContainsAllKeywordsPredicate} are equal.
     * @param other the other instance.
     * @return a boolean value.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsAllKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsAllKeywordsPredicate) other).keywords)); // state check
    }

}
