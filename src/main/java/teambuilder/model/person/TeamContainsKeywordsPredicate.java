package teambuilder.model.person;

import java.util.List;
import java.util.function.Predicate;

import teambuilder.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code teams} matches any of the keywords given.
 */
public class TeamContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public TeamContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> person.getTeams().stream()
                        .anyMatch(team -> StringUtil.containsWordIgnoreCase(team.tagName, keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TeamContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TeamContainsKeywordsPredicate) other).keywords)); // state check
    }
}
