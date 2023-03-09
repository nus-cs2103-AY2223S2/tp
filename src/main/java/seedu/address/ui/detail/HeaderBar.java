package seedu.address.ui.detail;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

/**
 * An ui for the status bar that is displayed at the header of the application.
 */
public class HeaderBar extends UiPart<Region> {
    private static final String FXML = "HeaderBar.fxml";

    @FXML
    private Label title;

    /**
     * Creates a {@code HeaderBar} with the given {@code Stage} and {@code Student}.
     */
    public HeaderBar(String titleText) {
        super(FXML);
        title.setText(titleText);
    }
}
