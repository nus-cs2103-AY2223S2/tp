package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.person.Patient;

public class PatientListPanel extends UiPart<Region> {
    private static final String FXML = "PatientListPanel.fxml";
    @FXML
    private ListView<Patient> patientListView;

    @FXML
    private Label selectedName;
    @FXML
    private Label selectedPhone;
    @FXML
    private Label selectedAddress;
    @FXML
    private Label selectedEmail;
    @FXML
    private Label selectedBirthDate;
    @FXML
    private Label selectedGender;
    @FXML
    private Label selectedIc;

    public PatientListPanel(ObservableList<Patient> patientList) {
        super(FXML);
        // PERSON LIST
        patientListView.setItems(patientList);
        patientListView.setCellFactory(listView -> new PatientListViewCell());
        displayPatientDetail();
    }

    private void displayPatientDetail() {
        patientListView.setOnMouseClicked(event -> {
            Patient selectedPatient = patientListView.getSelectionModel().getSelectedItem();
            System.out.println(selectedPatient.toString());
            selectedName.setText(selectedPatient.getName().fullName);
            selectedPhone.setText("Tel: " + selectedPatient.getPhone().value);
            selectedAddress.setText("Address: " + selectedPatient.getAddress().value);
            selectedEmail.setText("Email: " + selectedPatient.getEmail().value);
            selectedBirthDate.setText("Date of Birth: " + selectedPatient.getBirthDate().value);
            selectedGender.setText("Gender: " + selectedPatient.getGender().value);
            selectedIc.setText("Ic: " + selectedPatient.getIc().value);
        });
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
//    public displaySelectedPatient(Patient selectedPatient) {
//        super(FXML);
//        selectedName.setText(selectedPatient.getName().fullName);
//        selectedPhone.setText(selectedPatient.getPhone().value);
//        selectedAddress.setText(selectedPatient.getAddress().value);
//        selectedEmail.setText(selectedPatient.getEmail().value);
//        selectedBirthDate.setText(selectedPatient.getBirthDate().value);
//        selectedGender.setText(selectedPatient.getGender().value);
//        selectedIc.setText(selectedPatient.getIc().value);
//    }