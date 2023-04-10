package seedu.careflow.ui;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import seedu.careflow.logic.CareFlowLogic;
import seedu.careflow.model.patient.DrugAllergy;
import seedu.careflow.model.patient.Patient;
import seedu.careflow.model.patient.Phone;

/**
 * Panel containing the list of patients.
 */
public class PatientListPanel extends UiPart<Region> {
    private static final String FXML = "PatientListPanel.fxml";
    @FXML
    private ListView<Patient> patientListView;
    @FXML
    private ScrollPane patientDetailDisplay;
    @FXML
    private VBox patientDetailContainer;

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
    @FXML
    private Label selectedDrugAllergy;
    @FXML
    private Label selectedEmergencyContact;

    @FXML
    private Label phoneField;
    @FXML
    private Label addressField;
    @FXML
    private Label emailField;
    @FXML
    private Label birthDateField;
    @FXML
    private Label genderField;
    @FXML
    private Label icField;
    @FXML
    private Label drugAllergyField;
    @FXML
    private Label emergencyContactField;

    /**
     * Creates a {@code PatientListPanel} with the given {@code ObservableList}.
     */
    public PatientListPanel(ObservableList<Patient> patientList, CareFlowLogic logic) {
        super(FXML);
        // PERSON LIST
        patientListView.setItems(patientList);
        patientListView.setCellFactory(listView -> new PatientListViewCell());

        setClickEventListener();
        setUpdateEventListener(logic);
        setupStyle();
    }

    /**
     * Set up event listener to listen to updates within the ObservableList
     */
    private void setUpdateEventListener(CareFlowLogic logic) {
        logic.getFilteredPatientList().addListener(new ListChangeListener<Patient>() {
            @Override
            public void onChanged(Change<? extends Patient> c) {
                Patient selectedPatient = patientListView.getSelectionModel().getSelectedItem();
                if (selectedPatient != null) {
                    updateDisplay(selectedPatient);
                } else {
                    updateNullDisplay();
                }
            }
        });
    }

    /**
     * Set up event listener to listen to click events within patientListView
     */
    private void setClickEventListener() {
        patientListView.setOnMouseClicked(event -> {
            Patient selectedPatient = patientListView.getSelectionModel().getSelectedItem();
            updateDisplay(selectedPatient);
        });
    }

    /**
     * Update the display inside {@code patientDetailDisplay} in response to changes inside the ObservableList or click
     * @param selectedPatient the patient to be displayed
     */
    public void updateDisplay(Patient selectedPatient) {
        Label[] fields = new Label[]{phoneField, addressField, emailField, birthDateField, genderField, icField,
            drugAllergyField, emergencyContactField};
        Label[] details = new Label[]{selectedName, selectedPhone, selectedAddress, selectedEmail, selectedBirthDate,
            selectedGender, selectedIc, selectedDrugAllergy, selectedEmergencyContact};
        setupStyle();
        setPatientFieldDisplay(fields);
        updateDisplayedPatientDetail(selectedPatient, details);
    }

    /**
     * Update the display inside {@code patientDetailDisplay} in response to view command
     * @param selectedPatient the patient to be displayed
     */
    public void updateViewCommandDisplay(Patient selectedPatient) {
        patientListView.getSelectionModel().select(patientListView.getItems().indexOf(selectedPatient));
        Label[] fields = new Label[]{phoneField, addressField, emailField, birthDateField, genderField, icField,
            drugAllergyField, emergencyContactField};
        Label[] details = new Label[]{selectedName, selectedPhone, selectedEmail, selectedBirthDate, selectedGender,
            selectedIc, selectedDrugAllergy, selectedEmergencyContact};
        setupStyle();
        setPatientFieldDisplay(fields);
        updateDisplayedPatientDetail(selectedPatient, details);
    }

    /**
     * Set patient display to empty
     */
    public void updateNullDisplay() {
        selectedName.setText("Please select a patient");
        selectedPhone.setText("");
        selectedAddress.setText("");
        selectedEmail.setText("");
        selectedBirthDate.setText("");
        selectedGender.setText("");
        selectedIc.setText("");
        selectedDrugAllergy.setText("");
        selectedEmergencyContact.setText("");
        phoneField.setText("");
        addressField.setText("");
        emailField.setText("");
        birthDateField.setText("");
        genderField.setText("");
        icField.setText("");
        drugAllergyField.setText("");
        emergencyContactField.setText("");
    }

    /**
     * Set up the display of relevant Patient fields
     * @param fields fields to be displayed
     */
    private void setPatientFieldDisplay(Label[] fields) {
        phoneField.setText("Tel");
        addressField.setText("Address");
        emailField.setText("Email");
        birthDateField.setText("Date of Birth");
        genderField.setText("Gender");
        icField.setText("IC");
        drugAllergyField.setText("Drug Allergy");
        emergencyContactField.setText("Emergency Contact");
        for (Label field: fields) {
            field.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(4),
                    new Insets(-3, -10, -3, -10))));
            VBox parent = (VBox) field.getParent();
            parent.setMargin(field, new Insets(0, 0, 10, 0));
        }
    }

    /**
     * Update the detail information to be displayed inside {@code patientDetailDisplay}
     * @param selectedPatient the Patient whose information is to be displayed
     * @param details the Labels to hold the information of the selected Patient
     */
    private void updateDisplayedPatientDetail(Patient selectedPatient, Label[] details) {
        selectedName.setText(selectedPatient.getName().fullName);
        selectedName.setPadding(new Insets(0, -10, 0, -10));
        selectedPhone.setText(selectedPatient.getPhone().value);
        selectedAddress.setText(selectedPatient.getAddress().value);
        selectedEmail.setText(selectedPatient.getEmail().value);
        selectedBirthDate.setText(selectedPatient.getBirthDate().value);
        selectedGender.setText(selectedPatient.getGender().value);
        selectedIc.setText(selectedPatient.getIc().value);
        DrugAllergy drugAllergy = selectedPatient.getDrugAllergy();
        selectedDrugAllergy.setText(drugAllergy == null ? "N.A." : drugAllergy.value);
        Phone emergentContact = selectedPatient.getEmergencyContact();
        selectedEmergencyContact.setText(emergentContact == null ? "N.A." : emergentContact.value);
        for (Label detail : details) {
            detail.setWrapText(true);
            detail.setMinWidth(0);
        }
    }

    /**
     * Set up the styling and spacing of {@code patientDetailDisplay} region
     */
    private void setupStyle() {
        patientDetailContainer.setSpacing(10);
        patientDetailContainer.setPadding(new Insets(10, 0, 0, 20));
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
