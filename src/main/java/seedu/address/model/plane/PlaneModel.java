package seedu.address.model.plane;

import static java.util.Objects.requireNonNull;

/**
 * Represents a plane's model in the Wingman app.
 */
public class PlaneModel {
    private final String planeModel;

    /**
     * Creates a plane model.
     * @param planeModel the model of the plane.
     */
    public PlaneModel(String planeModel) {
        requireNonNull(planeModel);
        this.planeModel = planeModel;
    }

    @Override
    public String toString() {
        return this.planeModel;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof PlaneModel
                && this.planeModel.equals(((PlaneModel) other).planeModel));
    }
}