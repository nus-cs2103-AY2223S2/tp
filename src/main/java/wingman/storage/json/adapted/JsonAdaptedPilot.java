package wingman.storage.json.adapted;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import wingman.commons.exceptions.IllegalValueException;
import wingman.model.pilot.Gender;
import wingman.model.pilot.Pilot;
import wingman.model.pilot.PilotRank;
import wingman.model.pilot.exceptions.InvalidGenderException;
import wingman.model.pilot.exceptions.InvalidPilotRankException;
import wingman.storage.json.JsonAdaptedModel;

/**
 * Represents a Jackson-friendly version of a Pilot.
 */
public class JsonAdaptedPilot implements JsonAdaptedModel<Pilot> {
    public static final String MISSING_FIELD_MESSAGE_FORMAT =
            "Pilot's %s field is missing!";

    /**
     * The id of the pilot.
     */
    private final String id;

    /**
     * The name of the pilot.
     */
    private final String name;

    /**
     * The rank of the pilot.
     */
    private final int rank;

    /**
     * The age of the pilot.
     */
    private final int age;

    /**
     * The gender of the pilot.
     */
    private final int gender;

    /**
     * The flight hour of the pilot.
     */
    private final int flightHour;

    /**
     * The availability of the pilot.
     */
    private boolean isAvailable;

    /**
     * Constructs a {@code JsonAdaptedPilot} with the given pilot details.
     * This is intended for Jackson to use.
     *
     * @param id   The id of the pilot.
     * @param rank The rank of the pilot.
     */
    @JsonCreator
    public JsonAdaptedPilot(
            @JsonProperty("id") String id,
            @JsonProperty("name") String name,
            @JsonProperty("rank") int rank,
            @JsonProperty("age") int age,
            @JsonProperty("gender") int gender,
            @JsonProperty("flightHour") int flightHour,
            @JsonProperty("isAvailable") boolean isAvailable
    ) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.age = age;
        this.gender = gender;
        this.flightHour = flightHour;
        this.isAvailable = isAvailable;
    }

    /**
     * Converts a given {@code Pilot} into this class for Jackson use.
     *
     * @param pilot The pilot to be converted.
     */
    public JsonAdaptedPilot(Pilot pilot) {
        this.id = pilot.getId();
        this.name = pilot.getName();
        this.rank = pilot.getRank().toIndex();
        this.gender = pilot.getGender().toIndex();
        this.age = pilot.getAge();
        this.flightHour = pilot.getFlightHour();
        this.isAvailable = pilot.isAvailable();
    }

    @Override
    public Pilot toModelType() throws IllegalValueException {
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

        final PilotRank rank;

        try {
            rank = PilotRank.fromIndex(this.rank);
        } catch (InvalidPilotRankException e) {
            throw new IllegalValueException(e.getMessage());
        }

        final Gender gender;

        try {
            gender = Gender.fromIndex(this.gender);
        } catch (InvalidGenderException e) {
            throw new IllegalValueException(e.getMessage());
        }

        Pilot newPilot = new Pilot(id, name, age, gender, rank, flightHour);
        if (this.isAvailable) {
            newPilot.setAvailable();
        } else {
            newPilot.setUnavailable();
        }

        return newPilot;
    }
}
