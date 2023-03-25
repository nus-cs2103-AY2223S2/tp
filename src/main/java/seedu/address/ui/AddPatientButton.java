package seedu.address.ui;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;

/**
 * Represents the controls of the {@code CommissionListPanel}.
 */
public class AddPatientButton extends UiPart<Region> {

    private static final String FXML = "AddPatientButton.fxml";

    private AddPatientWindow addPatientWindow;

    @FXML
    private Button addCustomerButton;

        public AddPatientButton(CommandBox.CommandExecutor commandExecutor) {
        super(FXML);
        this.addPatientWindow = new AddPatientWindow(commandExecutor);
    }


    /**
     * Opens the add customer window or focuses on it if it's already opened.
     */
    @FXML
    private void showAddPatientWindow() {
        if (!addPatientWindow.isShowing()) {
            addPatientWindow.showAddPatientWindow();
        } else {
            addPatientWindow.requestFocus();
        }
    }
}

