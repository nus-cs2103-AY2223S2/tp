package seedu.address.model.location;

import java.util.Collection;
import java.util.List;

import seedu.address.model.location.util.DistanceUtil;
import seedu.address.model.location.util.LocationDataUtil;

/**
 * Recommends a location based on a collection of locations
 * and a collection of destinations
 */
public class LocationRecommender {

    private Collection<Location> destinations;

    public LocationRecommender() {
        destinations = LocationDataUtil.MEET_LOCATIONS;
    }

    public void initialise(Collection<Location> destinations) {
        this.destinations = destinations;
    }

    public List<Location> recommend(Collection<Location> locations) {
        Location midpoint = DistanceUtil.getMidpoint(locations);
        return DistanceUtil.getClosestPoints(midpoint, 10, destinations);
    }
}
