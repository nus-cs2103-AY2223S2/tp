package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ThemeCommand.MESSAGE_CHANGE_THEME_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ThemeCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_theme_success() {
        String validTheme = "light";
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_CHANGE_THEME_SUCCESS, validTheme);
        assertCommandSuccess(new ThemeCommand(validTheme), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidTheme_throwsCommandException() {
        String invalidTheme = "notATheme";
        ThemeCommand themeCommand = new ThemeCommand(invalidTheme);

        assertCommandFailure(themeCommand, model, Messages.MESSAGE_INVALID_THEME);
    }
}
