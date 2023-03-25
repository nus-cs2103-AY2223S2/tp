package seedu.patientist.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.patientist.commons.core.LogsCenter;
import seedu.patientist.model.person.Person;
import seedu.patientist.model.person.patient.Patient;


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

    public final Person person;

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
    private Label status;
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
            status.setText("");
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
            status.setText(patientToView.getPatientStatusDetails().getDetails());
        }
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}
