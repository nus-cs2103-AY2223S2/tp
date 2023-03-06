package seedu.address.model.plane;

import static java.util.Objects.requireNonNull;

/**
 * Represents a plane's age in the Wingman app.
 */
public class PlaneAge {
    private final int planeAge;

    /**
     * Creates a plane age.
     * @param planeAge the age of the plane.
     */
    public PlaneAge(int planeAge) {
        this.planeAge = planeAge;
    }

    public int toInteger() {
        return this.planeAge;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof PlaneAge
                && planeAge == (((PlaneAge) other).planeAge));
    }
}
