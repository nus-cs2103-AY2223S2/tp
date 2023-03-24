package seedu.dengue.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.dengue.model.person.Age;
import seedu.dengue.model.person.Date;
import seedu.dengue.model.person.Name;
import seedu.dengue.model.person.Person;
import seedu.dengue.model.person.Postal;
import seedu.dengue.model.util.SampleDataUtil;
import seedu.dengue.model.variant.Variant;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_POSTAL = "654214";
    public static final String DEFAULT_DATE = "2023-03-05";
    public static final String DEFAULT_AGE = "23";

    private Name name;
    private Postal postal;
    private Date date;
    private Age age;
    private Set<Variant> variants;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        this.name = new Name(DEFAULT_NAME);
        this.postal = new Postal(DEFAULT_POSTAL);
        this.date = new Date(DEFAULT_DATE);
        this.age = new Age(DEFAULT_AGE);
        this.variants = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        postal = personToCopy.getPostal();
        date = personToCopy.getDate();
        age = personToCopy.getAge();
        variants = new HashSet<>(personToCopy.getVariants());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code variants} into a {@code Set<Variant>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withVariants(String ... variants) {
        this.variants = SampleDataUtil.getVariantSet(variants);
        return this;
    }

    /**
     * Sets the {@code Age} of the {@code Person} that we are building.
     */
    public PersonBuilder withAge(String age) {
        this.age = new Age(age);
        return this;
    }

    /**
     * Sets the {@code Postal} of the {@code Person} that we are building.
     */
    public PersonBuilder withPostal(String postal) {
        this.postal = new Postal(postal);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Person} that we are building.
     */
    public PersonBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    public Person build() {
        return new Person(name, postal, date, age, variants);
    }

}
