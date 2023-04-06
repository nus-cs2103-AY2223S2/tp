package ezschedule.logic.commands;

import static ezschedule.logic.commands.CommandTestUtil.VALID_DATE_A;
import static ezschedule.logic.commands.CommandTestUtil.VALID_DATE_B;
import static ezschedule.logic.commands.CommandTestUtil.VALID_RECUR_FACTOR_DAY;
import static ezschedule.logic.commands.CommandTestUtil.VALID_RECUR_FACTOR_MONTH;
import static ezschedule.logic.commands.CommandTestUtil.VALID_RECUR_FACTOR_WEEK;
import static ezschedule.logic.commands.CommandTestUtil.assertCommandFailure;
import static ezschedule.logic.commands.CommandTestUtil.assertCommandSuccess;
import static ezschedule.testutil.TypicalEvents.getTypicalScheduler;
import static ezschedule.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static ezschedule.testutil.TypicalIndexes.INDEX_SECOND_EVENT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ezschedule.commons.core.index.Index;
import ezschedule.model.Model;
import ezschedule.model.ModelManager;
import ezschedule.model.Scheduler;
import ezschedule.model.UserPrefs;
import ezschedule.model.event.Date;
import ezschedule.model.event.Event;
import ezschedule.model.event.RecurFactor;
import ezschedule.testutil.EventBuilder;
import ezschedule.testutil.TypicalEvents;

public class RecurCommandTest {

    private final Model model = new ModelManager(getTypicalScheduler(), new UserPrefs());

    private final Index targetIndexDay = INDEX_FIRST_EVENT;
    private final Date endDateDay = new Date("2023-05-15"); // recur till 2023-05-15
    private final RecurFactor rfDay = new RecurFactor(VALID_RECUR_FACTOR_DAY);
    private final RecurCommand validDailyRecurCommandStub = new RecurCommand(targetIndexDay, endDateDay, rfDay);

    private final Index targetIndexWeek = INDEX_FIRST_EVENT;
    private final Date endDateWeek = new Date("2023-12-01"); // recur till 2023-12-01
    private final RecurFactor rfWeek = new RecurFactor(VALID_RECUR_FACTOR_WEEK);
    private final RecurCommand validWeeklyRecurCommandStub = new RecurCommand(targetIndexWeek, endDateWeek, rfWeek);

    private final Index targetIndexMonth = INDEX_FIRST_EVENT;
    private final Date endDateMonth = new Date("2023-10-01"); // recur till 2023-10-01
    private final RecurFactor rfMonth = new RecurFactor(VALID_RECUR_FACTOR_MONTH);
    private final RecurCommand validMonthlyRecurCommandStub = new RecurCommand(targetIndexMonth, endDateMonth, rfMonth);

    @Test
    public void getCommandWord_success() {
        String commandWord = "recur";

        assertTrue(commandWord.equals(validDailyRecurCommandStub.commandWord()));
    }

    @Test
    public void execute_withRecurFactorDay_success() {
        Event eventToRecur = TypicalEvents.ART; // event date = 2023-05-01

        String expectedMessage = String.format(RecurCommand.MESSAGE_SUCCESS, eventToRecur);
        Model expectedModel = new ModelManager(new Scheduler(model.getScheduler()), new UserPrefs());

        // 14 days to recur
        for (int i = 0; i < 14; i++) {
            Date newDate = new Date(eventToRecur.getDate().date.plusDays(1).toString());
            eventToRecur =
                    new Event(eventToRecur.getName(), newDate,
                            eventToRecur.getStartTime(), eventToRecur.getEndTime());

            expectedModel.addEvent(eventToRecur);
        }

        assertCommandSuccess(validDailyRecurCommandStub, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_withRecurFactorWeek_success() {
        Event eventToRecur = TypicalEvents.ART; // event date = 2023-05-01
        String expectedMessage = String.format(RecurCommand.MESSAGE_SUCCESS, eventToRecur);
        Model expectedModel = new ModelManager(new Scheduler(model.getScheduler()), new UserPrefs());

        // 30 weeks to recur
        for (int i = 0; i < 30; i++) {
            Date newDate = new Date(eventToRecur.getDate().date.plusWeeks(1).toString());
            eventToRecur =
                    new Event(eventToRecur.getName(), newDate,
                            eventToRecur.getStartTime(), eventToRecur.getEndTime());

            expectedModel.addEvent(eventToRecur);
        }

        assertCommandSuccess(validWeeklyRecurCommandStub, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_withRecurFactorMonth_success() {
        Event eventToRecur = TypicalEvents.ART; // event date = 2023-05-01
        String expectedMessage = String.format(RecurCommand.MESSAGE_SUCCESS, eventToRecur);
        Model expectedModel = new ModelManager(new Scheduler(model.getScheduler()), new UserPrefs());

        // 1 month to recur
        for (int i = 0; i < 5; i++) {
            Date newDate = new Date(eventToRecur.getDate().date.plusMonths(1).toString());
            eventToRecur =
                    new Event(eventToRecur.getName(), newDate,
                            eventToRecur.getStartTime(), eventToRecur.getEndTime());

            expectedModel.addEvent(eventToRecur);
        }

        assertCommandSuccess(validMonthlyRecurCommandStub, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_withRecurFactorDayExceedLimit_failure() {

        int maxDaysInMonth = 30;

        Index targetIndex = INDEX_FIRST_EVENT;
        Date endDate = new Date("2023-06-01"); // recur from 2023-05-01 till 2023-06-01
        RecurFactor rf = new RecurFactor(VALID_RECUR_FACTOR_DAY);
        RecurCommand recurCommand = new RecurCommand(targetIndex, endDate, rf);

        assertCommandFailure(recurCommand, model, String.format(
                RecurCommand.MESSAGE_RECUR_FACTOR_CAP, rf, maxDaysInMonth, rf));
    }

    @Test
    public void execute_withRecurFactorWeekExceedLimit_failure() {

        int maxWeeksInYear = 52;

        Index targetIndex = INDEX_FIRST_EVENT;
        Date endDate = new Date("2024-06-01"); // recur from 2023-05-01 till 2024-06-01
        RecurFactor rf = new RecurFactor(VALID_RECUR_FACTOR_WEEK);
        RecurCommand recurCommand = new RecurCommand(targetIndex, endDate, rf);

        assertCommandFailure(recurCommand, model, String.format(
                RecurCommand.MESSAGE_RECUR_FACTOR_CAP, rf, maxWeeksInYear, rf));
    }

    @Test
    public void execute_withRecurFactorMonthExceedLimit_failure() {

        int maxMonthInYear = 12;

        Index targetIndex = INDEX_FIRST_EVENT;
        Date endDate = new Date("2024-06-01"); // recur from 2023-05-01 till 2024-06-01
        RecurFactor rf = new RecurFactor(VALID_RECUR_FACTOR_MONTH);
        RecurCommand recurCommand = new RecurCommand(targetIndex, endDate, rf);

        assertCommandFailure(recurCommand, model, String.format(
                RecurCommand.MESSAGE_RECUR_FACTOR_CAP, rf, maxMonthInYear, rf));
    }

    @Test
    public void execute_recurWithPastEndDate_failure() {
        Index targetIndex = INDEX_FIRST_EVENT;
        Date endDate = new Date("2020-05-01"); // recur from 2023-05-01 till 2020-05-01
        RecurFactor rf = new RecurFactor(VALID_RECUR_FACTOR_MONTH);
        RecurCommand recurCommand = new RecurCommand(targetIndex, endDate, rf);

        assertCommandFailure(recurCommand, model, RecurCommand.MESSAGE_FAILURE_PAST_DATE);
    }

    @Test
    public void execute_recurDailyWithClash_failure() {
        Event eventToRecur = TypicalEvents.ART; // event date = 2023-05-01
        Date clashDate = new Date(eventToRecur.getDate().date.plusDays(4).toString());
        String month = validDailyRecurCommandStub.intToStringMonth(clashDate.getMonth());

        // EAT has clashing time with ART
        Event eat = new EventBuilder().withName("Eating contest")
                .withDate("2023-05-05").withStartTime("12:00").withEndTime("13:00").build();
        model.addEvent(eat);

        assertCommandFailure(validDailyRecurCommandStub, model,
                String.format(RecurCommand.MESSAGE_FAILURE_EVENT_CLASH, clashDate.getDay(), month));
    }

    @Test
    public void execute_recurWeeklyWithClash_failure() {
        Event eventToRecur = TypicalEvents.ART; // event date = 2023-05-01
        Date clashDate = new Date(eventToRecur.getDate().date.plusWeeks(5).toString());
        String month = validWeeklyRecurCommandStub.intToStringMonth(clashDate.getMonth());

        // eat has clashing time with ART
        Event eat = new EventBuilder().withName("Eating contest")
                .withDate("2023-06-05").withStartTime("12:00").withEndTime("13:00").build();
        model.addEvent(eat);

        assertCommandFailure(validWeeklyRecurCommandStub, model,
                String.format(RecurCommand.MESSAGE_FAILURE_EVENT_CLASH, clashDate.getDay(), month));
    }

    @Test
    public void execute_recurMonthlyWithClash_failure() {
        Event eventToRecur = TypicalEvents.ART; // event date = 2023-05-01
        Date clashDate = new Date(eventToRecur.getDate().date.plusMonths(5).toString());
        String month = validMonthlyRecurCommandStub.intToStringMonth(clashDate.getMonth());

        // EAT has clashing time with ART
        Event eat = new EventBuilder().withName("Eating contest")
                .withDate("2023-10-01").withStartTime("12:00").withEndTime("13:00").build();
        model.addEvent(eat);

        assertCommandFailure(validMonthlyRecurCommandStub, model,
                String.format(RecurCommand.MESSAGE_FAILURE_EVENT_CLASH, clashDate.getDay(), month));
    }

    @Test
    public void execute_validIntToStringMonths_success() {
        // valid months [1, 12]
        String expectedMonthJan = "January";
        String actualMonthJan = validDailyRecurCommandStub.intToStringMonth(1);

        String expectedMonthFeb = "February";
        String actualMonthFeb = validDailyRecurCommandStub.intToStringMonth(2);

        String expectedMonthMar = "March";
        String actualMonthMar = validDailyRecurCommandStub.intToStringMonth(3);

        String expectedMonthApr = "April";
        String actualMonthApr = validDailyRecurCommandStub.intToStringMonth(4);

        String expectedMonthMay = "May";
        String actualMonthMay = validDailyRecurCommandStub.intToStringMonth(5);

        String expectedMonthJun = "June";
        String actualMonthJun = validDailyRecurCommandStub.intToStringMonth(6);

        String expectedMonthJul = "July";
        String actualMonthJul = validDailyRecurCommandStub.intToStringMonth(7);

        String expectedMonthAug = "August";
        String actualMonthAug = validDailyRecurCommandStub.intToStringMonth(8);

        String expectedMonthSep = "September";
        String actualMonthSep = validDailyRecurCommandStub.intToStringMonth(9);

        String expectedMonthOct = "October";
        String actualMonthOct = validDailyRecurCommandStub.intToStringMonth(10);

        String expectedMonthNov = "November";
        String actualMonthNov = validDailyRecurCommandStub.intToStringMonth(11);

        String expectedMonthDec = "December";
        String actualMonthDec = validDailyRecurCommandStub.intToStringMonth(12);

        assertTrue(expectedMonthJan.equals(actualMonthJan));
        assertTrue(expectedMonthFeb.equals(actualMonthFeb));
        assertTrue(expectedMonthMar.equals(actualMonthMar));
        assertTrue(expectedMonthApr.equals(actualMonthApr));
        assertTrue(expectedMonthMay.equals(actualMonthMay));
        assertTrue(expectedMonthJun.equals(actualMonthJun));
        assertTrue(expectedMonthJul.equals(actualMonthJul));
        assertTrue(expectedMonthAug.equals(actualMonthAug));
        assertTrue(expectedMonthSep.equals(actualMonthSep));
        assertTrue(expectedMonthOct.equals(actualMonthOct));
        assertTrue(expectedMonthNov.equals(actualMonthNov));
        assertTrue(expectedMonthDec.equals(actualMonthDec));
    }

    @Test
    public void execute_invalidIntToStringMonths_success() {
        String invalidMessage = "No such month";

        // invalid months [..., 0]
        String actualMonthZero = validDailyRecurCommandStub.intToStringMonth(0);
        String actualMonthNegative = validDailyRecurCommandStub.intToStringMonth(-10);

        // invalid months [13, ...]
        String actualMonthThirteen = validDailyRecurCommandStub.intToStringMonth(13);
        String actualMonthHundred = validDailyRecurCommandStub.intToStringMonth(100);


        assertEquals(invalidMessage, actualMonthZero);
        assertEquals(invalidMessage, actualMonthNegative);
        assertEquals(invalidMessage, actualMonthThirteen);
        assertEquals(invalidMessage, actualMonthHundred);
    }

    @Test
    public void equals_success() {
        final RecurCommand standardCommand =
                new RecurCommand(INDEX_FIRST_EVENT, new Date(VALID_DATE_A),
                        new RecurFactor(VALID_RECUR_FACTOR_DAY));

        // same values -> returns true
        RecurCommand commandWithSameValues =
                new RecurCommand(INDEX_FIRST_EVENT, standardCommand.getEndDate(),
                        standardCommand.getFactor());
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new RecurCommand(INDEX_SECOND_EVENT, new Date(VALID_DATE_A),
                new RecurFactor(VALID_RECUR_FACTOR_DAY))));

        // different date -> returns false
        assertFalse(standardCommand.equals(new RecurCommand(INDEX_FIRST_EVENT, new Date(VALID_DATE_B),
                new RecurFactor(VALID_RECUR_FACTOR_DAY))));

        // different factor -> returns false
        assertFalse(standardCommand.equals(new RecurCommand(INDEX_FIRST_EVENT, new Date(VALID_DATE_A),
                new RecurFactor(VALID_RECUR_FACTOR_MONTH))));
    }
}
