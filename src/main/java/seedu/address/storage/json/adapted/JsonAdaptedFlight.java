package seedu.address.storage.json.adapted;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.flight.Flight;
import seedu.address.storage.json.JsonAdaptedModel;

/**
 * Represents a Jackson-friendly version of a Flight
 */
public class JsonAdaptedFlight implements JsonAdaptedModel<Flight> {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Flight's %s field is missing!";

    /**
     * The id of the flight.
     */
    private final String id;

    /**
     * The code of the flight.
     */
    private final String code;

    /**
     * The plane linked to the flight.
     */
    private String plane;

    /**
     * Constructs a {@code JsonAdaptedFlight} with the given flight details.
     * This is intended for Jackson to use.
     *
     * @param id   The id of the flight.
     * @param code The name of the flight.
     */
    @JsonCreator
    public JsonAdaptedFlight(@JsonProperty("id") String id,
                             @JsonProperty("code") String code,
                             @JsonProperty("plane") String plane) {
        this.id = id;
        this.code = code;
        this.plane = plane;
    }

    /**
     * Converts a given {@code Flight} into this class for Jackson use.
     *
     * @param flight The flight to be converted.
     */
    public JsonAdaptedFlight(Flight flight) {
        this.id = flight.getId();
        this.code = flight.getCode();

        if (flight.hasLinkedPlane()) {
            this.plane = flight.getLinkedPlane().getId();
        } else {
            this.plane = "";
        }
    }

    @Override
    public Flight toModelType() throws IllegalValueException {
        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "id"));
        }
        if (code == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "code"));
        }

        return new Flight(id, code);
    }
}
