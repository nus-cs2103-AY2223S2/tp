package seedu.address.ui.popups;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import seedu.address.ui.MainWindow;
import seedu.address.ui.UiPart;

/**
 * Hbox containing button
 */
public class ControlBox extends UiPart<HBox> {
    private static final String FXML = "ControlBox.fxml";
    private final PopupAddInternship popupInternship;
    private final MainWindow mainWindow;

    /**
     * Creates a {@code ControlBox}.
     */
    public ControlBox(MainWindow mainWindow) {
        super(FXML);
        this.mainWindow = mainWindow;
        popupInternship = new PopupAddInternship(mainWindow);
    }

    /**
     * Handles the add internship button clicked event.
     */
    @FXML
    private void handleAddInternshipClicked() {
        if (!popupInternship.isShowing()) {
            popupInternship.show();
        } else {
            popupInternship.focus();
        }
    }
}
