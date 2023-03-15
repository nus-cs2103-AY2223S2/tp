package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.deletePersonIndexOne;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class RedoCommandTest {

    @Test
    public void execute_noUndoneChangesToRevert_throwsCommandException() {
        Model model = new ModelManager();
        RedoCommand redoCommand = new RedoCommand();
        assertThrows(CommandException.class, RedoCommand.MESSAGE_CANNOT_REDO, () -> redoCommand.execute(model));
    }

    @Test
    public void execute_hasUndoneChangesToRevert_success() {
        RedoCommand redoCommand = new RedoCommand();

        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        deletePersonIndexOne(model);
        deletePersonIndexOne(expectedModel);

        model.undoSocket();
        expectedModel.undoSocket();

        expectedModel.redoSocket();
        assertCommandSuccess(redoCommand, model, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_hasMultipleUndoneChangesToRevert_success() {
        RedoCommand redoCommand = new RedoCommand();

        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        deletePersonIndexOne(model);
        deletePersonIndexOne(expectedModel);
        deletePersonIndexOne(model);
        deletePersonIndexOne(expectedModel);

        model.undoSocket();
        model.undoSocket();
        expectedModel.undoSocket();
        expectedModel.undoSocket();

        expectedModel.redoSocket();
        assertCommandSuccess(redoCommand, model, RedoCommand.MESSAGE_SUCCESS, expectedModel);

        expectedModel.redoSocket();
        assertCommandSuccess(redoCommand, model, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
