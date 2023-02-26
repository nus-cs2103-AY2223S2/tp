package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalEduMate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class UserCommandTest {
    private Model model = new ModelManager(getTypicalEduMate(), new UserPrefs());

    @Test
    public void constructor_validUser_success() {
        UserCommand userCommand = new UserCommand();
        assertNotEquals(userCommand, null);
    }

    @Test
    public void execute_validUser_success() {
        UserCommand userCommand = new UserCommand();
        String expectedMessage = UserCommand.SHOWING_USER_PROFILE_MESSAGE;

        try {
            CommandResult commandResult = userCommand.execute(model);
            assertEquals(commandResult.getFeedbackToUser(), expectedMessage);
        } catch (CommandException ce) {
            assertTrue(false);
        }
    }
}
