package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.awt.GraphicsEnvironment;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class CopyCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

    @Test
    public void execute_copyValidIndex_success() {
        if (GraphicsEnvironment.isHeadless()) {
            CopyCommand copyCommand = new CopyCommand(INDEX_FIRST_PERSON);
            Person personToCopy = expectedModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
            String expectedMessage = CopyCommand.MESSAGE_NO_CLIPBOARD_FOUND + copyCommand.getInformation(personToCopy);

            assertCommandSuccess(copyCommand, model, expectedMessage, expectedModel);
        } else {
            CopyCommand copyCommand = new CopyCommand(INDEX_FIRST_PERSON);

            assertCommandSuccess(copyCommand, model, CopyCommand.MESSAGE_COPY_SUCCESS, expectedModel);
        }
    }

    @Test
    public void execute_copyInvalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        CopyCommand copyCommand = new CopyCommand(outOfBoundIndex);

        assertCommandFailure(copyCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        CopyCommand copyFirstCommand = new CopyCommand(INDEX_FIRST_PERSON);
        CopyCommand copySecondCommand = new CopyCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(copyFirstCommand.equals(copyFirstCommand));

        // same values -> returns true
        CopyCommand copyFirstCommandCopy = new CopyCommand(INDEX_FIRST_PERSON);
        assertTrue(copyFirstCommand.equals(copyFirstCommandCopy));

        // different types -> returns false
        assertFalse(copyFirstCommand.equals(1));

        // null -> returns false
        assertFalse(copyFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(copyFirstCommand.equals(copySecondCommand));
    }

}
