package seedu.address.model.location;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Location object which contains a Latitude(lat), a Longitude(lon) and a name.
 * Calculations are handled by {@code DistanceUtil} and parsing is handled by {@code LocationUtil}.
 */
public class Location implements Comparable<Location> {

    public static final String MESSAGE_CONSTRAINTS = "Locations should be between [1.23776, 1.46066] and "
            + "[103.61751, 104.04360] for latitude and longitude respectively.";

    /**
     * NUS is a commonly used location, so we cache it here.
     */
    public static final Location NUS = new Location("NUS", 1.29551, 103.77693);

    /**
     * The absolute bounds of Singapore.
     * i.e. the lat and the lon should never break these bounds.
     */
    private static final double MIN_LAT = 1.23776;
    private static final double MAX_LAT = 1.47066;
    private static final double MIN_LON = 103.61751;
    private static final double MAX_LON = 104.04360;
    private static final double ALLOWABLE_ERROR = 0.0001;

    private static final String DOUBLE_PATTERN = "[0-9]+(\\.)?[0-9]*";

    private final String name;
    private final double lat;
    private final double lon;

    /**
     * Constructs an unnamed {@code Location}.
     * @param lat A valid latitude.
     * @param lon A valid longitude.
     */
    public Location(double lat, double lon) {
        this("", lat, lon);
    }

    /**
     * Constructs a named {@code Location}.
     * @param name A non-null string.
     * @param lat A valid latitude.
     * @param lon A valid longitude.
     */
    public Location(String name, double lat, double lon) {
        requireAllNonNull(name, lat, lon);
        checkArgument(isValidLocation(lat, lon), MESSAGE_CONSTRAINTS);
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    /**
     * Checks whether the location is valid.
     * We also check whether the lat and lon can be parsed into a {@code Double}
     * so that we don't have to error-handle later on.
     * @param lat A string representation of the latitude.
     * @param lon A string representation of the longitude.
     * @return whether the lat and lon fit the requirements.
     */
    public static boolean isValidLocation(String lat, String lon) {
        return lat.matches(DOUBLE_PATTERN)
                && lon.matches(DOUBLE_PATTERN)
                && isValidLocation(Double.parseDouble(lat), Double.parseDouble(lon));
    }

    /**
     * Checks whether the location lies within the bounds of Singapore.
     * @param lat Latitude.
     * @param lon Longitude.
     * @return Whether the lat and lon fit the requirements.
     */
    public static boolean isValidLocation(double lat, double lon) {
        return isValidLatitude(lat)
                && isValidLongitude(lon);
    }

    /**
     * Checks whether the latitude lies within the bounds of Singapore.
     * @param lat Latitude.
     * @return Whether the lat fits the requirements.
     */
    public static boolean isValidLatitude(double lat) {
        return lat >= MIN_LAT && lat <= MAX_LAT;
    }

    /**
     * Checks whether the longitude lies within the bounds of Singapore.
     * @param lon Longitude.
     * @return Whether the lon fits the requirements.
     */
    public static boolean isValidLongitude(double lon) {
        return lon >= MIN_LON && lon <= MAX_LON;
    }

    /**
     * Gets the String value stored within the location.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the latitude of the location.
     */
    public double getLat() {
        return lat;
    }

    /**
     * Gets the longitude of the location.
     */
    public double getLon() {
        return lon;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Location
                && name.equals(((Location) other).name)
                && Math.abs(lat - ((Location) other).lat) <= ALLOWABLE_ERROR
                && Math.abs(lon - ((Location) other).lon) <= ALLOWABLE_ERROR);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lat, lon);
    }

    @Override
    public int compareTo(Location otherLocation) {
        return name.compareTo(otherLocation.name);
    }
}
