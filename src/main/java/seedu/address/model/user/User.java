package seedu.address.model.user;


import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventList;
import seedu.address.model.person.Person;
import seedu.address.model.person.fields.Address;
import seedu.address.model.person.fields.CommunicationChannel;
import seedu.address.model.person.fields.Email;
import seedu.address.model.person.fields.Faculty;
import seedu.address.model.person.fields.Favorite;
import seedu.address.model.person.fields.Gender;
import seedu.address.model.person.fields.Major;
import seedu.address.model.person.fields.Modules;
import seedu.address.model.person.fields.Name;
import seedu.address.model.person.fields.Phone;
import seedu.address.model.person.fields.Race;
import seedu.address.model.person.fields.Tags;


/**
 * Store all relevant information about the User.
 */
public class User extends Person {
    private static final String DEFAULT_NAME = "Neo";

    private final EventList events;

    /**
     * Creates a new User by copying the fields of the Person passed in
     */
    public User(Person user, EventList events) {
        super(user.getName(),
                user.getPhone(),
                user.getEmail(),
                user.getAddress(),
                user.getGender(),
                user.getMajor(),
                user.getModules(),
                user.getRace(),
                user.getTags(),
                user.getComms(),
                user.getFavorite(),
                user.getFaculty());
        this.events = events;
    }

    /**
     * Every field must be present and not null. This constructor accepts a List of events.
     */
    public User(Name name, Phone phone, Email email, Address address, Gender gender,
                Major major, Modules modules, Race race, Tags tags, CommunicationChannel comms,
                Favorite favorite, Faculty faculty, List<Event> events) {
        super(name, phone, email, address, gender, major, modules, race, tags, comms, favorite, faculty);
        Objects.requireNonNull(events);
        this.events = new EventList();
        this.events.setEvents(events);
    }

    /**
     * Every field must be present and not null. This constructor accepts a UniqueEventList of events.
     */
    public User(Name name, Phone phone, Email email, Address address, Gender gender,
                Major major, Modules modules, Race race, Tags tags, CommunicationChannel comms,
                Favorite favorite, Faculty faculty, EventList events) {
        super(name, phone, email, address, gender, major, modules, race, tags, comms, favorite, faculty);
        Objects.requireNonNull(events);
        this.events = events;
    }

    /**
     * Constructor for an empty {@code User}.
     */
    public User() {
        super(new Name(DEFAULT_NAME));
        events = new EventList();
    }

    public boolean hasEvent(Event e) {
        return this.events.contains(e);
    }

    public void addEvent(Event e) {
        this.events.add(e);
    }

    public void deleteEvent(Event event) {
        this.events.remove(event);
    }

    public EventList getEvents() {
        return this.events;
    }

    public void deletePersonFromAllEvents(Person target) {
        this.events.deletePersonFromAllEvents(target);
    }

    public boolean isPersonTaggedToEvent(Index index, Person p) {
        return this.events.isPersonTaggedToEvent(index, p);
    }

    public void editPersonForAllEvents(Person personToEdit, Person editedPerson) {
        this.events.editPersonForAllEvents(personToEdit, editedPerson);
    }

    public Event getEvent(Index index) {
        return this.events.getEvent(index);
    }

    public void setEvent(Index index, Event event) {
        events.setEvent(index, event);
    }

    public void tagPersonToEvent(Index eventIndex, Person taggingPerson) {
        this.events.tagPersonToEvent(eventIndex, taggingPerson);
    }

    public void untagPersonToEvent(Index eventIndex, Person taggingPerson) {
        this.events.untagPersonToEvent(eventIndex, taggingPerson);
    }

    public void updateAllDateTimes() {
        this.events.updateAllDateTimes();
    }
}

