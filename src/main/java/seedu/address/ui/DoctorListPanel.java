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
import seedu.address.model.person.patient.Patient;
import seedu.address.model.person.patient.PatientContainsKeywordsPredicate;
import seedu.address.model.person.patient.PatientFilter;

/**
 * Panel containing the list of doctors.
 */
public class DoctorListPanel extends UiPart<Region> {
    private static final String FXML = "DoctorListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(DoctorListPanel.class);

    @FXML
    private ListView<Doctor> doctorListView;

    private Doctor selectedDoctor;

    /**
     * Creates a {@code DoctorListPanel} with the given {@code ObservableList}.
     */
    public DoctorListPanel(ObservableList<Doctor> doctorList,
                           EnlargedDoctorInfoCard enlargedDoctorInfoCard,
                           EnlargedInfoCardDisplayController infoCardDisplayController,
                           Logic logic) {
        super(FXML);
        doctorListView.setItems(doctorList);
        doctorListView.setCellFactory(listView -> {
            DoctorListViewCell generatedCell = new DoctorListViewCell(logic);
            generatedCell.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                infoCardDisplayController.displayDoctor();
            });
            return generatedCell;
        });
        setSelectedDoctor(doctorList);
        showSelectedDoctorInfo(enlargedDoctorInfoCard);
    }

    /**
     * Returns the {@code Doctor} selected by the user.
     *
     * @return doctor selected by user
     */
    public Doctor getSelectedDoctor() {
        return this.selectedDoctor;
    }

    /**
     * Sets the {@code Doctor} selected by the user.
     *
     * @param doctorList list of {@code Doctor} objects queried by the user
     */
    private void setSelectedDoctor(ObservableList<Doctor> doctorList) {
        selectedDoctor = null;
        if (!doctorList.isEmpty()) {
            selectedDoctor = doctorList.get(0);
        }
    }

    /**
     * Prompts {@code EnlargedDoctorInfoCard} to display the
     * information of the {@code Doctor} selected by the user.
     * This is done by configuring a {@code ChangeListener} to listen to user selection.
     *
     * @param enlargedDoctorInfoCard the UI part displaying the information of {@code Doctor} selected
     */
    private void showSelectedDoctorInfo(
            EnlargedDoctorInfoCard enlargedDoctorInfoCard) {
        ChangeListener<Doctor> changeListener = (observable, oldValue, newValue) -> {
            selectedDoctor = observable.getValue();
            enlargedDoctorInfoCard
                    .updateSelectedDoctorOptional(Optional.ofNullable(selectedDoctor));
        };
        doctorListView.getSelectionModel().selectedItemProperty().addListener(changeListener);
    }

    /**
     * Scrolls the {@code ListView} to display the {@code Doctor}
     * at the specified {@code Index}.
     *
     * @param doctorIndex an Index object representing the Doctor.
     * @throws ArrayIndexOutOfBoundsException if index is invalid.
     */
    public void scrollTo(Index doctorIndex) throws ArrayIndexOutOfBoundsException {
        int index = doctorIndex.getZeroBased();
        if (index >= doctorListView.getItems().size() || index < 0) {
            String errorMessage = "Supplied doctorIndex ("
                    + index
                    + ") must be between 0 and size of listview - 1 ("
                    + (doctorListView.getItems().size() - 1) + ")!";
            throw new ArrayIndexOutOfBoundsException(errorMessage);
        }
        doctorListView.scrollTo(index);
    }

    /**
     * Selects the {@code DoctorListViewCell} of the {@code Doctor} supplied.
     *
     * @param doctor selected doctor.
     * @throws ArrayIndexOutOfBoundsException if index is invalid.
     */
    public void selectDoctor(Optional<Doctor> doctor) {
        if (doctor.isEmpty()) {
            String warnMessage = "Supplied doctor is null!";
            logger.warning(warnMessage);
            return;
        }
        doctorListView.getSelectionModel().select(doctor.get());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Doctor} using a {@code DoctorCard}.
     */
    class DoctorListViewCell extends ListCell<Doctor> {

        private Optional<Doctor> doctor;

        public DoctorListViewCell(Logic logic) {
            super();
            this.doctor = Optional.empty();
            this.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                PatientFilter patientContainsDoctor =
                        new PatientFilter(this.doctor);
                Predicate<Patient> patientsOfDoctorPredicate =
                        new PatientContainsKeywordsPredicate(patientContainsDoctor);
                logic.updateFilteredPatientList(patientsOfDoctorPredicate);
            });
        }

        @Override
        protected void updateItem(Doctor doctor, boolean empty) {
            super.updateItem(doctor, empty);
            this.doctor = Optional.ofNullable(doctor);

            if (empty || doctor == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DoctorCard(doctor, getIndex() + 1).getRoot());
            }
        }
    }

}
