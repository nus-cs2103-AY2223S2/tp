package seedu.address.model.location;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Handles the computations between locations.
 * Different from {@code LocationUtil} which handles parses and processes the Locations instead.
 */
public class DistanceUtil {

    private static final double DISTANCE_CONSTANT = 111.33;

    /**
     * Calculates the distance between locations.
     */
    public static double getDistance(Location firstLocation, Location secondLocation) {
        return DISTANCE_CONSTANT * Math.pow(
                Math.pow(firstLocation.getLat() - secondLocation.getLat(), 2)
                        + Math.pow(firstLocation.getLon() - secondLocation.getLon(), 2), 0.5);
    }

    /**
     * Gets the midpoint location based on a list of locations.
     * By default, an invalid calculation returns the coordinates of NUS.
     */
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

    /**
     * Returns the closest point to a particular location.
     * For example, "the closest restaurant to home" would be {@code getClosestPoint(home, restaurants)}.
     */
    public static Optional<Location> getClosestPoint(
            Location location, List<Location> locations) {
        return locations.stream()
                .min(Comparator.comparingDouble((Location location1) -> getDistance(location1, location)));
    }

    /**
     * Returns the closest points to a particular location.
     * For example, "the 5 closest restaurants to home" would be {@code getClosestPoint(home, 5, restaurants)}.
     */
    public static List<Location> getClosestPoints(
            Location location, int limit, Collection<Location> locations) {
        return locations.stream()
                .sorted(Comparator.comparingDouble((Location other) -> getDistance(other, location)))
                .limit(limit)
                .collect(Collectors.toList());
    }
}
