package seedu.address.ui;

import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

public class AddPatientWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(AddPatientWindow.class);
    private static final String FXML = "AddPatientWindow.fxml";

    private final ErrorMessageDisplay errorMessageDisplay;
    private final TagsHandler tagsHandler;

    @FXML
    private TextField address;
    @FXML
    private BorderPane patientInfoPane;
    @FXML
    private Label addPatientWindowHeader;
    @FXML
    private TextField email;
    @FXML
    private HBox errorMessagePlaceholder;
    @FXML
    private Button handleAddPatientCommandButton;
    @FXML
    private TextField name;
    @FXML
    private TextField phone;
    @FXML
    private TextField tagField;
    @FXML
    private FlowPane tags;
    @FXML
    private TextField medicalCondition;
    @FXML
    private TextField age;
    @FXML
    private TextField time;

    public AddPatientWindow(Stage stage, String customerWindowHeaderName) {
        super(FXML);
        errorMessageDisplay = new ErrorMessageDisplay(errorMessagePlaceholder);
        tagsHandler = new TagsHandler(tagField, tags, errorDisplay);
    }

    @FXML
    private void closeAddPatientWindow() {
        name.clear();
        age.clear();
        medicalCondition.clear();
        time.clear();
        tagsHandler.clear();
        phone.clear();
        email.clear();
        address.clear();
        errorMessageDisplay.clearError();
    }

    @FXML
    public void showAddPatientWindow() {
        getRoot().show();
        getRoot().centerOnScreen();
        logger.fine("Showing Add Patient Window.");
        getRoot().requestFocus();
    }



}
