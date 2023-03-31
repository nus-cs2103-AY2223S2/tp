package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.model.location.util.TypicalLocation.GREAT_WORLD;
import static seedu.address.model.timetable.util.TypicalTime.ELEVEN_AM;
import static seedu.address.model.timetable.util.TypicalTime.ONE_PM;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.recommendation.Recommendation;
import seedu.address.model.time.Day;
import seedu.address.model.time.TimeBlock;
import seedu.address.model.time.TimePeriod;

public class JsonAdaptedRecommendationTest {

    private static final TimePeriod TIME_PERIOD =
            new TimeBlock(ELEVEN_AM, ONE_PM, Day.TUESDAY);
    private static final ContactIndex CONTACT_INDEX = new ContactIndex(4);
    private static final boolean IS_SAVED = true;

    @Test
    public void toModelType_validRecommendationDetails_returnsRecommendation() throws Exception {
        // from model and back
        Recommendation expected = new Recommendation(GREAT_WORLD,
                TIME_PERIOD, CONTACT_INDEX, IS_SAVED);
        JsonAdaptedRecommendation jsonAdaptedRecommendation =
                new JsonAdaptedRecommendation(expected);

        assertEquals(jsonAdaptedRecommendation.toModelType(), expected);

        // directly from json creator
        JsonAdaptedLocation jsonAdaptedLocation = new JsonAdaptedLocation(GREAT_WORLD);
        JsonAdaptedTimePeriod jsonAdaptedTimePeriod = new JsonAdaptedTimePeriod(TIME_PERIOD);
        jsonAdaptedRecommendation =
                new JsonAdaptedRecommendation(jsonAdaptedLocation,
                        jsonAdaptedTimePeriod, IS_SAVED, CONTACT_INDEX.getContactIndex());

        assertEquals(jsonAdaptedRecommendation.toModelType(), expected);
    }

    @Test
    public void toModelType_validWithoutIsSaved_returnsRecommendation() throws Exception {
        Recommendation expected = new Recommendation(GREAT_WORLD, TIME_PERIOD, CONTACT_INDEX);
        JsonAdaptedRecommendation jsonAdaptedRecommendation =
                new JsonAdaptedRecommendation(expected);

        assertEquals(jsonAdaptedRecommendation.toModelType(), expected);
    }

    @Test
    public void toModelType_validWithoutContactIndex_returnsRecommendation() throws Exception {
        Recommendation expected = new Recommendation(GREAT_WORLD, TIME_PERIOD, IS_SAVED);
        JsonAdaptedRecommendation jsonAdaptedRecommendation =
                new JsonAdaptedRecommendation(expected);

        assertEquals(jsonAdaptedRecommendation.toModelType(), expected);
    }

    @Test
    public void toModelType_validWithoutContactIndexIsSaved_returnsRecommendation() throws Exception {
        Recommendation expected = new Recommendation(GREAT_WORLD, TIME_PERIOD);
        JsonAdaptedRecommendation jsonAdaptedRecommendation =
                new JsonAdaptedRecommendation(expected);

        assertEquals(jsonAdaptedRecommendation.toModelType(), expected);
    }

    @Test
    public void toModelType_nullLocation_throwsIllegalValueException() throws Exception {
        JsonAdaptedTimePeriod jsonAdaptedTimePeriod = new JsonAdaptedTimePeriod(TIME_PERIOD);
        JsonAdaptedRecommendation jsonAdaptedRecommendation =
                new JsonAdaptedRecommendation(null, jsonAdaptedTimePeriod,
                        IS_SAVED, CONTACT_INDEX.getContactIndex());

        assertEquals(jsonAdaptedTimePeriod.toModelType(), TIME_PERIOD);
        assertThrows(IllegalValueException.class, jsonAdaptedRecommendation::toModelType);
    }

    @Test
    public void toModelType_nullTimePeriod_throwsIllegalValueException() throws Exception {
        JsonAdaptedLocation jsonAdaptedLocation = new JsonAdaptedLocation(GREAT_WORLD);
        JsonAdaptedRecommendation jsonAdaptedRecommendation =
                new JsonAdaptedRecommendation(jsonAdaptedLocation, null,
                        IS_SAVED, CONTACT_INDEX.getContactIndex());

        assertEquals(jsonAdaptedLocation.toModelType(), GREAT_WORLD);
        assertThrows(IllegalValueException.class, jsonAdaptedRecommendation::toModelType);
    }

    @Test
    public void toModelType_nullIndex_throwsIllegalValueException() {
        JsonAdaptedLocation jsonAdaptedLocation = new JsonAdaptedLocation(GREAT_WORLD);
        JsonAdaptedTimePeriod jsonAdaptedTimePeriod = new JsonAdaptedTimePeriod(TIME_PERIOD);
        JsonAdaptedRecommendation jsonAdaptedRecommendation =
                new JsonAdaptedRecommendation(jsonAdaptedLocation, jsonAdaptedTimePeriod,
                        IS_SAVED, null);

        assertThrows(IllegalValueException.class, jsonAdaptedRecommendation::toModelType);
    }
}
