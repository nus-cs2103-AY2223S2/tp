package seedu.internship.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import seedu.internship.logic.commands.CommandResult;
import seedu.internship.logic.commands.ListCommand;
import seedu.internship.logic.commands.exceptions.CommandException;

/**
 * A test class for the command box of InternBuddy
 *
 * @@author potty10-reused
 * Adapted and reused from
 * https://github.com/se-edu/addressbook-level4/blob/master/src/test/java/seedu/address/ui/CommandBoxTest.java
 * with modifications.
 */
//
public class CommandBoxTest extends GuiUnitTest {
    private static final String COMMAND_THAT_SUCCEEDS = ListCommand.COMMAND_WORD;
    private static final String COMMAND_THAT_FAILS = "hello there";
    private static final String COMMAND_INPUT_FIELD_ID = "#commandTextField";

    private TextField commandTextField;

    private void run(String command) {
        fxRobot.clickOn(commandTextField);
        fxRobot.interact(() -> commandTextField.setText(command));
        fxRobot.type(KeyCode.ENTER);
    }

    @BeforeEach
    public void setUp() {
        CommandBox commandBox = new CommandBox(commandText -> {
            if (commandText.equals(COMMAND_THAT_SUCCEEDS)) {
                return new CommandResult("Command successful");
            }
            throw new CommandException("Command failed");
        });
        commandTextField = getChildNode(commandBox.getRoot(), COMMAND_INPUT_FIELD_ID);
        uiPartExtension.setUiPart(commandBox);
    }

    @Test
    public void commandBox_multipleCommands_success() {
        assertBehaviorForSuccessfulCommand();
        assertBehaviorForSuccessfulCommand();
        assertBehaviorForFailedCommand();
        assertBehaviorForFailedCommand();
        assertBehaviorForSuccessfulCommand();
    }

    @Test
    public void commandBox_handleKeyPress_success() {
        run("CHEESE");
        run("HELLO");
        fxRobot.push(KeyCode.UP);
        fxRobot.push(KeyCode.UP);
        assertEquals("CHEESE", commandTextField.getText());
        fxRobot.push(KeyCode.DOWN);
        assertEquals("HELLO", commandTextField.getText());
    }


    /**
     * Runs a command that fails, then verifies that the command box is not cleared.
     */
    private void assertBehaviorForFailedCommand() {
        run(COMMAND_THAT_FAILS);
        assertEquals(COMMAND_THAT_FAILS, commandTextField.getText());
    }

    /**
     * Runs a command that succeeds, then verifies that the command box is cleared.
     */
    private void assertBehaviorForSuccessfulCommand() {
        run(COMMAND_THAT_SUCCEEDS);
        assertEquals("", commandTextField.getText());
    }
}
