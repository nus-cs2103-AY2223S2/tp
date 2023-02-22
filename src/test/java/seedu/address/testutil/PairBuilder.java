package seedu.address.testutil;

import static seedu.address.testutil.TypicalElderlys.AMY;
import static seedu.address.testutil.TypicalVolunteers.BOB;

import seedu.address.model.pair.Pair;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Volunteer;

/**
 * A utility class to help with building Pair objects.
 */
public class PairBuilder {

    public static final Elderly DEFAULT_ELDERLY = AMY;
    public static final Volunteer DEFAULT_VOLUNTEER = BOB;
    private Elderly elderly;
    private Volunteer volunteer;

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
    public PairBuilder withElderly(Elderly elderly) {
        this.elderly = elderly;
        return this;
    }

    /**
     * Sets the {@code Volunteer} of the {@code pair} that we are building.
     */
    public PairBuilder withVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
        return this;
    }

    public Pair build() {
        return new Pair(elderly, volunteer);
    }

}
