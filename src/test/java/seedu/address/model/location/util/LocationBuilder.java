package seedu.address.model.location.util;

import seedu.address.model.location.Location;

/**
 * Creates a new Location.
 */
class LocationBuilder {
    private static final String DEFAULT_NAME = Location.NUS.getName();
    private static final double DEFAULT_LAT = Location.NUS.getLatitude();
    private static final double DEFAULT_LON = Location.NUS.getLongitude();
    private String name;
    private double lat;
    private double lon;

    public LocationBuilder() {
        name = DEFAULT_NAME;
        lat = DEFAULT_LAT;
        lon = DEFAULT_LON;
    }

    public LocationBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public LocationBuilder withLat(double lat) {
        if (!Location.isValidLatitude(lat)) {
            return this;
        }
        this.lat = lat;
        return this;
    }

    public LocationBuilder withLon(double lon) {
        if (!Location.isValidLongitude(lon)) {
            return this;
        }
        this.lon = lon;
        return this;
    }

    public Location build() {
        return new Location(name, lat, lon);
    }
}
