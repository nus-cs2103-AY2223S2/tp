
package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.contact.Contact;
import seedu.address.model.contact.ContactName;
import seedu.address.model.contact.ContactPhone;
import seedu.address.model.person.Address;
import seedu.address.model.person.Event;
import seedu.address.model.person.Mark;
import seedu.address.model.person.Name;
import seedu.address.model.person.Rate;
import seedu.address.model.person.Time;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_RATE = "85355255";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_START_TIME = "12-03-2023 11:00";
    public static final String DEFAULT_END_TIME = "12-03-2023 12:00";
    public static final String DEFAULT_CONTACTNUM = "91234567";

    private Name name;
    private Rate rate;
    private Address address;
    private Time startTime;
    private Time endTime;
    private Mark mark;
    private Set<Tag> tags;
    private Contact contact;


    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        rate = new Rate(DEFAULT_RATE);
        address = new Address(DEFAULT_ADDRESS);
        startTime = new Time(DEFAULT_START_TIME);
        endTime = new Time(DEFAULT_END_TIME);
        tags = new HashSet<>();
        contact = new Contact(new ContactName(DEFAULT_NAME), new ContactPhone(DEFAULT_CONTACTNUM));
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Event personToCopy) {
        name = personToCopy.getName();
        rate = personToCopy.getRate();
        address = personToCopy.getAddress();
        startTime = personToCopy.getStartTime();
        endTime = personToCopy.getEndTime();
        mark = personToCopy.getMark();
        tags = new HashSet<>(personToCopy.getTags());
        contact = personToCopy.getContact();
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
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Rate} of the {@code Person} that we are building.
     */
    public PersonBuilder withRate(String rate) {
        this.rate = new Rate(rate);
        return this;
    }

    /**
     * Sets the start {@code Time} of the {@code Event} that we are building.
     */
    public PersonBuilder withStartTime(String startTime) {
        this.startTime = new Time(startTime);
        return this;
    }

    /**
     * Sets the end {@code Time} of the {@code Event} that we are building.
     */
    public PersonBuilder withEndTime(String endTime) {
        this.endTime = new Time(endTime);
        return this;
    }

    /**
     * Sets the {@code Mark} of the {@code Event} that we are building.
     */
    public PersonBuilder withMark(String mark) {
        this.mark = new Mark();
        if (mark.equals("[X]")) {
            this.mark.setDone();
        }
        return this;
    }
    /**
     * Sets the {@code contact} of the {@code Event} that we are building.
     */
    public PersonBuilder withContact(String name, String number) {
        this.contact = new Contact(new ContactName(name), new ContactPhone(number));
        return this;
    }

    /**
     * Builds the event.
     * @return Event that is built.
     */
    public Event build() {
        Event event = new Event(name, rate, address, startTime, endTime, tags);
        if (!contact.equals(new Contact(new ContactName(DEFAULT_NAME), new ContactPhone(DEFAULT_CONTACTNUM)))) {
            event.linkContact(this.contact);
        }
        return event;
    }

}
