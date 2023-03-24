package seedu.address.model.location;

import seedu.address.model.location.util.DistanceUtil;
import seedu.address.model.location.util.LocationDataUtil;

import java.util.Collection;
import java.util.List;

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
