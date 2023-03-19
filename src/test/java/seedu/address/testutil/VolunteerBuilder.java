package seedu.address.testutil;

import seedu.address.model.person.Volunteer;

/**
 * A utility class to help with building Volunteer objects.
 */
public class VolunteerBuilder extends PersonBuilderScaffold<VolunteerBuilder> {

    /**
     * Creates a {@code VolunteerBuilder} with the default details.
     */
    public VolunteerBuilder() {
        super();
    }

    /**
     * Initializes the VolunteerBuilder with the data of {@code volunteerToCopy}.
     */
    public VolunteerBuilder(Volunteer volunteerToCopy) {
        super(volunteerToCopy);
    }

    public Volunteer build() {
        return new Volunteer(name, phone, email, address, nric, age, region, tags, availableDates);
    }
}
