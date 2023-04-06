package seedu.patientist.ui;

import java.util.Comparator;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.patientist.logic.Logic;
import seedu.patientist.model.person.patient.Patient;
import seedu.patientist.model.ward.Ward;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PatientCard extends UiPart<Region> {

    private static final String FXML = "PatientListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on Patientist level 4</a>
     */
    public final Patient patient;

    private final Logic logic;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label idNumber;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label priority;
    @FXML
    private Label ward;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PatientCard(Logic logic, Patient patient, int displayedIndex) {
        super(FXML);

        this.patient = patient;
        this.logic = logic;
        id.setText(displayedIndex + ". ");
        name.setText(patient.getName().fullName);
        phone.setText(patient.getPhone().value);
        address.setText(patient.getAddress().value);
        email.setText(patient.getEmail().value);
        ward.setText(getWard());
        String s = patient.getIdNumber().toString();
        idNumber.setText(s);
        priority.setText(patient.getPriority().tagName);
        textToColor(priority.getText());
        tags.getChildren().add(new Label(patient.getRoleTag().tagName));
        patient.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PatientCard)) {
            return false;
        }

        // state check
        PatientCard card = (PatientCard) other;
        return id.getText().equals(card.id.getText())
               && patient.equals(card.patient);
    }

    private void textToColor(String text) {
        switch(text) {
        case "LOW":
            priority.setStyle("-fx-background-color: #5FA051;"
                              + " -fx-text-fill: white;"
                              + " -fx-padding: 1 3 1 3;"
                              + " -fx-border-radius: 2;"
                              + " -fx-background-radius: 2;"
                              + " -fx-font-size: 13;");
            return;
        case "MEDIUM":
            priority.setStyle("-fx-background-color: #EB9C5C;"
                              + " -fx-text-fill: white;"
                              + " -fx-padding: 1 3 1 3;"
                              + " -fx-border-radius: 2;"
                              + " -fx-background-radius: 2;"
                              + " -fx-font-size: 13;");
            return;
        case "HIGH":
            priority.setStyle("-fx-background-color: #A90505;"
                              + " -fx-text-fill: white;"
                              + " -fx-padding: 1 3 1 3;"
                              + " -fx-border-radius: 2;"
                              + " -fx-background-radius: 2;"
                              + " -fx-font-size: 13;");
            return;
        default:
            return;
        }
    }

    private String getWard() {
        ObservableList<Ward> wards = this.logic.getPatientist().getWardList();
        for (Ward ward: wards) {
            if (ward.containsPatient(patient)) {
                return ward.getWardName();
            }
        }

        return null;
    }
}
