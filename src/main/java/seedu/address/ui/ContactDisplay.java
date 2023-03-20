package seedu.address.ui;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.logic.Logic;

/**
 * The Contact Display displaying the list of doctors,
 * patients and their respective information.
 */
public class ContactDisplay extends UiPart<Region>{

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

    public ContactDisplay(Logic logic) {
        super(FXML);
        this.logic = logic;
        this.infoCardDisplayController = new EnlargedInfoCardDisplayController(this);
        fillInnerParts();
    }

    private void fillInnerParts() {
        enlargedDoctorInfoCard = new EnlargedDoctorInfoCard();
        enlargedPatientInfoCard = new EnlargedPatientInfoCard();
        enlargedPersonInfoCardPlaceholder.getChildren().add(enlargedDoctorInfoCard.getRoot());

        patientListPanel = new PatientListPanel(logic.getFilteredPatientList(),
                enlargedPatientInfoCard, infoCardDisplayController);
        patientListPanelPlaceholder.getChildren().add(patientListPanel.getRoot());

        doctorListPanel = new DoctorListPanel(logic.getFilteredDoctorList(),
                enlargedDoctorInfoCard, infoCardDisplayController);
        doctorListPanelPlaceholder.getChildren().add(doctorListPanel.getRoot());
    }

    public void setFeedbackToUser() {
        enlargedPersonInfoCardPlaceholder.getChildren().clear();
        if (infoCardDisplayController.getDisplayDoctorInfoCard()) {
            enlargedPersonInfoCardPlaceholder.getChildren().add(enlargedDoctorInfoCard.getRoot());
        } else if (infoCardDisplayController.getDisplayPatientInfoCard()) {
            enlargedPersonInfoCardPlaceholder.getChildren().add(enlargedPatientInfoCard.getRoot());
        } else {
            //TODO: LOG HERE! This should not be reached.
        }
    }

}
