package seedu.address.logic.recommender.location;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.location.Location;
import seedu.address.model.location.util.DistanceUtil;
import seedu.address.model.location.util.LocationDataUtil;

/**
 * Recommends a location based on a collection of locations
 * and a collection of destinations
 */
public class LocationRecommender {

    private static final Logger logger = LogsCenter.getLogger(LocationRecommender.class);

    private Collection<Location> destinations;

    /**
     * Constructs a location recommender with default destinations.
     */
    public LocationRecommender() {
        destinations = LocationDataUtil.MEET_LOCATIONS;
    }

    /**
     * Sets up the recommender with the destinations that we want.
     */
    public void initialise(Collection<Location> destinations) {
        this.destinations = destinations;
    }

    /**
     * Recommends a set of destinations given a list of sources.
     * @param sources Where people are coming from.
     */
    public List<Location> recommend(Collection<Location> sources) {
        logger.info(String.format("Locations to consider: %s", sources.toString()));

        Location midpoint = DistanceUtil.getMidpoint(sources);

        logger.info(String.format("Midpoint: %s", midpoint));

        List<Location> recommendedLocations =
                DistanceUtil.getClosestPoints(midpoint, 10, destinations);

        logger.info(recommendedLocations.toString());

        return recommendedLocations;
    }
}
