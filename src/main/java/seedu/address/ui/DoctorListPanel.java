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
import seedu.address.model.person.doctor.Doctor;

/**
 * Panel containing the list of doctors.
 */
public class DoctorListPanel extends UiPart<Region> {
    private static final String FXML = "DoctorListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(DoctorListPanel.class);

    @FXML
    private ListView<Doctor> doctorListView;

    /**
     * Creates a {@code DoctorListPanel} with the given {@code ObservableList}.
     */
    public DoctorListPanel(ObservableList<Doctor> doctorList,
                           ContactDisplay parent) {
        super(FXML);
        doctorListView.setItems(doctorList);
        doctorListView.setCellFactory(listView -> new DoctorListViewCell(parent));
    }


    /**
     * Selects the {@code DoctorListViewCell} of the {@code Doctor} supplied.
     *
     * @param doctor selected doctor.
     */
    public void selectDoctor(Doctor doctor) {
        requireNonNull(doctor);
        doctorListView.getSelectionModel().select(doctor);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Doctor} using a {@code DoctorCard}.
     */
    class DoctorListViewCell extends ListCell<Doctor> {

        private Doctor doctor;

        public DoctorListViewCell(ContactDisplay grandparent) {
            super();
            this.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                if (doctor == null) {
                    logger.warning("DoctorListViewCell is not populated");
                    return;
                }
                grandparent.setFeedbackUponSelectingDoctor(doctor);
            });
        }

        @Override
        protected void updateItem(Doctor doctor, boolean empty) {
            super.updateItem(doctor, empty);
            this.doctor = doctor;

            if (empty || doctor == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DoctorCard(doctor, getIndex() + 1).getRoot());
            }
        }
    }

}
