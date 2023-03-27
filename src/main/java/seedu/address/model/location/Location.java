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
    private static final double MIN_LATITUDE = 1.23776;
    private static final double MAX_LATITUDE = 1.47066;
    private static final double MIN_LONGITUDE = 103.61751;
    private static final double MAX_LONGITUDE = 104.04360;
    private static final double ALLOWABLE_ERROR = 0.0001;

    private static final String DOUBLE_PATTERN = "[0-9]+(\\.)?[0-9]*";

    private final String name;
    private final double latitude;
    private final double longitude;

    /**
     * Constructs an unnamed {@code Location}.
     * @param latitude A valid latitude.
     * @param longitude A valid longitude.
     */
    public Location(double latitude, double longitude) {
        this("", latitude, longitude);
    }

    /**
     * Constructs a named {@code Location}.
     * @param name A non-null string.
     * @param latitude A valid latitude.
     * @param longitude A valid longitude.
     */
    public Location(String name, double latitude, double longitude) {
        requireAllNonNull(name, latitude, longitude);
        checkArgument(isValidLocation(latitude, longitude), MESSAGE_CONSTRAINTS);
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Checks whether the location is valid.
     * We also check whether the lat and lon can be parsed into a {@code Double}
     * so that we don't have to error-handle later on.
     * @param latitude A string representation of the latitude.
     * @param longitude A string representation of the longitude.
     * @return whether the lat and lon fit the requirements.
     */
    public static boolean isValidLocation(String latitude, String longitude) {
        return latitude.matches(DOUBLE_PATTERN)
                && longitude.matches(DOUBLE_PATTERN)
                && isValidLocation(Double.parseDouble(latitude), Double.parseDouble(longitude));
    }

    /**
     * Checks whether the location lies within the bounds of Singapore.
     * @param latitude Latitude.
     * @param longitude Longitude.
     * @return Whether the lat and lon fit the requirements.
     */
    public static boolean isValidLocation(double latitude, double longitude) {
        return isValidLatitude(latitude)
                && isValidLongitude(longitude);
    }

    /**
     * Checks whether the latitude lies within the bounds of Singapore.
     * @param latitude Latitude.
     * @return Whether the latitude fits the requirements.
     */
    public static boolean isValidLatitude(double latitude) {
        return latitude >= MIN_LATITUDE && latitude <= MAX_LATITUDE;
    }

    /**
     * Checks whether the longitude lies within the bounds of Singapore.
     * @param longitude Longitude.
     * @return Whether the longitude fits the requirements.
     */
    public static boolean isValidLongitude(double longitude) {
        return longitude >= MIN_LONGITUDE && longitude <= MAX_LONGITUDE;
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
    public double getLatitude() {
        return latitude;
    }

    /**
     * Gets the longitude of the location.
     */
    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return String.format("[%s %s %s]", getName(), latitude, longitude);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Location)) {
            return false;
        }

        Location otherLocation = (Location) other;
        return Math.abs(latitude - otherLocation.latitude) <= ALLOWABLE_ERROR
                && Math.abs(longitude - otherLocation.longitude) <= ALLOWABLE_ERROR;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, latitude, longitude);
    }

    @Override
    public int compareTo(Location otherLocation) {
        return name.compareTo(otherLocation.name);
    }
}
