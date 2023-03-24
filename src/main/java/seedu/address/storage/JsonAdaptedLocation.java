package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.location.Location;

public class JsonAdaptedLocation {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Location's %s field is missing!";
    protected final String name;
    protected final double lat;
    protected final double lon;

    /**
     * Constructs a {@code JsonAdaptedLocation} with the given location details.
     */
    @JsonCreator
    public JsonAdaptedLocation(
            @JsonProperty("name") String name,
            @JsonProperty("lat") double lat,
            @JsonProperty("lon") double lon) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public JsonAdaptedLocation(Location location) {
        name = location.getName();
        lat = location.getLat();
        lon = location.getLon();
    }

    public Location toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, String.class.getSimpleName()));
        }

        return new Location(name, lat, lon);
    }
}
