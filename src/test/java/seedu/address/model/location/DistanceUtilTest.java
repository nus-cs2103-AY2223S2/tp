package seedu.address.model.location;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

public class DistanceUtilTest {
    private static final double LAT_1 = 1.3405;
    private static final double LON_1 = 103.8126;
    private static final double LAT_2 = 1.4012;
    private static final double LON_2 = 103.7956;
    private static final double LAT_3 = 1.4011;
    private static final double LON_3 = 103.8011;
    private static final double LAT_4 = 1.3759;
    private static final double LON_4 = 103.8436;

    private static final Location LOCATION_1 = new Location(LAT_1, LON_1);
    private static final Location LOCATION_2 = new Location(LAT_2, LON_2);
    private static final Location LOCATION_3 = new Location(LAT_3, LON_3);
    private static final Location LOCATION_4 = new Location(LAT_4, LON_4);

    @Test
    void getDistance() {
        // commutative
        assertEquals(
                DistanceUtil.getDistance(LOCATION_1, LOCATION_2),
                DistanceUtil.getDistance(LOCATION_2, LOCATION_1));
        assertEquals(
                DistanceUtil.getDistance(LOCATION_1, LOCATION_3),
                DistanceUtil.getDistance(LOCATION_3, LOCATION_1));
        assertEquals(
                DistanceUtil.getDistance(LOCATION_3, LOCATION_2),
                DistanceUtil.getDistance(LOCATION_2, LOCATION_3));

        // triangle inequality
        assertTrue(DistanceUtil.getDistance(LOCATION_1, LOCATION_2)
                + DistanceUtil.getDistance(LOCATION_2, LOCATION_3)
                >= DistanceUtil.getDistance(LOCATION_1, LOCATION_3));
        assertTrue(DistanceUtil.getDistance(LOCATION_2, LOCATION_1)
                + DistanceUtil.getDistance(LOCATION_1, LOCATION_3)
                >= DistanceUtil.getDistance(LOCATION_2, LOCATION_3));
        assertTrue(DistanceUtil.getDistance(LOCATION_1, LOCATION_3)
                + DistanceUtil.getDistance(LOCATION_3, LOCATION_2)
                >= DistanceUtil.getDistance(LOCATION_1, LOCATION_2));

        // reflexivity
        assertEquals(0, DistanceUtil.getDistance(LOCATION_1, LOCATION_1));
        assertEquals(0, DistanceUtil.getDistance(LOCATION_2, LOCATION_2));
        assertEquals(0, DistanceUtil.getDistance(LOCATION_3, LOCATION_3));
    }

    @Test
    void getMidpoint() {
        // commutativity
        assertEquals(
                DistanceUtil.getMidpoint(List.of(LOCATION_1, LOCATION_2)),
                DistanceUtil.getMidpoint(List.of(LOCATION_2, LOCATION_1)));
        assertEquals(
                DistanceUtil.getMidpoint(List.of(LOCATION_3, LOCATION_2)),
                DistanceUtil.getMidpoint(List.of(LOCATION_2, LOCATION_3)));
        assertEquals(
                DistanceUtil.getMidpoint(List.of(LOCATION_1, LOCATION_3)),
                DistanceUtil.getMidpoint(List.of(LOCATION_3, LOCATION_1)));
        assertEquals(
                DistanceUtil.getMidpoint(List.of(LOCATION_1, LOCATION_2, LOCATION_3)),
                DistanceUtil.getMidpoint(List.of(LOCATION_3, LOCATION_1, LOCATION_2)));

        // associativity
        assertEquals(
                DistanceUtil.getMidpoint(List.of(
                        DistanceUtil.getMidpoint(List.of(LOCATION_1, LOCATION_2)),
                        DistanceUtil.getMidpoint(List.of(LOCATION_3, LOCATION_4)))),
                DistanceUtil.getMidpoint(List.of(
                        DistanceUtil.getMidpoint(List.of(LOCATION_1, LOCATION_3)),
                        DistanceUtil.getMidpoint(List.of(LOCATION_2, LOCATION_4)))));
        assertEquals(
                DistanceUtil.getMidpoint(List.of(
                        DistanceUtil.getMidpoint(List.of(LOCATION_1, LOCATION_2)),
                        DistanceUtil.getMidpoint(List.of(LOCATION_3, LOCATION_4)))),
                DistanceUtil.getMidpoint(List.of(
                        LOCATION_1, LOCATION_2, LOCATION_3, LOCATION_4)));
        assertEquals(
                DistanceUtil.getMidpoint(List.of(LOCATION_1, LOCATION_2, LOCATION_3)),
                DistanceUtil.getMidpoint(List.of(
                        DistanceUtil.getMidpoint(List.of(LOCATION_1, LOCATION_2)),
                        DistanceUtil.getMidpoint(List.of(LOCATION_2, LOCATION_3)),
                        DistanceUtil.getMidpoint(List.of(LOCATION_1, LOCATION_3)))));

        // reflexivity
        assertEquals(LOCATION_1,
                DistanceUtil.getMidpoint(List.of(LOCATION_1, LOCATION_1)));
        assertEquals(LOCATION_2,
                DistanceUtil.getMidpoint(List.of(LOCATION_2, LOCATION_2, LOCATION_2)));
        assertEquals(LOCATION_3,
                DistanceUtil.getMidpoint(List.of(LOCATION_3)));
    }

    @Test
    void getClosestPoint() {
        // reflexivity
        assertEquals(Optional.of(LOCATION_1),
                DistanceUtil.getClosestPoint(LOCATION_1, List.of(LOCATION_1)));
        assertEquals(Optional.of(LOCATION_2),
                DistanceUtil.getClosestPoint(LOCATION_2, List.of(LOCATION_2, LOCATION_2)));
        assertTrue(DistanceUtil.getClosestPoint(LOCATION_3, List.of()).isEmpty());

        // valid permutations
        assertEquals(Optional.of(LOCATION_2),
                DistanceUtil.getClosestPoint(LOCATION_3, List.of(LOCATION_1, LOCATION_2)));
        assertEquals(Optional.of(LOCATION_1),
                DistanceUtil.getClosestPoint(LOCATION_1, List.of(LOCATION_1, LOCATION_2)));
        assertEquals(Optional.of(LOCATION_1),
                DistanceUtil.getClosestPoint(
                        DistanceUtil.getMidpoint(List.of(LOCATION_1, LOCATION_1, LOCATION_2)),
                        List.of(LOCATION_1, LOCATION_2)));
    }

    @Test
    void getClosestPoints() {
        // reflexivity
        assertEquals(List.of(LOCATION_1),
                DistanceUtil.getClosestPoints(LOCATION_1, 1, List.of(LOCATION_1)));
        assertEquals(List.of(LOCATION_2, LOCATION_2),
                DistanceUtil.getClosestPoints(LOCATION_2, 2, List.of(LOCATION_2, LOCATION_2)));
        assertTrue(DistanceUtil.getClosestPoints(LOCATION_3, 1, List.of()).isEmpty());
    }
}
