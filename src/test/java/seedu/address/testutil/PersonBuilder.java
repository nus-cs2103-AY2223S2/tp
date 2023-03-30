package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Platoon;
import seedu.address.model.person.Rank;
import seedu.address.model.person.Unit;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_RANK = "3SG";
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_UNIT = "N/A";
    public static final String DEFAULT_COMPANY = "N/A";
    public static final String DEFAULT_PLATOON = "N/A";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Rank rank;
    private Name name;
    private Unit unit;
    private Company company;
    private Platoon platoon;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        rank = new Rank(DEFAULT_RANK);
        name = new Name(DEFAULT_NAME);
        unit = new Unit(DEFAULT_UNIT);
        company = new Company(DEFAULT_COMPANY);
        platoon = new Platoon(DEFAULT_PLATOON);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        rank = personToCopy.getRank();
        name = personToCopy.getName();
        unit = personToCopy.getUnit();
        company = personToCopy.getCompany();
        platoon = personToCopy.getPlatoon();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Rank} of the {@code Person} that we are building.
     */
    public PersonBuilder withRank(String rank) {
        this.rank = new Rank(rank);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Unit} of the {@code Person} that we are building.
     */
    public PersonBuilder withUnit(String unit) {
        this.unit = new Unit(unit);
        return this;
    }

    /**
     * Sets the {@code Company} of the {@code Person} that we are building.
     */
    public PersonBuilder withCompany(String company) {
        this.company = new Company(company);
        return this;
    }

    /**
     * Sets the {@code Platoon} of the {@code Person} that we are building.
     */
    public PersonBuilder withPlatoon(String platoon) {
        this.platoon = new Platoon(platoon);
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
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }


    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }


    public Person build() {
        return new Person(rank, name, unit, company, platoon, phone, email, address, tags);
    }

}
