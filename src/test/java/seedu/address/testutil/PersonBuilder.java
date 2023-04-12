package seedu.address.testutil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Meeting;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.PolicyTag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final LocalDateTime DEFAULT_START = LocalDateTime.of(2023, 3, 25, 12, 0);
    public static final LocalDateTime DEFAULT_END = LocalDateTime.of(2023, 3, 25, 15, 0);
    public static final Meeting DEFAULT_MEETING = new Meeting("DEFAULT", DEFAULT_START, DEFAULT_END);

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private ArrayList<Meeting> meetings;
    private Set<PolicyTag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        meetings = new ArrayList<>();
        meetings.add(DEFAULT_MEETING);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        meetings = personToCopy.getMeetings();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<PolicyTag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String... tags) {
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
     * Sets the {@code Meeting} of the {@code Person} that we are building.
     */
    public PersonBuilder withMeeting(ArrayList<Meeting> meetings) {
        this.meetings = meetings;
        return this;
    }

    /**
     * Sets the {@code Meeting} of the {@code Person} that we are building
     * using String inputs
     */
    public PersonBuilder withMeetings(String dateTime) throws ParseException {
        if (dateTime.isEmpty()) {
            this.meetings.add(new Meeting());
        } else {
            String[] args = dateTime.trim().split(" ", 1);
            String desc = args[0];
            LocalDateTime start = ParserUtil.parseDateTime(args[1]);
            LocalDateTime end = ParserUtil.parseDateTime(args[2]);
            Meeting meetingToAdd = new Meeting(desc, start, end);
            this.meetings.add(meetingToAdd);
        }

        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, tags, meetings);
    }
}
