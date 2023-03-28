package tfifteenfour.clipboard.logic.predicates;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import tfifteenfour.clipboard.model.course.Session;

/**
 * Tests that a {@code Student}'s {@code Name} matches any of the keywords given.
 */
public class SessionNameContainsPredicate implements Predicate<Session> {
    private final List<String> keywords;

    public SessionNameContainsPredicate(String[] keywords) {
        this.keywords = Arrays.asList(keywords);
    }

    @Override
    public boolean test(Session session) {
        return keywords.stream()
                .anyMatch(keyword -> session.getSessionName().toLowerCase().contains(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SessionNameContainsPredicate // instanceof handles nulls
                && keywords.equals(((SessionNameContainsPredicate) other).keywords)); // state check
    }

}
