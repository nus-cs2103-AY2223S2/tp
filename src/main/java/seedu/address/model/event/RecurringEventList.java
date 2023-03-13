package seedu.address.model.event;

import java.time.LocalDateTime;
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
        int count = 1;
        for (RecurringEvent re : recurringEvents) {
            output.append(count + ". " + re.getEventName()).append("\n");
            count++;
        }
        return output.toString();
    }

    public boolean contain(RecurringEvent event) {
        return recurringEvents.contains(event);
    }

    /**
     * Prints out a list of all event that occur within the given time period
     * @param startPeriod stand for the starting date of the time period
     * @param endPeriod stands for the ending date of the time period
     * @return a string of all events that occured within the time period
     */
    public String listBetweenOccurence(LocalDateTime startPeriod, LocalDateTime endPeriod) {
        StringBuilder output = new StringBuilder();
        for (RecurringEvent re : recurringEvents) {
            if (re.occursBetween(startPeriod, endPeriod)) {
                output.append(re).append("\n");
            }
        }
        return output.toString();
    }
}
