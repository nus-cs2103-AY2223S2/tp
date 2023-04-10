package teambuilder.testutil;

import java.util.HashSet;
import java.util.Set;

import teambuilder.model.person.Address;
import teambuilder.model.person.Email;
import teambuilder.model.person.Major;
import teambuilder.model.person.Name;
import teambuilder.model.person.Person;
import teambuilder.model.person.Phone;
import teambuilder.model.tag.Tag;
import teambuilder.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_MAJOR = "computer science";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Major major;
    private Set<Tag> tags;
    private Set<Tag> teams;


    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = Phone.of(DEFAULT_PHONE);
        email = Email.of(DEFAULT_EMAIL);
        address = Address.of(DEFAULT_ADDRESS);
        major = Major.of(DEFAULT_MAJOR);
        tags = new HashSet<>();
        teams = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        major = personToCopy.getMajor();
        tags = new HashSet<>(personToCopy.getTags());
        teams = new HashSet<>(personToCopy.getTeams());
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
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTeams(String ... tags) {
        this.teams = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = Address.of(address);
        return this;
    }

    /**
     * Sets the {@code Major} of the {@code Major} that we are building.
     */
    public PersonBuilder withMajor(String major) {
        this.major = Major.of(major);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = Phone.of(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = Email.of(email);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building to be empty Email.
     */
    public PersonBuilder withoutEmail() {
        this.email = Email.getEmptyEmail();
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building to be an emptyPhone.
     */
    public PersonBuilder withoutPhone() {
        this.phone = Phone.getEmptyPhone();
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, major, tags, teams);
    }
}
