package seedu.loyaltylift.model.customer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
public class PointsTest {
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

        // different points -> returns false
        Points differentPoints = new Points(200, 200);
        assertFalse(points.equals(differentPoints));
    }

    @Test
    public void addPointsEquals() {
        Points.AddPoints addPoints = new Points.AddPoints(100, Points.AddPoints.Modifier.PLUS);

        // same object -> returns true
        assertTrue(addPoints.equals(addPoints));

        // same values -> returns true
        Points.AddPoints addPointsCopy = new Points.AddPoints(addPoints.value, addPoints.modifier);
        assertTrue(addPoints.equals(addPointsCopy));

        // different types -> returns false
        assertFalse(addPoints.equals(1));

        // null -> returns false
        assertFalse(addPoints.equals(null));

        // different addpoints -> returns false
        Points.AddPoints differentAddPoints = new Points.AddPoints(100, Points.AddPoints.Modifier.MINUS);
        assertFalse(addPoints.equals(differentAddPoints));
    }
}
