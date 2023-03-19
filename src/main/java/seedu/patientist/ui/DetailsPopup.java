package seedu.patientist.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import seedu.patientist.commons.core.LogsCenter;
import seedu.patientist.model.person.Person;
import seedu.patientist.model.person.patient.Patient;


/**
 * The UI component that is responsible for a pop-up to show details of Person.
 */
public class DetailsPopup extends UiPart<Stage> {

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
    public DetailsPopup(Person personToView, Stage root) {
        super(FXML, root);
        this.person = personToView;
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

    /**
     * Creates a {@code DetailsPopup} with the given {@code personListPanel}.
     */
    public DetailsPopup(Person personToView) {
        this(personToView, new Stage());
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }
}
