package seedu.address.ui.cli_section;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

public class UserDialog extends UiPart<Region> {
    private static final String FXML = "cli_section/UserDialog.fxml";

    @FXML
    private Label userDialogText;

    public UserDialog(String text) {
        super(FXML);
        userDialogText.setText(text);
    }
}
