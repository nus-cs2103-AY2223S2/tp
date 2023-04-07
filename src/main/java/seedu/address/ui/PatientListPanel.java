package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.patient.Patient;

/**
 * Panel containing the list of patients.
 */
public class PatientListPanel extends UiPart<Region> {
    private static final String FXML = "PatientListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PatientListPanel.class);

    @FXML
    private ListView<Patient> patientListView;

    /**
     * Creates a {@code PatientListPanel} with the given {@code ObservableList}.
     */
    public PatientListPanel(ObservableList<Patient> patientList,
                            ContactDisplay parent) {
        super(FXML);
        patientListView.setItems(patientList);
        patientListView.setCellFactory(listView -> new PatientListViewCell(parent));
    }

    /**
     * Selects the {@code PatientListViewCell} of the {@code Patient} supplied.
     *
     * @param patient selected patient.
     */
    public void selectPatient(Patient patient) {
        requireNonNull(patient);
        patientListView.getSelectionModel().select(patient);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Patient} using a {@code PatientCard}.
     */
    class PatientListViewCell extends ListCell<Patient> {

        private Patient patient;

        public PatientListViewCell(ContactDisplay grandparent) {
            super();
            this.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                if (patient == null) {
                    logger.warning("PatientListViewCell is not populated");
                    return;
                }
                grandparent.setFeedbackUponSelectingPatient(patient);
            });
        }

        @Override
        protected void updateItem(Patient patient, boolean empty) {
            super.updateItem(patient, empty);
            this.patient = patient;

            if (empty || patient == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PatientCard(patient, getIndex() + 1).getRoot());
            }
        }
    }

}
