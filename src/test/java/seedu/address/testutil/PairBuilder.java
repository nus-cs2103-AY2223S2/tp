package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import seedu.address.model.pair.Pair;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Pair objects.
 */
public class PairBuilder {

    public static final Person DEFAULT_ELDERLY = ALICE;
    public static final Person DEFAULT_VOLUNTEER = BOB;
    private Person elderly;
    private Person volunteer;

    /**
     * Creates a {@code PairBuilder} with the default details.
     */
    public PairBuilder() {
        elderly = DEFAULT_ELDERLY;
        volunteer = DEFAULT_VOLUNTEER;
    }

    /**
     * Initializes the PairBuilder with the data of {@code pairToCopy}.
     */
    public PairBuilder(Pair pairToCopy) {
        elderly = pairToCopy.getElderly();
        volunteer = pairToCopy.getVolunteer();
    }

    /**
     * Sets the {@code Elderly} of the {@code pair} that we are building.
     */
    public PairBuilder withElderly(Person elderly) {
        this.elderly = elderly;
        return this;
    }

    /**
     * Sets the {@code Volunteer} of the {@code pair} that we are building.
     */
    public PairBuilder withVolunteer(Person volunteer) {
        this.volunteer = volunteer;
        return this;
    }

    public Pair build() {
        return new Pair(elderly, volunteer);
    }

}
