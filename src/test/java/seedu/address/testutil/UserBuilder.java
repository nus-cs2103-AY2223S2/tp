package seedu.address.testutil;

import java.util.HashSet;

import seedu.address.model.event.Event;
import seedu.address.model.event.OneTimeEvent;
import seedu.address.model.event.RecurringEvent;
import seedu.address.model.event.EventList;
import seedu.address.model.event.fields.DateTime;
import seedu.address.model.event.fields.Description;
import seedu.address.model.event.fields.Recurrence;
import seedu.address.model.person.Person;
import seedu.address.model.person.fields.Address;
import seedu.address.model.person.fields.CommunicationChannel;
import seedu.address.model.person.fields.Email;
import seedu.address.model.person.fields.Faculty;
import seedu.address.model.person.fields.Gender;
import seedu.address.model.person.fields.Major;
import seedu.address.model.person.fields.Modules;
import seedu.address.model.person.fields.Name;
import seedu.address.model.person.fields.Phone;
import seedu.address.model.person.fields.Race;
import seedu.address.model.person.fields.Tags;
import seedu.address.model.user.User;
import seedu.address.model.util.SampleDataUtil;


/**
 * A utility class to help with building User objects.
 */
public class UserBuilder {
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_GENDER = "Male";
    public static final String DEFAULT_MAJOR = "Computer Science";
    public static final String DEFAULT_RACE = "Chinese";
    public static final String DEFAULT_COMMS = "Telegram";

    public static final String DEFAULT_FACULTY = "School Of Computing";

    protected Name name;
    protected Phone phone;
    protected Email email;
    protected Address address;
    protected Tags tags;
    protected Gender gender;
    protected Major major;
    protected Modules modules;
    protected Race race;
    protected CommunicationChannel comms;

    protected Faculty faculty;
    protected EventList events;

    /**
     * Creates a {@code UserBuilder} with the default details.
     */
    public UserBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new Tags(new HashSet<>());
        this.gender = new Gender(DEFAULT_GENDER);
        this.major = new Major(DEFAULT_MAJOR);
        this.modules = new Modules(new HashSet<>());
        this.race = new Race(DEFAULT_RACE);
        this.comms = new CommunicationChannel(DEFAULT_COMMS);
        this.faculty = new Faculty(DEFAULT_FACULTY);
        this.events = new EventList();
    }

    /**
     * Initializes the UserBuilder with the data of {@code userToCopy}.
     */
    public UserBuilder(User userToCopy) {
        name = userToCopy.getName();
        phone = userToCopy.getPhone();
        email = userToCopy.getEmail();
        address = userToCopy.getAddress();
        tags = userToCopy.getTags();
        this.gender = userToCopy.getGender();
        this.major = userToCopy.getMajor();
        this.modules = userToCopy.getModules();
        this.race = userToCopy.getRace();
        this.comms = userToCopy.getComms();
        this.faculty = userToCopy.getFaculty();
        this.events = userToCopy.getEvents();
    }

    /**
     * Initializes the UserBuilder with the data of {@code userToCopy}.
     */
    public UserBuilder(Person userToCopy) {
        name = userToCopy.getName();
        phone = userToCopy.getPhone();
        email = userToCopy.getEmail();
        address = userToCopy.getAddress();
        tags = userToCopy.getTags();
        this.gender = userToCopy.getGender();
        this.major = userToCopy.getMajor();
        this.modules = userToCopy.getModules();
        this.race = userToCopy.getRace();
        this.comms = userToCopy.getComms();
        this.faculty = userToCopy.getFaculty();
        this.events = new EventList();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public UserBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public UserBuilder withTags(String ... tags) {
        this.tags = new Tags(SampleDataUtil.getTagSet(tags));
        return this;
    }

    /**
     * Parses the {@code mods} into a {@code Set<NusMod>} and set it to the {@code Person} that we are building.
     */
    public UserBuilder withModules(String ... mods) {
        this.modules = new Modules(SampleDataUtil.getModsSet(mods));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public UserBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public UserBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public UserBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code Person} that we are building.
     */
    public UserBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }

    /**
     * Sets the {@code Major} of the {@code Person} that we are building.
     */
    public UserBuilder withMajor(String major) {
        this.major = new Major(major);
        return this;
    }

    /**
     * Sets the {@code Race} of the {@code Person} that we are building.
     */
    public UserBuilder withRace(String race) {
        this.race = new Race(race);
        return this;
    }

    /**
     * Sets the {@code Comms} of the {@code Person} that we are building.
     */
    public UserBuilder withComms(String comms) {
        this.comms = new CommunicationChannel(comms);
        return this;
    }

    /**
     * Sets the {@code Faculty} of the {@code Person} that we are building.
     */
    public UserBuilder withFaculty(String faculty) {
        this.faculty = new Faculty(faculty);
        return this;
    }

    /**
     * Adds an event with the {@code description}, {@code start}, {@code end} and {@code recurrence}
     * to the {@code User} that we are building.
     */
    public UserBuilder withEvents(Description description, DateTime start, DateTime end, Recurrence recurrence) {
        if (recurrence.isRecurring()) {
            this.events.add(new RecurringEvent(description, start, end, recurrence, new HashSet<>()));
        } else {
            this.events.add(new OneTimeEvent(description, start, end, new HashSet<>()));
        }
        return this;
    }

    /**
     * Adds the {@code Event} to the {@code User} that we are building.
     */
    public UserBuilder withEvents(Event event) {
        this.events.add(event);
        return this;
    }

    /**
     * Builds the {@code user} object
     */
    public User build() {
        return new User(new Person(this.name, this.phone, this.email, this.address, this.gender,
                this.major, this.modules, this.race, this.tags, this.comms, null, this.faculty),
                this.events);
    }
}
