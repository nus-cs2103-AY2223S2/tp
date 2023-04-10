package seedu.address.logic.commands.reminder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalDeliveryJobs.getTypicalDeliveryJobSystem;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


public class DeleteReminderCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalDeliveryJobSystem(), new UserPrefs());

    @Test
    public void execute_invalidIndexReminderList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getReminderList().size() + 1);
        DeleteReminderCommand deleteCommand = new DeleteReminderCommand(outOfBoundIndex.getZeroBased());

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_REMINDER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteReminderCommand deleteFirstCommand = new DeleteReminderCommand(INDEX_FIRST_PERSON.getZeroBased());
        DeleteReminderCommand deleteSecondCommand = new DeleteReminderCommand(INDEX_SECOND_PERSON.getZeroBased());

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteReminderCommand deleteFirstCommandCopy = new DeleteReminderCommand(INDEX_FIRST_PERSON.getZeroBased());
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different index -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

}
