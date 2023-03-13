package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.api.condition.EnabledIf;

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
    @EnabledIf(value = "java.awt.GraphicsEnvironment.isHeadless")
    public void execute_copyValidIndexInHeadless_success() {
        CopyCommand copyCommand = new CopyCommand(INDEX_FIRST_PERSON);
        Person personToCopy = expectedModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        String expectedMessage = CopyCommand.MESSAGE_NO_CLIPBOARD_FOUND + copyCommand.getInformation(personToCopy);

        assertCommandSuccess(copyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    @DisabledIf(value = "java.awt.GraphicsEnvironment.isHeadless")
    public void execute_copyValidIndexNotInHeadless_success() {
        CopyCommand copyCommand = new CopyCommand(INDEX_FIRST_PERSON);

        assertCommandSuccess(copyCommand, model, CopyCommand.MESSAGE_COPY_SUCCESS, expectedModel);
    }

    @Test
    public void execute_copyInvalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        CopyCommand copyCommand = new CopyCommand(outOfBoundIndex);

        assertCommandFailure(copyCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
}
