package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class CopyCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

    @Test
    public void execute_copyValidIndex_success() {
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
