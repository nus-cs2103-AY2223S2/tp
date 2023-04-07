package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import java.util.Optional;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.patient.Patient;

/**
 * The Contact Display displaying the list of doctors,
 * patients and their respective information.
 */
public class ContactDisplay extends UiPart<Region> {

    private static final String FXML = "ContactDisplay.fxml";

    private static final Logger logger = LogsCenter.getLogger(ContactDisplay.class);

    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private EnlargedDoctorInfoCard enlargedDoctorInfoCard;
    private EnlargedPatientInfoCard enlargedPatientInfoCard;
    private DoctorListPanel doctorListPanel;
    private PatientListPanel patientListPanel;

    @FXML
    private GridPane contactDisplay;

    @FXML
    private StackPane enlargedPersonInfoCardPlaceholder;

    @FXML
    private StackPane doctorListPanelPlaceholder;

    @FXML
    private StackPane patientListPanelPlaceholder;

    /**
     * Creates a {@code ContactDisplay}
     * with the given {@code Logic}.
     */
    public ContactDisplay(Logic logic) {
        super(FXML);
        this.logic = logic;
        fillInnerParts();
    }

    /**
     * Fills up all the placeholders of this {@code ContactDisplay}.
     */
    private void fillInnerParts() {
        enlargedDoctorInfoCard = new EnlargedDoctorInfoCard();
        enlargedPatientInfoCard = new EnlargedPatientInfoCard();
        enlargedPersonInfoCardPlaceholder.getChildren().add(enlargedDoctorInfoCard.getRoot());

        patientListPanel = new PatientListPanel(logic.getFilteredPatientList(), this);
        patientListPanelPlaceholder.getChildren().add(patientListPanel.getRoot());

        doctorListPanel = new DoctorListPanel(logic.getFilteredDoctorList(), this);
        doctorListPanelPlaceholder.getChildren().add(doctorListPanel.getRoot());
    }

    /**
     * Updates the contact display based on user command.
     *
     * @param commandResult a command result.
     */
    public void setFeedbackToUser(CommandResult commandResult) {
        // If command does not need GUI Interaction, function call ends.
        if (!commandResult.hasGuiInteraction()) {
            logger.info("Command did not result in GUI Interaction");
            return;
        }
        setFeedbackIfDoctorSelected(commandResult);
        setFeedbackIfPatientSelected(commandResult);
    }

    /**
     * Updates the contact display if user selected a doctor.
     *
     * @param commandResult a command result.
     */
    private void setFeedbackIfDoctorSelected(CommandResult commandResult) {
        Optional<Doctor> selectedDoctor = commandResult.getSelectedDoctor();
        selectedDoctor.ifPresent(this::setFeedbackUponSelectingDoctor);
    }

    /**
     * Updates the contact display to show the doctor selected by user.
     *
     * @param selectedDoctor a doctor selected by user.
     */
    protected void setFeedbackUponSelectingDoctor(Doctor selectedDoctor) {
        requireNonNull(selectedDoctor);
        logic.updateFilteredPatientListBasedOnDoctor(selectedDoctor);
        doctorListPanel.selectDoctor(selectedDoctor);
        enlargedDoctorInfoCard.updateSelectedDoctor(selectedDoctor);
        enlargedPersonInfoCardPlaceholder.getChildren().clear();
        enlargedPersonInfoCardPlaceholder.getChildren().add(enlargedDoctorInfoCard.getRoot());
    }

    /**
     * Updates the contact display if user selected a patient.
     *
     * @param commandResult a command result.
     */
    private void setFeedbackIfPatientSelected(CommandResult commandResult) {
        Optional<Patient> selectedPatient = commandResult.getSelectedPatient();
        selectedPatient.ifPresent(this::setFeedbackUponSelectingPatient);
    }

    /**
     * Updates the contact display to show the patient selected by user.
     *
     * @param selectedPatient a patient selected by user.
     */
    protected void setFeedbackUponSelectingPatient(Patient selectedPatient) {
        requireNonNull(selectedPatient);
        logic.updateFilteredDoctorListBasedOnPatient(selectedPatient);
        patientListPanel.selectPatient(selectedPatient);
        enlargedPatientInfoCard.updateSelectedPatient(selectedPatient);
        enlargedPersonInfoCardPlaceholder.getChildren().clear();
        enlargedPersonInfoCardPlaceholder.getChildren().add(enlargedPatientInfoCard.getRoot());
    }
}
