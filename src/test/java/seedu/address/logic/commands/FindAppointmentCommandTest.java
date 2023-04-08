package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.commons.core.Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.FIRST_APPOINTMENT;
import static seedu.address.testutil.TypicalAppointments.SECOND_APPOINTMENT;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentList;
import static seedu.address.testutil.TypicalPatients.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.AppointmentHasOverlapPredicate;
import seedu.address.model.appointment.TimeInTimeslotPredicate;
import seedu.address.testutil.FxTest;

/**
 * Contains integration tests (interaction with the Model) for {@code FindAppointmentCommandTest}.
 */
public class FindAppointmentCommandTest extends FxTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalAppointmentList(), new UserPrefs());
    private Model expectedModel =
        new ModelManager(getTypicalAddressBook(), getTypicalAppointmentList(), new UserPrefs());

    @Test
    public void timeInTimeslotEquals() {
        try {
            TimeInTimeslotPredicate firstPredicate =
                new TimeInTimeslotPredicate("01012000 00:00");
            TimeInTimeslotPredicate secondPredicate =
                new TimeInTimeslotPredicate("19032023 08:00");

            FindAppointmentCommand findFirstCommand = new FindAppointmentCommand(firstPredicate);
            FindAppointmentCommand findSecondCommand = new FindAppointmentCommand(secondPredicate);

            // same object -> returns true
            assertTrue(findFirstCommand.equals(findFirstCommand));

            // same values -> returns true
            FindAppointmentCommand findFirstCommandCopy = new FindAppointmentCommand(firstPredicate);
            assertTrue(findFirstCommand.equals(findFirstCommandCopy));

            // different types -> returns false
            assertFalse(findFirstCommand.equals(1));

            // null -> returns false
            assertFalse(findFirstCommand.equals(null));

            // different appointment -> returns false
            assertFalse(findFirstCommand.equals(findSecondCommand));
        } catch (ParseException pe) {
            fail();
        }
    }

    @Test
    public void hasOverlapEquals() {
        try {
            AppointmentHasOverlapPredicate firstPredicate =
                new AppointmentHasOverlapPredicate("01012000 00:00 01012000 01:00");
            AppointmentHasOverlapPredicate secondPredicate =
                new AppointmentHasOverlapPredicate("19032023 08:00 19032023 09:00");

            FindAppointmentCommand findFirstCommand = new FindAppointmentCommand(firstPredicate);
            FindAppointmentCommand findSecondCommand = new FindAppointmentCommand(secondPredicate);

            // same object -> returns true
            assertTrue(findFirstCommand.equals(findFirstCommand));

            // same values -> returns true
            FindAppointmentCommand findFirstCommandCopy = new FindAppointmentCommand(firstPredicate);
            assertTrue(findFirstCommand.equals(findFirstCommandCopy));

            // different types -> returns false
            assertFalse(findFirstCommand.equals(1));

            // null -> returns false
            assertFalse(findFirstCommand.equals(null));

            // different appointment -> returns false
            assertFalse(findFirstCommand.equals(findSecondCommand));
        } catch (ParseException pe) {
            fail();
        }
    }

    @Test
    public void execute_timeInTimeslot_noAppointmentFound() {
        try {
            String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 0);
            TimeInTimeslotPredicate predicate = prepareInTimeslotPredicate("01012000 00:00");
            FindAppointmentCommand command = new FindAppointmentCommand(predicate);
            expectedModel.updateFilteredAppointmentList(predicate);
            assertCommandSuccess(command, model, expectedMessage, expectedModel);
            assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
        } catch (ParseException pe) {
            fail();
        }
    }

    @Test
    public void execute_timeInTimeslot_oneAppointmentFound() {
        try {
            String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 1);
            TimeInTimeslotPredicate predicate = prepareInTimeslotPredicate("19032023 08:30");
            FindAppointmentCommand command = new FindAppointmentCommand(predicate);
            expectedModel.updateFilteredAppointmentList(predicate);
            assertCommandSuccess(command, model, expectedMessage, expectedModel);
            assertEquals(Arrays.asList(FIRST_APPOINTMENT), model.getFilteredAppointmentList());
        } catch (ParseException pe) {
            fail();
        }
    }

    @Test
    public void execute_hasOverlap_multipleAppointmentsFound() {
        try {
            String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 2);
            AppointmentHasOverlapPredicate predicate = prepareHasOverlapPredicate("19032023 08:00 20032023 14:00");
            FindAppointmentCommand command = new FindAppointmentCommand(predicate);
            expectedModel.updateFilteredAppointmentList(predicate);
            assertCommandSuccess(command, model, expectedMessage, expectedModel);
            assertEquals(Arrays.asList(FIRST_APPOINTMENT, SECOND_APPOINTMENT), model.getFilteredAppointmentList());
        } catch (ParseException pe) {
            fail();
        }
    }

    /**
     * Parses {@code userInput} into a {@code TimeInTimeslotPredicatePredicate}.
     */
    private TimeInTimeslotPredicate prepareInTimeslotPredicate(String userInput) throws ParseException {
        return new TimeInTimeslotPredicate(userInput);
    }

    /**
     * Parses {@code userInput} into a {@code AppointmentHasOverlapPredicate}.
     */
    private AppointmentHasOverlapPredicate prepareHasOverlapPredicate(String userInput) throws ParseException {
        return new AppointmentHasOverlapPredicate(userInput);
    }
}
