package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_APPLICATION_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.application.InternshipStatus.ACCEPTED;
import static seedu.address.model.application.InternshipStatus.DECLINED;
import static seedu.address.model.application.InternshipStatus.PENDING;
import static seedu.address.model.application.InternshipStatus.RECEIVED;
import static seedu.address.testutil.TypicalInternships.ALICE;
import static seedu.address.testutil.TypicalInternships.BANK_OF_AMERICA;
import static seedu.address.testutil.TypicalInternships.getTypicalAddressBook;
import static seedu.address.testutil.TypicalNotes.getTypicalNoteList;
import static seedu.address.testutil.TypicalTodos.getTypicalTodoList;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.application.StatusPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindStatusCommand}.
 */
public class FindStatusCommandTest {
    private Model model = new ModelManager(
            getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(), getTypicalNoteList());
    private Model expectedModel = new ModelManager(
            getTypicalAddressBook(), new UserPrefs(), getTypicalTodoList(), getTypicalNoteList());

    @Test
    public void equals() {
        StatusPredicate firstPredicate = new StatusPredicate(PENDING);
        StatusPredicate secondPredicate = new StatusPredicate(ACCEPTED);

        FindStatusCommand findFirstCommand = new FindStatusCommand(firstPredicate);
        FindStatusCommand findSecondCommand = new FindStatusCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindStatusCommand findFirstCommandCopy = new FindStatusCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_oneStatus_multipleApplicationsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICATION_LISTED_OVERVIEW, 2);
        StatusPredicate predicate = new StatusPredicate(DECLINED);
        FindStatusCommand command = new FindStatusCommand(predicate);
        expectedModel.updateFilteredInternshipList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BANK_OF_AMERICA, ALICE), model.getSortedFilteredInternshipList());
    }

    @Test
    public void execute_noMatchingStatus_noApplicationsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICATION_LISTED_OVERVIEW, 0);
        StatusPredicate predicate = new StatusPredicate(RECEIVED);
        FindStatusCommand command = new FindStatusCommand(predicate);
        expectedModel.updateFilteredInternshipList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getSortedFilteredInternshipList());
    }
}
