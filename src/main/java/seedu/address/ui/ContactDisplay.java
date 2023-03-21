package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.logic.Logic;

/**
 * The Contact Display displaying the list of doctors,
 * patients and their respective information.
 */
public class ContactDisplay extends UiPart<Region> {

    private static final String FXML = "ContactDisplay.fxml";

    private Logic logic;

    // To toggle between displaying Doctor and Patient Info
    private EnlargedInfoCardDisplayController infoCardDisplayController;

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
        this.infoCardDisplayController = new EnlargedInfoCardDisplayController(this);
        fillInnerParts();
    }

    /**
     * Fills up all the placeholders of this {@code ContactDisplay}.
     */
    private void fillInnerParts() {
        enlargedDoctorInfoCard = new EnlargedDoctorInfoCard();
        enlargedPatientInfoCard = new EnlargedPatientInfoCard();
        enlargedPersonInfoCardPlaceholder.getChildren().add(enlargedDoctorInfoCard.getRoot());

        patientListPanel = new PatientListPanel(logic.getFilteredPatientList(),
                enlargedPatientInfoCard, infoCardDisplayController);
        patientListPanelPlaceholder.getChildren().add(patientListPanel.getRoot());

        doctorListPanel = new DoctorListPanel(logic.getFilteredDoctorList(),
                enlargedDoctorInfoCard, infoCardDisplayController, patientListPanel);
        doctorListPanelPlaceholder.getChildren().add(doctorListPanel.getRoot());
    }

    /**
     * Updates {@code enlargedPersonInfoCardPlaceholder} to show the
     * enlarged information of either the doctor or patient.
     *
     */
    public void setFeedbackToUser() {
        enlargedPersonInfoCardPlaceholder.getChildren().clear();
        if (infoCardDisplayController.shouldDisplayDoctorInfoCard()) {
            enlargedPersonInfoCardPlaceholder.getChildren().add(enlargedDoctorInfoCard.getRoot());
        } else if (infoCardDisplayController.shouldDisplayPatientInfoCard()) {
            enlargedPersonInfoCardPlaceholder.getChildren().add(enlargedPatientInfoCard.getRoot());
        } else {
            //TODO: LOG HERE! This should not be reached.
        }
    }

}
