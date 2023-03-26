package seedu.address.logic.recommender.location;

import java.util.Collection;
import java.util.List;

import seedu.address.model.location.Location;
import seedu.address.model.location.util.DistanceUtil;
import seedu.address.model.location.util.LocationDataUtil;

/**
 * Recommends a location based on a collection of locations
 * and a collection of destinations
 */
public class LocationRecommender {

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
        Location midpoint = DistanceUtil.getMidpoint(sources);
        return DistanceUtil.getClosestPoints(midpoint, 10, destinations);
    }
}
