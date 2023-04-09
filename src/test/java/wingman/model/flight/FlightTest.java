package wingman.model.flight;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import wingman.model.link.exceptions.LinkException;
import wingman.model.location.FlightLocationType;
import wingman.model.location.Location;

public class FlightTest {

    private final String code1 = "SQ123";
    private final String code2 = " ";

    private final Flight flight1 = new Flight(code1);
    private final Flight flight2 = new Flight(code2);
    private final Flight flight3 = new Flight(code1);

    private final Location location1 = new Location("Singapore");

    @Test
    public void testFlight() {
        assertDoesNotThrow(() -> new Flight(code1));
        assertDoesNotThrow(() -> new Flight(code2));
    }
    @Test
    public void testGetCode() {
        assertEquals(code1, flight1.getCode());
        assertEquals(code2, flight2.getCode());
        assertEquals(code1, flight3.getCode());
    }

    @Test
    public void testSetLocation() throws LinkException {
        assertDoesNotThrow(() -> flight1.setLocation(FlightLocationType.LOCATION_DEPARTURE, location1));
        assertTrue(flight1.getLocationLink().contains(FlightLocationType.LOCATION_DEPARTURE, location1));
    }

    @Test
    public void testRemoveLocation() throws LinkException {
        assertDoesNotThrow(() -> flight1.setLocation(FlightLocationType.LOCATION_DEPARTURE, location1));
        assertTrue(flight1.getLocationLink().contains(FlightLocationType.LOCATION_DEPARTURE, location1));

        assertDoesNotThrow(() -> flight1.removeLocation(FlightLocationType.LOCATION_DEPARTURE, location1));
        assertFalse(flight1.getLocationLink().contains(FlightLocationType.LOCATION_DEPARTURE, location1));
    }

    @Test
    public void testGetDisplayList() {
        List<String> result = flight1.getDisplayList();
        assertEquals(5, result.size());
        assertEquals(code1, result.get(0));
        assertEquals(
                String.format("%s: %s\n", "Plane", flight1.getPlaneLink().toString()),
                result.get(1));
        assertEquals(
                String.format("%s: %s\n", "Pilots", flight1.getPilotLink().toString()),
                result.get(2));
        assertEquals(
                String.format("%s: %s\n", "Crew", flight1.getCrewLink().toString()),
                result.get(3));
        assertEquals(
                String.format("%s: %s\n", "Locations", flight1.getLocationLink().toString()),
                result.get(4));
    }

    @Test
    public void testId() {
        assertNotNull(flight1.getId());
    }

    @Test
    public void testToString() {
        assertEquals(code1, flight1.toString());
        assertEquals(code2, flight2.toString());
        assertEquals(code1, flight3.toString());
    }

    @Test
    public void testEquals() {
        // positive cases
        assertEquals(flight1, flight1);
        assertEquals(flight2, flight2);
        assertEquals(flight1, flight3);

        // negative cases
        assertNotEquals(flight1, flight2);
        assertNotEquals(flight2, flight1);
        assertNotEquals(flight2, flight3);
        assertNotEquals(flight1, "flight1");
    }
}
