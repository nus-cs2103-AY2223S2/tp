package seedu.loyaltylift.model.customer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
public class PointsTest {
    @Test
    public void equals() {
        Points points = new Points(100);

        // same object -> returns true
        assertTrue(points.equals(points));

        // same values -> returns true
        Points pointsCopy = new Points(points.value);
        assertTrue(points.equals(pointsCopy));

        // different types -> returns false
        assertFalse(points.equals(1));

        // null -> returns false
        assertFalse(points.equals(null));

        // different remark -> returns false
        Points differentPoints = new Points(200);
        assertFalse(points.equals(differentPoints));
    }
}
