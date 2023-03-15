package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.tag.GroupTag;
import seedu.address.model.tag.ModuleTag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amirah Tan";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amirahtan@gmail.com";
    public static final String DEFAULT_ADDRESS = "Tanah Merah";
    public static final String DEFAULT_TELEGRAM_HANDLE = "@amirahtan";
    public static final Integer DEFAULT_CONTACT_INDEX = 1;

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private TelegramHandle telegramHandle;
    private ContactIndex contactIndex;
    private Set<GroupTag> groupTags;
    private Set<ModuleTag> moduleTags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        telegramHandle = new TelegramHandle(DEFAULT_TELEGRAM_HANDLE);
        contactIndex = new ContactIndex(DEFAULT_CONTACT_INDEX);
        groupTags = new HashSet<>();
        moduleTags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        groupTags = new HashSet<>(personToCopy.getImmutableGroupTags());
        moduleTags = new HashSet<>(personToCopy.getImmutableModuleTags());
        telegramHandle = personToCopy.getTelegramHandle();
        contactIndex = personToCopy.getContactIndex();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code groupTags} into a {@code Set<GroupTag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withGroupTags(String ... groupTags) {
        this.groupTags = SampleDataUtil.getGroupTagSet(groupTags);
        return this;
    }

    /**
     * Parses the {@code moduleTags} into a {@code Set<ModuleTag>}
     * and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withModuleTags(String ... moduleTags) {
        this.moduleTags = SampleDataUtil.getModuleTagSet(moduleTags);
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
     * Sets the {@code TelegramHandle} of the {@code Person} that we are building.
     */
    public PersonBuilder withTelegramHandle(String telegramHandle) {
        this.telegramHandle = new TelegramHandle(telegramHandle);
        return this;
    }

    /**
     * Sets the {@code ContactIndex} of the {@code Person} that we are building.
     */
    public PersonBuilder withContactIndex(Integer index) {
        this.contactIndex = new ContactIndex(index);
        return this;
    }

    /**
     * Returns a {@code Person} with input features called so far.
     */
    public Person build() {
        return new Person(name, phone, email, address, telegramHandle, contactIndex,
                groupTags, moduleTags);
    }

}
