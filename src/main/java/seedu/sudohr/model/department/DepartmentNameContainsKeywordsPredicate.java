package seedu.sudohr.model.department;

import java.util.List;
import java.util.function.Predicate;

import seedu.sudohr.commons.util.StringUtil;


/**
 * Tests that a {@code Department}'s {@code Name} matches any of the keywords given.
 */
public class DepartmentNameContainsKeywordsPredicate implements Predicate<Department> {

    private final List<String> keywords;

    public DepartmentNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Department department) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(department.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DepartmentNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((DepartmentNameContainsKeywordsPredicate) other).keywords)); // state check
    }



}
