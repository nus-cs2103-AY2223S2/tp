package seedu.modtrek.ui.clisection;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.modtrek.logic.commands.CommandResult;
import seedu.modtrek.logic.commands.exceptions.CommandException;
import seedu.modtrek.logic.parser.exceptions.ParseException;
import seedu.modtrek.ui.UiPart;

/**
 * A UI component that displays the portion of a Command-Line Interface to input commands.
 */
public class CliSection extends UiPart<Region> {
    private static final String FXML = "clisection/CliSection.fxml";

    private final CommandExecutor commandExecutor;

    @FXML
    private ScrollPane cliScreenScrollContainer;

    @FXML
    private VBox cliScreen;

    @FXML
    private TextField cliInput;

    /**
     * Creates a {@code CliSection} with the given {@code CommandExecutor}.
     */
    public CliSection(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;

        // Scroll down to the end every time cliScreen's height changes.
        cliScreen.heightProperty().addListener((observable) -> cliScreenScrollContainer.setVvalue(1.0));
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        String commandText = cliInput.getText();
        if (commandText.equals("")) {
            return;
        }

        // Remove the initial welcome dialog shown on cliScreen on app launch
        cliScreen.getChildren().remove(cliScreen.lookup("#cli-screen-welcome-dialog"));

        addUserDialog(commandText);

        //  Call execute here
        try {
            CommandResult commandResult = commandExecutor.execute(commandText);
            addSystemDialog(commandResult.getFeedbackToUser(), true);
        } catch (CommandException | ParseException e) {
            addSystemDialog(e.getMessage(), false);
        } finally {
            cliInput.setText("");
        }
    }

    private void addUserDialog(String text) {
        UserDialog userDialog = new UserDialog(text);
        cliScreen.getChildren().add(userDialog.getRoot());
    }

    private void addSystemDialog(String text, boolean isSuccess) {
        SystemDialog systemDialog = new SystemDialog(text, isSuccess);
        cliScreen.getChildren().add(systemDialog.getRoot());
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.modtrek.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }
}
