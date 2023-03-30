package seedu.address.ui;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.ui.events.AttemptLoginEvent;
import seedu.address.ui.events.LoginFailEvent;
import seedu.address.ui.events.ProceedCreatePasswordEvent;

/**
 * A section of the UI that will display the default login page
 */
public class DefaultLoginSection extends UiPart<Region> {
    private static final String FXML = "DefaultLoginSection.fxml";
    @FXML
    private Label errorMessageLabel;

    @FXML
    private TextField inputField;

    @FXML
    private VBox tempContainer;

    private Stage primaryStage;

    /**
     * Creates a default login page
     */
    public DefaultLoginSection(Stage primaryStage) {
        super(FXML);
        this.primaryStage = primaryStage;
        this.addEventHandlers();
    }

    /**
     * Handles the event when the user submits his/her response
     */
    @FXML
    private void handleSubmit(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String password = inputField.getText().toLowerCase();
            AttemptLoginEvent attemptLoginEvent = new AttemptLoginEvent(password);
            Event.fireEvent(tempContainer, attemptLoginEvent);
        }
    }

    /**
     * Adds event handlers to listen for events
     */
    private void addEventHandlers() {
        this.primaryStage.addEventHandler(LoginFailEvent.LOGIN_FAIL_EVENT,
                this::handleLoginError);

    }

    /**
     * Handles the event when the user decides to create a new password
     */
    @FXML
    private void handleCreateNewPassword() {
        ProceedCreatePasswordEvent proceedCreatePasswordEvent = new ProceedCreatePasswordEvent();
        Event.fireEvent(tempContainer, proceedCreatePasswordEvent);
    }

    /**
     * Handles the event when the user fails to login with the given password
     * @param event
     */
    @FXML
    private void handleLoginError(LoginFailEvent event) {
        this.errorMessageLabel.setText("Password entered is incorrect. Please try again!");
    }
}
