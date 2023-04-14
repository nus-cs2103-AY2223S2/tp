package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.files.FilesManager;

/**
 * A pop-up window that handles the check availability and add appointment functions.
 */
public class AddMcInfo extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(AddAppointmentWindow.class);
    private static final String FXML = "GenerateMC.fxml";
    private static int counter = 0;
    private final ErrorMessageDisplay errorMessageDisplay;
    private Stage mcStage;
    private FilesManager filesManager;

    @FXML
    private TextField medicalCondition;
    @FXML
    private TextField doctorName;
    @FXML
    private TextField days;
    @FXML
    private HBox errorMessagePlaceholder;
    @FXML
    private Button handleAddApppointmentButton;
    @FXML
    private Label addAppointmentWindowHeader;

    /**
     * Constructor for AddAppointmentWindow.
     *
     * @param mcStage Stage for the AddPatientWindow.
     */
    public AddMcInfo(Stage mcStage, FilesManager filesManager) {
        super(FXML, mcStage);
        this.mcStage = mcStage;
        this.filesManager = filesManager;
        this.errorMessageDisplay = new ErrorMessageDisplay(errorMessagePlaceholder);
        counter++;
    }

    /**
     * Close the current AddAppointmentWindow.
     */
    @FXML
    public void closeAddAppointmentWindow() {
        counter = 0;
        medicalCondition.clear();
        doctorName.clear();
        days.clear();
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
    String getMedicalConditionInput() {
        return medicalCondition.getText();
    }

    /**
     * Get the new appointment starting time.
     *
     * @return New appointment starting time.
     */
    String getDoctorNameInput() {
        return doctorName.getText();
    }

    /**
     * Get the new appointment ending time.
     *
     * @return New appointment ending time.
     */
    int getDurationInput() {
        int intDays;
        try {
            intDays = Integer.parseInt(days.getText());
        } catch (NumberFormatException e) {
            errorMessageDisplay.setError("please enter duration in integer format");
        }
        return Integer.parseInt(days.getText());
    }
    /**
     * Handle the input addAppointment by executing the command line and close the pop-up window.
     */
    @FXML
    public void handleAddAppointmentCommandInput() {
        try {
            String doctorName = this.getDoctorNameInput();
            String medical = this.getMedicalConditionInput();
            int duration = this.getDurationInput();
            if (duration < 1 || duration > 60) {
                throw new DurationException("Duration should be 1-60 days");
            }
            if (doctorName.length() > 25 || medical.length() > 200) {
                throw new DurationException("doctor name should be less than 25 chars"
                    + " while medical condition should be less than 200 chars");
            }
            if (doctorName.length() < 1 || medical.length() < 1) {
                throw new DurationException("doctor name and medical condition is necessary");
            }
            filesManager.generateMc(doctorName, medical, duration);
            counter = 0;
            closeAddAppointmentWindow();
            mcStage.close();
        } catch (NumberFormatException e) {
            errorMessageDisplay.setError("duration should be integer format");
        } catch (DurationException e) {
            errorMessageDisplay.setError(e.getMessage());
        }
    }
    public int getCounter() {
        return counter;
    }

    /**
     * Exception thrown when duration is not in the right format
     */
    public class DurationException extends Exception {
        public DurationException(String message) {
            super(message);
        }
    }
}


