package tfifteenfour.clipboard.logic.predicates;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import tfifteenfour.clipboard.model.course.Group;

/**
 * Tests that a {@code Student}'s {@code Name} matches any of the keywords given.
 */
public class GroupNameContainsPredicate implements Predicate<Group> {
    private final List<String> keywords;

    public GroupNameContainsPredicate(String[] keywords) {
        this.keywords = Arrays.asList(keywords);
    }

    @Override
    public boolean test(Group group) {
        return keywords.stream()
                .anyMatch(keyword -> group.getGroupName().toLowerCase().contains(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GroupNameContainsPredicate // instanceof handles nulls
                && keywords.equals(((GroupNameContainsPredicate) other).keywords)); // state check
    }

}
