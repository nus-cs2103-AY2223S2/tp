
package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalContacts.getTypicalContactList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Event;
import seedu.address.model.person.Rate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code RateCommand}.
 */
public class RateCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalContactList(), new UserPrefs());

    @Test
    public void execute_validIndex_success() {
        Event event = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Rate rate = event.getRate();
        RateCommand rateCommand = new RateCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(RateCommand.MESSAGE_SUCCESS, rate);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getContactList(), new UserPrefs());
        expectedModel.getRate(event);

        assertCommandSuccess(rateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        RateCommand rateCommand = new RateCommand(outOfBoundIndex);

        assertCommandFailure(rateCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        RateCommand rateFirstCommand = new RateCommand(INDEX_FIRST_PERSON);
        RateCommand rateSecondCommand = new RateCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(rateFirstCommand.equals(rateFirstCommand));

        // same values -> returns true
        RateCommand rateFirstCommandCopy = new RateCommand(INDEX_FIRST_PERSON);
        assertTrue(rateFirstCommand.equals(rateFirstCommandCopy));

        // different types -> returns false
        assertFalse(rateFirstCommand.equals(1));

        // null -> returns false
        assertFalse(rateFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(rateFirstCommand.equals(rateSecondCommand));
    }
}
