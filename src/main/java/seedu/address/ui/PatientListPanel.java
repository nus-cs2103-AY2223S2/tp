package seedu.address.ui;

import java.util.Optional;
import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
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

    private Patient selectedPatient;

    /**
     * Creates a {@code PatientListPanel} with the given {@code ObservableList}.
     */
    public PatientListPanel(ObservableList<Patient> patientList, EnlargedContactCard enlargedContactCard) {
        super(FXML);
        patientListView.setItems(patientList);
        patientListView.setCellFactory(listView -> new PatientListViewCell());
        setSelectedPatient(patientList);
        showSelectedPatientInfo(enlargedContactCard);
    }

    /**
     * Returns the {@code Patient} selected by the user.
     *
     * @return patient selected by user
     */
    public Patient getSelectedPatient() {
        return this.selectedPatient;
    }

    /**
     * Sets the {@code Patient} selected by the user.
     *
     * @param patientList list of {@code Patient} objects queried by the user
     */
    private void setSelectedPatient(ObservableList<Patient> patientList) {
        selectedPatient = null;
        if (!patientList.isEmpty()) {
            selectedPatient = patientList.get(0);
        }
    }

    /**
     * Prompts {@code EnlargedContactCard} to display the information of the {@code Patient} selected by the user.
     * This is done by configuring a {@code ChangeListener} to listen to user selection.
     *
     * @param enlargedContactCard the UI part displaying the information of {@code Patient} selected
     */
    private void showSelectedPatientInfo(EnlargedContactCard enlargedContactCard) {
        ChangeListener<Patient> changeListener = (observable, oldValue, newValue) -> {
            selectedPatient = observable.getValue();
            enlargedContactCard.updateSelectedDoctorOptional(Optional.ofNullable(selectedPatient));
        };
        patientListView.getSelectionModel().selectedItemProperty().addListener(changeListener);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Patient} using a {@code PatientCard}.
     */
    class PatientListViewCell extends ListCell<Patient> {
        @Override
        protected void updateItem(Patient patient, boolean empty) {
            super.updateItem(patient, empty);

            if (empty || patient == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PatientCard(patient, getIndex() + 1).getRoot());
            }
        }
    }

}
