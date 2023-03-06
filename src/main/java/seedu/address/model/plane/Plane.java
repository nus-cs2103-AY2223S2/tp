package seedu.address.model.plane;

import java.util.UUID;
import java.util.List;

import seedu.address.model.item.Identifiable;

/**
 * Represents a plane in the Wingman app.
 */
public class Plane implements Identifiable {
    private final PlaneModel model;
    private final PlaneAge age;
    private final String id;

    /**
     * Creates a plane with a random UUID as its id.
     * @param model the model of the plane.
     * @param age   the age of the plane.
     */
    public Plane(PlaneModel model, PlaneAge age) {
        this.model = model;
        this.age = age;
        this.id = UUID.randomUUID().toString();
    }

    /**
     * Creates a plane with a specific given id.
     * @param model the model of the plane.
     * @param age   the age of the plane.
     * @param id    the id of the plane.
     */
    public Plane(PlaneModel model, PlaneAge age, String id) {
        this.model = model;
        this.age = age;
        this.id = id;
    }

    /**
     * Returns the model of the plane.
     * @return the model of the plane.
     */
    public PlaneModel getModel() {
        return this.model;
    }

    /**
     * Returns the age of the plane.
     * @return the age of the plane.
     */
    public PlaneAge getAge() {
        return this.age;
    }

    public List<String> getDisplayList() {
        return List.of("UUID: " + this.id,
                "Model: " + this.model,
                "Age: " + this.age);
    }

    public String getId() {
        return this.id;
    }
}