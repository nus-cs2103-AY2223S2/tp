package seedu.address.ui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.ui.events.CreatePasswordSuccessfulEvent;

/**
 * Controller for when the user creates a new password
 *
 * @author Haiqel Bin Hanaffi
 */
public class CreatePasswordSection extends UiPart<Region> {
    private static final String FXML = "CreatePasswordSection.fxml";

    @FXML
    private PasswordField firstInputField;

    @FXML
    private PasswordField secondInputField;

    @FXML
    private Label errorMessageLabel;

    @FXML
    private VBox tempContainer;

    public CreatePasswordSection() {
        super(FXML);
    }

    @FXML
    private void handleSubmit(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            // Check if both passwords match
            String newPassword = firstInputField.getText();
            String reEnterPassword = secondInputField.getText();
            if (newPassword.equals(reEnterPassword)) {
                // send event to go to loading screen
                CreatePasswordSuccessfulEvent createPasswordSuccessfulEvent =
                        new CreatePasswordSuccessfulEvent(newPassword);
                Event.fireEvent(tempContainer, createPasswordSuccessfulEvent);
            } else {
                this.errorMessageLabel.setText("Passwords do not match. Please try again.");
            }
        }
    }

    @FXML
    private void handleButtonSubmit(ActionEvent event) {
        // Check if both passwords match
        String newPassword = firstInputField.getText();
        String reEnterPassword = secondInputField.getText();
        if (newPassword.equals(reEnterPassword)) {
            // send event to go to loading screen
            CreatePasswordSuccessfulEvent createPasswordSuccessfulEvent =
                    new CreatePasswordSuccessfulEvent(newPassword);
            Event.fireEvent(tempContainer, createPasswordSuccessfulEvent);
        } else {
            this.errorMessageLabel.setText("Passwords do not match. Please try again.");
        }
    }


}
