package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.location.Location;
import seedu.address.model.recommender.Recommendation;
import seedu.address.model.scheduler.time.TimePeriod;
import seedu.address.model.tag.ModuleTag;

/**
 * Jackson-friendly version of {@link ModuleTag}.
 */
public class JsonAdaptedRecommendation {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Recommendation's %s field is missing!";

    protected final JsonAdaptedLocation location;
    protected final JsonAdaptedTimePeriod timePeriod;
    protected final boolean isSaved;

    /**
     * Constructs a {@code Recommendation} with the given
     * {@code location}, {@code timePeriod}, {@code isSaved}.
     */
    @JsonCreator
    public JsonAdaptedRecommendation(
            @JsonProperty("location") JsonAdaptedLocation location,
            @JsonProperty("timePeriod") JsonAdaptedTimePeriod timePeriod,
            @JsonProperty("isSaved") boolean isSaved) {
        this.location = location;
        this.timePeriod = timePeriod;
        this.isSaved = isSaved;
    }

    /**
     * Converts a given {@code Recommendation} into this class for Jackson use.
     */
    public JsonAdaptedRecommendation(Recommendation recommendation) {
        location = new JsonAdaptedLocation(recommendation.getLocation());
        timePeriod = new JsonAdaptedTimePeriod(recommendation.getTimePeriod());
        isSaved = recommendation.getIsSaved();
    }

    /**
     * Converts this Jackson-friendly adapted Recommendation object into
     * the model's {@code Recommendation} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Recommendation toModelType() throws IllegalValueException {
        if (location == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Location.class.getSimpleName()));
        }
        final Location modelLocation = location.toModelType();

        if (timePeriod == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, TimePeriod.class.getSimpleName()));
        }
        final TimePeriod modelTimePeriod = timePeriod.toModelType();

        return new Recommendation(modelLocation, modelTimePeriod, isSaved);
    }
}
