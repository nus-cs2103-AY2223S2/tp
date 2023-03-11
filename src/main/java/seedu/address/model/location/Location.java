package seedu.address.model.location;

import java.util.Objects;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class Location implements Comparable<Location> {

    public static final String MESSAGE_CONSTRAINTS = "Locations should be between [1.23776, 1.46066] and "
            + "[103.61751, 104.04360] for latitude and longitude respectively.";
    private static final double MIN_LAT = 1.23776;
    private static final double MAX_LAT = 1.47066;
    private static final double MIN_LON = 103.61751;
    private static final double MAX_LON = 104.04360;

    public static final Location NUS = new Location("NUS", 1.29551, 103.77693);

    private final String name;
    private final double lat;
    private final double lon;

    public Location(double lat, double lon) {
        this("", lat, lon);
    }

    public Location(String name, double lat, double lon) {
        requireAllNonNull(lat, lon);
        checkArgument(isValidLocation(lat, lon), MESSAGE_CONSTRAINTS);
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public static boolean isValidLatitude(double lat) {
        return lat >= MIN_LAT && lat <= MAX_LAT;
    }

    public static boolean isValidLongitude(double lon) {
        return lon >= MIN_LON && lon <= MAX_LON;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidLocation(double lat, double lon) {
        return isValidLatitude(lat) && isValidLongitude(lon);
    }

    public String getName() {
        return name;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    @Override
    public String toString() {
        return String.format("<Location: %s %b %b>", name, lat, lon);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Location
                && name.equals(((Location) other).name)
                && lat == ((Location) other).lat
                && lon == ((Location) other).lon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lon);
    }

    @Override
    public int compareTo(Location otherLocation) {
        return name.compareTo(otherLocation.name);
    }
}
