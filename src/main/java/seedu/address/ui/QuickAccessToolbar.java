package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class QuickAccessToolbar extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "QuickAccessToolbar.fxml";

    private final CommandExecutor commandExecutor;

    @FXML
    private Button homeButton;

    @FXML
    private Button notificationButton;

    @FXML
    private Button helpButton;

    /**
     * Creates a {@code QuickAccessToolBar} with 3 buttons homeButton, notificationButton and helpButton.
     */
    public QuickAccessToolbar(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
    }

    /**
     * Handles the home button pressed event.
     */
    @FXML
    private void redirectToHome() {
        try {
            setFocusButton(homeButton);
            commandExecutor.execute("list");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure(homeButton);
        }
    }

    /**
     * Handles the notification button pressed event.
     */
    @FXML
    private void redirectToReminder() {
        try {
            setFocusButton(notificationButton);
            commandExecutor.execute("remind");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure(notificationButton);
        }
    }

    /**
     * Handles the help button pressed event.
     */
    @FXML
    private void redirectToHelp() {
        try {
            setFocusButton(helpButton);
            commandExecutor.execute("help");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure(helpButton);
        }
    }

    private void setFocusButton(Button focusButton) {
        focusButton.requestFocus();
    }

    /**
     * Sets the button style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure(Button button) {
        ObservableList<String> styleClass = button.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.address.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }
}
