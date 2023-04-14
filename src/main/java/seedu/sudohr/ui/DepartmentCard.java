package seedu.sudohr.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.sudohr.model.department.Department;


/**
 * An UI component that displays information of a {@code Department}.
 */

public class DepartmentCard extends UiPart<Region> {

    private static final String FXMl = "DepartmentListCard.fxml";

    public final Department department;

    @FXML
    private HBox cardPane;
    @FXML
    private Label number;
    @FXML
    private Label totalEmployees;
    @FXML
    private Label name;
    /**
     * Creates a {@code DepartmentCard} with the given {@code Department} and index to display.
     * @param department The department to be featured on the card
     * @param displayedIndex the relative number of the current card to the view
     */

    public DepartmentCard(Department department, int displayedIndex) {
        super(FXMl);
        this.department = department;
        number.setText(displayedIndex + ". ");
        totalEmployees.setText("Number of employees: " + department.employeeCount());
        name.setText(department.getName().toString());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DepartmentCard)) {
            return false;
        }

        DepartmentCard card = (DepartmentCard) other;
        return number.getText().equals(card.number.getText())
                && department.equals(card.department);
    }

}
