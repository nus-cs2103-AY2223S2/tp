package seedu.address.model.location;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.location.util.TypicalLocation.ADMIRALTY;
import static seedu.address.model.location.util.TypicalLocation.ANG_MO_KIO;
import static seedu.address.model.location.util.TypicalLocation.BEDOK;
import static seedu.address.model.location.util.TypicalLocation.BISHAN;
import static seedu.address.model.location.util.TypicalLocation.BOON_LAY;
import static seedu.address.model.location.util.TypicalLocation.BRADDELL;
import static seedu.address.model.location.util.TypicalLocation.BUONA_VISTA;
import static seedu.address.model.location.util.TypicalLocation.CANBERRA;
import static seedu.address.model.location.util.TypicalLocation.CHANGI_AIRPORT;
import static seedu.address.model.location.util.TypicalLocation.CHOA_CHU_KANG;
import static seedu.address.model.location.util.TypicalLocation.CITY_HALL;
import static seedu.address.model.location.util.TypicalLocation.CLEMENTI;
import static seedu.address.model.location.util.TypicalLocation.DHOBY_GHAUT;
import static seedu.address.model.location.util.TypicalLocation.EUNOS;
import static seedu.address.model.location.util.TypicalLocation.HARBOURFRONT;
import static seedu.address.model.location.util.TypicalLocation.HOUGANG;
import static seedu.address.model.location.util.TypicalLocation.JOO_KOON;
import static seedu.address.model.location.util.TypicalLocation.KHATIB;
import static seedu.address.model.location.util.TypicalLocation.KRANJI;
import static seedu.address.model.location.util.TypicalLocation.LABRADOR_PARK;
import static seedu.address.model.location.util.TypicalLocation.NOVENA;
import static seedu.address.model.location.util.TypicalLocation.ORCHARD;
import static seedu.address.model.location.util.TypicalLocation.PASIR_RIS;
import static seedu.address.model.location.util.TypicalLocation.PAYA_LEBAR;
import static seedu.address.model.location.util.TypicalLocation.PIONEER;
import static seedu.address.model.location.util.TypicalLocation.SEMBAWANG;
import static seedu.address.model.location.util.TypicalLocation.SIMEI;
import static seedu.address.model.location.util.TypicalLocation.TAMPINES;
import static seedu.address.model.location.util.TypicalLocation.TELOK_AYER;
import static seedu.address.model.location.util.TypicalLocation.TELOK_BLANGAH;
import static seedu.address.model.location.util.TypicalLocation.TOA_PAYOH;
import static seedu.address.model.location.util.TypicalLocation.TUAS_CRESCENT;
import static seedu.address.model.location.util.TypicalLocation.WOODLANDS;
import static seedu.address.model.location.util.TypicalLocation.YISHUN;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.model.location.util.DistanceUtil;

public class DistanceUtilTest {

    private static final List<Location> CORNERS_DESTINATIONS =
            List.of(WOODLANDS, BISHAN, PASIR_RIS, JOO_KOON, HARBOURFRONT);

    @Test
    void getDistance_near_commutative() {
        assertEquals(
                DistanceUtil.getDistance(SIMEI, BEDOK),
                DistanceUtil.getDistance(BEDOK, SIMEI));
    }

    @Test
    void getDistance_far_commutative() {
        assertEquals(
                DistanceUtil.getDistance(PASIR_RIS, JOO_KOON),
                DistanceUtil.getDistance(JOO_KOON, PASIR_RIS));
    }

    @Test
    void getDistance_shortShortLong_triangleInequality() {
        assertTrue(DistanceUtil.getDistance(SIMEI, BEDOK)
                + DistanceUtil.getDistance(BEDOK, EUNOS)
                >= DistanceUtil.getDistance(SIMEI, EUNOS));
    }

    @Test
    void getDistance_longShortShort_triangleInequality() {
        assertTrue(DistanceUtil.getDistance(BISHAN, TOA_PAYOH)
                + DistanceUtil.getDistance(TOA_PAYOH, BRADDELL)
                >= DistanceUtil.getDistance(BISHAN, BRADDELL));
    }

    @Test
    void getDistance_shortShortShortLong_triangleInequality() {
        assertTrue(DistanceUtil.getDistance(BISHAN, BRADDELL)
                + DistanceUtil.getDistance(BRADDELL, TOA_PAYOH)
                + DistanceUtil.getDistance(TOA_PAYOH, NOVENA)
                >= DistanceUtil.getDistance(BISHAN, NOVENA));
    }

    @Test
    void getDistance_sameObject_distanceIsZero() {
        assertEquals(0, DistanceUtil.getDistance(SIMEI, SIMEI));
    }

    @Test
    void getMidpoint_near_commutative() {
        assertEquals(
                DistanceUtil.getMidpoint(SIMEI, BEDOK),
                DistanceUtil.getMidpoint(BEDOK, SIMEI));
    }

    @Test
    void getMidpoint_far_commutative() {
        assertEquals(
                DistanceUtil.getMidpoint(PASIR_RIS, JOO_KOON),
                DistanceUtil.getMidpoint(JOO_KOON, PASIR_RIS));
    }

    @Test
    void getMidpoint_tripleNear_commutative() {
        assertEquals(
                DistanceUtil.getMidpoint(BISHAN, BRADDELL, TOA_PAYOH),
                DistanceUtil.getMidpoint(TOA_PAYOH, BRADDELL, BISHAN));
    }

    @Test
    void getMidpoint_quadrupleFar_commutative() {
        assertEquals(
                DistanceUtil.getMidpoint(PASIR_RIS, WOODLANDS, JOO_KOON, HARBOURFRONT),
                DistanceUtil.getMidpoint(WOODLANDS, HARBOURFRONT, JOO_KOON, PASIR_RIS));
    }

    @Test
    void getMidpoint_nearFar_associative() {
        assertEquals(
                DistanceUtil.getMidpoint(
                        DistanceUtil.getMidpoint(BISHAN, BRADDELL),
                        DistanceUtil.getMidpoint(BEDOK, SIMEI)),
                DistanceUtil.getMidpoint(
                        DistanceUtil.getMidpoint(BISHAN, BEDOK),
                        DistanceUtil.getMidpoint(BRADDELL, SIMEI)));
    }

    @Test
    void getMidpoint_farFar_associative() {
        assertEquals(
                DistanceUtil.getMidpoint(List.of(
                        DistanceUtil.getMidpoint(PASIR_RIS, WOODLANDS),
                        DistanceUtil.getMidpoint(JOO_KOON, HARBOURFRONT))),
                DistanceUtil.getMidpoint(List.of(
                        PASIR_RIS, WOODLANDS, JOO_KOON, HARBOURFRONT)));
    }

    @Test
    void getMidpoint_triple_associative() {
        assertEquals(
                DistanceUtil.getMidpoint(BISHAN, BRADDELL, TOA_PAYOH),
                DistanceUtil.getMidpoint(
                        DistanceUtil.getMidpoint(BISHAN, TOA_PAYOH),
                        DistanceUtil.getMidpoint(BRADDELL, TOA_PAYOH),
                        DistanceUtil.getMidpoint(BISHAN, BRADDELL)));
    }

    @Test
    void getMidpoint_double_reflexive() {
        assertEquals(SIMEI,
                DistanceUtil.getMidpoint(SIMEI, SIMEI));
    }

    @Test
    void getMidpoint_triple_reflexive() {
        assertEquals(SIMEI,
                DistanceUtil.getMidpoint(SIMEI, SIMEI, SIMEI));
    }

    @Test
    void getClosestPoint_double_reflexive() {
        assertEquals(Optional.of(SIMEI),
                DistanceUtil.getClosestPoint(SIMEI, SIMEI));
    }

    @Test
    void getClosestPoint_triple_reflexive() {
        assertEquals(Optional.of(BEDOK),
                DistanceUtil.getClosestPoint(BEDOK, BEDOK, BEDOK));
    }

    @Test
    void getClosestPoint_empty_empty() {
        assertTrue(DistanceUtil.getClosestPoint(BISHAN).isEmpty());
    }

    @Test
    void getClosestPoint_validEast_east() {
        assertEquals(Optional.of(PASIR_RIS),
                DistanceUtil.getClosestPoint(BEDOK, CORNERS_DESTINATIONS));
    }

    @Test
    void getClosestPoint_validWest_west() {
        assertEquals(Optional.of(JOO_KOON),
                DistanceUtil.getClosestPoint(BOON_LAY, CORNERS_DESTINATIONS));
    }

    @Test
    void getClosestPoint_validNorth_north() {
        assertEquals(Optional.of(WOODLANDS),
                DistanceUtil.getClosestPoint(KRANJI, CORNERS_DESTINATIONS));
    }

    @Test
    void getClosestPoint_validSouth_south() {
        assertEquals(Optional.of(HARBOURFRONT),
                DistanceUtil.getClosestPoint(CITY_HALL, CORNERS_DESTINATIONS));
    }

    @Test
    void getClosestPoint_validCentral_central() {
        assertEquals(Optional.of(BISHAN),
                DistanceUtil.getClosestPoint(ANG_MO_KIO, CORNERS_DESTINATIONS));
    }

    @Test
    void getClosestPoint_validEastWithMidpoint_east() {
        Location midpoint = DistanceUtil.getMidpoint(BEDOK, SIMEI, PAYA_LEBAR, CHANGI_AIRPORT);
        assertEquals(Optional.of(PASIR_RIS),
                DistanceUtil.getClosestPoint(midpoint, CORNERS_DESTINATIONS));
    }

    @Test
    void getClosestPoint_validWestWithMidpoint_west() {
        Location midpoint = DistanceUtil.getMidpoint(BOON_LAY, PIONEER, BUONA_VISTA, TUAS_CRESCENT);
        assertEquals(Optional.of(JOO_KOON),
                DistanceUtil.getClosestPoint(midpoint, CORNERS_DESTINATIONS));
    }

    @Test
    void getClosestPoint_validNorthWithMidpoint_north() {
        Location midpoint = DistanceUtil.getMidpoint(KRANJI, ADMIRALTY, YISHUN, CHOA_CHU_KANG);
        assertEquals(Optional.of(WOODLANDS),
                DistanceUtil.getClosestPoint(midpoint, CORNERS_DESTINATIONS));
    }

    @Test
    void getClosestPoint_validSouthWithMidPoint_south() {
        Location midpoint = DistanceUtil.getMidpoint(CLEMENTI, TELOK_BLANGAH, TELOK_AYER, ORCHARD);
        assertEquals(Optional.of(HARBOURFRONT),
                DistanceUtil.getClosestPoint(midpoint, CORNERS_DESTINATIONS));
    }

    @Test
    void getClosestPoint_validCentralWithMidPoint_central() {
        Location midpoint = DistanceUtil.getMidpoint(BRADDELL, ANG_MO_KIO, HOUGANG, ORCHARD);
        assertEquals(Optional.of(BISHAN),
                DistanceUtil.getClosestPoint(midpoint, CORNERS_DESTINATIONS));
    }

    @Test
    void getClosestPoint_validCentralWithFarMidpoint_central() {
        Location midpoint = DistanceUtil.getMidpoint(TAMPINES, ADMIRALTY, LABRADOR_PARK, CLEMENTI);
        assertEquals(Optional.of(BISHAN),
                DistanceUtil.getClosestPoint(midpoint, CORNERS_DESTINATIONS));
    }

    @Test
    void getClosestPoints_single_reflexive() {
        assertEquals(List.of(SIMEI),
                DistanceUtil.getClosestPoints(SIMEI, 1, SIMEI));
    }

    @Test
    void getClosestPoints_double_reflexive() {
        assertEquals(List.of(BEDOK, BEDOK),
                DistanceUtil.getClosestPoints(BEDOK, 2, List.of(BEDOK, BEDOK)));
    }

    @Test
    void getClosestPoints_empty_empty() {
        assertTrue(DistanceUtil.getClosestPoints(EUNOS, 1, List.of()).isEmpty());
    }

    @Test
    void getClosestPoints_fromNorth_success() {
        List<Location> destinations = List.of(CANBERRA, KHATIB,
                ANG_MO_KIO, BRADDELL, NOVENA, ORCHARD);

        List<Location> expectedLocations = List.of(CANBERRA, KHATIB, ANG_MO_KIO);
        assertEquals(expectedLocations,
                DistanceUtil.getClosestPoints(SEMBAWANG, 3, destinations));
    }

    @Test
    void getClosestPoints_fromSouth_success() {
        List<Location> destinations = List.of(CANBERRA, KHATIB,
                ANG_MO_KIO, BRADDELL, NOVENA, ORCHARD);

        List<Location> expectedLocations = List.of(ORCHARD, NOVENA, BRADDELL, ANG_MO_KIO);
        assertEquals(expectedLocations,
                DistanceUtil.getClosestPoints(DHOBY_GHAUT, 4, destinations));
    }

    @Test
    void getApproximateLocations() {
        Location midpoint = DistanceUtil.getMidpoint(PASIR_RIS, SIMEI);
        Location approximateMidpoint = DistanceUtil.getApproximateLocations(PASIR_RIS, SIMEI, 1).get(0);
        assertEquals(midpoint, approximateMidpoint);
    }
}
