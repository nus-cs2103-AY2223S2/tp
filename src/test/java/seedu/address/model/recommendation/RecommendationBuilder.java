package seedu.address.model.recommendation;

import static seedu.address.model.location.util.TypicalLocation.STEVENS;
import static seedu.address.model.timetable.time.TypicalTimePeriod.TIME_PERIOD;

import seedu.address.model.location.Location;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.time.TimePeriod;

/**
 * Creates a recommendation.
 */
public class RecommendationBuilder {
    private static final Location DEFAULT_LOCATION = STEVENS;
    private static final TimePeriod DEFAULT_TIME_PERIOD = TIME_PERIOD;
    private static final ContactIndex DEFAULT_INDEX = new ContactIndex(0);

    private Location location;
    private TimePeriod timePeriod;
    private ContactIndex index;

    /**
     * Creates a {@code RecommendationBuilder} with the default details.
     */
    public RecommendationBuilder() {
        location = DEFAULT_LOCATION;
        timePeriod = DEFAULT_TIME_PERIOD;
        index = DEFAULT_INDEX;
    }

    /**
     * Initializes the RecommendationBuilder with the data of {@code recommendationToCopy}.
     */
    public RecommendationBuilder(Recommendation recommendation) {
        location = recommendation.getLocation();
        timePeriod = recommendation.getTimePeriod();
    }

    /**
     * Creates a new RecommendationBuilder with an updated location.
     */
    public RecommendationBuilder withLocation(Location location) {
        this.location = location;
        return this;
    }

    /**
     * Creates a new RecommendationBuilder with an updated TimePeriod.
     */
    public RecommendationBuilder withTimePeriod(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
        return this;
    }

    /**
     * Creates a new RecommendationBuilder with an updated index.
     */
    public RecommendationBuilder withIndex(ContactIndex index) {
        this.index = index;
        return this;
    }

    /**
     * Returns a new Recommendation based on the details provided.
     */
    public Recommendation build() {
        return new Recommendation(location, timePeriod, index);
    }
}
