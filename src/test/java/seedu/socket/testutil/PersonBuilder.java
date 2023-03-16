package seedu.socket.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.socket.model.person.Address;
import seedu.socket.model.person.Email;
import seedu.socket.model.person.GitHubProfile;
import seedu.socket.model.person.Name;
import seedu.socket.model.person.Person;
import seedu.socket.model.person.Phone;
import seedu.socket.model.tag.Language;
import seedu.socket.model.tag.Tag;
import seedu.socket.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PROFILE = "";
    public static final String DEFAULT_PHONE = "";
    public static final String DEFAULT_EMAIL = "";
    public static final String DEFAULT_ADDRESS = "";

    private Name name;
    private GitHubProfile profile;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Language> languages;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        profile = new GitHubProfile(DEFAULT_PROFILE);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        languages = new HashSet<>();
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        profile = personToCopy.getProfile();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        languages = new HashSet<>(personToCopy.getLanguages());
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
     * Sets the {@code Profile} of the {@code Person} that we are building.
     */
    public PersonBuilder withProfile(String profile) {
        this.profile = new GitHubProfile(profile);
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
     * Parses the {@code languages} into a {@code Set<Language>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withLanguages(String ... languages) {
        this.languages = SampleDataUtil.getLanguageSet(languages);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Person build() {
        return new Person(name, profile, phone, email, address, languages, tags);
    }

}
