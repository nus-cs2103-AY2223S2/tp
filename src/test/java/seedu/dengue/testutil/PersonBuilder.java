package seedu.dengue.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.dengue.model.person.Age;
import seedu.dengue.model.person.DateAndTime;
import seedu.dengue.model.person.Name;
import seedu.dengue.model.person.Person;
import seedu.dengue.model.person.Postal;
import seedu.dengue.model.tag.Tag;
import seedu.dengue.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_POSTAL = "654214";
    public static final String DEFAULT_DATEANDTIME = "amy@gmail.com";
    public static final String DEFAULT_AGE = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Postal postal;
    private DateAndTime dateAndTime;
    private Age age;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        postal = new Postal(DEFAULT_POSTAL);
        dateAndTime = new DateAndTime(DEFAULT_AGE);
        age = new Age(DEFAULT_DATEANDTIME);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        postal = personToCopy.getPostal();
        dateAndTime = personToCopy.getDateAndTime();
        age = personToCopy.getAge();
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
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withDateAndTime(String email) {
        this.dateAndTime = new DateAndTime(email);
        return this;
    }

    public Person build() {
        return new Person(name, postal, dateAndTime, age, tags);
    }

}
