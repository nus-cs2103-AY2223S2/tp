package seedu.address.model.plane;

import static java.util.Objects.requireNonNull;

/**
 * Represents a plane's age in the Wingman app.
 */
public class PlaneAge {
    private final String planeAge;

    /**
     * Creates a plane age.
     * @param planeAge the age of the plane.
     */
    public PlaneAge(String planeAge) {
        requireNonNull(planeAge);
        this.planeAge = planeAge;
    }

    @Override
    public String toString() {
        return this.planeAge;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof PlaneAge
                && planeAge.equals(((PlaneAge) other).planeAge));
    }

}
