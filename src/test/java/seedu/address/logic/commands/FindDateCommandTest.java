package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_APPLICATION_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalInternships.ALICE;
import static seedu.address.testutil.TypicalInternships.BANK_OF_AMERICA;
import static seedu.address.testutil.TypicalInternships.BENSON;
import static seedu.address.testutil.TypicalInternships.META;
import static seedu.address.testutil.TypicalInternships.getTypicalAddressBook;
import static seedu.address.testutil.TypicalNotes.getTypicalNoteList;
import static seedu.address.testutil.TypicalTodos.getTypicalTodoList;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.application.AfterDatePredicate;
import seedu.address.model.application.BeforeDatePredicate;
import seedu.address.model.application.BetweenDatePredicate;
import seedu.address.model.application.DatePredicate;
import seedu.address.model.application.InterviewDate;

public class FindDateCommandTest {
    private Model model = new ModelManager(
            getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(), getTypicalNoteList());
    private Model expectedModel = new ModelManager(
            getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(), getTypicalNoteList());

    @Test
    public void equals() {
        AfterDatePredicate firstPredicate = new AfterDatePredicate(new InterviewDate("2023-03-15 08:00 AM"));
        AfterDatePredicate secondPredicate = new AfterDatePredicate(new InterviewDate("2023-03-15 11:00 AM"));
        BeforeDatePredicate thirdPredicate = new BeforeDatePredicate(new InterviewDate("2023-03-15 08:00 AM"));

        FindDateCommand findFirstCommand = new FindDateCommand(firstPredicate);
        FindDateCommand findSecondCommand = new FindDateCommand(secondPredicate);
        FindDateCommand findThirdCommand = new FindDateCommand(thirdPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindDateCommand findFirstCommandCopy = new FindDateCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different date -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));

        // different type of predicate -> returns false
        assertFalse(findFirstCommand.equals(findThirdCommand));
    }

    @Test
    public void execute_afterDatePredicate_multipleApplicationsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICATION_LISTED_OVERVIEW, 3);
        DatePredicate predicate = new AfterDatePredicate(new InterviewDate("2023-03-15 08:00 AM"));
        FindDateCommand command = new FindDateCommand(predicate);
        expectedModel.updateFilteredInternshipList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(META, BANK_OF_AMERICA, ALICE), model.getSortedFilteredInternshipList());
    }

    @Test
    public void execute_afterDatePredicate_noApplicationsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICATION_LISTED_OVERVIEW, 0);
        DatePredicate predicate = new AfterDatePredicate(new InterviewDate("2023-07-15 08:00 AM"));
        FindDateCommand command = new FindDateCommand(predicate);
        expectedModel.updateFilteredInternshipList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getSortedFilteredInternshipList());
    }


    @Test
    public void execute_beforeDatePredicate_multipleApplicationsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICATION_LISTED_OVERVIEW, 2);
        DatePredicate predicate = new BeforeDatePredicate(new InterviewDate("2023-03-27 08:00 AM"));
        FindDateCommand command = new FindDateCommand(predicate);
        expectedModel.updateFilteredInternshipList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getSortedFilteredInternshipList());
    }

    @Test
    public void execute_beforeDatePredicate_noApplicationsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICATION_LISTED_OVERVIEW, 0);
        DatePredicate predicate = new BeforeDatePredicate(new InterviewDate("2023-02-27 07:00 AM"));
        FindDateCommand command = new FindDateCommand(predicate);
        expectedModel.updateFilteredInternshipList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getSortedFilteredInternshipList());
    }

    @Test
    public void execute_betweenDatePredicate_multipleApplicationsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICATION_LISTED_OVERVIEW, 2);
        DatePredicate predicate = new BetweenDatePredicate(
                new InterviewDate("2023-03-31 08:00 AM"), new InterviewDate("2023-04-09 09:00 PM"));
        FindDateCommand command = new FindDateCommand(predicate);
        expectedModel.updateFilteredInternshipList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(META, BANK_OF_AMERICA), model.getSortedFilteredInternshipList());
    }

    @Test
    public void execute_betweenDatePredicateEdgeCase_multipleApplicationsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICATION_LISTED_OVERVIEW, 1);
        DatePredicate predicate = new BetweenDatePredicate(
                new InterviewDate("2023-03-27 09:00 AM"), new InterviewDate("2023-04-01 08:00 PM"));
        FindDateCommand command = new FindDateCommand(predicate);
        expectedModel.updateFilteredInternshipList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(META), model.getSortedFilteredInternshipList());
    }

    @Test
    public void execute_betweenDatePredicate_noApplicationsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICATION_LISTED_OVERVIEW, 0);
        DatePredicate predicate = new BetweenDatePredicate(
                new InterviewDate("2023-06-27 09:00 AM"), new InterviewDate("2023-06-28 08:00 PM"));
        FindDateCommand command = new FindDateCommand(predicate);
        expectedModel.updateFilteredInternshipList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getSortedFilteredInternshipList());
    }
}
