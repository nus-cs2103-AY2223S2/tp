package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.model.event.Event;

/**
 * Tests if a {@code Person}'s {@code EventSet} contains target event.
 */
public class EventSetContainsEventPredicate implements Predicate<Person> {
    private final Event targetEvent;
    public EventSetContainsEventPredicate(Event targetEvent) {
        this.targetEvent = targetEvent;
    }

    @Override
    public boolean test(Person person) {
        return person.getEventSet().contains(targetEvent);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventSetContainsEventPredicate // instanceof handles nulls
                && targetEvent.equals(((EventSetContainsEventPredicate) other).targetEvent)); // state check
    }
}
