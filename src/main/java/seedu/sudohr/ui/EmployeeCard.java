package seedu.sudohr.ui;

import java.util.Comparator;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.sudohr.model.department.Department;
import seedu.sudohr.model.employee.Employee;

/**
 * An UI component that displays information of a {@code Employee}.
 */
public class EmployeeCard extends UiPart<Region> {

    private static final String FXML = "EmployeeListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on SudoHr level 4</a>
     */

    public final Employee employee;

    @FXML
    private HBox cardPane2;
    @FXML
    private Label name;
    @FXML
    private Label hashId;
    @FXML
    private Label employeeId;
    @FXML
    private Label index;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code EmployeeCard} with the given {@code Employee} and index to display.
     */
    public EmployeeCard(Employee employee, int displayedIndex, ObservableList<Department> departments) {
        super(FXML);
        this.employee = employee;
        index.setText(displayedIndex + ". ");
        name.setText(employee.getName().fullName);
        hashId.setText("#" + employee.getId().value);
        employeeId.setText("Employee ID: " + employee.getId().value);
        phone.setText(employee.getPhone().value);
        address.setText(employee.getAddress().value);
        email.setText(employee.getEmail().value);
        employee.getTags().stream()
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
        if (!(other instanceof EmployeeCard)) {
            return false;
        }

        // state check
        EmployeeCard card = (EmployeeCard) other;
        return index.getText().equals(card.index.getText())
                && employee.equals(card.employee);
    }
}
