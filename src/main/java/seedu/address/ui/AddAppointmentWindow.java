package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.commands.SearchAppointmentCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;


/**
 * A pop-up window that handles the check availability and add appointment functions.
 */
public class AddAppointmentWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(AddAppointmentWindow.class);
    private static final String FXML = "AddAppointmentWindow.fxml";
    private final ErrorMessageDisplay errorMessageDisplay;
    private final CommandBox.CommandExecutor commandExecutor;
    private Stage appointmentStage;
    private ListView<Person> personListView;

    @FXML
    private TextField fromTime;
    @FXML
    private TextField toTime;
    @FXML
    private TextField date;
    @FXML
    private HBox errorMessagePlaceholder;
    @FXML
    private Button handleCheckAvailbilityCommandButton;
    @FXML
    private Button handleAddApppointmentButton;
    @FXML
    private Label addAppointmentWindowHeader;

    /**
     * Constructor for AddAppointmentWindow.
     *
     * @param commandExecutor CommandExecutor that is used to execute corresponding commands.
     * @param appointmentStage Stage for the AddPatientWindow.
     * @param personListView List of Patients for view.
     */
    public AddAppointmentWindow(CommandBox.CommandExecutor commandExecutor, Stage appointmentStage,
                                ListView<Person> personListView) {
        super(FXML, appointmentStage);
        this.appointmentStage = appointmentStage;
        this.errorMessageDisplay = new ErrorMessageDisplay(errorMessagePlaceholder);
        this.commandExecutor = commandExecutor;
        this.personListView = personListView;
    }

    /**
     * Close the current AddAppointmentWindow.
     */
    @FXML
    public void closeAddAppointmentWindow() {
        date.clear();
        toTime.clear();
        fromTime.clear();
        errorMessageDisplay.clearError();
    }

    /**
     * Check whether AddAppointmentWindow is showing.
     *
     * @return AddAppointmentWindow is showing or not.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Requests that this AddAppointmentWindow get the input focus.
     */
    public void requestFocus() {
        getRoot().requestFocus();
    }

    /**
     * Show the AddAppointmentWindow.
     */
    @FXML
    public void showAddAppointmentWindow() {
        getRoot().show();
        getRoot().centerOnScreen();
        logger.fine("Showing Add Appointment Window.");
        getRoot().requestFocus();
    }

    /**
     * Get the new appointment date.
     *
     * @return New appointment date.
     */
    String getDateInput() {
        return date.getText();
    }

    /**
     * Get the new appointment starting time.
     *
     * @return New appointment starting time.
     */
    String getFromTimeInput() {
        return fromTime.getText();
    }

    /**
     * Get the new appointment ending time.
     *
     * @return New appointment ending time.
     */
    String getToTimeInput() {
        return toTime.getText();
    }

    /**
     * Get the check availability command.
     *
     * @return Check date availability command.
     */
    private String getCheckAvailabilityCommand() {
        String checkAvailabilityCommand = SearchAppointmentCommand.COMMAND_WORD + " ";
        checkAvailabilityCommand += getDateInput();

        return checkAvailabilityCommand;
    }

    /**
     * Get the add appointment command.
     *
     * @return Add appointment command.
     */
    private String getAddAppointmentCommand() {
        String addAppointmentCommand = AddAppointmentCommand.COMMAND_WORD + " ";
        int targetIndex = personListView.getSelectionModel().getSelectedIndex() + 1;
        addAppointmentCommand += targetIndex + " ";
        addAppointmentCommand += "/from " + getDateInput() + " " + getFromTimeInput() + " ";
        addAppointmentCommand += "/to " + getDateInput() + " " + getToTimeInput();

        return addAppointmentCommand;
    }

    /**
     * Handle the input checkAvailabilityCommand by executing the command line.
     */
    @FXML
    public void handleCheckAvailabilityCommandInput() {
        String checkAvailabilityCommandInput = getCheckAvailabilityCommand();

        try {
            commandExecutor.execute(checkAvailabilityCommandInput);
            closeAddAppointmentWindow();
            appointmentStage.close();
        } catch (CommandException | ParseException e) {
            errorMessageDisplay.setError(e.getMessage());
        }
    }



    /**
     * Handle the input addAppointment by executing the command line and close the pop-up window.
     */
    @FXML
    public void handleAddAppointmentCommandInput() {
        String addAppointmentCommandInput = getAddAppointmentCommand();

        try {
            commandExecutor.execute(addAppointmentCommandInput);
            closeAddAppointmentWindow();
            appointmentStage.close();
        } catch (CommandException | ParseException e) {
            errorMessageDisplay.setError(e.getMessage());
        }
    }

}

