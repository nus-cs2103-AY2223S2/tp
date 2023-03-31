package seedu.address.logic.recommender.location;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.model.location.util.TypicalLocation.ADMIRALTY;
import static seedu.address.model.location.util.TypicalLocation.ANG_MO_KIO;
import static seedu.address.model.location.util.TypicalLocation.BEDOK;
import static seedu.address.model.location.util.TypicalLocation.BISHAN;
import static seedu.address.model.location.util.TypicalLocation.BRADDELL;
import static seedu.address.model.location.util.TypicalLocation.BUKIT_PANJANG;
import static seedu.address.model.location.util.TypicalLocation.CITY_HALL;
import static seedu.address.model.location.util.TypicalLocation.CLEMENTI;
import static seedu.address.model.location.util.TypicalLocation.DOVER;
import static seedu.address.model.location.util.TypicalLocation.HARBOURFRONT;
import static seedu.address.model.location.util.TypicalLocation.HONG_KAH;
import static seedu.address.model.location.util.TypicalLocation.HOUGANG;
import static seedu.address.model.location.util.TypicalLocation.HUME;
import static seedu.address.model.location.util.TypicalLocation.JURONG_EAST;
import static seedu.address.model.location.util.TypicalLocation.JURONG_TOWN_HALL;
import static seedu.address.model.location.util.TypicalLocation.JURONG_WEST;
import static seedu.address.model.location.util.TypicalLocation.MARINA_SOUTH;
import static seedu.address.model.location.util.TypicalLocation.MARSILING;
import static seedu.address.model.location.util.TypicalLocation.NOVENA;
import static seedu.address.model.location.util.TypicalLocation.ORCHARD;
import static seedu.address.model.location.util.TypicalLocation.PASIR_RIS;
import static seedu.address.model.location.util.TypicalLocation.PAYA_LEBAR;
import static seedu.address.model.location.util.TypicalLocation.SIMEI;
import static seedu.address.model.location.util.TypicalLocation.TANAH_MERAH;
import static seedu.address.model.location.util.TypicalLocation.TELOK_AYER;
import static seedu.address.model.location.util.TypicalLocation.TELOK_BLANGAH;
import static seedu.address.model.location.util.TypicalLocation.WOODLANDS;
import static seedu.address.model.location.util.TypicalLocation.WOODLANDS_SOUTH;
import static seedu.address.model.location.util.TypicalLocation.YISHUN;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.location.Location;
import seedu.address.model.location.util.LocationDataUtil;

public class LocationRecommenderTest {
    private final LocationRecommender recommender = new LocationRecommender();
    private final Set<Location> destinations =
            Set.of(TANAH_MERAH, BISHAN, WOODLANDS, CLEMENTI, HARBOURFRONT);

    @Test
    void constructor_validArgs_success() {
        assertDoesNotThrow(LocationRecommender::new);
    }

    @Test
    void initialise_validArgs_success() {
        assertDoesNotThrow(() -> recommender.initialise(LocationDataUtil.EAT_LOCATIONS));
    }

    @Test
    void recommend_east_eastRecommended() {
        Set<Location> eastLocations = Set.of(SIMEI, PASIR_RIS, BEDOK, PAYA_LEBAR);
        recommender.initialise(destinations);
        Location topRecommendation = recommender.recommend(eastLocations).get(0);
        assertEquals(topRecommendation, TANAH_MERAH);
    }

    @Test
    void recommend_north_northRecommended() {
        Set<Location> northLocations = Set.of(ADMIRALTY, WOODLANDS_SOUTH, HUME,
                BUKIT_PANJANG, YISHUN, MARSILING);
        recommender.initialise(destinations);
        Location topRecommendation = recommender.recommend(northLocations).get(0);
        assertEquals(topRecommendation, WOODLANDS);
    }

    @Test
    void recommend_south_southRecommended() {
        Set<Location> southLocations = Set.of(TELOK_BLANGAH, TELOK_AYER,
                CLEMENTI, MARINA_SOUTH, CITY_HALL);
        recommender.initialise(destinations);
        Location topRecommendation = recommender.recommend(southLocations).get(0);
        assertEquals(topRecommendation, HARBOURFRONT);
    }

    @Test
    void recommend_west_westRecommended() {
        Set<Location> westLocations = Set.of(CLEMENTI, DOVER, JURONG_WEST,
                JURONG_TOWN_HALL, JURONG_EAST, HONG_KAH);
        recommender.initialise(destinations);
        Location topRecommendation = recommender.recommend(westLocations).get(0);
        assertEquals(topRecommendation, CLEMENTI);
    }

    @Test
    void recommend_central_centralRecommended() {
        Set<Location> centralLocations = Set.of(ANG_MO_KIO, BRADDELL, NOVENA,
                ORCHARD, HOUGANG);
        recommender.initialise(destinations);
        Location topRecommendation = recommender.recommend(centralLocations).get(0);
        assertEquals(topRecommendation, BISHAN);
    }
}
