package seedu.address.model.timingrecommender;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.timetable.util.TypicalTime.EIGHT_AM;
import static seedu.address.model.timetable.util.TypicalTime.ELEVEN_PM;
import static seedu.address.model.timetable.util.TypicalTime.TWELVE_PM;
import static seedu.address.model.timetable.util.TypicalTimetable.FULL_CONFLICT_TIMETABLE_A;
import static seedu.address.model.timetable.util.TypicalTimetable.FULL_CONFLICT_TIMETABLE_B;
import static seedu.address.model.timetable.util.TypicalTimetable.TIMETABLE_A;
import static seedu.address.model.timetable.util.TypicalTimetable.TIMETABLE_B;
import static seedu.address.model.timetable.util.TypicalTimetable.TIMETABLE_C;
import static seedu.address.model.timetable.util.TypicalTimetable.TIMETABLE_D;
import static seedu.address.model.timetable.util.TypicalTimetable.TIMETABLE_E;
import static seedu.address.testutil.TypicalPersons.getTypicalEduMate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.joda.time.Hours;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.recommender.timing.TimingRecommender;
import seedu.address.model.EduMateHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.time.Day;
import seedu.address.model.time.TimePeriod;

class TimingRecommenderTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalEduMate(), new UserPrefs(), new EduMateHistory());
    }

    @Test
    public void initialise_modelOnly_success() {
        assertDoesNotThrow(() -> new TimingRecommender(model));
    }

    @Test
    public void initialise_allValidContactIndices_success() {
        ContactIndex[] indices = { new ContactIndex(2),
            new ContactIndex(5),
            new ContactIndex(13)};
        TimingRecommender timingRecommender = new TimingRecommender(model);
        timingRecommender.initialise(new ArrayList<ContactIndex>(List.of(indices)));
        assertEquals(3, timingRecommender.getParticipants().size());
    }

    @Test
    public void initialise_someValidContactIndices_success() {
        ContactIndex[] indices = { new ContactIndex(2),
            new ContactIndex(998),
            new ContactIndex(3)};
        TimingRecommender timingRecommender = new TimingRecommender(model);
        timingRecommender.initialise(new ArrayList<ContactIndex>(List.of(indices)));
        assertEquals(2, timingRecommender.getParticipants().size());
    }

    @Test
    public void initialise_allInvalidContactIndicies_emptyParticipantList() {
        ContactIndex[] indices = { new ContactIndex(999),
            new ContactIndex(998),
            new ContactIndex(997)};
        TimingRecommender timingRecommender = new TimingRecommender(model);
        timingRecommender.initialise(new ArrayList<ContactIndex>(List.of(indices)));
        assertTrue(timingRecommender.getSchedules().isEmpty());
    }

    @Test
    public void testRecommendations_twoTimetables_success() {
        TimingRecommender timingRecommender = new TimingRecommender(model);
        timingRecommender.addTimetable(TIMETABLE_A);
        timingRecommender.addTimetable(TIMETABLE_B);
        // expected longest interval is 12noon - 11pm on Monday
        Optional<TimePeriod> longestInterval = timingRecommender.giveLongestTimingRecommendation();
        assertTrue(longestInterval.isPresent());
        assertEquals(Hours.hours(11), longestInterval.get().getHoursBetween());
        assertEquals(TWELVE_PM, longestInterval.get().getStartTime());
        assertEquals(ELEVEN_PM, longestInterval.get().getEndTime());
        assertEquals(Day.MONDAY, longestInterval.get().getSchoolDay());
    }

    @Test
    public void testRecommendations_threeTimetablesVariant1_success() {
        TimingRecommender timingRecommender = new TimingRecommender(model);
        timingRecommender.addTimetable(TIMETABLE_A);
        timingRecommender.addTimetable(TIMETABLE_B);
        timingRecommender.addTimetable(TIMETABLE_C);
        // expected longest interval is 12noon - 10pm on Monday
        Optional<TimePeriod> longestInterval = timingRecommender.giveLongestTimingRecommendation();
        assertTrue(longestInterval.isPresent());
        assertEquals(Hours.hours(11), longestInterval.get().getHoursBetween());
        assertEquals(TWELVE_PM, longestInterval.get().getStartTime());
        assertEquals(ELEVEN_PM, longestInterval.get().getEndTime());
        assertEquals(Day.MONDAY, longestInterval.get().getSchoolDay());
    }

    @Test
    public void testRecommendations_threeTimetablesVariant2_success() {
        TimingRecommender timingRecommender = new TimingRecommender(model);
        timingRecommender.addTimetable(TIMETABLE_C);
        timingRecommender.addTimetable(TIMETABLE_D);
        timingRecommender.addTimetable(TIMETABLE_E);
        // expected longest interval is 12noon - 11pm on Monday
        Optional<TimePeriod> longestInterval = timingRecommender.giveLongestTimingRecommendation();
        assertTrue(longestInterval.isPresent());
        assertEquals(Hours.hours(11), longestInterval.get().getHoursBetween());
        assertEquals(TWELVE_PM, longestInterval.get().getStartTime());
        assertEquals(ELEVEN_PM, longestInterval.get().getEndTime());
        assertEquals(Day.MONDAY, longestInterval.get().getSchoolDay());
    }

    @Test
    public void testRecommendations_threeTimetablesVariant4_success() {
        TimingRecommender timingRecommender = new TimingRecommender(model);
        timingRecommender.addTimetable(TIMETABLE_A);
        timingRecommender.addTimetable(TIMETABLE_D);
        timingRecommender.addTimetable(TIMETABLE_E);
        // expected longest interval is 12noon - 11pm on Monday
        Optional<TimePeriod> longestInterval = timingRecommender.giveLongestTimingRecommendation();
        assertTrue(longestInterval.isPresent());
        assertEquals(Hours.hours(11), longestInterval.get().getHoursBetween());
        assertEquals(TWELVE_PM, longestInterval.get().getStartTime());
        assertEquals(ELEVEN_PM, longestInterval.get().getEndTime());
        assertEquals(Day.MONDAY, longestInterval.get().getSchoolDay());
    }

    @Test
    public void testRecommendations_oneDayFullConflict_returnsWholeOtherDayTimeBlock() {
        TimingRecommender timingRecommender = new TimingRecommender(model);
        timingRecommender.addTimetable(FULL_CONFLICT_TIMETABLE_A);
        timingRecommender.addTimetable(FULL_CONFLICT_TIMETABLE_B);
        // expected longest interval 8am - 11pm
        Optional<TimePeriod> longestInterval = timingRecommender.giveLongestTimingRecommendation();
        assertTrue(longestInterval.isPresent());
        assertEquals(Hours.hours(15), longestInterval.get().getHoursBetween());
        assertEquals(EIGHT_AM, longestInterval.get().getStartTime());
        assertEquals(ELEVEN_PM, longestInterval.get().getEndTime());
        assertEquals(Day.MONDAY, longestInterval.get().getSchoolDay());

    }

    @Test
    public void testRecommendations_oneDayFullConflictWithTimetableA_returnsWholeOtherDayTimeBlock() {
        TimingRecommender timingRecommender = new TimingRecommender(model);
        timingRecommender.addTimetable(TIMETABLE_A);
        timingRecommender.addTimetable(FULL_CONFLICT_TIMETABLE_A);
        timingRecommender.addTimetable(FULL_CONFLICT_TIMETABLE_B);
        // expected longest interval 8am - 11pm
        Optional<TimePeriod> longestInterval = timingRecommender.giveLongestTimingRecommendation();
        assertTrue(longestInterval.isPresent());
        assertEquals(Hours.hours(15), longestInterval.get().getHoursBetween());
        assertEquals(EIGHT_AM, longestInterval.get().getStartTime());
        assertEquals(ELEVEN_PM, longestInterval.get().getEndTime());
        assertEquals(Day.WEDNESDAY, longestInterval.get().getSchoolDay());

    }

    @Test
    public void testRecommendations_emptyTimetable_emptyArrayList() {
        TimingRecommender timingRecommender = new TimingRecommender(model);
        Optional<TimePeriod> longestInterval = timingRecommender.giveLongestTimingRecommendation();
        assertTrue(longestInterval.isEmpty());
    }

    @Test
    public void equalityCheck_sameObjectModel_equals() {
        TimingRecommender timingRecommender = new TimingRecommender(model);
        assertEquals(timingRecommender.getModel(), model);
    }

}
