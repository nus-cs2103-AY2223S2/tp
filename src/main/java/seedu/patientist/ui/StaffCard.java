package seedu.patientist.ui;

import java.util.Comparator;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.patientist.logic.Logic;
import seedu.patientist.model.person.staff.Staff;
import seedu.patientist.model.ward.Ward;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class StaffCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on Patientist level 4</a>
     */
    public final Staff staff;

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
    private Label ward;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public StaffCard(Logic logic, Staff staff, int displayedIndex) {
        super(FXML);

        this.staff = staff;
        this.logic = logic;
        id.setText(displayedIndex + ". ");
        name.setText(staff.getName().fullName);
        phone.setText(staff.getPhone().value);
        address.setText(staff.getAddress().value);
        email.setText(staff.getEmail().value);
        ward.setText(getWard());
        String s = staff.getIdNumber().toString();
        idNumber.setText(s);
        tags.getChildren().add(new Label(staff.getRoleTag().tagName));
        staff.getTags().stream()
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
        if (!(other instanceof StaffCard)) {
            return false;
        }

        // state check
        StaffCard card = (StaffCard) other;
        return id.getText().equals(card.id.getText())
               && staff.equals(card.staff);
    }

    private String getWard() {
        ObservableList<Ward> wards = this.logic.getPatientist().getWardList();
        for (Ward ward: wards) {
            if (ward.containsStaff(staff)) {
                return ward.getWardName();
            }
        }

        return null;
    }
}
