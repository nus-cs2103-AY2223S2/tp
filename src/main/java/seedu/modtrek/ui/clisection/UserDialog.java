package seedu.modtrek.ui.clisection;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.modtrek.ui.UiPart;

/**
 * A UI component that displays the input dialog after input of commands.
 */
public class UserDialog extends UiPart<Region> {
    private static final String FXML = "cli_section/UserDialog.fxml";

    @FXML
    private Label userDialogText;

    /**
     * Creates a {@code UserDialog}.
     *
     * @param text the input string
     */
    public UserDialog(String text) {
        super(FXML);
        userDialogText.setText(text);
    }
}
