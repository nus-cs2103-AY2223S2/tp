package seedu.sudohr.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.sudohr.model.leave.Leave;

/**
 * Creates a Ui card to display date information
 */

public class LeaveCard extends UiPart<Region> {

    private static final String FXMl = "LeaveCard.fxml";

    public final Leave leave;

    @FXML
    private HBox cardPane;
    @FXML
    private Label date;
    @FXML
    private Label totalNumber;


    /**
     * Creates a {@code LeaveCard} with the given {@code Leave}
     * @param leave
     */
    public LeaveCard(Leave leave) {
        super(FXMl);
        this.leave = leave;
        date.setText(leave.getDate().toString());
        totalNumber.setText("Employees on leave: " + leave.getEmployees().size());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof LeaveCard)) {
            return false;
        }

        LeaveCard card = (LeaveCard) other;
        return date.getText().equals(card.date.getText())
                && leave.equals(card.leave);
    }

}
