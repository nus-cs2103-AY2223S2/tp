package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.DarkCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.DarkCommand.MESSAGE_ERROR;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserData;
import seedu.address.model.UserPrefs;

public class DarkCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new UserData());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new UserData());

    //Default Start up Dark Theme
    //Expected Behaviour: Error
    @Test
    public void execute_default_dark_failure() {
        assertCommandFailure(new DarkCommand(), model, MESSAGE_ERROR);
    }

    //From Light Theme
    //Expected Behaviour to change from Light Theme -> Dark Theme
    @Test
    public void execute_light_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS, true);
        model.setCssFilePath("view/LightTheme.css");
        expectedModel.setCssFilePath("view/LightTheme.css");
        assertCommandSuccess(new DarkCommand(), model, expectedCommandResult, expectedModel);
    }

    //Already in Dark Theme
    //Expected Behavior : Throw error
    @Test
    void execute_light_failure() {
        model.setCssFilePath("view/DarkTheme.css");
        assertCommandFailure(new DarkCommand(), model, MESSAGE_ERROR);
    }
}
