
package seedu.event.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.event.model.contact.Contact;
import seedu.event.model.contact.ContactName;
import seedu.event.model.contact.ContactPhone;
import seedu.event.model.event.Address;
import seedu.event.model.event.Event;
import seedu.event.model.event.Mark;
import seedu.event.model.event.Name;
import seedu.event.model.event.Rate;
import seedu.event.model.event.Time;
import seedu.event.model.tag.Tag;
import seedu.event.model.util.SampleDataUtil;

/**
 * A utility class to help with building Event objects.
 */
public class EventBuilder {

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
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        name = new Name(DEFAULT_NAME);
        rate = new Rate(DEFAULT_RATE);
        address = new Address(DEFAULT_ADDRESS);
        startTime = new Time(DEFAULT_START_TIME);
        endTime = new Time(DEFAULT_END_TIME);
        tags = new HashSet<>();
        contact = new Contact(new ContactName(DEFAULT_NAME), new ContactPhone(DEFAULT_CONTACTNUM));
    }

    /**
     * Initializes the EventBuilder with the data of {@code eventToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
        name = eventToCopy.getName();
        rate = eventToCopy.getRate();
        address = eventToCopy.getAddress();
        startTime = eventToCopy.getStartTime();
        endTime = eventToCopy.getEndTime();
        mark = eventToCopy.getMark();
        tags = new HashSet<>(eventToCopy.getTags());
        contact = eventToCopy.getContact();
    }

    /**
     * Sets the {@code Name} of the {@code Event} that we are building.
     */
    public EventBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Event} that we are building.
     */
    public EventBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Event} that we are building.
     */
    public EventBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Rate} of the {@code Event} that we are building.
     */
    public EventBuilder withRate(String rate) {
        this.rate = new Rate(rate);
        return this;
    }

    /**
     * Sets the start {@code Time} of the {@code Event} that we are building.
     */
    public EventBuilder withStartTime(String startTime) {
        this.startTime = new Time(startTime);
        return this;
    }

    /**
     * Sets the end {@code Time} of the {@code Event} that we are building.
     */
    public EventBuilder withEndTime(String endTime) {
        this.endTime = new Time(endTime);
        return this;
    }

    /**
     * Sets the {@code Mark} of the {@code Event} that we are building.
     */
    public EventBuilder withMark(String mark) {
        this.mark = new Mark();
        if (mark.equals("[X]")) {
            this.mark.setDone();
        }
        return this;
    }
    /**
     * Sets the {@code contact} of the {@code Event} that we are building.
     */
    public EventBuilder withContact(String name, String number) {
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
