package seedu.modtrek.ui.clisection;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.modtrek.ui.UiPart;

/**
 * A UI component that displays the input dialog after input of commands.
 */
public class UserDialog extends UiPart<Region> {
    private static final String FXML = "clisection/UserDialog.fxml";

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

        /* Set userDialogText's min height so that it's height does not shrink when scrollpane overflows. */
        userDialogText.setMinHeight(Region.USE_PREF_SIZE);
    }
}
