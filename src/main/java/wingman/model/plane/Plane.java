package wingman.model.plane;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import wingman.model.item.Item;
import wingman.model.location.PlaneLocationType;

/**
 * Represents a plane in the Wingman app.
 */
public class Plane implements Item {
    /**
     * The shape of the link between plane and flight
     */
    public static final Map<FlightPlaneType, Integer> SHAPE =
            Map.of(FlightPlaneType.PLANE_USING, 1);

    /**
     * THe shape of the link between plane and location.
     * Here the number could be arbitrarily large values to
     * indicate "infinity".
     */
    public static final Map<PlaneLocationType, Integer> SHAPE_FOR_LOCATION =
            Map.of(PlaneLocationType.LOCATION_USING, 1000000);

    private static final String AGE_STRING = "Age";
    private static final String AVAILABILITY_STRING = "Status";
    private final String id;
    private final String model;
    private final int age;
    private boolean isAvailable;

    /**
     * Creates a plane with a random UUID as its id.
     *
     * @param model the model of the plane.
     * @param age   the age of the plane.
     */
    public Plane(String model, int age) {
        this.id = UUID.randomUUID().toString();
        this.model = model;
        this.age = age;
        this.isAvailable = true;
    }

    /**
     * Creates a plane with a specific given id.
     *
     * @param model the model of the plane.
     * @param age   the age of the plane.
     * @param id    the id of the plane.
     */
    public Plane(String id, String model, int age) {
        this.model = model;
        this.age = age;
        this.id = id;
        this.isAvailable = true;
    }

    /**
     * Returns the id of the plane.
     *
     * @return the id of the plane.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Returns the model of the plane.
     *
     * @return the model of the plane.
     */
    public String getModel() {
        return this.model;
    }

    /**
     * Returns the age of the plane.
     *
     * @return the age of the plane.
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Returns the availability of the plane.
     *
     * @return the availability of the plane.
     */
    public boolean isAvailable() {
        return this.isAvailable;
    }

    /**
     * Sets the availability of the plane to unavailable.
     */
    public void setUnavailable() {
        this.isAvailable = false;
    }

    /**
     * Sets the availability of the plane to available.
     */
    public void setAvailable() {
        this.isAvailable = true;
    }

    /**
     * Returns a String corresponding to the availability of the plane.
     *
     * @return the availability of the plane as a String
     */
    public String getAvailabilityString() {
        return (this.isAvailable)
                ? "Available"
                : "Unavailable";
    }

    @Override
    public List<String> getDisplayList() {
        return List.of(
                String.format("%s", model),
                String.format("%s: %s", AGE_STRING, age)
        );
    }

    @Override
    public String toString() {
        return getModel();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Plane)) {
            return false;
        }

        Plane other = (Plane) obj;

        return ((other.getModel().equals(this.getModel())) && (other.getAge() == this.getAge()));
    }
}
