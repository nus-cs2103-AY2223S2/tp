package seedu.internship.model.event;

import java.util.function.Predicate;

import seedu.internship.model.internship.Internship;

/**
 * Predicate filters to give you a List with one required event.
 */
public class EventByInternship implements Predicate<Event> {
    private final Internship intern;

    /**
     * Intialises Predicate with Internship.
     * @param intern Internship
     */
    public EventByInternship(Internship intern) {
        this.intern = intern;
    }

    @Override
    public boolean test(Event event) {
        return event.isSameInternship(this.intern);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { // short circuit if same object
            return true;
        }
        if (other instanceof EventByInternship) { // instanceof handles nulls
            EventByInternship otherPred = (EventByInternship) other;
            return otherPred.intern.equals(this.intern);
        }
        return false;
    }
}
