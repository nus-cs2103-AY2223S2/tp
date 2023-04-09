package wingman.model.plane;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import wingman.model.location.Location;

public class PlaneTest {
    private final String model1 = "model";
    private final String model2 = " ";

    private final int age1 = 1;
    private final int age2 = -1;

    private final Plane plane1 = new Plane(model1, age1);
    private final Plane plane2 = new Plane(model2, age1);
    private final Plane plane3 = new Plane(model1, age2);
    private final Plane plane4 = new Plane(model1, age1);
    private final Location location = new Location("location");

    @Test
    public void testPlane() {
        // positive test case
        assertDoesNotThrow(() -> new Plane(model1, age1));
        assertDoesNotThrow(() -> new Plane(model2, age1));
        assertDoesNotThrow(() -> new Plane(model1, age2));
    }

    @Test
    public void testGetModel() {
        assertEquals(model1, plane1.getModel());
    }

    @Test
    public void testGetDisplayList() {
        assertEquals(List.of(model1, String.format("Age: %s", age1)), plane1.getDisplayList());
    }

    @Test
    public void testToString() {
        assertEquals(model1, plane1.toString());
        assertEquals(model2, plane2.toString());
        assertEquals(model1, plane3.toString());
        assertEquals(model1, plane4.toString());
    }

    @Test
    public void testEquals() {
        assertEquals(plane1, plane1);
        assertEquals(plane2, plane2);
        assertEquals(plane3, plane3);
        assertEquals(plane4, plane4);
        assertEquals(plane1, plane4);
        assertEquals(plane4, plane1);

        assertNotEquals(plane1, plane2);
        assertNotEquals(plane2, plane1);
        assertNotEquals(plane1, plane3);
        assertNotEquals(plane3, plane1);
        assertNotEquals(plane2, plane3);
        assertNotEquals(plane3, plane2);
        assertNotEquals(plane1, location);
    }
}
