package wingman.storage.json.adapted;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import wingman.commons.exceptions.IllegalValueException;
import wingman.model.crew.Crew;
import wingman.model.crew.CrewRank;
import wingman.model.crew.exceptions.InvalidCrewRankException;
import wingman.storage.json.JsonAdaptedModel;

/**
 * Represents a Jackson-friendly version of a Crew.
 */
public class JsonAdaptedCrew implements JsonAdaptedModel<Crew> {
    public static final String MISSING_FIELD_MESSAGE_FORMAT =
            "Crew's %s field is missing!";

    /**
     * The id of the crew.
     */
    private final String id;

    /**
     * The name of the crew.
     */
    private final String name;

    /**
     * The rank of the crew.
     */
    private final int rank;

    /**
     * The availability of the crew.
     */
    private boolean isAvailable;

    /**
     * Constructs a {@code JsonAdaptedPilot} with the given crew details.
     * This is intended for Jackson to use.
     *
     * @param id   The id of the crew.
     * @param rank The rank of the crew.
     */
    @JsonCreator
    public JsonAdaptedCrew(
            @JsonProperty("id") String id,
            @JsonProperty("name") String name,
            @JsonProperty("rank") int rank,
            @JsonProperty("isAvailable") boolean isAvailable
    ) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.isAvailable = isAvailable;
    }


    /**
     * Converts a given {@code Pilot} into this class for Jackson use.
     *
     * @param crew The crew to be converted.
     */
    public JsonAdaptedCrew(Crew crew) {
        this.id = crew.getId();
        this.name = crew.getName();
        this.rank = crew.getRank().toIndex();
        this.isAvailable = crew.isAvailable();
    }

    @Override
    public Crew toModelType() throws IllegalValueException {
        if (id == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, "id")
            );
        }
        if (name == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, "name")
            );
        }

        final CrewRank rank;

        try {
            rank = CrewRank.fromIndex(this.rank);
        } catch (InvalidCrewRankException e) {
            throw new IllegalValueException(e.getMessage());
        }

        Crew newCrew = new Crew(id, name, rank);
        if (this.isAvailable) {
            newCrew.setAvailable();
        } else {
            newCrew.setUnavailable();
        }

        return newCrew;
    }
}
