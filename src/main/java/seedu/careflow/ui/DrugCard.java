package seedu.careflow.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.careflow.model.drug.Drug;

/**
 * An UI component that displays information of a {@code Drug}.
 */
public class DrugCard extends UiPart<Region> {

    private static final String FXML = "DrugListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Drug drug;

    @FXML
    private HBox cardPane;
    @FXML
    private Label tradeName;
    @FXML
    private Label id;

    /**
     * Creates a {@code DrugCode} with the given {@code Drug} and index to display.
     */
    public DrugCard(Drug drug, int displayedIndex) {
        super(FXML);
        this.drug = drug;
        id.setText(displayedIndex + ". ");
        tradeName.setText(this.drug.getTradeName().tradeName);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DrugCard)) {
            return false;
        }

        // state check
        DrugCard card = (DrugCard) other;
        return tradeName.getText().equals(card.tradeName.getText())
                && drug.equals(card.drug);
    }
}

