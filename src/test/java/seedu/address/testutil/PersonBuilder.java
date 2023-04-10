package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import seedu.address.model.event.IsolatedEvent;
import seedu.address.model.event.IsolatedEventList;
import seedu.address.model.event.RecurringEvent;
import seedu.address.model.event.RecurringEventList;
import seedu.address.model.group.Group;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private Set<Group> groups;
    private Set<IsolatedEvent> isolatedEvents;
    private Set<RecurringEvent> recurringEvents;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        groups = new HashSet<>();
        isolatedEvents = new TreeSet<>();
        recurringEvents = new TreeSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
        groups = new HashSet<>(personToCopy.getGroups());
        isolatedEvents = new TreeSet<>(personToCopy.getIsolatedEventList().getSet());
        recurringEvents = new TreeSet<>(personToCopy.getRecurringEventList().getSet());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code groups} into a {@code Set<Group>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withGroups(String ... groups) {
        this.groups = SampleDataUtil.getGroupSet(groups);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code IsolatedEventList} of the {@code Person} that we are building.
     */
    public PersonBuilder withIsolatedEventList(Set<IsolatedEvent> isolatedEvents) {
        this.isolatedEvents = isolatedEvents;
        return this;
    }

    /**
     * Sets the {@code RecurringEventList} of the {@code Person} that we are building.
     */
    public PersonBuilder withRecurringEventList(Set<RecurringEvent> recurringEvents) {
        this.recurringEvents = recurringEvents;
        return this;
    }

    /**
     * Builds a person with the data entered into the {@code PersonBuilder}
     * @return A {@code Person} that has the details stored in the {@code PersonBuilder}.
     */
    public Person build() {
        IsolatedEventList isolatedEventList = new IsolatedEventList();
        RecurringEventList recurringEventList = new RecurringEventList();

        isolatedEventList.addAll(isolatedEvents);
        recurringEventList.addAll(recurringEvents);

        return new Person(name, phone, email, address, tags, groups, isolatedEventList, recurringEventList);
    }

}
