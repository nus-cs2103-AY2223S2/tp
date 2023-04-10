package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.location.Location;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.recommendation.Recommendation;
import seedu.address.model.tag.ModuleTag;
import seedu.address.model.time.TimePeriod;

/**
 * Jackson-friendly version of {@link ModuleTag}.
 */
public class JsonAdaptedRecommendation {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Recommendation's %s field is missing!";

    protected final JsonAdaptedLocation location;
    protected final JsonAdaptedTimePeriod timePeriod;
    protected final boolean isSaved;
    protected final Integer index;

    /**
     * Constructs a {@code Recommendation} with the given
     * {@code location}, {@code timePeriod}, {@code isSaved}.
     */
    @JsonCreator
    public JsonAdaptedRecommendation(
            @JsonProperty("location") JsonAdaptedLocation location,
            @JsonProperty("timePeriod") JsonAdaptedTimePeriod timePeriod,
            @JsonProperty("isSaved") boolean isSaved,
            @JsonProperty("index") Integer index) {
        this.location = location;
        this.timePeriod = timePeriod;
        this.isSaved = isSaved;
        this.index = index;
    }

    /**
     * Converts a given {@code Recommendation} into this class for Jackson use.
     */
    public JsonAdaptedRecommendation(Recommendation recommendation) {
        location = new JsonAdaptedLocation(recommendation.getLocation());
        timePeriod = new JsonAdaptedTimePeriod(recommendation.getTimePeriod());
        isSaved = recommendation.getIsSaved();
        index = recommendation.getContactIndex().getContactIndex();
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

        if (index == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Integer.class.getSimpleName()));
        }

        final ContactIndex modelContactIndex = new ContactIndex(index);

        return new Recommendation(modelLocation, modelTimePeriod, modelContactIndex, isSaved);
    }
}
