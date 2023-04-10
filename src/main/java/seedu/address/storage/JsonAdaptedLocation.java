package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.location.Location;

/**
 * Json object for converting Locations.
 */
public class JsonAdaptedLocation {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Location's %s field is missing!";
    protected final String name;
    protected final double latitude;
    protected final double longitude;

    /**
     * Constructs a {@code JsonAdaptedLocation} with the given location details.
     */
    @JsonCreator
    public JsonAdaptedLocation(
            @JsonProperty("name") String name,
            @JsonProperty("latitude") double latitude,
            @JsonProperty("longitude") double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Constructor for a {@code JasonAdaptedLocation} object.
     * @param location
     */
    public JsonAdaptedLocation(Location location) {
        name = location.getName();
        latitude = location.getLatitude();
        longitude = location.getLongitude();
    }

    /**
     * Converts the json object to a {@code Location} type.
     */
    public Location toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, String.class.getSimpleName()));
        }

        if (!Location.isValidLatitude(latitude)) {
            throw new IllegalValueException("Invalid latitude!");
        }

        if (!Location.isValidLongitude(longitude)) {
            throw new IllegalValueException("Invalid longitude!");
        }

        return new Location(name, latitude, longitude);
    }
}
