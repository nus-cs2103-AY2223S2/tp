package seedu.address.ui;

//import com.sun.javafx.collections.ObservableListWrapper;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;

import java.util.logging.Logger;

public class PersonViewPanel extends UiPart<Region> {
    private static final String FXML = "PersonViewPanel.fxml";

    private final Person person;

    @FXML
    private Label name;

    @FXML
    private Label phone;
    @FXML
    private Label email;

    @FXML
    private Label nric;

    @FXML
    private Label address;

    @FXML
    private ListView<Appointment> appointments;

    @FXML
    private FlowPane medications;

    /**
     * Generates a Person View Panel.
     * @param person Person to generate the panel about.
     */
    public PersonViewPanel(Person person) {
        super(FXML);
        /*if (person == null) {
            this.person = null;
            return;
        }
        this.person = person;
        setPersonalDetails();
        setAppointmentDetails();
         */
        this.person = person;
        String sname = person.getName().toString();
        name.setText(sname);
        phone.setText(person.getPhone().toString());
        email.setText(person.getEmail().toString());
        nric.setText(person.getNric().toString());
        address.setText(person.getAddress().toString());

        //PersonInfoCard personInfoCard = new PersonInfoCard(person);

        /*if (person.getMedications().size() > 0) {
            medications.getChildren().add(new Label(person.getMedicationString()));
        }
    }
    /*
    private void setAppointmentDetails() {
        ObservableList<Appointment> appointmentsObservableList =
                new ObservableListWrapper<>(person.getPatientAppointments());
        appointments.setItems(appointmentsObservableList);
        appointments.setCellFactory(item -> new AppointmentListViewCell());
    }

    class AppointmentListViewCell extends ListCell<Appointment> {
        @Override
        protected void updateItem(Appointment appointment, boolean empty) {
            super.updateItem(appointment, empty);

            if (empty || appointment == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new AppointmentCard(pastAppointment, getIndex() + 1).getRoot());
            }
        }
    }

     */
}
