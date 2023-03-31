package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.model.timetable.util.TypicalLesson.CS2106_WED_10AM_2HR;
import static seedu.address.model.timetable.util.TypicalTime.EIGHT_AM;
import static seedu.address.model.timetable.util.TypicalTime.ELEVEN_PM;
import static seedu.address.model.timetable.util.TypicalTime.NINE_AM;
import static seedu.address.model.timetable.util.TypicalTime.TEN_AM;
import static seedu.address.model.timetable.util.TypicalTime.TEN_PM;
import static seedu.address.model.timetable.util.TypicalTime.TWELVE_PM;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.commitment.Lesson;
import seedu.address.model.location.Location;
import seedu.address.model.time.Day;
import seedu.address.model.time.TimeBlock;

public class JsonAdaptedLessonTest {

    @Test
    public void toModelType_validLessonDetails_returnsLesson() throws Exception {
        JsonAdaptedLesson jsonAdaptedLesson =
                new JsonAdaptedLesson(CS2106_WED_10AM_2HR);
        assertEquals(jsonAdaptedLesson.toModelType(), CS2106_WED_10AM_2HR);

        jsonAdaptedLesson =
                new JsonAdaptedLesson(10, 12, "WEDNESDAY");
        assertEquals(jsonAdaptedLesson.toModelType().getLocation(), CS2106_WED_10AM_2HR.getLocation());
        assertEquals(jsonAdaptedLesson.toModelType().getTimePeriod(), CS2106_WED_10AM_2HR.getTimePeriod());
    }

    @Test
    public void toModelType_earlyStartTime_returnsLesson() throws Exception {
        JsonAdaptedLesson jsonAdaptedLesson =
                new JsonAdaptedLesson(8, 12, "WEDNESDAY");
        Lesson expectedLesson = new Lesson("", EIGHT_AM,
                TWELVE_PM, Day.WEDNESDAY, Location.NUS);
        assertEquals(jsonAdaptedLesson.toModelType(), expectedLesson);
    }

    @Test
    public void toModelType_lateStartTime_returnsLesson() throws Exception {
        JsonAdaptedLesson jsonAdaptedLesson =
                new JsonAdaptedLesson(22, 23, "WEDNESDAY");
        Lesson expectedLesson = new Lesson("", TEN_PM,
                ELEVEN_PM, Day.WEDNESDAY, Location.NUS);
        assertEquals(jsonAdaptedLesson.toModelType(), expectedLesson);
    }

    @Test
    public void toModelType_lateEndTime_returnsLesson() throws Exception {
        JsonAdaptedLesson jsonAdaptedLesson =
                new JsonAdaptedLesson(10, 23, "WEDNESDAY");
        Lesson expectedLesson = new Lesson("CS2106", TEN_AM,
                ELEVEN_PM, Day.WEDNESDAY, Location.NUS);
        assertEquals(jsonAdaptedLesson.toModelType(), expectedLesson);
    }

    @Test
    public void toModelType_earlyEndTime_returnsLesson() throws Exception {
        JsonAdaptedLesson jsonAdaptedLesson =
                new JsonAdaptedLesson(8, 9, "WEDNESDAY");
        Lesson expectedLesson = new Lesson("", EIGHT_AM,
                NINE_AM, Day.WEDNESDAY, Location.NUS);
        assertEquals(jsonAdaptedLesson.toModelType(), expectedLesson);
    }

    @Test
    public void toModelType_tooEarlyStartTime_throwsIllegalValueException() {
        JsonAdaptedLesson jsonAdaptedLesson =
                new JsonAdaptedLesson(7, 12, "WEDNESDAY");
        assertThrows(IllegalValueException.class, jsonAdaptedLesson::toModelType);
    }

    @Test
    public void toModelType_tooLateEndTime_throwsAssertionError() {
        JsonAdaptedLesson jsonAdaptedLesson =
                new JsonAdaptedLesson(10, 24, "WEDNESDAY");
        assertThrows(AssertionError.class, jsonAdaptedLesson::toModelType);
    }

    @Test
    public void toModelType_startTimeAfterEndTime_throwsAssertionError() {
        JsonAdaptedLesson jsonAdaptedLesson =
                new JsonAdaptedLesson(12, 10, "WEDNESDAY");
        assertThrows(AssertionError.class, jsonAdaptedLesson::toModelType);
    }

    @Test
    public void toModelType_startTimeSameAsEndTime_throwsAssertionError() {
        JsonAdaptedLesson jsonAdaptedLesson =
                new JsonAdaptedLesson(10, 10, "WEDNESDAY");
        assertThrows(AssertionError.class, jsonAdaptedLesson::toModelType);
    }

    @Test
    public void toModelType_dayShortForm_returnsLesson() throws Exception {
        Lesson expected = new Lesson(new TimeBlock(TEN_AM, TWELVE_PM, Day.FRIDAY));
        JsonAdaptedLesson jsonAdaptedLesson =
                new JsonAdaptedLesson(10, 12, "FRI");
        assertEquals(jsonAdaptedLesson.toModelType(), expected);
    }

    @Test
    public void toModelType_lowerCase_returnsLesson() throws Exception {
        Lesson expected = new Lesson(new TimeBlock(TEN_AM, TWELVE_PM, Day.FRIDAY));
        JsonAdaptedLesson jsonAdaptedLesson =
                new JsonAdaptedLesson(10, 12, "friday");
        assertEquals(jsonAdaptedLesson.toModelType(), expected);
    }

    @Test
    public void toModelType_inconsistentCase_returnsLesson() throws Exception {
        Lesson expected = new Lesson(new TimeBlock(TEN_AM, TWELVE_PM, Day.FRIDAY));
        JsonAdaptedLesson jsonAdaptedLesson =
                new JsonAdaptedLesson(10, 12, "FriDAY");
        assertEquals(jsonAdaptedLesson.toModelType(), expected);
    }
}
