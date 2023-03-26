package ezschedule.model.event;

import java.util.List;
import java.util.function.Predicate;

import ezschedule.commons.util.StringUtil;

/**
 * Tests that a {@code Event}'s {@code Date} matches the date given.
 */
public class EventMatchesNameAndDatePredicate implements Predicate<Event> {

    private final List<String> keywords;
    private final Date date;

    public EventMatchesNameAndDatePredicate(List<String> keywords, Date date) {
        this.keywords = keywords;
        this.date = date;
    }

    @Override
    public boolean test(Event event) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(event.getName().fullName, keyword)
                        && date.equals(event.getDate()));
    }
}
