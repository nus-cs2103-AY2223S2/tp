package seedu.modtrek.ui.cli_section;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.modtrek.logic.commands.exceptions.CommandException;
import seedu.modtrek.logic.parser.exceptions.ParseException;
import seedu.modtrek.ui.UiPart;

public class CliSection extends UiPart<Region> {
    private static final String FXML = "cli_section/CliSection.fxml";

    @FXML
    private ScrollPane cliScreenScrollContainer;

    @FXML
    private VBox cliScreen;

    @FXML
    private TextField cliInput;

    public CliSection() {
        super(FXML);

        /* Scroll down to the end every time cliScreen's height changes. */
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

        /* Remove the initial welcome dialog shown on cliScreen on app launch */
        cliScreen.getChildren().remove(cliScreen.lookup("#cli-screen-welcome-dialog"));

        addUserDialog(commandText);

        /* TODO: Call execute() here... */
        addSystemDialog("response response response response response response response", false);

        cliInput.setText("");
    }

    private void addUserDialog(String text) {
        UserDialog userDialog = new UserDialog(text);
        cliScreen.getChildren().add(userDialog.getRoot());
    }

    private void addSystemDialog(String text, boolean isSuccess) {
        SystemDialog systemDialog = new SystemDialog(text, isSuccess);
        cliScreen.getChildren().add(systemDialog.getRoot());
    }
}
