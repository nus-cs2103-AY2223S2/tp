package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.model.person.Person;


/**
 * A list of events used by User.
 * We allow for duplicate events to be inputted for discretion of the user.
 * The removal of an event uses Event#equals(Object) to ensure that the event with exactly the same fields
 * will be removed.
 * Supports a minimal set of list operations.
 */
public class EventList implements Iterable<Event> {

    private final ObservableList<Event> internalList = FXCollections.observableArrayList();
    private final ObservableList<Event> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent event as the given argument.
     */
    public boolean contains(Event toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds an event to the list.
     * The event must not already exist in the list.
     */
    public void add(Event toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent event from the list.
     * The event must exist in the list.
     */
    public void remove(Event toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new EventNotFoundException();
        }
    }

    public void setEvents(EventList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    public void setEvents(List<Event> events) {
        requireAllNonNull(events);


        internalList.setAll(events);
    }

    public Event getEvent(Index index) {
        requireAllNonNull(index);

        int zeroBasedIndex = index.getZeroBased();

        if (zeroBasedIndex > this.internalList.size() - 1) {
            throw new EventNotFoundException();
        }
        return this.internalList.get(zeroBasedIndex);
    }

    public void setEvent(Index index, Event event) {
        requireAllNonNull(index, event);
        try {
            internalList.set(index.getZeroBased(), event);
        } catch (IndexOutOfBoundsException e) {
            throw new EventNotFoundException();
        }
    }


    /**
     * Removes person from all events in the event list.
     */
    public void deletePersonFromAllEvents(Person person) {
        internalList.setAll(internalList.stream()
                .map(event -> event.deleteTaggedPerson(person))
                .collect(Collectors.toList()));
    }

    /**
     * Edits events that have {@code personToEdit} tagged to the {@code editedPerson}.
     */
    public void editPersonForAllEvents(Person personToEdit, Person editedPerson) {
        internalList.setAll(internalList.stream()
                .map(event -> event.editTaggedPerson(personToEdit, editedPerson))
                .collect(Collectors.toList()));
    }

    /**
     * Checks if a person is tagged to an event.
     */
    public boolean isPersonTaggedToEvent(Index index, Person p) {
        requireAllNonNull(index, p);

        int zeroBasedIndex = index.getZeroBased();

        if (zeroBasedIndex > this.internalList.size() - 1) {
            throw new EventNotFoundException();
        }

        Event eventToTagged = this.internalList.get(zeroBasedIndex);

        return eventToTagged.hasTaggedPerson(p);
    }

    /**
     * Tags the given Person {@code taggingPerson} to the event at {@code index}.
     */
    public void tagPersonToEvent(Index index, Person taggingPerson) {
        requireAllNonNull(index, taggingPerson);
        Event eventCopy = getEvent(index).addTaggedPerson(taggingPerson);
        internalList.set(index.getZeroBased(), eventCopy);
    }

    /**
     * Untags the given Person {@code taggingPerson} from the event at {@code index}.
     */
    public void untagPersonToEvent(Index index, Person taggingPerson) {
        requireAllNonNull(index, taggingPerson);
        Event eventCopy = getEvent(index).deleteTaggedPerson(taggingPerson);
        internalList.set(index.getZeroBased(), eventCopy);
    }

    /**
     * Updates all events to their next earliest occurence.
     */
    public void updateAllDateTimes() {
        internalList.setAll(internalList.stream()
                .map(Event::updateDateTime)
                .collect(Collectors.toList()));
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Event> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Event> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventList // instanceof handles nulls
                && internalList.equals(((EventList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Event e: this.internalList) {
            sb.append(e.toString());
        }
        return sb.toString();
    }
}
