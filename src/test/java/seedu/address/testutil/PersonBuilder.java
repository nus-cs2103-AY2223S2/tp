package seedu.address.testutil;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.parser.DateTimeParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.note.Note;
import seedu.address.model.person.Address;
import seedu.address.model.person.ApplicationDateTime;
import seedu.address.model.person.Email;
import seedu.address.model.person.InterviewDateTime;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Status;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_STATUS = "APPLIED";
    public static final LocalDateTime DEFAULT_APPLICATIONDATETIME =
            LocalDateTime.of(2023, 02, 02, 14, 0);

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Status status;
    private ApplicationDateTime applicationDateTime;
    private Optional<InterviewDateTime> interviewDateTime;
    private Set<Note> notes;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        status = Status.valueOf(DEFAULT_STATUS);
        applicationDateTime = new ApplicationDateTime(DEFAULT_APPLICATIONDATETIME);
        interviewDateTime = Optional.empty();
        notes = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        status = personToCopy.getStatus();
        applicationDateTime = personToCopy.getApplicationDateTime();
        interviewDateTime = personToCopy.getInterviewDateTime();
        notes = new HashSet<>(personToCopy.getNotes());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code notes} into a {@code Set<Note>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withNotes(String ... notes) {
        this.notes = SampleDataUtil.getNoteSet(notes);
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
     * Sets the {@code Status} of the {@code Person} that we are building.
     */
    public PersonBuilder withStatus(String status) {
        this.status = Status.valueOf(status);
        return this;
    }

    /**
     * Sets the {@code InterviewDateTime} of the {@code Person} that we are building.
     */
    public PersonBuilder withInterviewDateTime(String interviewDateTime) {
        try {
            if (interviewDateTime.equals("")) {
                this.interviewDateTime = Optional.empty();
            } else {
                this.interviewDateTime = Optional.of(new InterviewDateTime(interviewDateTime));
            }
        } catch (ParseException e) {
            this.interviewDateTime = Optional.empty();
        }
        return this;
    }

    /**
     * Sets the {@code InterviewDateTime} of the {@code Person} that we are building.
     */
    public PersonBuilder withApplicationDateTime(String applicationDateTime) throws AssertionError {
        try {
            assert (!applicationDateTime.equals(""));
            this.applicationDateTime = new ApplicationDateTime(DateTimeParser.parseDateTime(applicationDateTime));
        } catch (ParseException e) {
            throw new AssertionError("There should never be a ParseException thrown here.");
        }
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, status, applicationDateTime, interviewDateTime, notes);
    }

}
