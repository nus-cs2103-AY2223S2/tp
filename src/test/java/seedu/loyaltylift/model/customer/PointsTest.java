package seedu.loyaltylift.model.customer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.loyaltylift.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PointsTest {

    @Test
    public void constructor_nullCurrentPoints_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Points(null, 100));
    }

    @Test
    public void constructor_nullCumulativePoints_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Points(100, null));
    }
    @Test
    public void equals() {
        Points points = new Points(100, 100);

        // same object -> returns true
        assertTrue(points.equals(points));

        // same values -> returns true
        Points pointsCopy = new Points(points.value, points.cumulative);
        assertTrue(points.equals(pointsCopy));

        // different types -> returns false
        assertFalse(points.equals(1));

        // null -> returns false
        assertFalse(points.equals(null));

        // different current points -> returns false
        // though the case where cumulative points < current points should not happen
        Points differentCurrentPoints = new Points(200, 100);
        assertFalse(points.equals(differentCurrentPoints));

        // different cumulative points -> returns false
        Points differentCumulativePoints = new Points(100, 200);
        assertFalse(points.equals(differentCumulativePoints));

        // different current and cumulative points -> returns false
        Points differentPoints = new Points(200, 200);
        assertFalse(points.equals(differentPoints));
    }

    @Test
    public void isValidPoints() {

        // invalid parts
        assertFalse(Points.isValidPoints(-1)); // negative points
        assertFalse(Points.isValidPoints(Points.MAXIMUM_POINTS + 1)); // above maximum points

        // valid email
        assertTrue(Points.isValidPoints(Points.MINIMUM_POINTS)); // minimum points
        assertTrue(Points.isValidPoints(Points.MAXIMUM_POINTS)); // maximum points
        assertTrue(Points.isValidPoints(100)); // in between minimum and maximum points
    }

    @Test
    public void isValidAdditionPoints() {

        // invalid parts
        assertFalse(Points.isValidAddition(Points.MAXIMUM_POINTS_SUBTRACT - 1)); // below maximum points to subtract
        assertFalse(Points.isValidAddition(Points.MAXIMUM_POINTS_ADD + 1)); // above maximum points to add

        // valid email
        assertTrue(Points.isValidAddition(Points.MAXIMUM_POINTS_SUBTRACT)); // maximum points to subtract
        assertTrue(Points.isValidAddition(Points.MAXIMUM_POINTS_ADD)); // maximum points to add
        assertTrue(Points.isValidAddition(100)); // in between minimum and maximum points
    }
}
