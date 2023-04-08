package seedu.address.model.recommendation;

import static seedu.address.model.location.util.TypicalLocation.NEWTON;
import static seedu.address.model.timetable.time.TypicalTimePeriod.THU_4PM_3HR;
import static seedu.address.model.timetable.time.TypicalTimePeriod.THU_5PM_1HR;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Typical recommendations given by EduMate.
 */
public class TypicalRecommendations {
    public static final Recommendation RECOMMENDATION_STEVENS_THU_10AM_2HR =
            new RecommendationBuilder().build();
    public static final Recommendation RECOMMENDATION_STEVENS_THU_5PM_1HR =
            new RecommendationBuilder().withTimePeriod(THU_5PM_1HR).build();
    public static final Recommendation RECOMMENDATION_NEWTON_THU_4PM_3HR = new RecommendationBuilder()
            .withLocation(NEWTON).withTimePeriod(THU_4PM_3HR).build();

    public static List<Recommendation> getTypicalRecommendations() {
        return new ArrayList<>(Arrays.asList(
                RECOMMENDATION_NEWTON_THU_4PM_3HR,
                RECOMMENDATION_STEVENS_THU_10AM_2HR,
                RECOMMENDATION_STEVENS_THU_5PM_1HR));
    }
}
