package seedu.address.model.event;

import java.util.Comparator;

import seedu.address.logic.parser.SortEventKey;
import seedu.address.model.event.exceptions.SortComparatorException;

/**
 * Contains comparators used when sorting Events in a list.
 */
public class EventComparator {
    private static final Comparator<Event> NAME_ASC_COMPARATOR = new Comparator<Event>() {
        @Override
        public int compare(Event o1, Event o2) {
            return o1.getName().toString().compareTo(o2.getName().toString());
        }
    };

    private static final Comparator<Event> NAME_DESC_COMPARATOR = new Comparator<Event>() {
        @Override
        public int compare(Event o1, Event o2) {
            return o2.getName().toString().compareTo(o1.getName().toString());
        }
    };

    private static final Comparator<Event> START_DATE_TIME_COMPARATOR = new Comparator<Event>() {
        @Override
        public int compare(Event o1, Event o2) {
            return o1.getStartDateTime().getDateTime().compareTo(o2.getStartDateTime().getDateTime());
        }
    };

    private static final Comparator<Event> END_DATE_TIME_COMPARATOR = new Comparator<Event>() {
        @Override
        public int compare(Event o1, Event o2) {
            return o1.getEndDateTime().getDateTime().compareTo(o2.getEndDateTime().getDateTime());
        }
    };

    public static final Comparator<Event> getComparator(SortEventKey sortEventKey) throws SortComparatorException {
        switch (sortEventKey) {
        case SORT_BY_NAME_ASC:
            return NAME_ASC_COMPARATOR;
        case SORT_BY_NAME_DESC:
            return NAME_DESC_COMPARATOR;
        case SORT_BY_START_DATE_TIME:
            return START_DATE_TIME_COMPARATOR;
        case SORT_BY_END_DATE_TIME:
            return END_DATE_TIME_COMPARATOR;
        default:
            throw new SortComparatorException();
        }
    }
}
