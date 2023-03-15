package seedu.address.model.event;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.TreeSet;

import seedu.address.model.event.exceptions.EventNotFoundException;

/**
 * Represents the list of {@code RecurringEvent} that each {@code Person} has.
 */
public class RecurringEventList {

    private final TreeSet<RecurringEvent> recurringEvents;


    public RecurringEventList() {
        recurringEvents = new TreeSet<>();
    }

    public RecurringEventList(RecurringEventList toCopy) {
        this.recurringEvents = toCopy.getRecurringEvents();
    }

    public void insert(RecurringEvent newEvent) {
        this.recurringEvents.add(newEvent);
    }

    public TreeSet<RecurringEvent> getRecurringEvents() {
        return recurringEvents;
    }

    /**
     * Gets the total number of event in the recurringEvents
     * @return the size of the recurringEvents
     */
    public int getSize() {
        return recurringEvents.size();
    }

    /**
     * Get the recurring event in the recurring event list with the event's index.
     * @param index of the event.
     * @return recurringEventObject
     */
    public RecurringEvent getRecurringEvent(int index) {
        Iterator<RecurringEvent> it = recurringEvents.iterator();
        RecurringEvent recurringEvent = null;
        int counter = 0;
        while (it.hasNext()) {
            recurringEvent = it.next();
            if (counter == index) {
                break;
            }
            counter++;
        }
        return recurringEvent;
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

    public void deleteRecurringEvent(RecurringEvent event) {
        recurringEvents.remove(event);
    }

    /**
     * Edit recurring event parameters in the recurring event list
     * @param originalEvent to be edited
     * @param editedRecurringEvent to be edited to
     */
    public void edit(RecurringEvent originalEvent, RecurringEvent editedRecurringEvent) {
        if (!recurringEvents.contains(originalEvent)) {
            throw new EventNotFoundException();
        }
        recurringEvents.remove(originalEvent);
        recurringEvents.add(editedRecurringEvent);
    }

    /**
     * Checks if the other eventList has the exact same events as this event list
     * @param otherEventList to check if it is the same
     * @return true if it is the same and false if not the same
     */
    public boolean equals(RecurringEventList otherEventList) {
        if (getSize() != otherEventList.getSize()) {
            return true;
        }
        for (RecurringEvent re : otherEventList.getRecurringEvents()) {
            if (!contain(re)) {
                return false;
            }
        }
        return true;
    }

}
