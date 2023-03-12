package seedu.address.model.location;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class LocationTest {

    private static final double VALID_LAT_1 = 1.34917;
    private static final double VALID_LON_1 = 103.87348;
    private static final String VALID_NAME_1 = "Serangoon";

    private static final double VALID_LAT_2 = 1.35111;
    private static final double VALID_LON_2 = 103.84833;
    private static final String VALID_NAME_2 = "Bishan";

    private static final double VALID_LAT_3 = 1.28472;
    private static final double VALID_LON_3 = 103.84389;
    private static final String VALID_NAME_3 = "Chinatown";

    private static final List<Double> VALID_LAT_LIST =
            List.of(VALID_LAT_1, VALID_LAT_2, VALID_LAT_3);
    private static final List<Double> VALID_LON_LIST =
            List.of(VALID_LON_1, VALID_LON_2, VALID_LON_3);
    private static final List<String> VALID_NAME_LIST =
            List.of(VALID_NAME_1, VALID_NAME_2, VALID_NAME_3);

    private static final Location VALID_LOCATION_1 =
            new Location(VALID_NAME_1, VALID_LAT_1, VALID_LON_1);
    private static final Location VALID_LOCATION_2 =
            new Location(VALID_NAME_2, VALID_LAT_2, VALID_LON_2);
    private static final Location VALID_LOCATION_3 =
            new Location(VALID_NAME_3, VALID_LAT_3, VALID_LON_3);

    private static final List<Location> VALID_LOCATION_LIST =
            List.of(VALID_LOCATION_1, VALID_LOCATION_2, VALID_LOCATION_3);

    private static final double INVALID_LAT_1 = 103.88453;
    private static final double INVALID_LON_1 = 1.36775;

    private static final double INVALID_LAT_2 = 1;
    private static final double INVALID_LON_2 = 103;

    private static final double INVALID_LAT_3 = 2;
    private static final double INVALID_LON_3 = 105;

    private static final List<Double> INVALID_LAT_LIST =
            List.of(INVALID_LAT_1, INVALID_LAT_2, INVALID_LAT_3);
    private static final List<Double> INVALID_LON_LIST =
            List.of(INVALID_LON_1, INVALID_LON_2, INVALID_LON_3);

    @Test
    public void isValidLocation_validLatLong_true() {
        VALID_LAT_LIST
                .forEach(lat -> VALID_LON_LIST
                        .forEach(lon -> assertTrue(Location.isValidLocation(lat, lon))));
    }

    @Test
    public void isValidLocation_invalidLat_false() {
        INVALID_LAT_LIST
                .forEach(lat -> VALID_LON_LIST
                        .forEach(lon -> assertFalse(Location.isValidLocation(lat, lon))));
    }

    @Test
    public void isValidLocation_invalidLon_false() {
        INVALID_LON_LIST
                .forEach(lon -> VALID_LAT_LIST
                        .forEach(lat -> assertFalse(Location.isValidLocation(lat, lon))));
    }

    @Test
    public void isValidLocation_invalidLatLon_false() {
        INVALID_LAT_LIST
                .forEach(lat -> INVALID_LON_LIST
                        .forEach(lon -> assertFalse(Location.isValidLocation(lat, lon))));
    }

    @Test
    public void isValidLocation_reversedInputs_false() {
        VALID_LAT_LIST
                .forEach(lat -> VALID_LON_LIST
                        .forEach(lon -> assertFalse(Location.isValidLocation(lon, lat))));
    }

    @Test
    public void isValidLatitude_validLat_true() {
        VALID_LAT_LIST
                .forEach(lat -> assertTrue(Location.isValidLatitude(lat)));
    }

    @Test
    public void isValidLatitude_invalidLat_true() {
        INVALID_LAT_LIST
                .forEach(lat -> assertFalse(Location.isValidLatitude(lat)));
    }

    @Test
    public void isValidLongitude_validLong_true() {
        VALID_LON_LIST
                .forEach(lon -> assertTrue(Location.isValidLongitude(lon)));
    }

    @Test
    public void isValidLongitude_invalidLong_true() {
        INVALID_LON_LIST
                .forEach(lon -> assertFalse(Location.isValidLongitude(lon)));
    }

    @Test
    public void getName() {
        assertEquals(VALID_LOCATION_1.getName(),
                VALID_NAME_1);
        assertEquals(VALID_LOCATION_2.getName(),
                VALID_NAME_2);
        assertEquals(VALID_LOCATION_3.getName(),
                VALID_NAME_3);
    }

    @Test
    public void getLat() {
        assertEquals(VALID_LOCATION_1.getLat(),
                VALID_LAT_1);
        assertEquals(VALID_LOCATION_2.getLat(),
                VALID_LAT_2);
        assertEquals(VALID_LOCATION_3.getLat(),
                VALID_LAT_3);
    }

    @Test
    public void getLon() {
        assertEquals(VALID_LOCATION_1.getLon(),
                VALID_LON_1);
        assertEquals(VALID_LOCATION_2.getLon(),
                VALID_LON_2);
        assertEquals(VALID_LOCATION_3.getLon(),
                VALID_LON_3);
    }

    @Test
    public void testToString() {
        assertTrue(VALID_LOCATION_1.toString()
                .contains(VALID_NAME_1));
        assertTrue(VALID_LOCATION_2.toString()
                .contains(VALID_NAME_2));
        assertTrue(VALID_LOCATION_3.toString()
                .contains(VALID_NAME_3));
    }

    @Test
    public void testEquals() {
        // same everything
        assertEquals(VALID_LOCATION_1,
                new Location(VALID_NAME_1, VALID_LAT_1, VALID_LON_1));
        assertEquals(VALID_LOCATION_2,
                new Location(VALID_NAME_2, VALID_LAT_2, VALID_LON_2));
        assertEquals(VALID_LOCATION_3,
                new Location(VALID_NAME_3, VALID_LAT_3, VALID_LON_3));

        // empty constructor
        assertEquals(new Location(VALID_LAT_1, VALID_LON_1),
                new Location("", VALID_LAT_1, VALID_LON_1));
        assertEquals(new Location(VALID_LAT_2, VALID_LON_2),
                new Location("", VALID_LAT_2, VALID_LON_2));
        assertEquals(new Location(VALID_LAT_3, VALID_LON_3),
                new Location("", VALID_LAT_3, VALID_LON_3));

        // different name
        assertNotEquals(VALID_LOCATION_1,
                new Location(VALID_NAME_2, VALID_LAT_1, VALID_LON_1));
        assertNotEquals(VALID_LOCATION_2,
                new Location(VALID_NAME_3, VALID_LAT_2, VALID_LON_2));
        assertNotEquals(VALID_LOCATION_3,
                new Location(VALID_NAME_1, VALID_LAT_3, VALID_LON_3));

        // different lat
        assertNotEquals(VALID_LOCATION_1,
                new Location(VALID_NAME_1, VALID_LAT_2, VALID_LON_1));
        assertNotEquals(VALID_LOCATION_2,
                new Location(VALID_NAME_2, VALID_LAT_3, VALID_LON_2));
        assertNotEquals(VALID_LOCATION_3,
                new Location(VALID_NAME_3, VALID_LAT_1, VALID_LON_3));

        // different lon
        assertNotEquals(VALID_LOCATION_1,
                new Location(VALID_NAME_1, VALID_LAT_1, VALID_LON_2));
        assertNotEquals(VALID_LOCATION_2,
                new Location(VALID_NAME_2, VALID_LAT_2, VALID_LON_3));
        assertNotEquals(VALID_LOCATION_3,
                new Location(VALID_NAME_3, VALID_LAT_3, VALID_LON_1));

        // different everything
        assertNotEquals(VALID_LOCATION_1, VALID_LOCATION_2);
        assertNotEquals(VALID_LOCATION_1, VALID_LOCATION_3);
        assertNotEquals(VALID_LOCATION_2, VALID_LOCATION_3);
    }

    @Test
    public void testHashCode() {
        // same everything
        assertEquals(VALID_LOCATION_1.hashCode(),
                new Location(VALID_NAME_1, VALID_LAT_1, VALID_LON_1).hashCode());
        assertEquals(VALID_LOCATION_2.hashCode(),
                new Location(VALID_NAME_2, VALID_LAT_2, VALID_LON_2).hashCode());
        assertEquals(VALID_LOCATION_3.hashCode(),
                new Location(VALID_NAME_3, VALID_LAT_3, VALID_LON_3).hashCode());

        // empty constructor
        assertEquals(new Location(VALID_LAT_1, VALID_LON_1).hashCode(),
                new Location("", VALID_LAT_1, VALID_LON_1).hashCode());
        assertEquals(new Location(VALID_LAT_2, VALID_LON_2).hashCode(),
                new Location("", VALID_LAT_2, VALID_LON_2).hashCode());
        assertEquals(new Location(VALID_LAT_3, VALID_LON_3).hashCode(),
                new Location("", VALID_LAT_3, VALID_LON_3).hashCode());

        // different name
        assertNotEquals(VALID_LOCATION_1.hashCode(),
                new Location(VALID_NAME_2, VALID_LAT_1, VALID_LON_1).hashCode());
        assertNotEquals(VALID_LOCATION_2.hashCode(),
                new Location(VALID_NAME_3, VALID_LAT_2, VALID_LON_2).hashCode());
        assertNotEquals(VALID_LOCATION_3.hashCode(),
                new Location(VALID_NAME_1, VALID_LAT_3, VALID_LON_3).hashCode());

        // different lat
        assertNotEquals(VALID_LOCATION_1.hashCode(),
                new Location(VALID_NAME_1, VALID_LAT_2, VALID_LON_1).hashCode());
        assertNotEquals(VALID_LOCATION_2.hashCode(),
                new Location(VALID_NAME_2, VALID_LAT_3, VALID_LON_2).hashCode());
        assertNotEquals(VALID_LOCATION_3.hashCode(),
                new Location(VALID_NAME_3, VALID_LAT_1, VALID_LON_3).hashCode());

        // different lon
        assertNotEquals(VALID_LOCATION_1.hashCode(),
                new Location(VALID_NAME_1, VALID_LAT_1, VALID_LON_2).hashCode());
        assertNotEquals(VALID_LOCATION_2.hashCode(),
                new Location(VALID_NAME_2, VALID_LAT_2, VALID_LON_3).hashCode());
        assertNotEquals(VALID_LOCATION_3.hashCode(),
                new Location(VALID_NAME_3, VALID_LAT_3, VALID_LON_1).hashCode());

        // different everything
        assertNotEquals(VALID_LOCATION_1.hashCode(), VALID_LOCATION_2.hashCode());
        assertNotEquals(VALID_LOCATION_1.hashCode(), VALID_LOCATION_3.hashCode());
        assertNotEquals(VALID_LOCATION_2.hashCode(), VALID_LOCATION_3.hashCode());
    }

    @Test
    public void compareTo() {
        assertEquals(List.of(VALID_LOCATION_2, VALID_LOCATION_3, VALID_LOCATION_1),
                VALID_LOCATION_LIST.stream().sorted().collect(Collectors.toList()));
    }
}
