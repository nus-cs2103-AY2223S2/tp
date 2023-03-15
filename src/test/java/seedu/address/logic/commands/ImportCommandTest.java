package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.ImportCommand.MESSAGE_NO_FACULTY;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ImportCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    public void executeWrongInput_failure() {
        ImportCommand importCommand = new ImportCommand("abcde");
        String expectedMessage = String.format(MESSAGE_NO_FACULTY);
        assertCommandFailure(importCommand, model, expectedMessage);
    }
}
