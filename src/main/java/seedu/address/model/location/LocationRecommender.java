package seedu.address.model.location;

import seedu.address.model.location.util.DistanceUtil;

import java.util.Collection;
import java.util.List;

public class LocationRecommender {
    public List<Location> recommend(Collection<Location> locations, Collection<Location> destinations) {
        Location midpoint = DistanceUtil.getMidpoint(locations);
        return DistanceUtil.getClosestPoints(midpoint, 10, destinations);
    }
}
