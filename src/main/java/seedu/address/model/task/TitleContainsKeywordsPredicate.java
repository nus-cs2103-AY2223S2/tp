package seedu.address.model.task;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that an {@code Todo}'s {@code Title} and {@code JobTitle} matches any of the keywords given.
 */
public class TitleContainsKeywordsPredicate implements Predicate<InternshipTodo> {
    private final List<String> keywords;

    /**
     * Creates a {@code TitleContainsKeywordsPredicate} instance using the provided {@code keyword}.
     */
    public TitleContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(InternshipTodo todo) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        todo.getInternshipTitle().toString()
                                + " " + todo.getJobTitle().toString(),
                        keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TitleContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TitleContainsKeywordsPredicate) other).keywords)); // state check
    }

}
