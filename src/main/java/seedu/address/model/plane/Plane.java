package seedu.address.model.plane;

import java.util.UUID;
import java.util.List;

import seedu.address.model.item.Identifiable;

/**
 * Represents a plane in the Wingman app.
 */
public class Plane implements Identifiable {
    private final String id;
    private final String model;
    private final int age;

    /**
     * Creates a plane with a random UUID as its id.
     * @param model the model of the plane.
     * @param age   the age of the plane.
     */
    public Plane(String model, int age) {
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
    public Plane(String id, String model, int age) {
        this.model = model;
        this.age = age;
        this.id = id;
    }

    /**
     * Returns the model of the plane.
     * @return the model of the plane.
     */
    public String getModel() {
        return this.model;
    }

    /**
     * Returns the age of the plane.
     * @return the age of the plane.
     */
    public int getAge() {
        return this.age;
    }

    public List<String> getDisplayList() {
        return List.of("UUID: " + id,
                "Model: " + model,
                "Age: " + age);
    }

    public String getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ID: " + id + " Model: " + model + " Age: " + age;
    }
}
