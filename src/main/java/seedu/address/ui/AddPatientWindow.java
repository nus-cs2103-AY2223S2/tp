package seedu.address.ui;

import java.util.HashSet;
import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

import static seedu.address.logic.parser.CliSyntax.*;

/**
 * A pop-up window that handles the add function.
 */
public class AddPatientWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(AddPatientWindow.class);
    private static final String FXML = "AddPatientWindow.fxml";
    private final ErrorMessageDisplay errorMessageDisplay;
    private final CommandBox.CommandExecutor commandExecutor;
    private Stage addStage;
    private static final String ERROR_BLANK_TAG_NAME = "Tag name should not be blank!";
    private static final String ERROR_DUPLICATED_TAG_NAME = "This tag has already been added!";
    private final HashSet<String> uniqueTags;

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
        this.commandExecutor = commandExecutor;
        this.uniqueTags = new HashSet<>();

        tagField.setOnKeyPressed(keyPressed -> {
            if (keyPressed.getCode() == KeyCode.ENTER) {
                handleAddTag();
            }
        });
    }

    //@@author lxz333
    //Reused from https://github.com/AY2223S1-CS2103T-W11-3/tp
    // with minor modifications
    public void handleAddTag() {
        String tagName = tagField.getText().trim();

        if (tagName.isEmpty()) {
            errorMessageDisplay.setError(ERROR_BLANK_TAG_NAME);
            return;
        }

        if (!Tag.isValidTagName(tagName)) {
            errorMessageDisplay.setError(Tag.MESSAGE_CONSTRAINTS);
            return;
        }

        if (uniqueTags.add(tagName)) {
            HBox newTag = constructNewTag(tagName);
            tags.getChildren().add(newTag);
            errorMessageDisplay.clearError();
        } else {
            errorMessageDisplay.setError(ERROR_DUPLICATED_TAG_NAME);
        }

        tagField.clear();
    }

    /**
     * Create tags for the patient in the HBox for tags.
     *
     * @param tagName
     * @return HBox containing the tag labels.
     */
    private HBox constructNewTag(String tagName) {
        Label newTagName = new Label(tagName);
        Label deleteTag = new Label("X");

        HBox newTag = new HBox(newTagName, deleteTag);
        newTag.setSpacing(5);
        newTag.setMaxWidth(50);
        deleteTag.setOnMouseClicked(e -> {
            tags.getChildren().remove(newTag);
            uniqueTags.remove(tagName);
        });

        return newTag;
    }
    //@@author

    /**
     * Clear all the current tags of the patient.
     */
    public void clearTags() {
        uniqueTags.clear();
        tagField.clear();
        tags.getChildren().clear();
    }

    /**
     * Get all the unique tags of the patient.
     *
     * @return Unique tags of the patient.
     */
    public HashSet<String> getTags() {
        return uniqueTags;
    }

    /**
     * Close the current AddPatientWindow.
     */
    @FXML
    public void closeAddPatientWindow() {
        name.clear();
        age.clear();
        medicalCondition.clear();
        time.clear();
        phone.clear();
        email.clear();
        address.clear();
        errorMessageDisplay.clearError();
        clearTags();
    }

    /**
     * Show the AddPatientWindow.
     */
    @FXML
    public void showAddPatientWindow() {
        getRoot().show();
        getRoot().centerOnScreen();
        logger.fine("Showing Add Patient Window.");
        getRoot().requestFocus();
    }

    /**
     * Check whether AddPatientWindow is showing.
     *
     * @return AddPatientWindow is showing or not.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Requests that this AddPatientWindow get the input focus.
     */
    public void requestFocus() {
        getRoot().requestFocus();
    }

    /**
     * Get the new patient's name.
     *
     * @return New patient's name.
     */
    String getPatientNameInput() {
        return name.getText();
    }

    /**
     * Get the new patient's address.
     *
     * @return New patient's address.
     */
    String getPatientAddressInput() {
        return address.getText();
    }

    /**
     * Get the new patient's phone number.
     *
     * @return New patient's phone number.
     */
    String getPatientPhoneInput() {
        return phone.getText();
    }

    /**
     * Get the new patient's email.
     *
     * @return New patient's email.
     */
    String getPatientEmailInput() {
        return email.getText();
    }

    /**
     * Get the new patient's time.
     *
     * @return New patient's time.
     */
    String getPatientTimeInput() {
        return time.getText();
    }

    /**
     * Get the new patient's medical condition.
     *
     * @return New patient's medical condition.
     */
    String getPatientMedicalConditionInput() {
        return medicalCondition.getText();
    }

    /**
     * Get the new patient's age.
     *
     * @return New patient's age.
     */
    String getPatientAgeInput() {
        return age.getText();
    }

    /**
     * Get the new patient's tags.
     *
     * @return New patient's tags.
     */
    HashSet<String> getCustomerTagsInput() {
        return getTags();
    }

    /**
     * Get the add patient command.
     *
     * @return addPatientCommand
     */
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

    /**
     * Handle the input addPatientCommand by executing the command line and close the pop-up window.
     */
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
