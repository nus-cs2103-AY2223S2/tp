package ezschedule.model.event;

import java.util.List;
import java.util.function.Predicate;

import ezschedule.commons.util.StringUtil;

/**
 * Tests that an {@code Event}'s {@code Name} partially-matches any of the keywords given.
 */
public class EventContainsKeywordsPredicate implements Predicate<Event> {

    private final List<String> keywords;

    /**
     * Constructs a {@code EventContainsKeywordsPredicate}.
     *
     * @param keywords The given words to be matched with.
     */
    public EventContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Event event) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(event.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((EventContainsKeywordsPredicate) other).keywords)); // state check
    }
}
