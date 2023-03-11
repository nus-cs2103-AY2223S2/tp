package seedu.address.model.event;

import java.util.TreeSet;

/**
 * Represents the list of {@code RecurringEvent} that each {@code Person} has.
 */
public class RecurringEventList {
    private final TreeSet<RecurringEvent> recurringEvents = new TreeSet<>();

    public void insert(RecurringEvent newEvent) {
        this.recurringEvents.add(newEvent);
    }
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (RecurringEvent re : recurringEvents) {
            output.append(re).append("\n");
        }
        return output.toString();
    }
}
