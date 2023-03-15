package seedu.address.testutil;

import seedu.address.model.person.Volunteer;
import seedu.address.model.person.information.Age;
import seedu.address.model.person.information.Nric;

/**
 * A utility class to help with building Volunteer objects.
 */
public class VolunteerBuilder extends PersonBuilderScaffold<VolunteerBuilder> {
    public static final String DEFAULT_AGE = "20";
    public static final String DEFAULT_NRIC = "T1234567I";
    private Age age;
    private Nric nric;

    /**
     * Creates a {@code VolunteerBuilder} with the default details.
     */
    public VolunteerBuilder() {
        super();
        this.age = new Age(DEFAULT_AGE);
        this.nric = new Nric(DEFAULT_NRIC);
    }

    /**
     * Initializes the VolunteerBuilder with the data of {@code volunteerToCopy}.
     */
    public VolunteerBuilder(Volunteer volunteerToCopy) {
        super(volunteerToCopy);
        age = volunteerToCopy.getAge();
        nric = volunteerToCopy.getNric();
    }

    /**
     * Sets the {@code Age} of the {@code Volunteer} that we are building.
     */
    public VolunteerBuilder withAge(String age) {
        this.age = new Age(age);
        return this;
    }

    /**
     * Sets the {@code Nric} of the {@code Volunteer} that we are building.
     */
    public VolunteerBuilder withNric(String nric) {
        this.nric = new Nric(nric);
        return this;
    }

    public Volunteer build() {
        return new Volunteer(name, phone, email, address, nric, age, tags, availableDates);
    }
}
