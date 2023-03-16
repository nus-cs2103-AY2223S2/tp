package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.person.PayRate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Session;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_PAY_RATE = "15";
    public static final String DEFAULT_SESSION_START = "28-02-2022 12:00";
    public static final String DEFAULT_SESSION_END = "28-02-2022 14:00";



    private Name name;
    private Phone phone;
    private Address address;
    private PayRate payRate;
    private Session session;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        address = new Address(DEFAULT_ADDRESS);
        payRate = new PayRate(DEFAULT_PAY_RATE);
        session = new Session(DEFAULT_SESSION_START, DEFAULT_SESSION_END);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        address = personToCopy.getAddress();
        payRate = personToCopy.getPayRate();
        session = personToCopy.getSession();
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
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Session} of the {@code Person} that we are building.
     */
    public PersonBuilder withSession(String startDateTime, String endDateTime) {
        this.session = new Session(startDateTime, endDateTime);
        return this;
    }


    /**
     * Sets the {@code Pay Rate} of the {@code Person} that we are building.
     */
    public PersonBuilder withPayRate(String payRate) {
        this.payRate = new PayRate(payRate);
        return this;
    }

    public Person build() {
        return new Person(name, phone, address, payRate, session, tags);
    }


}
