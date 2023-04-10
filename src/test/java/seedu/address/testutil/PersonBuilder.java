package seedu.address.testutil;

import java.util.HashSet;

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
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_GENDER = "Male";
    public static final String DEFAULT_MAJOR = "Computer Science";
    public static final String DEFAULT_RACE = "Chinese";
    public static final String DEFAULT_COMMS = "Telegram";

    public static final String DEFAULT_FACULTY = "School Of Computing";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Tags tags;
    private Gender gender;
    private Major major;
    private Modules modules;
    private Race race;
    private CommunicationChannel comms;

    private Faculty faculty;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        this.name = new Name(DEFAULT_NAME);
        this.phone = new Phone(DEFAULT_PHONE);
        this.email = new Email(DEFAULT_EMAIL);
        this.address = new Address(DEFAULT_ADDRESS);
        this.tags = new Tags(new HashSet<>());
        this.gender = new Gender(DEFAULT_GENDER);
        this.major = new Major(DEFAULT_MAJOR);
        this.modules = new Modules(new HashSet<>());
        this.race = new Race(DEFAULT_RACE);
        this.comms = new CommunicationChannel(DEFAULT_COMMS);
        this.faculty = new Faculty(DEFAULT_FACULTY);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        this.name = personToCopy.getName();
        this.phone = personToCopy.getPhone();
        this.email = personToCopy.getEmail();
        this.address = personToCopy.getAddress();
        this.tags = personToCopy.getTags();
        this.gender = personToCopy.getGender();
        this.major = personToCopy.getMajor();
        this.modules = personToCopy.getModules();
        this.race = personToCopy.getRace();
        this.comms = personToCopy.getComms();
        this.faculty = personToCopy.getFaculty();
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
        this.tags = new Tags(SampleDataUtil.getTagSet(tags));
        return this;
    }

    /**
     * Parses the {@code mods} into a {@code Set<NusMod>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withModules(String ... mods) {
        this.modules = new Modules(SampleDataUtil.getModsSet(mods));
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
     * Sets the {@code Gender} of the {@code Person} that we are building.
     */
    public PersonBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }

    /**
     * Sets the {@code Major} of the {@code Person} that we are building.
     */
    public PersonBuilder withMajor(String major) {
        this.major = new Major(major);
        return this;
    }

    /**
     * Sets the {@code Race} of the {@code Person} that we are building.
     */
    public PersonBuilder withRace(String race) {
        this.race = new Race(race);
        return this;
    }

    /**
     * Sets the {@code Comms} of the {@code Person} that we are building.
     */
    public PersonBuilder withComms(String comms) {
        this.comms = new CommunicationChannel(comms);
        return this;
    }

    /**
     * Sets the {@code Faculty} of the {@code Person} that we are building.
     */
    public PersonBuilder withFaculty(String faculty) {
        this.faculty = new Faculty(faculty);
        return this;
    }

    /**
     * Builds the {@code person} object
     */
    public Person build() {
        return new Person(this.name, this.phone, this.email, this.address, this.gender,
                this.major, this.modules, this.race, this.tags, this.comms, null, this.faculty);
    }

}
