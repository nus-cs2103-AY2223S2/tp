package seedu.address.ui;

import java.util.HashSet;
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
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.logic.parser.CliSyntax.*;

public class AddPatientWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(AddPatientWindow.class);
    private static final String FXML = "AddPatientWindow.fxml";

    private final ErrorMessageDisplay errorMessageDisplay;
    private final TagsHandler tagsHandler;
    private final CommandBox.CommandExecutor commandExecutor;
    private Stage addStage;

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


    public AddPatientWindow(CommandBox.CommandExecutor commandExecutor, Stage addStage) {
        super(FXML, addStage);
        this.addStage = addStage;
        this.errorMessageDisplay = new ErrorMessageDisplay(errorMessagePlaceholder);
        tagsHandler = new TagsHandler(tagField, tags, errorMessageDisplay);
        this.commandExecutor = commandExecutor;
    }

    @FXML
    public void closeAddPatientWindow() {
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

    public boolean isShowing() {
        return getRoot().isShowing();
    }

    public void requestFocus() {
        getRoot().requestFocus();
    }

    String getPatientNameInput() {
        return name.getText();
    }

    String getPatientAddressInput() {
        return address.getText();
    }

    String getPatientPhoneInput() {
        return phone.getText();
    }

    String getPatientEmailInput() {
        return email.getText();
    }

    String getPatientTimeInput() {
        return time.getText();
    }

    String getPatientMedicalConditionInput() {
        return medicalCondition.getText();
    }

    String getPatientAgeInput() {
        return age.getText();
    }

    HashSet<String> getCustomerTagsInput() {
        return tagsHandler.getTags();
    }

    private String getAddPatientCommand() {
        String addPatientCommandInput = AddCommand.COMMAND_WORD + " ";
        addPatientCommandInput += PREFIX_NAME + getPatientNameInput() + " ";
        addPatientCommandInput += PREFIX_PHONE + getPatientPhoneInput() + " ";
        addPatientCommandInput += PREFIX_EMAIL + getPatientEmailInput() + " ";
        addPatientCommandInput += PREFIX_ADDRESS + getPatientAddressInput() + " ";


        HashSet<String> uniqueTags = getCustomerTagsInput();

        for (String tag : uniqueTags) {
            addPatientCommandInput += PREFIX_TAG + tag + " ";
        }

        if (!getPatientTimeInput().isBlank()) {
            addPatientCommandInput += PREFIX_SCHEDULE + getPatientTimeInput() + " ";
        }
        if (!getPatientMedicalConditionInput().isBlank()) {
            addPatientCommandInput += PREFIX_MEDICAL + getPatientMedicalConditionInput() + " ";
        }
        if (!getPatientAgeInput().isBlank()) {
            addPatientCommandInput += PREFIX_AGE + getPatientAgeInput() + " ";
        }

        return addPatientCommandInput;
    }

    @FXML
    public void handleAddPatientCommandInput() {
        String addPatientCommandInput = getAddPatientCommand();
        try {
            commandExecutor.execute(addPatientCommandInput);
            closeAddPatientWindow();
            addStage.close();
        } catch (CommandException | ParseException e) {
            errorMessageDisplay.setError(e.getMessage());
        }
    }

}
