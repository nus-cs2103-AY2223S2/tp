package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.fish.Fish;

/**
 * An UI component that displays information of a {@code Fish}.
 */
public class FishCard extends UiPart<Region> {

    private static final String FXML = "FishListCard.fxml";
    private static final String NAME_HEADER = "Name: ";
    private static final String LAST_FED_DATE_HEADER = "Last fed on: ";


    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Fish fish;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label lastFedDate;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code FishCode} with the given {@code Fish} and index to display.
     */
    public FishCard(Fish fish, int displayedIndex) {
        super(FXML);
        this.fish = fish;
        id.setText(displayedIndex + ". ");
        String nameLabelToBeSet = NAME_HEADER + fish.getName().fullName;
        name.setText(nameLabelToBeSet);
        String lastFedDateLabelToBeSet = LAST_FED_DATE_HEADER + fish.getLastFedDate().value;
        lastFedDate.setText(lastFedDateLabelToBeSet);
        address.setText(fish.getAddress().value);
        email.setText(fish.getEmail().value);
        fish.getTags().stream()
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
        if (!(other instanceof FishCard)) {
            return false;
        }

        // state check
        FishCard card = (FishCard) other;
        return id.getText().equals(card.id.getText())
                && fish.equals(card.fish);
    }
}
