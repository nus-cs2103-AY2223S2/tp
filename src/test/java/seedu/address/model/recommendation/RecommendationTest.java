package seedu.address.model.recommendation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.location.util.TypicalLocation.PUNGGOL;
import static seedu.address.model.location.util.TypicalLocation.SEMBAWANG;
import static seedu.address.model.location.util.TypicalLocation.STEVENS;
import static seedu.address.model.location.util.TypicalLocation.TAN_KAH_KEE;
import static seedu.address.model.timetable.util.TypicalTime.ONE_PM;
import static seedu.address.model.timetable.util.TypicalTime.TEN_AM;
import static seedu.address.model.timetable.util.TypicalTime.THREE_PM;
import static seedu.address.model.timetable.util.TypicalTime.TWELVE_PM;
import static seedu.address.model.timetable.util.TypicalTime.TWO_PM;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.ContactIndex;
import seedu.address.model.time.Day;
import seedu.address.model.time.HourBlock;
import seedu.address.model.time.TimeBlock;
import seedu.address.model.time.TimePeriod;

public class RecommendationTest {

    private static final ContactIndex CONTACT_INDEX = new ContactIndex(4);
    private static final TimePeriod TIME_PERIOD =
            new TimeBlock(TEN_AM, ONE_PM, Day.THURSDAY);
    private static final Recommendation RECOMMENDATION =
            new Recommendation(STEVENS, TIME_PERIOD, CONTACT_INDEX, false);

    @Test
    public void constructor_fullConstructor_success() {
        assertDoesNotThrow(() -> new Recommendation(SEMBAWANG, TIME_PERIOD,
                CONTACT_INDEX, false));
    }

    @Test
    public void constructor_noIsSaved_success() {
        assertDoesNotThrow(() -> new Recommendation(STEVENS, TIME_PERIOD,
                CONTACT_INDEX));
    }

    @Test
    public void constructor_noContactIndex_success() {
        assertDoesNotThrow(() -> new Recommendation(STEVENS, TIME_PERIOD, false));
    }

    @Test
    public void constructor_noContactIndexIsSaved_success() {
        assertDoesNotThrow(() -> new Recommendation(STEVENS, TIME_PERIOD));
    }

    @Test
    public void constructor_nullLocation_throwsException() {
        assertThrows(NullPointerException.class, ()
                -> new Recommendation(null, TIME_PERIOD));
        assertThrows(NullPointerException.class, ()
                -> new Recommendation(null, TIME_PERIOD, CONTACT_INDEX));
        assertThrows(NullPointerException.class, ()
                -> new Recommendation(null, TIME_PERIOD, true));
        assertThrows(NullPointerException.class, ()
                -> new Recommendation(null, TIME_PERIOD, CONTACT_INDEX, true));
    }

    @Test
    public void constructor_nullTimePeriod_throwsException() {
        assertThrows(NullPointerException.class, ()
                -> new Recommendation(STEVENS, null));
        assertThrows(NullPointerException.class, ()
                -> new Recommendation(STEVENS, null, CONTACT_INDEX));
        assertThrows(NullPointerException.class, ()
                -> new Recommendation(STEVENS, null, true));
        assertThrows(NullPointerException.class, ()
                -> new Recommendation(STEVENS, null, CONTACT_INDEX, true));
    }

    @Test
    public void constructor_nullContactIndex_throwsException() {
        assertThrows(NullPointerException.class, ()
                -> new Recommendation(STEVENS, TIME_PERIOD, null, true));
    }

    @Test
    public void getLocation_locationGiven_success() {
        assertEquals(RECOMMENDATION.getLocation(), STEVENS);
    }

    @Test
    public void getTimePeriod_timePeriodGiven_success() {
        assertEquals(RECOMMENDATION.getTimePeriod(), TIME_PERIOD);
    }

    @Test
    public void getContactIndex_contactIndexGiven_success() {
        assertEquals(RECOMMENDATION.getContactIndex(), CONTACT_INDEX);
    }

    @Test
    public void getContactIndex_contactIndexNotGiven_zeroIndexGiven() {
        Recommendation recommendationWithoutIndex =
                new Recommendation(STEVENS, TIME_PERIOD, false);
        assertEquals(recommendationWithoutIndex.getContactIndex(), new ContactIndex(0));
    }

    @Test
    public void getIsSaved_isSavedGiven_success() {
        assertFalse(RECOMMENDATION.getIsSaved());
    }

    @Test
    public void getIsSaved_isSavedNotGiven_falseGiven() {
        Recommendation recommendationWithoutIsSaved =
                new Recommendation(STEVENS, TIME_PERIOD, CONTACT_INDEX);
        assertFalse(recommendationWithoutIsSaved.getIsSaved());
    }

    @Test
    public void get_indexIsSavedNotGiven_success() {
        Recommendation recommendation = new Recommendation(STEVENS, TIME_PERIOD);
        assertFalse(recommendation.getIsSaved());
        assertEquals(recommendation.getContactIndex(), new ContactIndex(0));
    }

    @Test
    public void compareTo() {
        Recommendation noonRecommendation =
                new Recommendation(STEVENS, new TimeBlock(TWELVE_PM, THREE_PM, Day.TUESDAY));
        Recommendation sameRecommendation =
                new Recommendation(STEVENS, TIME_PERIOD, CONTACT_INDEX, false);
        assertEquals(noonRecommendation.compareTo(RECOMMENDATION), -1);
        assertEquals(RECOMMENDATION.compareTo(sameRecommendation), 0);
    }

    @Test
    public void equals_sameObject_true() {
        assertEquals(RECOMMENDATION, RECOMMENDATION);
    }

    @Test
    public void equals_sameValues_true() {
        assertEquals(RECOMMENDATION,
                new Recommendation(STEVENS, TIME_PERIOD, CONTACT_INDEX, false));
    }

    @Test
    public void equals_differentValues_false() {
        assertNotEquals(RECOMMENDATION,
                new Recommendation(STEVENS, TIME_PERIOD, CONTACT_INDEX, true));
        assertEquals(RECOMMENDATION,
                new Recommendation(STEVENS, TIME_PERIOD, new ContactIndex(2), false));
        TimePeriod newTimePeriod = new TimeBlock(TEN_AM, TWELVE_PM, Day.THURSDAY);
        assertNotEquals(RECOMMENDATION,
                new Recommendation(STEVENS, newTimePeriod, CONTACT_INDEX, false));
        assertNotEquals(RECOMMENDATION,
                new Recommendation(PUNGGOL, TIME_PERIOD, CONTACT_INDEX, false));
    }

    @Test
    public void equals_differentTypes_false() {
        assertNotEquals(RECOMMENDATION, 2);
        assertNotEquals(RECOMMENDATION, null);
    }

    @Test
    public void toString_validLocation_containsLocationName() {
        assertTrue(RECOMMENDATION.toString().contains(STEVENS.getName()));
    }

    @Test
    public void toString_validTimePeriod_containsTimePeriod() {
        assertTrue(RECOMMENDATION.toString().contains(TIME_PERIOD.toString()));
        assertTrue(RECOMMENDATION.toString().contains(Day.THURSDAY.toString()));
    }

    @Test
    public void toString_validContactIndex_containsContactIndex() {
        assertTrue(RECOMMENDATION.toString().contains(CONTACT_INDEX.toString()));
    }

    @Test
    public void isSameRecommendation_sameFields_true() {
        Recommendation recommendation =
                new Recommendation(STEVENS, TIME_PERIOD, CONTACT_INDEX, false);
        assertTrue(recommendation.isSameRecommendation(RECOMMENDATION));
    }

    @Test
    public void isSameRecommendation_differentContactIndex_true() {
        Recommendation recommendation =
                new Recommendation(STEVENS, TIME_PERIOD, new ContactIndex(4), false);
        assertTrue(recommendation.isSameRecommendation(RECOMMENDATION));
    }

    @Test
    public void isSameRecommendation_differentIsSaved_true() {
        Recommendation recommendation =
                new Recommendation(STEVENS, TIME_PERIOD, CONTACT_INDEX, true);
        assertTrue(recommendation.isSameRecommendation(RECOMMENDATION));
    }

    @Test
    public void isSameRecommendation_differentLocation_false() {
        Recommendation recommendation =
                new Recommendation(TAN_KAH_KEE, TIME_PERIOD, CONTACT_INDEX, false);
        assertFalse(recommendation.isSameRecommendation(RECOMMENDATION));
    }

    @Test
    public void isSameRecommendation_differentTimePeriod_false() {
        TimePeriod newTimePeriod = new HourBlock(TWO_PM, Day.MONDAY);
        Recommendation recommendation =
                new Recommendation(TAN_KAH_KEE, newTimePeriod, CONTACT_INDEX, false);
        assertFalse(recommendation.isSameRecommendation(RECOMMENDATION));
    }

    @Test
    public void saveRecommendation_validRecommendation_originalUnchanged() {
        Recommendation recommendation =
                new Recommendation(STEVENS, TIME_PERIOD, CONTACT_INDEX, false);
        recommendation.saveRecommendation();
        assertFalse(recommendation.getIsSaved());
    }

    @Test
    public void saveRecommendation_validRecommendation_newUpdated() {
        Recommendation recommendation =
                new Recommendation(STEVENS, TIME_PERIOD, CONTACT_INDEX, false);
        Recommendation savedRecommendation = recommendation.saveRecommendation();
        assertTrue(savedRecommendation.getIsSaved());
        assertTrue(savedRecommendation.isSameRecommendation(recommendation));
    }

    @Test
    public void unsaveRecommendation_validRecommendation_originalUnchanged() {
        Recommendation recommendation =
                new Recommendation(STEVENS, TIME_PERIOD, CONTACT_INDEX, true);
        recommendation.unsaveRecommendation();
        assertTrue(recommendation.getIsSaved());
    }

    @Test
    public void unsaveRecommendation_validRecommendation_newUpdated() {
        Recommendation recommendation =
                new Recommendation(STEVENS, TIME_PERIOD, CONTACT_INDEX, true);
        Recommendation savedRecommendation = recommendation.unsaveRecommendation();
        assertFalse(savedRecommendation.getIsSaved());
        assertTrue(savedRecommendation.isSameRecommendation(recommendation));
    }

    @Test
    public void setContactIndex_validIndex_success() {
        Recommendation recommendation =
                new Recommendation(STEVENS, TIME_PERIOD, CONTACT_INDEX, true);
        Recommendation indexedRecommendation =
                recommendation.setContactIndex(new ContactIndex(3));
        Recommendation otherIndexedRecommendation =
                new Recommendation(STEVENS, TIME_PERIOD, new ContactIndex(3), true);
        assertEquals(indexedRecommendation, otherIndexedRecommendation);
    }
}
