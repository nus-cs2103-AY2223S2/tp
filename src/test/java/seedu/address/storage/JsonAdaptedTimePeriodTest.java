package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.model.time.Day.FRIDAY;
import static seedu.address.model.timetable.util.TypicalTime.EIGHT_AM;
import static seedu.address.model.timetable.util.TypicalTime.ELEVEN_AM;
import static seedu.address.model.timetable.util.TypicalTime.ELEVEN_PM;
import static seedu.address.model.timetable.util.TypicalTime.ONE_PM;
import static seedu.address.model.timetable.util.TypicalTime.TWELVE_PM;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.time.Day;
import seedu.address.model.time.HourBlock;
import seedu.address.model.time.TimeBlock;
import seedu.address.model.time.TimePeriod;
import seedu.address.model.time.exceptions.WrongTimeException;

public class JsonAdaptedTimePeriodTest {

    @Test
    public void toModelType_validTimePeriod_returnsTimePeriod() throws Exception {
        TimePeriod timePeriod = new TimeBlock(ELEVEN_AM, ONE_PM, FRIDAY);
        JsonAdaptedTimePeriod jsonAdaptedTimePeriod = new JsonAdaptedTimePeriod(timePeriod);
        assertEquals(jsonAdaptedTimePeriod.toModelType(), timePeriod);
    }

    @Test
    public void toModelType_earlyStartTime_returnsTimePeriod() throws Exception {
        TimePeriod timePeriod = new TimeBlock(EIGHT_AM, ONE_PM, FRIDAY);
        JsonAdaptedTimePeriod jsonAdaptedTimePeriod = new JsonAdaptedTimePeriod(timePeriod);
        assertEquals(jsonAdaptedTimePeriod.toModelType(), timePeriod);
    }

    @Test
    public void toModelType_lateEndTime_returnsTimePeriod() throws Exception {
        TimePeriod timePeriod = new TimeBlock(ELEVEN_AM, ELEVEN_PM, FRIDAY);
        JsonAdaptedTimePeriod jsonAdaptedTimePeriod = new JsonAdaptedTimePeriod(timePeriod);
        assertEquals(jsonAdaptedTimePeriod.toModelType(), timePeriod);
    }

    @Test
    public void toModelType_validTimeBlockHourBlock_returnsSameTimePeriod() throws Exception {
        TimePeriod timeBlock = new TimeBlock(ELEVEN_AM, TWELVE_PM, FRIDAY);
        JsonAdaptedTimePeriod jsonAdaptedTimeBlock = new JsonAdaptedTimePeriod(timeBlock);

        assertEquals(jsonAdaptedTimeBlock.toModelType(), timeBlock);

        TimePeriod hourBlock = new HourBlock(ELEVEN_AM, FRIDAY);
        JsonAdaptedTimePeriod jsonAdaptedHourBlock = new JsonAdaptedTimePeriod(hourBlock);

        assertEquals(jsonAdaptedHourBlock.toModelType(), timeBlock);

        assertEquals(jsonAdaptedHourBlock.toModelType(), jsonAdaptedTimeBlock.toModelType());
    }

    @Test
    public void toModelType_dayShortForm_throwsIllegalValueException() throws Exception {
        TimePeriod expected = new TimeBlock(ELEVEN_AM, ONE_PM, Day.FRIDAY);
        JsonAdaptedTimePeriod jsonAdaptedTimePeriod =
                new JsonAdaptedTimePeriod(11, 13, "FRI");
        assertEquals(jsonAdaptedTimePeriod.toModelType(), expected);
    }

    @Test
    public void toModelType_lowerCase_throwsIllegalValueException() throws Exception {
        TimePeriod expected = new TimeBlock(ELEVEN_AM, ONE_PM, Day.FRIDAY);
        JsonAdaptedTimePeriod jsonAdaptedTimePeriod =
                new JsonAdaptedTimePeriod(11, 13, "friday");
        assertEquals(jsonAdaptedTimePeriod.toModelType(), expected);
    }

    @Test
    public void toModelType_inconsistentCase_throwsIllegalValueException() throws Exception {
        TimePeriod expected = new TimeBlock(ELEVEN_AM, ONE_PM, Day.FRIDAY);
        JsonAdaptedTimePeriod jsonAdaptedTimePeriod =
                new JsonAdaptedTimePeriod(11, 13, "FriDAY");
        assertEquals(jsonAdaptedTimePeriod.toModelType(), expected);
    }

    @Test
    public void toModelType_nullDay_throwsNullPointerException() {
        JsonAdaptedTimePeriod jsonAdaptedTimePeriod =
                new JsonAdaptedTimePeriod(11, 13, null);
        assertThrows(NullPointerException.class, jsonAdaptedTimePeriod::toModelType);
    }

    @Test
    public void toModelType_emptyDay_throwsIllegalValueException() {
        JsonAdaptedTimePeriod jsonAdaptedTimePeriod =
                new JsonAdaptedTimePeriod(11, 13, "");
        assertThrows(IllegalValueException.class, jsonAdaptedTimePeriod::toModelType);
    }

    @Test
    public void toModelType_tooEarlyStartTime_throwsIllegalValueException() {
        JsonAdaptedTimePeriod jsonAdaptedTimePeriod =
                new JsonAdaptedTimePeriod(7, 13, "FRIDAY");
        assertThrows(IllegalValueException.class, jsonAdaptedTimePeriod::toModelType);
    }

    @Test
    public void toModelType_tooLateEndTime_throwsAssertionError() {
        JsonAdaptedTimePeriod jsonAdaptedTimePeriod =
                new JsonAdaptedTimePeriod(11, 24, "FRIDAY");
        assertThrows(AssertionError.class, jsonAdaptedTimePeriod::toModelType);
    }

    @Test
    public void toModelType_tooEarlyEndTime_throwsIllegalValueException() {
        JsonAdaptedTimePeriod jsonAdaptedTimePeriod =
                new JsonAdaptedTimePeriod(11, 8, "FRIDAY");
        assertThrows(IllegalValueException.class, jsonAdaptedTimePeriod::toModelType);
    }

    @Test
    public void toModelType_startTimeAfterEndTime_throwsWrongTimeException() {
        JsonAdaptedTimePeriod jsonAdaptedTimePeriod =
                new JsonAdaptedTimePeriod(13, 11, "FRIDAY");
        assertThrows(WrongTimeException.class, jsonAdaptedTimePeriod::toModelType);
    }

    @Test
    public void toModelType_startTimeSameAsEndTime_throwsWrongTimeException() {
        JsonAdaptedTimePeriod jsonAdaptedTimePeriod =
                new JsonAdaptedTimePeriod(11, 11, "FRIDAY");
        assertThrows(WrongTimeException.class, jsonAdaptedTimePeriod::toModelType);
    }

    @Test
    public void toModelType_invalidDay_throwsIllegalValueException() {
        JsonAdaptedTimePeriod jsonAdaptedTimePeriod =
                new JsonAdaptedTimePeriod(11, 13, "FREEDAY");
        assertThrows(IllegalValueException.class, jsonAdaptedTimePeriod::toModelType);
    }

    @Test
    public void toModelType_ambiguousDay_returnsTimePeriod() {
        JsonAdaptedTimePeriod jsonAdaptedTimePeriod =
                new JsonAdaptedTimePeriod(11, 13, "T");
        assertThrows(IllegalValueException.class, jsonAdaptedTimePeriod::toModelType);
    }
}
