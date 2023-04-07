package seedu.dengue.testutil;

import java.util.HashSet;
import java.util.Random;
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

    private static final Random RANDOM = new Random();
    private static final int BOUND = 199;
    private static final int SMALL_BOUND = 15;

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

    /**
     * Builds a random person with a randomly generated name and age.
     * @return A new random person.
     */
    public Person buildRandom() {
        StringBuilder nameBuilder = new StringBuilder();
        for (int i = 0; i < RANDOM.nextInt(SMALL_BOUND) + 5; i++) {
            nameBuilder.append(Character.toString(RANDOM.nextInt(26) + 97));
            if (RANDOM.nextInt(SMALL_BOUND) > (SMALL_BOUND * 4) / 5) {
                nameBuilder.append(" ");
            }
        }
        String newName = nameBuilder.toString();

        return new PersonBuilder().withAge(String.valueOf(RANDOM.nextInt(BOUND)))
                .withName(newName)
                .build();
    }
}
