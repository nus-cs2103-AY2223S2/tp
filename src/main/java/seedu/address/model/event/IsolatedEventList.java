package seedu.address.model.event;

import java.util.TreeSet;

/**
 * Represents the list of {@code IsolatedEvent} that each {@code Person} has.
 */
public class IsolatedEventList {
    private final TreeSet<IsolatedEvent> isolatedEvents = new TreeSet<>();
    public void insert(IsolatedEvent newEvent) {
        this.isolatedEvents.add(newEvent);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (IsolatedEvent ie : isolatedEvents) {
            output.append(ie).append("\n");
        }
        return output.toString();
    }
}
