package ezschedule.model.event;

import java.util.List;
import java.util.function.Predicate;

import ezschedule.commons.util.StringUtil;

/**
 * Tests that a {@code Event}'s {@code Name} matches any of the keywords and {@code Date} matches the date given.
 */
public class EventMatchesKeywordsAndDatePredicate implements Predicate<Event> {

    private final List<String> keywords;
    private final Date date;

    /**
     * Constructor for EventMatchesKeywordsAndDatePredicate.
     */
    public EventMatchesKeywordsAndDatePredicate(List<String> keywords, Date date) {
        this.keywords = keywords;
        this.date = date;
    }

    @Override
    public boolean test(Event event) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(event.getName().fullName, keyword)
                        && date.equals(event.getDate()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventMatchesKeywordsAndDatePredicate // instanceof handles nulls
                && keywords.equals(((EventMatchesKeywordsAndDatePredicate) other).keywords) // state check
                && date.equals(((EventMatchesKeywordsAndDatePredicate) other).date)); // state check
    }
}
