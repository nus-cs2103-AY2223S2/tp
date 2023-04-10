package seedu.address.logic.recommender.location;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.location.util.TypicalLocation.MAYFLOWER;
import static seedu.address.model.timetable.util.TypicalTime.ELEVEN_AM;
import static seedu.address.model.timetable.util.TypicalTime.NINE_AM;
import static seedu.address.model.timetable.util.TypicalTime.ONE_PM;
import static seedu.address.model.timetable.util.TypicalTime.TEN_AM;
import static seedu.address.model.timetable.util.TypicalTime.TWELVE_PM;
import static seedu.address.testutil.TypicalPersons.ALBERT;
import static seedu.address.testutil.TypicalPersons.ANG;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.model.location.Location;
import seedu.address.model.location.util.DistanceUtil;
import seedu.address.model.time.Day;
import seedu.address.model.time.HourBlock;
import seedu.address.model.time.TimePeriod;

public class LocationTrackerTest {

    private final LocationTracker tracker = new LocationTracker(ANG);

    @Test
    void constructor_validPerson_success() {
        assertDoesNotThrow(() -> new LocationTracker(ALBERT));
    }

    @Test
    void getLocation_atHome_success() {
        TimePeriod periodAtHome = new HourBlock(TWELVE_PM, Day.MONDAY);
        Optional<Location> optionalLocation = tracker.getLocation(periodAtHome);

        assertTrue(optionalLocation.isPresent());

        Location location = optionalLocation.get();

        assertEquals(location, MAYFLOWER);
    }

    @Test
    void getLocation_inSchool_success() {
        TimePeriod periodInSchool = new HourBlock(TWELVE_PM, Day.TUESDAY);
        Optional<Location> optionalLocation = tracker.getLocation(periodInSchool);
        assertTrue(optionalLocation.isPresent());
        Location location = optionalLocation.get();

        assertEquals(location, Location.NUS);

        periodInSchool = new HourBlock(ELEVEN_AM, Day.TUESDAY);
        optionalLocation = tracker.getLocation(periodInSchool);
        assertTrue(optionalLocation.isPresent());
        location = optionalLocation.get();

        assertEquals(location, Location.NUS);
    }

    @Test
    void getLocation_shouldStayInSchool_success() {
        TimePeriod periodInSchool = new HourBlock(TWELVE_PM, Day.WEDNESDAY);
        Optional<Location> optionalLocation = tracker.getLocation(periodInSchool);
        assertTrue(optionalLocation.isPresent());
        Location location = optionalLocation.get();

        assertEquals(location, Location.NUS);

        periodInSchool = new HourBlock(ELEVEN_AM, Day.WEDNESDAY);
        optionalLocation = tracker.getLocation(periodInSchool);
        assertTrue(optionalLocation.isPresent());
        location = optionalLocation.get();

        assertEquals(location, Location.NUS);

        periodInSchool = new HourBlock(ONE_PM, Day.WEDNESDAY);
        optionalLocation = tracker.getLocation(periodInSchool);
        assertTrue(optionalLocation.isPresent());
        location = optionalLocation.get();

        assertEquals(location, Location.NUS);
    }

    @Test
    void getLocation_inTransit_success() {
        TimePeriod periodInSchool = new HourBlock(NINE_AM, Day.TUESDAY);
        Optional<Location> optionalLocation = tracker.getLocation(periodInSchool);

        assertTrue(optionalLocation.isPresent());

        Location location = optionalLocation.get();

        assertNotEquals(location, Location.NUS);
        assertNotEquals(location, MAYFLOWER);
    }

    @Test
    void getLocation_inTransit_slowlyMoves() {
        TimePeriod periodInSchool = new HourBlock(NINE_AM, Day.THURSDAY);
        Optional<Location> optionalLocation = tracker.getLocation(periodInSchool);
        assertTrue(optionalLocation.isPresent());
        Location location = optionalLocation.get();

        assertEquals(location, Location.NUS);

        periodInSchool = new HourBlock(TEN_AM, Day.THURSDAY);
        optionalLocation = tracker.getLocation(periodInSchool);
        assertTrue(optionalLocation.isPresent());
        location = optionalLocation.get();
        double distanceFromNus = DistanceUtil.getDistance(location, Location.NUS);
        double distanceFromHome = DistanceUtil.getDistance(location, MAYFLOWER);

        assertNotEquals(location, Location.NUS);
        assertNotEquals(location, MAYFLOWER);

        periodInSchool = new HourBlock(TWELVE_PM, Day.THURSDAY);
        optionalLocation = tracker.getLocation(periodInSchool);
        assertTrue(optionalLocation.isPresent());
        location = optionalLocation.get();
        double newDistanceFromNus = DistanceUtil.getDistance(location, Location.NUS);
        double newDistanceFromHome = DistanceUtil.getDistance(location, MAYFLOWER);

        assertNotEquals(location, Location.NUS);
        assertNotEquals(location, MAYFLOWER);

        assertTrue(distanceFromNus < newDistanceFromNus);
        assertTrue(distanceFromHome > newDistanceFromHome);
        assertTrue(Math.abs(distanceFromHome
                + distanceFromNus
                - newDistanceFromHome
                - newDistanceFromNus) < 0.001);
    }

    @Test
    void toString_noLessons_noNus() {
        LocationTracker locationTracker = new LocationTracker(ALBERT);

        assertFalse(locationTracker.toString().contains("NUS"));
    }
}
