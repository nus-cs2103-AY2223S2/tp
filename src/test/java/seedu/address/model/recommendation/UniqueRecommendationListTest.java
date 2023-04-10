package seedu.address.model.recommendation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.location.util.TypicalLocation.STADIUM;
import static seedu.address.model.location.util.TypicalLocation.STEVENS;
import static seedu.address.model.timetable.util.TypicalTime.ONE_PM;
import static seedu.address.model.timetable.util.TypicalTime.TEN_AM;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.ContactIndex;
import seedu.address.model.recommendation.exceptions.DuplicateRecommendationException;
import seedu.address.model.recommendation.exceptions.RecommendationNotFoundException;
import seedu.address.model.time.Day;
import seedu.address.model.time.TimeBlock;
import seedu.address.model.time.TimePeriod;

public class UniqueRecommendationListTest {

    private static final ContactIndex CONTACT_INDEX = new ContactIndex(4);
    private static final TimePeriod TIME_PERIOD =
            new TimeBlock(TEN_AM, ONE_PM, Day.THURSDAY);
    private static final Recommendation RECOMMENDATION =
            new Recommendation(STEVENS, TIME_PERIOD, CONTACT_INDEX, false);
    private final UniqueRecommendationList uniqueRecommendationList = new UniqueRecommendationList();

    @Test
    public void contains_nullRecommendation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecommendationList.contains(null));
    }

    @Test
    public void contains_recommendationNotInList_false() {
        assertFalse(uniqueRecommendationList.contains(RECOMMENDATION));
    }

    @Test
    public void add_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecommendationList.add(null));
    }

    @Test
    public void add_existsInList_doesNothing() {
        UniqueRecommendationList recommendations = new UniqueRecommendationList();
        recommendations.add(RECOMMENDATION);

        UniqueRecommendationList updatedRecommendations = new UniqueRecommendationList();
        updatedRecommendations.add(RECOMMENDATION);
        updatedRecommendations.add(RECOMMENDATION);

        assertEquals(recommendations, updatedRecommendations);
    }

    @Test
    public void add_doesNotExistInList_adds() {
        UniqueRecommendationList recommendations = new UniqueRecommendationList();
        recommendations.add(RECOMMENDATION);

        assertTrue(recommendations.contains(RECOMMENDATION));
    }

    @Test
    public void setRecommendation_validTarget_success() {
        UniqueRecommendationList recommendations = new UniqueRecommendationList();
        recommendations.add(RECOMMENDATION);

        Recommendation newRecommendation =
                new Recommendation(STADIUM, TIME_PERIOD, CONTACT_INDEX, false);
        recommendations.setRecommendation(RECOMMENDATION, newRecommendation);

        assertFalse(recommendations.contains(RECOMMENDATION));
        assertTrue(recommendations.contains(newRecommendation));
    }

    @Test
    public void setRecommendation_targetDoesNotExist_throwsRecommendationNotFoundException() {
        UniqueRecommendationList recommendations = new UniqueRecommendationList();
        Recommendation newRecommendation =
                new Recommendation(STADIUM, TIME_PERIOD, CONTACT_INDEX, false);
        assertThrows(RecommendationNotFoundException.class, ()
                -> recommendations.setRecommendation(RECOMMENDATION, newRecommendation));
    }

    @Test
    public void setRecommendation_sameRecommendationButContains_success() {
        UniqueRecommendationList recommendations = new UniqueRecommendationList();
        Recommendation indexedRecommendation =
                new Recommendation(STEVENS, TIME_PERIOD, new ContactIndex(3), false);

        assertEquals(RECOMMENDATION, indexedRecommendation);
        assertTrue(RECOMMENDATION.isSameRecommendation(indexedRecommendation));

        recommendations.add(RECOMMENDATION);
        assertDoesNotThrow(() -> recommendations.add(indexedRecommendation));

        assertTrue(recommendations.contains(RECOMMENDATION));
        assertTrue(recommendations.contains(indexedRecommendation));
    }

    @Test
    public void setRecommendation_isNotSameRecommendationButContains_throwsDuplicateRecommendationException() {
        UniqueRecommendationList recommendations = new UniqueRecommendationList();
        Recommendation differentRecommendation =
                new Recommendation(STADIUM, TIME_PERIOD, CONTACT_INDEX, false);

        recommendations.add(RECOMMENDATION);
        recommendations.add(differentRecommendation);

        assertTrue(recommendations.contains(RECOMMENDATION));
        assertTrue(recommendations.contains(differentRecommendation));

        assertThrows(DuplicateRecommendationException.class, ()
                -> recommendations.setRecommendation(RECOMMENDATION, differentRecommendation));
    }

    @Test
    public void remove_exists_success() {
        UniqueRecommendationList recommendations = new UniqueRecommendationList();
        recommendations.add(RECOMMENDATION);

        assertTrue(recommendations.contains(RECOMMENDATION));

        recommendations.remove(RECOMMENDATION);

        assertFalse(recommendations.contains(RECOMMENDATION));
    }

    @Test
    public void remove_doesNotExist_throwsRecommendationNotFoundException() {
        UniqueRecommendationList recommendations = new UniqueRecommendationList();
        Recommendation differentRecommendation =
                new Recommendation(STADIUM, TIME_PERIOD, CONTACT_INDEX, false);

        recommendations.add(RECOMMENDATION);

        assertThrows(RecommendationNotFoundException.class, ()
                -> recommendations.remove(differentRecommendation));

        assertDoesNotThrow(() -> recommendations.remove(RECOMMENDATION));
    }

    @Test
    public void setRecommendations_nullUniqueRecommendationList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> uniqueRecommendationList.setRecommendations((UniqueRecommendationList) null));
    }

    @Test
    public void setRecommendations_nullRecommendationList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> uniqueRecommendationList.setRecommendations((List<Recommendation>) null));
    }

    @Test
    public void setRecommendations_validUniqueRecommendationsList_success() {
        UniqueRecommendationList oldUniqueRecommendationList = new UniqueRecommendationList();

        oldUniqueRecommendationList.add(RECOMMENDATION);

        UniqueRecommendationList newUniqueRecommendationList = new UniqueRecommendationList();
        Recommendation differentRecommendation =
                new Recommendation(STADIUM, TIME_PERIOD, CONTACT_INDEX, false);
        newUniqueRecommendationList.add(differentRecommendation);

        assertTrue(oldUniqueRecommendationList.contains(RECOMMENDATION));
        assertFalse(oldUniqueRecommendationList.contains(differentRecommendation));

        oldUniqueRecommendationList.setRecommendations(newUniqueRecommendationList);

        assertTrue(oldUniqueRecommendationList.contains(differentRecommendation));
        assertFalse(oldUniqueRecommendationList.contains(RECOMMENDATION));

        // make sure that the copied one is unaltered
        assertTrue(newUniqueRecommendationList.contains(differentRecommendation));
        assertFalse(newUniqueRecommendationList.contains(RECOMMENDATION));
    }

    @Test
    public void setRecommendations_unique_success() {
        List<Recommendation> recommendationsList = new ArrayList<>();
        Recommendation differentRecommendation =
                new Recommendation(STADIUM, TIME_PERIOD, CONTACT_INDEX, false);
        recommendationsList.add(RECOMMENDATION);
        recommendationsList.add(differentRecommendation);

        UniqueRecommendationList recommendations = new UniqueRecommendationList();
        recommendations.setRecommendations(recommendationsList);

        assertTrue(recommendations.contains(RECOMMENDATION));
        assertTrue(recommendations.contains(differentRecommendation));
    }

    @Test
    public void setRecommendations_notUnique_throwsDuplicateRecommendationException() {
        List<Recommendation> recommendationsList = new ArrayList<>();
        recommendationsList.add(RECOMMENDATION);
        Recommendation otherRecommendation =
                new Recommendation(STEVENS, TIME_PERIOD, new ContactIndex(3), false);
        recommendationsList.add(otherRecommendation);

        UniqueRecommendationList recommendations = new UniqueRecommendationList();

        assertThrows(DuplicateRecommendationException.class, ()
                -> recommendations.setRecommendations(recommendationsList));
    }

    @Test
    public void asUnmodifiableList_validRecommendations_success() {
        UniqueRecommendationList recommendations = new UniqueRecommendationList();
        Recommendation differentRecommendation =
                new Recommendation(STADIUM, TIME_PERIOD, CONTACT_INDEX, false);
        recommendations.add(RECOMMENDATION);
        recommendations.add(differentRecommendation);

        List<Recommendation> recommendationsList = List.of(RECOMMENDATION, differentRecommendation);

        assertEquals(recommendationsList, recommendations.asUnmodifiableObservableList());
    }

    @Test
    public void asUnmodifiableList_tryUpdate_throwsUnsupportedOperationException() {
        UniqueRecommendationList recommendations = new UniqueRecommendationList();
        List<Recommendation> recommendationsList = recommendations.asUnmodifiableObservableList();

        assertThrows(UnsupportedOperationException.class, () -> recommendationsList.add(RECOMMENDATION));
    }

    @Test
    public void iterator_empty_success() {
        assertFalse(uniqueRecommendationList.iterator().hasNext());
    }

    @Test
    public void equals_sameObject_true() {
        assertEquals(uniqueRecommendationList, uniqueRecommendationList);
    }

    @Test
    public void equals_sameValue_true() {
        UniqueRecommendationList recommendations = new UniqueRecommendationList();

        assertEquals(recommendations, uniqueRecommendationList);
    }

    @Test
    public void equals_differentType_false() {
        assertNotEquals(uniqueRecommendationList, 5);
    }

    @Test
    public void equals_differentValue_false() {
        UniqueRecommendationList otherRecommendations = new UniqueRecommendationList();
        otherRecommendations.add(RECOMMENDATION);

        assertNotEquals(otherRecommendations, uniqueRecommendationList);
    }

    @Test
    public void hashCode_sameValues_sameHashCode() {
        UniqueRecommendationList recommendations = new UniqueRecommendationList();
        UniqueRecommendationList otherRecommendations = new UniqueRecommendationList();

        recommendations.add(RECOMMENDATION);
        otherRecommendations.add(RECOMMENDATION);

        assertEquals(recommendations.hashCode(), otherRecommendations.hashCode());
    }
}
