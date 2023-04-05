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
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import ezschedule.commons.core.index.Index;
import ezschedule.model.Model;
import ezschedule.model.ModelManager;
import ezschedule.model.Scheduler;
import ezschedule.model.UserPrefs;
import ezschedule.model.event.Date;
import ezschedule.model.event.Event;
import ezschedule.model.event.RecurFactor;

public class RecurCommandTest {

    private final Model model = new ModelManager(getTypicalScheduler(), new UserPrefs());

    @Test
    public void execute_withRecurFactorDay_success() {
        Index targetIndex = INDEX_FIRST_EVENT;
        Date endDate = new Date("2023-05-15"); // recur from 2023-05-01 till 2023-05-15
        RecurFactor rf = new RecurFactor(VALID_RECUR_FACTOR_DAY);
        RecurCommand recurCommand = new RecurCommand(targetIndex, endDate, rf);

        Event eventToRecur = model.getFilteredEventList().get(0);
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

        assertCommandSuccess(recurCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_withRecurFactorWeek_success() {
        Index targetIndex = INDEX_FIRST_EVENT;
        Date endDate = new Date("2023-12-01"); // recur from 2023-05-01 till 2023-12-01
        RecurFactor rf = new RecurFactor(VALID_RECUR_FACTOR_WEEK);
        RecurCommand recurCommand = new RecurCommand(targetIndex, endDate, rf);

        Event eventToRecur = model.getFilteredEventList().get(0);
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

        assertCommandSuccess(recurCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_withRecurFactorMonth_success() {
        Index targetIndex = INDEX_FIRST_EVENT;
        Date endDate = new Date("2023-06-01"); // recur from 2023-05-01 till 2023-06-01
        RecurFactor rf = new RecurFactor(VALID_RECUR_FACTOR_MONTH);
        RecurCommand recurCommand = new RecurCommand(targetIndex, endDate, rf);

        Event eventToRecur = model.getFilteredEventList().get(0);
        String expectedMessage = String.format(RecurCommand.MESSAGE_SUCCESS, eventToRecur);
        Model expectedModel = new ModelManager(new Scheduler(model.getScheduler()), new UserPrefs());

        // 1 month to recur
        for (int i = 0; i < 1; i++) {
            Date newDate = new Date(eventToRecur.getDate().date.plusMonths(1).toString());
            eventToRecur =
                    new Event(eventToRecur.getName(), newDate,
                            eventToRecur.getStartTime(), eventToRecur.getEndTime());

            expectedModel.addEvent(eventToRecur);
        }

        assertCommandSuccess(recurCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_withRecurFactorDayExceedLimit_failure() {

        int maxDaysInMonth = 30;

        Index targetIndex = INDEX_FIRST_EVENT;
        Date endDate = new Date("2023-06-01"); // recur from 2023-05-01 till 2023-06-01
        RecurFactor rf = new RecurFactor(VALID_RECUR_FACTOR_DAY);
        RecurCommand recurCommand = new RecurCommand(targetIndex, endDate, rf);

        Event eventToRecur = model.getFilteredEventList().get(0);

        Model expectedModel = new ModelManager(new Scheduler(model.getScheduler()), new UserPrefs());

        // 31 days to recur
        for (int i = 0; i < 31; i++) {
            Date newDate = new Date(eventToRecur.getDate().date.plusDays(1).toString());
            eventToRecur =
                    new Event(eventToRecur.getName(), newDate,
                            eventToRecur.getStartTime(), eventToRecur.getEndTime());

            expectedModel.addEvent(eventToRecur);
        }

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

        Event eventToRecur = model.getFilteredEventList().get(0);

        Model expectedModel = new ModelManager(new Scheduler(model.getScheduler()), new UserPrefs());

        // 56 weeks to recur
        for (int i = 0; i < 56; i++) {
            Date newDate = new Date(eventToRecur.getDate().date.plusWeeks(1).toString());
            eventToRecur =
                    new Event(eventToRecur.getName(), newDate,
                            eventToRecur.getStartTime(), eventToRecur.getEndTime());

            expectedModel.addEvent(eventToRecur);
        }

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

        Event eventToRecur = model.getFilteredEventList().get(0);

        Model expectedModel = new ModelManager(new Scheduler(model.getScheduler()), new UserPrefs());

        // 13 month to recur
        for (int i = 0; i < 13; i++) {
            Date newDate = new Date(eventToRecur.getDate().date.plusMonths(1).toString());
            eventToRecur =
                    new Event(eventToRecur.getName(), newDate,
                            eventToRecur.getStartTime(), eventToRecur.getEndTime());

            expectedModel.addEvent(eventToRecur);
        }

        assertCommandFailure(recurCommand, model, String.format(
                RecurCommand.MESSAGE_RECUR_FACTOR_CAP, rf, maxMonthInYear, rf));
    }

    @Test
    public void execute_validIntToStringMonths_success() {
        Index targetIndex = INDEX_FIRST_EVENT;
        Date endDate = new Date("2023-06-01"); // recur from 2023-05-01 till 2023-06-01
        RecurFactor rf = new RecurFactor(VALID_RECUR_FACTOR_MONTH);
        RecurCommand recurCommand = new RecurCommand(targetIndex, endDate, rf);

        // valid months [1, 12]
        String expectedMonthJan = "January";
        String actualMonthJan = recurCommand.intToStringMonth(1);

        String expectedMonthJun = "June";
        String actualMonthJun = recurCommand.intToStringMonth(6);

        String expectedMonthDec = "December";
        String actualMonthDec = recurCommand.intToStringMonth(12);

        assertEquals(expectedMonthJan, actualMonthJan);
        assertEquals(expectedMonthJun, actualMonthJun);
        assertEquals(expectedMonthDec, actualMonthDec);
    }

    @Test
    public void execute_invalidIntToStringMonths_success() {
        Index targetIndex = INDEX_FIRST_EVENT;
        Date endDate = new Date("2023-06-01"); // recur from 2023-05-01 till 2023-06-01
        RecurFactor rf = new RecurFactor(VALID_RECUR_FACTOR_MONTH);
        RecurCommand recurCommand = new RecurCommand(targetIndex, endDate, rf);

        String invalidMessage = "No such month";

        // invalid months [..., 0]
        String actualMonthZero = recurCommand.intToStringMonth(0);
        String actualMonthNegative = recurCommand.intToStringMonth(-10);

        // invalid months [13, ...]
        String actualMonthThirteen = recurCommand.intToStringMonth(13);
        String actualMonthHundred = recurCommand.intToStringMonth(100);


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
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different index -> returns false
        assertNotEquals(standardCommand,
                new RecurCommand(INDEX_SECOND_EVENT, new Date(VALID_DATE_A),
                        new RecurFactor(VALID_RECUR_FACTOR_DAY)));

        // different date -> returns false
        assertNotEquals(standardCommand,
                new RecurCommand(INDEX_FIRST_EVENT, new Date(VALID_DATE_B),
                        new RecurFactor(VALID_RECUR_FACTOR_DAY)));

        // different factor -> returns false
        assertNotEquals(standardCommand,
                new RecurCommand(INDEX_FIRST_EVENT, new Date(VALID_DATE_A),
                        new RecurFactor(VALID_RECUR_FACTOR_MONTH)));
    }
}
