package seedu.address.model.event;

import java.util.Iterator;
import java.util.TreeSet;

import seedu.address.model.event.exceptions.EventNotFoundException;

/**
 * Represents the list of {@code IsolatedEvent} that each {@code Person} has.
 */
public class IsolatedEventList {
    private final TreeSet<IsolatedEvent> isolatedEvents = new TreeSet<>();

    public TreeSet<IsolatedEvent> getIsolatedEvents() {
        return isolatedEvents;
    }

    /**
     * Gets the total number of event in the isolatedEcent
     * @return the size of the isolatedEvents
     */
    public int getSize() {
        return isolatedEvents.size();
    }

    /**
     * Insert the isolated event object into the isolated event list.
     * @param newEvent to be inserted.
     */
    public void insert(IsolatedEvent newEvent) {
        this.isolatedEvents.add(newEvent);
    }

    /**
     * Check if the isolated event object is in the isolated event list.
     * @param event to be checked.
     * @return
     */
    public boolean contain(IsolatedEvent event) {
        return isolatedEvents.contains(event);
    }

    /**
     * Delete the isolated event from the isolated event list.
     * @param event to be deleted.
     */
    public void deleteIsolatedEvent(IsolatedEvent event) {
        isolatedEvents.remove(event);
    }

    /**
     * Get the isolated event in the isolated event list with the event's index.
     * @param index of the event.
     * @return IsolatedEventObject
     */
    public IsolatedEvent getIsolatedEvent(int index) {
        Iterator<IsolatedEvent> it = isolatedEvents.iterator();
        IsolatedEvent event = null;
        int counter = 0;
        while (it.hasNext()) {
            event = it.next();
            if (counter == index) {
                break;
            }
            counter++;
        }
        return event;
    }

    /**
     * Edit the current event in the isolated event list.
     * @param originalEvent to be edited.
     * @param editedEvent to be edited to.
     */
    public void edit(IsolatedEvent originalEvent, IsolatedEvent editedEvent) {
        if (!isolatedEvents.contains(originalEvent)) {
            throw new EventNotFoundException();
        }
        isolatedEvents.remove(originalEvent);
        isolatedEvents.add(editedEvent);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (IsolatedEvent ie : isolatedEvents) {
            output.append(ie.toString()).append("\n");
        }
        return output.toString();
    }

}
