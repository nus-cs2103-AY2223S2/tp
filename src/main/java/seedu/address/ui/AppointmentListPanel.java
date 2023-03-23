package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.ReadOnlyPatientList;
import seedu.address.model.appointment.Appointment;

/**
 * Panel containing the list of appointments.
 */
public class AppointmentListPanel extends UiPart<Region> {
    private static final String FXML = "AppointmentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AppointmentListPanel.class);
    private final ReadOnlyPatientList patientList;
    @FXML
    private ListView<Appointment> appointmentListView;

    /**
     * Creates an {@code AppointmentListPanel} with the given {@code ObservableList}.
     */
    public AppointmentListPanel(ObservableList<Appointment> appointments, ReadOnlyPatientList patientList) {
        super(FXML);
        this.patientList = patientList;
        appointmentListView.setItems(appointments);
        appointmentListView.setCellFactory(listView -> new AppointmentListViewCell());
    }

    class AppointmentListViewCell extends ListCell<Appointment> {
        @Override
        protected void updateItem(Appointment appointment, boolean empty) {
            super.updateItem(appointment, empty);

            if (empty || appointment == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new AppointmentCard(patientList, appointment, getIndex() + 1).getRoot());
            }
        }
    }
}
