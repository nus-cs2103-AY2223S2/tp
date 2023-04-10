package wingman.storage.json.adapted;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import wingman.commons.exceptions.IllegalValueException;
import wingman.model.plane.Plane;
import wingman.storage.json.JsonAdaptedModel;

/**
 * Represents a Jackson-friendly version of a Plane.
 */
public class JsonAdaptedPlane implements JsonAdaptedModel<Plane> {
    public static final String MISSING_FIELD_MESSAGE_FORMAT =
            "Plane's %s field is missing!";

    /**
     * The id of the plane.
     */
    private final String id;

    /**
     * The model of the plane.
     */
    private final String model;

    /**
     * The age of the plane.
     */
    private final int age;

    /**
     * The availability of the plane.
     */
    private boolean isAvailable;

    /**
     * Constructs a {@code JsonAdaptedPlane} with the given plane details.
     * This is intended for Jackson to use.
     *
     * @param id    the id of the plane.
     * @param model the model of the plane.
     * @param age   the age of the plane.
     */
    @JsonCreator
    public JsonAdaptedPlane(
            @JsonProperty("id") String id,
            @JsonProperty("model") String model,
            @JsonProperty("age") int age
    ) {
        this.id = id;
        this.model = model;
        this.age = age;
        this.isAvailable = true;
    }

    /**
     * Converts a given {@code Plane} into this class for Jackson use.
     *
     * @param plane the plane to be converted.
     */
    public JsonAdaptedPlane(Plane plane) {
        this.id = plane.getId();
        this.model = plane.getModel();
        this.age = plane.getAge();
        this.isAvailable = plane.isAvailable();
    }

    @Override
    public Plane toModelType() throws IllegalValueException {
        if (id == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, "id")
            );
        }

        if (model == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, "model")
            );
        }
        Plane newPlane = new Plane(id, model, age);
        if (this.isAvailable) {
            newPlane.setAvailable();
        } else {
            newPlane.setUnavailable();
        }

        return newPlane;
    }
}
