package seedu.patientist.ui;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import seedu.patientist.commons.core.LogsCenter;
import seedu.patientist.model.person.Person;
import seedu.patientist.model.person.patient.Patient;
import seedu.patientist.model.person.patient.PatientStatusDetails;
import seedu.patientist.model.person.patient.PatientToDo;

import static java.util.Objects.requireNonNull;


/**
 * The UI component that is responsible for a pop-up to show details of Person.
 */
public class DetailsPopup extends UiPart<Region> {

    private static final Logger logger = LogsCenter.getLogger(DetailsPopup.class);
    private static final String FXML = "DetailsPopup.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on Patientist level 4</a>
     */

    public Person person;

    @FXML
    private HBox detailsPane;
    @FXML
    private Label name;
    @FXML
    private Label idNumber;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private VBox status;
    @FXML
    private VBox todos;
    @FXML
    private FlowPane tags;


    /**
     * Creates a {@code DetailsPopup} with the given {@code personListPanel}.
     */
    public DetailsPopup(Person personToView) {
        super(FXML);
        this.person = personToView;
        if (personToView == null) {
            name.setText("");
            phone.setText("");
            address.setText("");
            email.setText("");
            idNumber.setText("");
            return;
        }
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        String s = person.getIdNumber().toString();
        idNumber.setText(s);
        if (personToView instanceof Patient) {
            Patient patientToView = (Patient) personToView;
            List<PatientStatusDetails> details = patientToView.getPatientStatusDetails();
            for (int i = 1; i < details.size() + 1; i++) {
                status.getChildren().add(new Label(String.format("%d. ", i) + details.get(i - 1).getDetails()));
            }
            List<PatientToDo> todos = patientToView.getPatientToDoList();
            for (int i = 1; i < todos.size() + 1; i++) {
                this.todos.getChildren()
                        .add(new Label(String.format("%d. ", i) + todos.get(i - 1).getTodo()));
            }
        }
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}
