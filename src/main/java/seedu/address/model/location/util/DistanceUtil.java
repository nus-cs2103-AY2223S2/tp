package seedu.address.model.location.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import seedu.address.model.location.Location;

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
                Math.pow(firstLocation.getLatitude() - secondLocation.getLatitude(), 2)
                        + Math.pow(firstLocation.getLongitude() - secondLocation.getLongitude(), 2), 0.5);
    }

    /**
     * Gets the midpoint location based on a list of locations.
     * By default, an invalid calculation returns the coordinates of NUS.
     */
    public static Location getMidpoint(Collection<? extends Location> locations) {
        double midLat = locations.stream()
                .mapToDouble(Location::getLatitude)
                .average()
                .orElse(Location.NUS.getLatitude());
        double midLon = locations.stream()
                .mapToDouble(Location::getLongitude)
                .average()
                .orElse(Location.NUS.getLongitude());
        return new Location(midLat, midLon);
    }

    public static Location getMidpoint(Location... locations) {
        return getMidpoint(Arrays.asList(locations));
    }

    /**
     * Creates n evenly-spaced locations between the start and end locations.
     */
    public static List<Location> getApproximateLocations(Location startLocation, Location endLocation, int n) {
        assert n > 0;
        double stepSize = 1 / ((double) n + 1);
        List<Location> locations = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            double lambda = i * stepSize;
            double newLatitude = getConvexCombination(startLocation.getLatitude(),
                    endLocation.getLatitude(), lambda);
            double newLongitude = getConvexCombination(startLocation.getLongitude(),
                    endLocation.getLongitude(), lambda);
            assert Location.isValidLatitude(newLatitude);
            assert Location.isValidLongitude(newLongitude);
            locations.add(new Location(newLatitude, newLongitude));
        }

        assert locations.size() == n;
        return locations;
    }

    private static double getConvexCombination(double first, double second, double lambda) {
        assert lambda >= 0 && lambda <= 1;
        return (first * (1 - lambda)) + (second * lambda);
    }

    /**
     * Returns the closest point to a particular location.
     * For example, "the closest restaurant to home" would be {@code getClosestPoint(home, restaurants)}.
     */
    public static Optional<Location> getClosestPoint(
            Location location, Collection<Location> locations) {
        return locations.stream()
                .min(Comparator.comparingDouble((Location location1) -> getDistance(location1, location)));
    }

    public static Optional<Location> getClosestPoint(
            Location location, Location... locations) {
        return getClosestPoint(location, Arrays.asList(locations));
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

    public static List<Location> getClosestPoints(
            Location location, int limit, Location... locations) {
        return getClosestPoints(location, limit, Arrays.asList(locations));
    }
}
