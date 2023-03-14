package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalPersons;

class UndoCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    void execute_noUndoableCommand_failure() {
        assertCommandFailure(new UndoCommand(), model, UndoCommand.MESSAGE_NO_UNDOABLE_COMMAND);
    }
    @Test
    void execute_undoableCommandPresent_success() {
        model.addPerson(TypicalPersons.IDA);

        assertCommandSuccess(new UndoCommand(), model,
                String.format("Successfully undone command:\nAdd %1$s", TypicalPersons.IDA), model);
    }

}
