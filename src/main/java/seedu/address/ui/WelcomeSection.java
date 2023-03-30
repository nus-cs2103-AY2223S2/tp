package seedu.address.ui;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.ui.events.ProceedCreatePasswordEvent;
import seedu.address.ui.events.SkipCreatePasswordEvent;

/**
 * Welcome Section
 *
 * @author Haiqel
 */
public class WelcomeSection extends UiPart<Region> {

    private static final String FXML = "WelcomeSection.fxml";
    @FXML
    private Label errorMessageLabel;

    @FXML
    private TextField inputField;

    @FXML
    private VBox tempContainer;

    public WelcomeSection() {
        super(FXML);
    }

    /**
     * Handles the event when the user submits his/her response
     */
    @FXML
    private void handleSubmit(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String input = inputField.getText().toLowerCase();
            if (input.equals("yes") || input.equals("y")) {
                ProceedCreatePasswordEvent proceedCreatePasswordEvent = new ProceedCreatePasswordEvent();
                Event.fireEvent(tempContainer, proceedCreatePasswordEvent);
            } else if (input.equals("no") || input.equals("n")) {
                SkipCreatePasswordEvent skipCreatePasswordEvent = new SkipCreatePasswordEvent();
                Event.fireEvent(tempContainer, skipCreatePasswordEvent);
            } else {
                // show error message
                this.errorMessageLabel.setText("Please enter only `yes`,`y`, `no`,`n` only!");
            }
        }
    }
}
