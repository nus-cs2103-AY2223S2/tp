package seedu.address.logic.recommender;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALBERT;
import static seedu.address.testutil.TypicalPersons.ANG;
import static seedu.address.testutil.TypicalPersons.getContactIndexOfPerson;
import static seedu.address.testutil.TypicalPersons.getTypicalEduMate;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.EduMateHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.location.util.LocationDataUtil;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.recommendation.Recommendation;

public class RecommenderTest {
    private final Model model = new ModelManager(getTypicalEduMate(), new UserPrefs(), new EduMateHistory());
    private final Recommender recommender = new Recommender(model);

    @Test
    void constructor_validModel_success() {
        assertDoesNotThrow(() -> new Recommender(model));
    }

    @Test
    void recommend_emptyArgs_empty() {
        List<Recommendation> recommendations = recommender.recommend(Set.of(), Set.of());
        assertTrue(recommendations.isEmpty());
    }

    @Test
    void recommend_mondayFree_mondayBest() {
        ContactIndex albertIndex = getContactIndexOfPerson(ALBERT);
        ContactIndex angIndex = getContactIndexOfPerson(ANG);

        Set<ContactIndex> participants = Set.of(albertIndex, angIndex);

        List<Recommendation> recommendations =
                recommender.recommend(participants, LocationDataUtil.MEET_LOCATIONS);

        assertFalse(recommendations.isEmpty());

        Recommendation bestRecommendation = recommendations.get(0);

        assertEquals(8, bestRecommendation.getTimePeriod().getStartTime().getHourOfDay());
        assertEquals(23, bestRecommendation.getTimePeriod().getEndTime().getHourOfDay());
    }
}
