package seedu.address.model.plane;

import java.util.List;
import java.util.UUID;

import seedu.address.model.item.Item;

/**
 * Represents a plane in the Wingman app.
 */
public class Plane implements Item {
    private static final String UUID_STRING = "UUID";
    private static final String MODEL_STRING = "Model";
    private static final String AGE_STRING = "Age";
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
     * Returns the id of the plane.
     * @return the id of the plane.
     */
    public String getId() {
        return this.id;
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
        return List.of(
                String.format("%s: %s", UUID_STRING, id),
                String.format("%s: %s", MODEL_STRING, model),
                String.format("%s: %s", AGE_STRING, age)
        );
    }

    @Override
    public String toString() {
        return String.format("%s: %s %s: %s %s: %s",
                UUID_STRING, id, MODEL_STRING, model, AGE_STRING, age);
    }
}
