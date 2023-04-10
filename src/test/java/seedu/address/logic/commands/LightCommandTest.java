package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.LightCommand.MESSAGE_ERROR;
import static seedu.address.logic.commands.LightCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserData;
import seedu.address.model.UserPrefs;

public class LightCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new UserData());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new UserData());

    //Default Start up Dark Theme
    //Expected Behaviour to change from Dark Theme -> Light Theme
    @Test
    public void execute_defaultlight_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS, false);
        assertCommandSuccess(new LightCommand(), model, expectedCommandResult, expectedModel);
    }

    //From Dark Theme
    //Expected Behaviour to change from Dark Theme -> Light Theme
    @Test
    public void execute_light_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS, false);
        model.setCssFilePath("view/DarkTheme.css");
        expectedModel.setCssFilePath("view/DarkTheme.css");
        assertCommandSuccess(new LightCommand(), model, expectedCommandResult, expectedModel);
    }

    //Already in Light Theme
    //Expected Behavior : Throw error
    @Test
    void execute_light_failure() {
        model.setCssFilePath("view/LightTheme.css");
        assertCommandFailure(new LightCommand(), model, MESSAGE_ERROR);
    }
}
