package seedu.address.storage.json.adapted;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.pilot.Pilot;
import seedu.address.model.pilot.PilotRank;
import seedu.address.model.pilot.exceptions.InvalidPilotRankException;
import seedu.address.storage.json.JsonAdaptedModel;

/**
 * Represents a Jackson-friendly version of a Pilot.
 */
public class JsonAdaptedPilot implements JsonAdaptedModel<Pilot> {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Pilot's %s field is missing!";

    /**
     * The id of the pilot.
     */
    private final String id;

    /**
     * The rank of the pilot.
     */
    private final String rank;

    /**
     * Constructs a {@code JsonAdaptedPilot} with the given pilot details.
     * This is intended for Jackson to use.
     *
     * @param id   The id of the pilot.
     * @param rank The rank of the pilot.
     */
    @JsonCreator
    public JsonAdaptedPilot(@JsonProperty("id") String id,
                            @JsonProperty("rank") String rank) {
        this.id = id;
        this.rank = rank;
    }


    /**
     * Converts a given {@code Pilot} into this class for Jackson use.
     *
     * @param pilot The pilot to be converted.
     */
    public JsonAdaptedPilot(Pilot pilot) {
        this.id = pilot.getId();
        this.rank = pilot.getRank().toString();
    }

    @Override
    public Pilot toModelType() throws IllegalValueException {
        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "id"));
        }
        if (rank == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "rank"));
        }
        try {
            return new Pilot(id, PilotRank.valueOf(rank));
        } catch (InvalidPilotRankException e) {
            throw new IllegalValueException(e.getMessage());
        }
    }
}
