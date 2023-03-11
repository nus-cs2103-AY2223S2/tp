package seedu.address.model.location;

import seedu.address.commons.exceptions.IllegalValueException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DistanceUtil {

    private static final double DISTANCE_CONSTANT = 111.33;

    public static double getDistance(Location firstLocation, Location secondLocation) {
        return DISTANCE_CONSTANT * Math.pow(
                Math.pow(firstLocation.getLat() - secondLocation.getLat(), 2)
                        + Math.pow(firstLocation.getLon() - secondLocation.getLon(), 2), 0.5);
    }

    public static Location getMidpoint(List<? extends Location> locations) {
        double midLat = locations.stream()
                .mapToDouble(Location::getLat)
                .average()
                .orElse(Location.NUS.getLat());
        double midLon = locations.stream()
                .mapToDouble(Location::getLon)
                .average()
                .orElse(Location.NUS.getLon());
        return new Location(midLat, midLon);
    }

    public static Optional<? extends Location> getClosestPoint(
            Location location, List<? extends Location> locations) {
        return locations.stream()
                .min(Comparator.comparingDouble((Location location1) -> getDistance(location1, location)));
    }

    public static List<? extends Location> getClosestPoints(
            Location location, int limit, List<? extends Location> locations) {
        return locations.stream()
                .sorted(Comparator.comparingDouble((Location location1) -> getDistance(location1, location)))
                .limit(limit)
                .collect(Collectors.toList());
    }
}
