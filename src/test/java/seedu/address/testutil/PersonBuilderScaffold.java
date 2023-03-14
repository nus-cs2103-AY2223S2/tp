package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Person;
import seedu.address.model.person.information.Address;
import seedu.address.model.person.information.Age;
import seedu.address.model.person.information.AvailableDate;
import seedu.address.model.person.information.Email;
import seedu.address.model.person.information.Name;
import seedu.address.model.person.information.Nric;
import seedu.address.model.person.information.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public abstract class PersonBuilderScaffold<T extends PersonBuilderScaffold<T>> {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_NRIC = "S1111111A";
    public static final String DEFAULT_AGE = "40";

    protected Name name;
    protected Phone phone;
    protected Email email;
    protected Address address;
    protected Nric nric;
    protected Age age;
    protected Set<Tag> tags;
    protected Set<AvailableDate> availableDates;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    protected PersonBuilderScaffold() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        nric = new Nric(DEFAULT_NRIC);
        age = new Age(DEFAULT_AGE);
        tags = new HashSet<>();
        availableDates = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    protected PersonBuilderScaffold(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        nric = personToCopy.getNric();
        age = personToCopy.getAge();
        tags = new HashSet<>(personToCopy.getTags());
        availableDates = new HashSet<>(personToCopy.getAvailableDates());
    }

    private T castSelf() {
        @SuppressWarnings("unchecked") T casted = (T) this;
        return casted;
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public T withName(String name) {
        this.name = new Name(name);
        return castSelf();
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public T withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return castSelf();
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public T withAvailableDates(String startDate, String endDate) {
        this.availableDates.add(SampleDataUtil.getAvailableDate(startDate, endDate));
        return castSelf();
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public T withAddress(String address) {
        this.address = new Address(address);
        return castSelf();
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public T withPhone(String phone) {
        this.phone = new Phone(phone);
        return castSelf();
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public T withEmail(String email) {
        this.email = new Email(email);
        return castSelf();
    }

    /**
     * Sets the {@code Nric} of the {@code Person} that we are building.
     */
    public T withNric(String nric) {
        this.nric = new Nric(nric);
        return castSelf();
    }

    /**
     * Sets the {@code Age} of the {@code Person} that we are building.
     */
    public T withAge(String age) {
        this.age = new Age(age);
        return castSelf();
    }

    /**
     * Build a person object
     */
    public abstract Person build();

}
