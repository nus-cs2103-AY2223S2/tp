package seedu.address.ui;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Logic;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.doctor.DoctorContainsKeywordsPredicate;
import seedu.address.model.person.doctor.DoctorFilter;
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
    public PatientListPanel(ObservableList<Patient> patientList,
                            EnlargedPatientInfoCard enlargedPatientInfoCard,
                            EnlargedInfoCardDisplayController infoCardDisplayController, Logic logic) {
        super(FXML);
        patientListView.setItems(patientList);
        patientListView.setCellFactory(listView -> {
            PatientListViewCell generatedCell = new PatientListViewCell(logic);
            generatedCell.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                infoCardDisplayController.displayPatient();
            });
            return generatedCell;
        });
        setSelectedPatient(patientList);
        showSelectedPatientInfo(enlargedPatientInfoCard);
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
     * Sets the list of {@code Patient} objects to be shown.
     *
     * @param patientList list of {@code Patient} objects to be shown.
     */
    public void setPatients(ObservableList<Patient> patientList) {
        patientListView.setItems(patientList);
    }

    /**
     * Prompts {@code EnlargedPatientInfoCard} to display the information of the {@code Patient} selected by the user.
     * This is done by configuring a {@code ChangeListener} to listen to user selection.
     *
     * @param enlargedPatientInfoCard the UI part displaying the information of {@code Patient} selected
     */
    private void showSelectedPatientInfo(
            EnlargedPatientInfoCard enlargedPatientInfoCard) {
        ChangeListener<Patient> changeListener = (observable, oldValue, newValue) -> {
            selectedPatient = observable.getValue();
            enlargedPatientInfoCard
                    .updateSelectedPatientOptional(Optional.ofNullable(selectedPatient));
        };
        patientListView.getSelectionModel().selectedItemProperty().addListener(changeListener);
    }

    /**
     * Scrolls the {@code ListView} to display the {@code Patient}
     * at the specified {@code Index}.
     *
     * @param patientIndex an Index object representing the Patient.
     * @throws ArrayIndexOutOfBoundsException if index is invalid.
     */
    public void scrollTo(Index patientIndex) throws ArrayIndexOutOfBoundsException {
        int index = patientIndex.getZeroBased();
        if (index >= patientListView.getItems().size() || index < 0) {
            String errorMessage = "Supplied patientIndex ("
                    + index
                    + ") must be between 0 and size of listview - 1 ("
                    + (patientListView.getItems().size() - 1) + ")!";
            throw new ArrayIndexOutOfBoundsException(errorMessage);
        }
        patientListView.scrollTo(index);
    }

    /**
     * Selects the {@code PatientListViewCell} of the {@code Patient} supplied.
     *
     * @param patient selected patient.
     * @throws ArrayIndexOutOfBoundsException if index is invalid.
     */
    public void selectPatient(Optional<Patient> patient) {
        if (patient.isEmpty()) {
            String warnMessage = "Supplied patient is null!";
            logger.warning(warnMessage);
            return;
        }
        patientListView.getSelectionModel().select(patient.get());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Patient} using a {@code PatientCard}.
     */
    class PatientListViewCell extends ListCell<Patient> {

        private Optional<Patient> patient;

        public PatientListViewCell(Logic logic) {
            super();
            this.patient = Optional.empty();
            this.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                DoctorFilter doctorContainsPatient =
                        new DoctorFilter(this.patient);
                Predicate<Doctor> doctorsOfPatientPredicate =
                        new DoctorContainsKeywordsPredicate(doctorContainsPatient);
                logic.updateFilteredDoctorList(doctorsOfPatientPredicate);
            });
        }

        @Override
        protected void updateItem(Patient patient, boolean empty) {
            super.updateItem(patient, empty);
            this.patient = Optional.ofNullable(patient);

            if (empty || patient == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PatientCard(patient, getIndex() + 1).getRoot());
            }
        }
    }

}
