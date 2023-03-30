package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.event.exceptions.DuplicateEventException;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.PersonNotFoundException;


/**
 * A list of events that enforces uniqueness between its elements and does not allow nulls.
 * An event is considered unique by comparing using {@code Event#isSameEvent(Event)}. As such, adding and updating of
 * events uses Event#isSameEvent(Event) for equality to ensure that the event being added or updated is
 * unique in terms of identity in the UniqueEventList. However, the removal of an event uses Event#equals(Object)
 * to ensure that the event with exactly the same fields will be removed.
 * Supports a minimal set of list operations.
 *
 * @see Event#isSameEvent(Event)
 */
public class UniqueEventList implements Iterable<Event> {

    private final ObservableList<Event> internalList = FXCollections.observableArrayList();
    private final ObservableList<Event> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent event as the given argument.
     */
    public boolean contains(Event toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameEvent);
    }

    /**
     * Adds an event to the list.
     * The event must not already exist in the list.
     */
    public void add(Event toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateEventException();
        }
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

    public void setEvents(UniqueEventList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    public void setEvents(List<Event> events) {
        requireAllNonNull(events);
        if (!eventsAreUnique(events)) {
            throw new DuplicateEventException();
        }

        internalList.setAll(events);
    }

    public Event getEvent(Index index) {
        requireAllNonNull(index);

        int zeroBasedIndex = index.getZeroBased();

        if (zeroBasedIndex > this.internalList.size() - 1 || zeroBasedIndex < 0) {
            throw new PersonNotFoundException();
        }
        return this.internalList.get(zeroBasedIndex);
    }

    public void setEvent(Event oldEvent, Event newEvent) {
        this.internalList.remove(oldEvent);
        this.internalList.add(newEvent);
    }

    /**
     * Returns true if {@code events} contains only unique events.
     */
    private boolean eventsAreUnique(List<Event> events) {
        for (int i = 0; i < events.size() - 1; i++) {
            for (int j = i + 1; j < events.size(); j++) {
                if (events.get(i).isSameEvent(events.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Removes person from all events in the event list.
     */
    public void deletePersonFromAllEvents(Person target) {
        ArrayList<Event> toRemove = new ArrayList<>();
        ArrayList<Event> toAdd = new ArrayList<>();
        for (Event e: this.internalList) {
            if (e.hasTaggedPerson(target)) {
                Event newEvent = e.deleteTaggedPerson(target);
                toRemove.add(e);
                toAdd.add(newEvent);
            }
        }
        this.internalList.removeAll(toRemove);
        this.internalList.addAll(toAdd);
    }

    /**
     * Edits events that have {@code personToEdit} tagged to the {@code editedPerson}.
     */
    public void editPersonForAllEvents(Person personToEdit, Person editedPerson) {
        ArrayList<Event> toRemove = new ArrayList<>();
        ArrayList<Event> toAdd = new ArrayList<>();
        for (Event e: this.internalList) {
            if (e.hasTaggedPerson(personToEdit)) {
                Event newEvent = e.editTaggedPerson(personToEdit, editedPerson);
                toRemove.add(e);
                toAdd.add(newEvent);
            }
        }
        this.internalList.removeAll(toRemove);
        this.internalList.addAll(toAdd);
    }

    /**
     * Checks if a person is tagged to an event.
     */
    public boolean isPersonTaggedToEvent(Index index, Person p) {
        requireAllNonNull(index, p);

        int zeroBasedIndex = index.getZeroBased();

        if (zeroBasedIndex > this.internalList.size() - 1 || zeroBasedIndex < 0) {
            throw new PersonNotFoundException();
        }

        Event eventToTagged = this.internalList.get(zeroBasedIndex);

        return eventToTagged.hasTaggedPerson(p);
    }

    /**
     * Tags the given Person {@code taggingPerson} to the event at {@code index}.
     */
    public void tagPersonToEvent(Index index, Person taggingPerson) {
        Event event = this.getEvent(index);
        Event copy = event.copy();
        copy.getTaggedPeople().add(taggingPerson);
        this.internalList.remove(event);
        this.internalList.add(copy);
    }

    /**
     * Untags the given Person {@code taggingPerson} from the event at {@code index}.
     */
    public void untagPersonToEvent(Index index, Person taggingPerson) {
        Event event = this.getEvent(index);
        Event copy = event.copy();
        copy.getTaggedPeople().removeIf(p -> p.equals(taggingPerson));
        this.internalList.remove(event);
        this.internalList.add(copy);
    }

    /**
     * Updates all events to their next earliest occurence.
     */
    public void updateAllDateTimes() {
        ArrayList<Event> eventsReplacements = new ArrayList<>();
        for (int i = 0; i < this.internalList.size(); i++) {
            eventsReplacements.add(this.internalList.get(i).updateDateTime());
        }
        this.internalList.clear();
        this.internalList.addAll(eventsReplacements);
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
                || (other instanceof UniqueEventList // instanceof handles nulls
                && internalList.equals(((UniqueEventList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
