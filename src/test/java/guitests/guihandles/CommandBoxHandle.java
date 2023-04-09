package guitests.guihandles;

import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

/**
 * A handle to the {@code CommandBox} in the GUI. Referenced from AB4.
 */
public class CommandBoxHandle extends NodeHandle<TextField> {

    public static final String COMMAND_INPUT_FIELD_ID = "#commandTextField";

    // @@author seanfirefox-reused
    public CommandBoxHandle(TextField commandBoxNode) {
        super(commandBoxNode);
    }

    // @@author seanfirefox-reused
    /**
     * Returns the text in the command box.
     */
    public String getInput() {
        return getRootNode().getText();
    }

    // @@author seanfirefox-reused
    /**
     * Enters the given command in the Command Box and presses enter.
     */
    public void run(String command) {
        click();
        guiRobot.interact(() -> getRootNode().setText(command));
        guiRobot.pauseForHuman();

        guiRobot.type(KeyCode.ENTER);
    }

    /**
     * Clears the Command Box
     */
    public void clear() {
        click();
        guiRobot.interact(() -> getRootNode().setText(""));
        guiRobot.pauseForHuman();
    }

    // @@author seanfirefox-reused
    /**
     * Returns the list of style classes present in the command box.
     */
    public ObservableList<String> getStyleClass() {
        return getRootNode().getStyleClass();
    }
}
