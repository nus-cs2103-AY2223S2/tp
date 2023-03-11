//package seedu.address.testutil;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import seedu.address.model.contact.Contact;
//import seedu.address.model.person.Address;
//import seedu.address.model.person.Email;
//import seedu.address.model.person.Name;
//import seedu.address.model.person.Person;
//import seedu.address.model.person.Phone;
//import seedu.address.model.tag.Tag;
//import seedu.address.model.util.SampleDataUtil;
//
///**
// * A utility class to help with building Person objects.
// */
//public class PersonBuilder {
//
//    public static final String DEFAULT_NAME = "Amy Bee";
//    public static final String DEFAULT_PHONE = "85355255";
//    public static final String DEFAULT_EMAIL = "amy@gmail.com";
//    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
//
//    // To be removed once Person class is updated
//    public static final String VALID_PHONE_PLACEHOLDER = "11111111";
//    public static final String VALID_EMAIL_PLACEHOLDER = "amy@example.com";
//
//    private Name name;
//    private Phone phone;
//    private Email email;
//    private Address address;
//    private Set<Tag> tags;
//    private Contact contact;
//
//    /**
//     * Creates a {@code PersonBuilder} with the default details.
//     */
//    public PersonBuilder() {
//        name = new Name(DEFAULT_NAME);
//        phone = new Phone(VALID_PHONE_PLACEHOLDER);
//        email = new Email(VALID_EMAIL_PLACEHOLDER);
//        address = new Address(DEFAULT_ADDRESS);
//        tags = new HashSet<>();
//    }
//
//    /**
//     * Initializes the PersonBuilder with the data of {@code personToCopy}.
//     */
//    public PersonBuilder(Person personToCopy) {
//        name = personToCopy.getName();
//        phone = new Phone(VALID_PHONE_PLACEHOLDER);
//        email = new Email(VALID_EMAIL_PLACEHOLDER);
//        address = personToCopy.getAddress();
//        tags = new HashSet<>(personToCopy.getTags());
//    }
//
//    /**
//     * Sets the {@code Name} of the {@code Person} that we are building.
//     */
//    public PersonBuilder withName(String name) {
//        this.name = new Name(name);
//        return this;
//    }
//
//    /**
//     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
//     */
//    public PersonBuilder withTags(String ... tags) {
//        this.tags = SampleDataUtil.getTagSet(tags);
//        return this;
//    }
//
//    /**
//     * Sets the {@code Address} of the {@code Person} that we are building.
//     */
//    public PersonBuilder withAddress(String address) {
//        this.address = new Address(address);
//        return this;
//    }
//
//    /**
//     * Sets the {@code Phone} of the {@code Person} that we are building.
//     */
//    public PersonBuilder withPhone(String phone) {
//        this.phone = new Phone(phone);
//        return this;
//    }
//
//    /**
//     * Sets the {@code Email} of the {@code Person} that we are building.
//     */
//    public PersonBuilder withEmail(String email) {
//        this.email = new Email(email);
//        return this;
//    }
//
//    /**
//     * Sets the {@code Email} of the {@code Person} that we are building.
//     */
//    public PersonBuilder withContact(Contact contact) {
//        this.contact = contact;
//        return this;
//    }
//
//    public Person build() {
//        return new Person(name, phone, email, address, tags);
//    }
//
//}
